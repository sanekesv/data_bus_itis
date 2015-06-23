package ru.kpfu.itis.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.service.UserService;

import java.util.Arrays;


@Component("domainAuthProvider")
public class AuthProvider implements AuthenticationProvider {


    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object passwordObj = authentication.getCredentials();
        if (passwordObj == null) {
            passwordObj = "";
        }
        String password = passwordObj.toString();
        String passwordHash = decodePassword(password);
        String login = authentication.getName();
        if (password.isEmpty() || login == null || login.isEmpty()) {
            throw new BadCredentialsException("bad");
        }
        User user = userService.findUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        if (user.getPassword().equals(passwordHash)) {
            String newToken = tokenService.generateNewToken();
            AuthenticationWithToken authToken =
                    new AuthenticationWithToken(
                            user,
                            null,
                            Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
            authToken.setToken(newToken);

            SecurityContextHolder.getContext().setAuthentication(authToken);
            tokenService.store(newToken, authToken);
            return authToken;
        } else {
            throw new BadCredentialsException("Bad user password");
        }
    }

    private String decodePassword(String password) {
        return shaPasswordEncoder.encodePassword(password, "");
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}