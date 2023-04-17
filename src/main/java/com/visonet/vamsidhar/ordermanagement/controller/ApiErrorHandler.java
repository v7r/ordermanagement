package com.visonet.vamsidhar.ordermanagement.controller;

import com.visonet.vamsidhar.ordermanagement.model.ApiError;
import com.visonet.vamsidhar.ordermanagement.model.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ValidationException.class)
    public final ResponseEntity<ApiError> handleValidationException(ValidationException ex, WebRequest request) {
        ApiError errorDetails = new ApiError(new Date(),
                ex.getMessage(),
                ex.getMsgKey(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
