package com.demo.exceptionhandler;

import javax.persistence.OptimisticLockException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.dto.ErrorResponse;
import com.demo.exception.CustomException;
import com.demo.exception.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ResourceNotFoundException.class, CustomException.class, EmptyResultDataAccessException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleNotFoundException(Exception exception) {
		ErrorResponse response = new ErrorResponse();
		response.setStatusCode(404);
		response.setStatus("ERROR");
		response.setMessage(exception.getMessage());
		return response;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

	public ErrorResponse handleAllExceptions(Exception exception) {

		ErrorResponse response = new ErrorResponse();

		response.setStatusCode(500);
		response.setStatus("ERROR");
		response.setMessage(exception.getMessage());

		return response;
	}
	
	@ExceptionHandler({OptimisticLockException.class})
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ErrorResponse handleConcurrentException(Exception exception) {
		ErrorResponse response = new ErrorResponse();
		response.setStatusCode(409);
		response.setStatus("ERROR");
		response.setMessage(exception.getMessage());
		return response;
	}

}
