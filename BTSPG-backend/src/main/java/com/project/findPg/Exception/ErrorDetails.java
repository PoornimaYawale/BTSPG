package com.project.findPg.Exception;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class ErrorDetails {
	
	private LocalDate timestamp;

	private String message;
	
	private String details;
}
