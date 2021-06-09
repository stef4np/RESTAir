package com.project.restair.exceptions.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.restair.dto.ExceptionDTO;
import com.project.restair.exceptions.CustomAlreadyAssignedException;
import com.project.restair.exceptions.CustomNotFoundException;
import com.project.restair.exceptions.CustomValidationException;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { CustomNotFoundException.class })
    protected ResponseEntity<ExceptionDTO> handleCustomNotFoundException(
      RuntimeException ex, WebRequest request) {
		ExceptionDTO excDTO = new ExceptionDTO(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
		return new ResponseEntity<ExceptionDTO>(excDTO, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(value = { CustomAlreadyAssignedException.class })
    protected ResponseEntity<ExceptionDTO> handleCustomAlreadyAssignedException(
      RuntimeException ex, WebRequest request) {
		ExceptionDTO excDTO = new ExceptionDTO(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(), ex.getMessage());
		return new ResponseEntity<ExceptionDTO>(excDTO, HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(value = { CustomValidationException.class })
    protected ResponseEntity<ExceptionDTO> handleCustomValidationException(
      RuntimeException ex, WebRequest request) {
		ExceptionDTO excDTO = new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
		return new ResponseEntity<ExceptionDTO>(excDTO, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(value = { DataIntegrityViolationException.class, ObjectOptimisticLockingFailureException.class })
	protected ResponseEntity<ExceptionDTO> handleDataIntegrityViolationException(
			RuntimeException ex, WebRequest request) {
		String message;
		if (ex instanceof ObjectOptimisticLockingFailureException) {
			message = "Record was updated by another user, reload and try again.";
		} else {
			message = "Selected Flight is already assigned to another Gate!";
		}
		ExceptionDTO excDTO = new ExceptionDTO(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(), message);
		return new ResponseEntity<ExceptionDTO>(excDTO, HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(value = { AccessDeniedException.class })
	protected ResponseEntity<ExceptionDTO> handleAccessDeniedException(
		      Exception ex, WebRequest request) {
		ExceptionDTO excDTO = new ExceptionDTO(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), 
				"Access Denied, Insufficient Rights!");
		return new ResponseEntity<ExceptionDTO>(excDTO, HttpStatus.FORBIDDEN);
    }

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<ExceptionDTO> handleAnyOtherException(
		      Exception ex, WebRequest request) {
		ExceptionDTO excDTO = new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
				"Oops, something went wrong. Contact System Administrator to resolve the issue.");
		return new ResponseEntity<ExceptionDTO>(excDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ExceptionDTO excDTO = new ExceptionDTO(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), 
				String.format("Oops, there is nothing here: %s %s", ex.getHttpMethod(), ex.getRequestURL()));
		return new ResponseEntity<Object>(excDTO, HttpStatus.NOT_FOUND);
	}
	
}
