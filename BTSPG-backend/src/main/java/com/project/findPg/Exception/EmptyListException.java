package com.project.findPg.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.experimental.SuperBuilder;


public class EmptyListException extends RuntimeException {
	
	public EmptyListException(String message)
	{
		super(message);
	}
	
}