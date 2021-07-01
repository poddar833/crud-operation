package com.microservicesdemo.crudoperation.common;



import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.microservicesdemo.crudoperation.bean.ErrorMessage;
import com.microservicesdemo.crudoperation.exceptions.NoRecordFoundException;

@ControllerAdvice
public class Exceptionhandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<ErrorMessage> somethingWentWrong(Exception e){
		String status =  HttpStatus.INTERNAL_SERVER_ERROR.toString();
		ErrorMessage error = new ErrorMessage(new Date(), status, e.getMessage(), "Path not available");
		return new ResponseEntity<ErrorMessage>(error,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NoRecordFoundException.class)
	public final ResponseEntity<ErrorMessage> recordNotFoundException(NoRecordFoundException e){
		String status =  HttpStatus.NOT_FOUND.toString();
		ErrorMessage error = new ErrorMessage(new Date(),status , "Record Not Found", e.getMessage());	
		return new ResponseEntity<ErrorMessage>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	
	
}
