package gn.jonas.crudapiexercise.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gn.jonas.crudapiexercise.services.exceptions.DatabaseViolationException;
import gn.jonas.crudapiexercise.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceErrorHandling {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> notFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String errorMessage = "Resource not found";
		String message = e.getMessage();

		StandardError error = new StandardError(errorMessage, message, status, request);

		return toResponse(error, status);
	}

	@ExceptionHandler(DatabaseViolationException.class)
	public ResponseEntity<StandardError> databaseViolation(DatabaseViolationException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String errorMessage = "Bad request";
		String message = e.getMessage();

		StandardError error = new StandardError(errorMessage, message, status, request);

		return toResponse(error, status);
	}

	private ResponseEntity<StandardError> toResponse(StandardError error, HttpStatus status) {
		return ResponseEntity.status(status).body(error);
	}

}
