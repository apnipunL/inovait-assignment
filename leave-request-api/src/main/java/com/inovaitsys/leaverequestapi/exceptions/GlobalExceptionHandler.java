package com.inovaitsys.leaverequestapi.exceptions;

import com.inovaitsys.leaverequestapi.dto.ErrorResponseDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponseDto> handleApplicationException(ApplicationException exception) {
        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception) {
        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("The username or password is incorrect"));
        }

        if (exception instanceof AccountStatusException) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("The account is locked"));
        }

        if (exception instanceof AccessDeniedException) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("You are not authorized to access this resource"));
        }

        if (exception instanceof SignatureException) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("The JWT signature is invalid"));
        }

        if (exception instanceof ExpiredJwtException) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("The JWT token has expired"));
        }

        if (exception instanceof MalformedJwtException) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseDto("Invalid or expired access token"));
        }

        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponseDto("Unknown internal server error"));
    }

}
