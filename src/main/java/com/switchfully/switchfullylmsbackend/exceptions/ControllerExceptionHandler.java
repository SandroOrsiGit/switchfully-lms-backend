package com.switchfully.switchfullylmsbackend.exceptions;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentDoesntExistException.class)
    @ResponseBody
    private ResponseEntity<Object> studentDoesntExist(StudentDoesntExistException e) {
        return responseEntityBuilder(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseBody
    private ResponseEntity<Object> courseNotFoundException(CourseNotFoundException e) {
        return responseEntityBuilder(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotACoachException.class)
    @ResponseBody
    private ResponseEntity<Object> notACoachException(NotACoachException e) {
        return responseEntityBuilder(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotAStudentException.class)
    @ResponseBody
    private ResponseEntity<Object> notAStudentException(NotAStudentException e) {
        return responseEntityBuilder(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotAPartOfThisCourseException.class)
    @ResponseBody
    private ResponseEntity<Object> notAPartOfThisCourseException(NotAPartOfThisCourseException e) {
        return responseEntityBuilder(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseBody
    private ResponseEntity<Object> idNotFoundException(IdNotFoundException e) {
        return responseEntityBuilder(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRoleException.class)
    @ResponseBody
    private ResponseEntity<Object> invalidRoleException(InvalidRoleException e) {
        return responseEntityBuilder(e, HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<Object> responseEntityBuilder(Exception e, HttpStatus status) {
        ApiError apiError = new ApiError(status, e.getMessage(), e);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}