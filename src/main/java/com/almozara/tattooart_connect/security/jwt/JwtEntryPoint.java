package com.almozara.tattooart_connect.security.jwt;

import com.almozara.tattooart_connect.global.dto.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException {
        logger.error("Token not found or invalid");
        MessageDto dto = new MessageDto(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED,
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                "Token not found or invalid",
                req.getRequestURI()
        );
        res.setContentType("application/json");
        res.setStatus(dto.getStatus().value());
        res.getWriter().write(MAPPER.writeValueAsString(dto));
        res.getWriter().flush();
        res.getWriter().close();
    }
}
