package com.project.findPg.Exception;

import lombok.experimental.SuperBuilder;

public class PaymentNotSuccessfullException extends RuntimeException {
	
	public PaymentNotSuccessfullException()
	{
		super("Payment not successfull!!! Try again.");
	}
	
}