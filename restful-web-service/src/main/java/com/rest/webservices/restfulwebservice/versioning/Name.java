package com.rest.webservices.restfulwebservice.versioning;

public class Name {
	private String fName;
	private String lName;
	
	
	
	public Name() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Name(String fName, String lName) {
		super();
		this.fName = fName;
		this.lName = lName;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}

}
