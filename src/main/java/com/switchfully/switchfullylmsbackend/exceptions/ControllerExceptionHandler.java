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

	@ExceptionHandler(NotACoachException.class)
	@ResponseBody
	private ResponseEntity<Object> notAnAdminException(NotACoachException e) {
		return responseEntityBuilder(e, HttpStatus.UNAUTHORIZED);
	}

	private ResponseEntity<Object> responseEntityBuilder(Exception e, HttpStatus status) {
		ApiError apiError = new ApiError(status, e.getMessage(), e);
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler(IdNotFoundException.class)
	protected void idNotFoundException(IdNotFoundException ex, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}
	@ExceptionHandler(InvalidRoleException.class)
	protected void invalidRoleException(InvalidRoleException ex, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
	}

}