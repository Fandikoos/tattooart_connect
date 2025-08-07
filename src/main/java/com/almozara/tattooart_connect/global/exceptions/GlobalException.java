package com.almozara.tattooart_connect.global.exceptions;

import com.almozara.tattooart_connect.global.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.PublicKey;

public class GlobalException {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MessageDto> badCredentialsException(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageDto(HttpStatus.NOT_FOUND, "Bad credentials"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessageDto> accessDeniedException(AccessDeniedException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new MessageDto(HttpStatus.FORBIDDEN, "cannot access this resource"));
    }
}
