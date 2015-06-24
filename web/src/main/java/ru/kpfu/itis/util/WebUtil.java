package ru.kpfu.itis.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtil {

    public static void objToResponse(Object o, HttpServletResponse httpResponse, int status, ObjectMapper msgMapper) {
        httpResponse.setStatus(status);
        httpResponse.addHeader("Content-Type", "application/json");
        try {
            String asString = msgMapper.writeValueAsString(o);
            httpResponse.getWriter().print(asString);
        } catch (Exception e) {
            try {
                httpResponse.getWriter().print("{\"msg:\": \"Mapper exception\"}");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
