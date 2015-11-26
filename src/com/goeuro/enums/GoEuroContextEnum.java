package com.goeuro.enums;

/**
 * @author Asad Mohy-ud-din, email: asadwithit@hotmail.com
*/
public enum GoEuroContextEnum {
	CITY_NAME("CITY_NAME"), OUTPUT("OUTPUT"), RESULT_LIST("RESULT_LIST");
	
	private String value;
	
	private GoEuroContextEnum(String value){
		this.value = value;
	}

	public String value(){
		return this.value;
	}
	
}
