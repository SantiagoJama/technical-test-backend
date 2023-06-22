package com.technicaltest.test.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * DTO para los mensajes de error
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails implements Serializable {
	
	private Date timestamp;
	private String message;
	private String details;


}
