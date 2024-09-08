package com.vvsdcpolicies.policies.utilities;

import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vvsdcpolicies.policies.exception.VvsdcException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	private static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);
	
	private Environment environment;

	public ExceptionControllerAdvice(Environment environment) {
		super();
		this.environment = environment;
	}
	
	@ExceptionHandler(VvsdcException.class)
	public ResponseEntity<ErrorInfo> vvsdcExceptionHandler(VvsdcException exception)
	{
		LOGGER.error(exception.getMessage(),exception);
		
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
		
		return new ResponseEntity<>(errorInfo,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception)
	{
		LOGGER.error(exception.getMessage(),exception);
		
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
		
		return new ResponseEntity<>(errorInfo,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class,ConstraintViolationException.class})
	public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception)
	{
		LOGGER.error(exception.getMessage(),exception);
		ErrorInfo errorInfo = new ErrorInfo();
		
		if(exception instanceof MethodArgumentNotValidException manvException)
		{
			errorInfo.setErrorMessage(manvException.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", ")));
		}
		else
		{
			ConstraintViolationException cvException = (ConstraintViolationException)exception;
			errorInfo.setErrorMessage(cvException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", ")));
			LOGGER.error(cvException.getLocalizedMessage());
		}
		
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorInfo,HttpStatus.BAD_REQUEST);
	}
}
