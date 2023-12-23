package com.project.findPg.Exception;

import lombok.experimental.SuperBuilder;
import java.lang.RuntimeException;

public class DetailNotFoundException extends RuntimeException {
	
	public DetailNotFoundException(String message,int Id)
	{
		super(message+":" + Id);
	}
	
}