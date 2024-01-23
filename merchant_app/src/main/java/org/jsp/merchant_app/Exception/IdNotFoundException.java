package org.jsp.merchant_app.Exception;

public class IdNotFoundException extends RuntimeException {
	
	@Override
	public String getMessage() {
		return " Invalid Merchant Id";
	}
	

}
