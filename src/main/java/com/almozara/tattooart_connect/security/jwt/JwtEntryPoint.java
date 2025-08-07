package com.almozara.tattooart_connect.security.jwt;

import com.almozara.tattooart_connect.global.dto.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        logger.error("Token not found or invalid");
//        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "unauthorized");
        MessageDto dto = new MessageDto(HttpStatus.UNAUTHORIZED, "Token not found or invalid");
        res.setContentType("application/json");
        res.setStatus(dto.getStatus().value());
        res.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        res.getWriter().flush();
        res.getWriter().close();
    }
}
