package ru.kpfu.itis.auth.ep;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import ru.kpfu.itis.auth.response.ErrorResponse;
import ru.kpfu.itis.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler {
    private ObjectMapper msgMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        writeError(response);
    }

    private void writeError(HttpServletResponse response) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Not allowed for user");
        errorResponse.setMsg("not_allowed");
        WebUtil.objToResponse(errorResponse, response, HttpServletResponse.SC_METHOD_NOT_ALLOWED, msgMapper);
    }

    public void setMsgMapper(ObjectMapper msgMapper) {
        this.msgMapper = msgMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        writeError(response);
    }
}