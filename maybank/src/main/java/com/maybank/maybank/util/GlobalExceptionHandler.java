package com.maybank.maybank.util;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.maybank.maybank.beans.ErrorDetails;

/**
 * 
 * @author Austin Teck
 * This handles both specific and global exceptions in a single class
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * 
	 * @param ex 404 exception 
	 * @param request interceptor to access general request metadata
	 * @return response entity body and error status code
	 */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
         return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
	 * 
	 * @param ex global exception 
	 * @param request interceptor to access general request metadata
	 * @return response entity body and error status code
	 */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExcpetionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
