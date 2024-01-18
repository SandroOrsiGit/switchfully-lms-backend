package com.switchfully.switchfullylmsbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    private ResponseEntity<Object> notAnAdminException(IllegalArgumentException e)  {
        return responseEntityBuilder(e, HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<Object> responseEntityBuilder(Exception e, HttpStatus status) {
        ApiError apiError = new ApiError(status, e.getMessage(), e);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}