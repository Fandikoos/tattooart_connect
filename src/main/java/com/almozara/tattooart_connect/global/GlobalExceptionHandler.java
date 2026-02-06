package com.almozara.tattooart_connect.global;

import com.almozara.tattooart_connect.global.dto.MessageDto;
import com.almozara.tattooart_connect.global.exceptions.DuplicateResourceException;
import com.almozara.tattooart_connect.global.exceptions.ExistingIdException;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.global.exceptions.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MessageDto> badCredentialsException(BadCredentialsException e, WebRequest request) {
        MessageDto apiError = new MessageDto(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED,
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                "INVALID_CREDENTIALS",
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessageDto> accessDeniedException(AccessDeniedException e, WebRequest request) {
        log.warn("Access Denied Exception: {}", e.getMessage());
        MessageDto apiError = new MessageDto(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN,
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                "You do not have permission to access this resource",
                request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
    }

    // Respuesta que llegara cuando se intercepten las excepciones a las peticiones, llegaria algo de este estilo:
    //    {
    //        "timestamp": "2025-10-31T22:10:12.123",
    //            "status": "NOT_FOUND",
    //            "error": "Not Found",
    //            "message": "Studio with id 99 not found",
    //            "path": "/api/studios/99"
    //    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageDto> handleNotFoundException(NotFoundException e, WebRequest request) {
        log.warn("Not found Exception: {}", e.getMessage());
        MessageDto apiError = new MessageDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<MessageDto> handleUserException(UserException e, WebRequest request) {
        log.warn("User Exception: {}", e.getMessage());
        MessageDto apiError = new MessageDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(ExistingIdException.class)
    public ResponseEntity<MessageDto> handleExistingIdException(ExistingIdException e, WebRequest request) {
        log.warn("Existing Id Exception: {}", e.getMessage());
        MessageDto apiError = new MessageDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDto> handleGlobalException(Exception e, WebRequest request) {
        log.error("Unexpected error: {}", e.getMessage(), e);
        MessageDto apiError = new MessageDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "An unexpected error occurred",
                request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<MessageDto> handleDuplicateResourceException(DuplicateResourceException e, WebRequest request) {
        log.error("Duplicate resource: {}", e.getMessage(), e);
        MessageDto apiError = new MessageDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT,
                HttpStatus.CONFLICT.getReasonPhrase(),
                e.getMessage(),
                request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }
}
