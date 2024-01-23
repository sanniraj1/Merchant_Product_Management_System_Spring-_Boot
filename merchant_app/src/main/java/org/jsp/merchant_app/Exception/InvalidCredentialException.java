package org.jsp.merchant_app.Exception;

public class InvalidCredentialException  extends RuntimeException{
	
	@Override
	public String getMessage() {
		return " Invalid Phone or email or id or password";
	}

}
