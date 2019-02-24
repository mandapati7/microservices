package com.testcom.oce.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.testcom.oce.controller.UserNotFoundException;

@ControllerAdvice
@RestController
public class OCEResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exRes = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exRes, HttpStatus.INTERNAL_SERVER_ERROR);
	};

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(UserNotFoundException ex, WebRequest request)
			throws Exception {
		ExceptionResponse exRes = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exRes, HttpStatus.NOT_FOUND);
	};

	/*
	 * @Override protected ResponseEntity<Object>
	 * handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
	 * headers, HttpStatus status, WebRequest request) {
	 * 
	 * ExceptionResponse exRes = new ExceptionResponse(new Date(), ex.getMessage(),
	 * ex.getBindingResult().toString()); return new ResponseEntity<Object>(exRes,
	 * HttpStatus.BAD_REQUEST);
	 * 
	 * }
	 */
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
