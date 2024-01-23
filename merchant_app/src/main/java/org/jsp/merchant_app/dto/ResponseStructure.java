package org.jsp.merchant_app.dto;

import lombok.Data;

@Data
public class ResponseStructure<T> {

	private String message;
	private T data;
	private int statusCode;
}
