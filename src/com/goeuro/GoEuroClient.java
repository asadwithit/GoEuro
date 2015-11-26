package com.goeuro;

import com.goeuro.chain.GoEuroChain;
import com.goeuro.helper.ExceptionHandler;

/**
 * @author Asad Mohy-ud-din, email: asadwithit@hotmail.com
*/
public class GoEuroClient {

	public static void main(String... params){
		try{
		String cityName = params[0];
		GoEuroChain.executeChain(cityName);
		
		} catch(ArrayIndexOutOfBoundsException e){
			ExceptionHandler.handleException(e);
		}
	}
}
