package com.goeuro.enums;

/**
 * @author Asad Mohy-ud-din, email: asadwithit@hotmail.com
*/
public enum GoEuroEnum {
	ID("_id"), NAME("name"), TYPE("type"), LATITUDE("latitude"), LONGITUDE("longitude");
	
	private String value;
	
	private GoEuroEnum(String value){
		this.value = value;
	}

	public String value(){
		return this.value;
	}
	
}
