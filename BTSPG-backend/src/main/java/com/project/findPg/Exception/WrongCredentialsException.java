package com.project.findPg.Exception;

import lombok.experimental.SuperBuilder;

public class WrongCredentialsException extends RuntimeException {
	
	public WrongCredentialsException(String message)
	{
		super(message);
	}
	
}