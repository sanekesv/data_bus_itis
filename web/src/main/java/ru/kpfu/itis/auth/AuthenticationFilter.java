package ru.kpfu.itis.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;
import ru.kpfu.itis.auth.response.ErrorResponse;
import ru.kpfu.itis.auth.response.TokenResponse;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.util.WebUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    public static final String TOKEN_SESSION_KEY = "token";
    public static final String USER_SESSION_KEY = "user";
    private AuthenticationManager authenticationManager;

    private ObjectMapper msgMapper;

    public AuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper msgMapper) {
        this.authenticationManager = authenticationManager;
        this.msgMapper = msgMapper;
    }

    /**
     * Retrieve first non null object
     *
     * @param items
     * @param <T>
     * @return
     */
    public static <T> T coalesce(T... items) {
        for (T i : items) if (i != null) return i;
        return null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = asHttp(request);
        HttpServletResponse httpResponse = asHttp(response);

        String login = coalesce(httpRequest.getHeader("X-Auth-Username"), httpRequest.getParameter("username"));
        String password = coalesce(httpRequest.getHeader("X-Auth-Password"), httpRequest.getParameter("password"));
        String token = coalesce(httpRequest.getHeader("X-Auth-Token"), httpRequest.getParameter("token"));

        String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);
        try {
            if (postToAuthenticate(httpRequest, resourcePath)) {
                logger.debug("Trying to authenticate user {} by X-Auth-Username method", login);
                try {
                    processUsernamePasswordAuthentication(httpResponse, login, password);
                } catch (Exception e) {
                    notifyBadPasswordException(httpResponse);
                }
                return;
            }

            if (token != null) {
                logger.debug("Trying to authenticate user by X-Auth-Token method. Token: {}", token);
                try {
                    processTokenAuthentication(httpResponse, token);
                } catch (BadCredentialsException e) {
                    notifyBadTokenException(httpResponse);
                    return;
                } catch (CredentialsExpiredException e) {
                    notifyCredentialsExpired(httpResponse);
                    return;
                }
                return;
            }

            logger.debug("AuthenticationFilter is passing request down the filter chain");
            addSessionContextToLogging();
            chain.doFilter(request, response);

        } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
            SecurityContextHolder.clearContext();
            logger.error("Internal authentication service exception", internalAuthenticationServiceException);
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            MDC.remove(TOKEN_SESSION_KEY);
            MDC.remove(USER_SESSION_KEY);
        }
    }

    private void notifyBadPasswordException(HttpServletResponse httpResponse) {
        ErrorResponse error = new ErrorResponse();
        error.setError("auth_error");
        error.setMsg("Bad username/password pair");
        WebUtil.objToResponse(error, httpResponse, HttpServletResponse.SC_UNAUTHORIZED, msgMapper);
    }

    private void notifyCredentialsExpired(HttpServletResponse httpResponse) {
        ErrorResponse error = new ErrorResponse();
        error.setError("expired_token");
        error.setMsg("Expired token");
        WebUtil.objToResponse(error, httpResponse, HttpServletResponse.SC_UNAUTHORIZED, msgMapper);
    }

    private void notifyBadTokenException(HttpServletResponse httpResponse) {
        ErrorResponse error = new ErrorResponse();
        error.setError("invalid_token");
        error.setMsg("No valid token");
        WebUtil.objToResponse(error, httpResponse, HttpServletResponse.SC_UNAUTHORIZED, msgMapper);
    }

    private void addSessionContextToLogging() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String tokenValue = "EMPTY";
        if (authentication != null && !Strings.isNullOrEmpty(authentication.getDetails().toString())) {
            MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("SHA-1");
            tokenValue = encoder.encodePassword(authentication.getDetails().toString(), "not_so_random_salt");
        }
        MDC.put(TOKEN_SESSION_KEY, tokenValue);

        String userValue = "EMPTY";
        if (authentication != null && !Strings.isNullOrEmpty(authentication.getPrincipal().toString())) {
            userValue = authentication.getPrincipal().toString();
        }
        MDC.put(USER_SESSION_KEY, userValue);
    }

    private HttpServletRequest asHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    private HttpServletResponse asHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }

    private boolean postToAuthenticate(HttpServletRequest httpRequest, String resourcePath) {
        return resourcePath.contains("login") && httpRequest.getMethod().equals("POST");
    }

    private void processUsernamePasswordAuthentication(HttpServletResponse httpResponse, String username, String password) {
        Authentication resultOfAuthentication = tryToAuthenticateWithUsernameAndPassword(username, password);
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
        User principal = (User) resultOfAuthentication.getPrincipal();
        TokenResponse tokenResponse = new TokenResponse(resultOfAuthentication.getDetails().toString(), TokenService.TOKEN_LIVE_TIME);
        WebUtil.objToResponse(tokenResponse, httpResponse, HttpServletResponse.SC_OK, msgMapper);
    }

    private Authentication tryToAuthenticateWithUsernameAndPassword(String username, String password) {
        UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(username, password);
        return tryToAuthenticate(requestAuthentication);
    }

    private void processTokenAuthentication(HttpServletResponse httpResponse, String token) {
        Authentication resultOfAuthentication = tryToAuthenticateWithToken(token);
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
        WebUtil.objToResponse(new String("Successfully"), httpResponse, HttpServletResponse.SC_OK, msgMapper);
    }

    private Authentication tryToAuthenticateWithToken(String token) {
        PreAuthenticatedAuthenticationToken requestAuthentication = new PreAuthenticatedAuthenticationToken(token, null);
        Authentication authentication = tryToAuthenticate(requestAuthentication);
        return authentication;
    }

    private Authentication tryToAuthenticate(Authentication requestAuthentication) {
        Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new InternalAuthenticationServiceException("Unable to authenticate Domain User for provided credentials");
        }
        logger.debug("User successfully authenticated");
        return responseAuthentication;
    }


}