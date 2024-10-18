package com.in.maurya.microservicetwo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostServiceException extends Exception {
	
	 public PostServiceException(String errorMessage) {  
		    super(errorMessage);  
		    }  

}
