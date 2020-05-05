package com.rest.webservices.restfulwebservice.helloworld;

public class HelloWorldBean {

	

	private String message;

	public HelloWorldBean(String message) {
		
		// TODO Auto-generated constructor stub
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message1) {
		this.message = message1;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

}
