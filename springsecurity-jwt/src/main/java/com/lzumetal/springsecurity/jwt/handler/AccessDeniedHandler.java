package com.lzumetal.springsecurity.jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzumetal.springsecurity.jwt.common.ResponseData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author liaosi
 * @date 2022-08-07
 */
@Component
public class AccessDeniedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ResponseData responseData = new ResponseData(403, "无访问权限");
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(responseData));
    }


}
