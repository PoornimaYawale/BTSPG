package com.project.findPg.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.experimental.SuperBuilder;


public class EmptyInputFieldException extends RuntimeException {
	
	public EmptyInputFieldException()
	{
		super("Input Fields are empty, please look into it.");
	}
	
}