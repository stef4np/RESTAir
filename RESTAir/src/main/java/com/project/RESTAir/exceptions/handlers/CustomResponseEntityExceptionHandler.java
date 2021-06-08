package com.project.RESTAir.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.RESTAir.dto.ExceptionDTO;
import com.project.RESTAir.exceptions.CustomAlreadyAssignedException;
import com.project.RESTAir.exceptions.CustomNotFoundException;
import com.project.RESTAir.exceptions.CustomValidationException;

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

}
