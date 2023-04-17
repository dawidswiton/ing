package com.dawidswitonmaniakowski.ing;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

import static java.util.Objects.isNull;

@ControllerAdvice
@Slf4j
class CustomErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(
            ConstraintViolationException exception,
            ServletWebRequest webRequest
    ) throws IOException {
        HttpServletResponse response = webRequest.getResponse();
        if(!isNull(response)) {
            response.sendError(
                    HttpStatus.BAD_REQUEST.value(),
                    exception.getMessage()
            );
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void httpMessageNotReadableException(
            HttpMessageNotReadableException exception,
            ServletWebRequest webRequest
    ) throws IOException {
        HttpServletResponse response = webRequest.getResponse();
        if(!isNull(response)) {
            response.sendError(
                    HttpStatus.BAD_REQUEST.value(),
                    exception.getMessage()
            );
        }
    }
}
