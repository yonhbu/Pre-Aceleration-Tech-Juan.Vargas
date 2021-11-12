package co.com.disney.handler.exception;

import java.util.Arrays;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.com.disney.model.dto.error.MessageErrorDTO;
import co.com.disney.model.exception.ResourceNotFoundException;
import co.com.disney.model.exception.UsernameNotFoundException;



@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {
	
	public GlobalExceptionHandler() {
		super();
	}
	
	
	@ExceptionHandler(value = {ResourceNotFoundException.class})
	public final ResponseEntity<Object> clientNotFound (RuntimeException ex, WebRequest request) {      
		MessageErrorDTO responseError = new MessageErrorDTO (
				new Date(),
				HttpStatus.NOT_FOUND.getReasonPhrase(),
				ex.getMessage(), 
				request.getDescription(false),
		        Arrays.asList("Param Not Found"));
  
		        
		return  handleExceptionInternal(ex, responseError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	
	@ExceptionHandler(value = {UsernameNotFoundException.class})
	public final ResponseEntity<Object> usernameNotFound (RuntimeException ex, WebRequest request) {      
		MessageErrorDTO responseError = new MessageErrorDTO (
				new Date(),
				HttpStatus.NOT_FOUND.getReasonPhrase(),
				ex.getMessage(), 
				request.getDescription(false),
		        Arrays.asList("Username Not Found"));
  
		        
		return  handleExceptionInternal(ex, responseError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	

}