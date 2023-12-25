package com.cities.exception.handler;

import com.cities.exception.CityNotFoundException;
import com.cities.exception.CountryNotFoundException;
import com.cities.exception.ExtensionNotAllowedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handle(CountryNotFoundException ex,
                                                   WebRequest request) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponseDto(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        ex.getMessage(),
                        ((ServletWebRequest) request).getRequest().getRequestURI()));
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handle(CityNotFoundException ex,
                                                   WebRequest request) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponseDto(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        ex.getMessage(),
                        ((ServletWebRequest) request).getRequest().getRequestURI()));
    }

    @ExceptionHandler(ExtensionNotAllowedException.class)
    public ResponseEntity<ErrorResponseDto> handle(ExtensionNotAllowedException ex,
                                                   WebRequest request) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponseDto(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        ex.getMessage(),
                        ((ServletWebRequest) request).getRequest().getRequestURI()));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponseDto> handle(MaxUploadSizeExceededException ex,
                                                   WebRequest request) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponseDto(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        "Max allowed file size is " + maxFileSize,
                        ((ServletWebRequest) request).getRequest().getRequestURI()));
    }
}
