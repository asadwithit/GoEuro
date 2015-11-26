package com.goeuro.helper;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

/**
 * @author Asad Mohy-ud-din, email: asadwithit@hotmail.com
*/
public class ExceptionHandler {

	final static Logger logger = Logger.getLogger(ExceptionHandler.class);
	
	public static final void handleException(Exception e){
		
		if(e instanceof ArrayIndexOutOfBoundsException){
			logger.error("Error while finding city details or empty city name. ", e);
		} else if(e instanceof UniformInterfaceException || e instanceof ClientHandlerException || e instanceof RuntimeException){
			logger.error("Error while executing InitCommand. ", e);
		} else if(e instanceof JsonParseException || e instanceof JsonProcessingException){
			logger.error("Error while executing ResponseHandlerCommand. ", e);
		} else if(e instanceof IOException){
			logger.error("Error while executing StorageCommand or ConfigReader. " , e);
		} else if(e instanceof Exception){
			logger.error("General error while executing the chain. " , e);
		}
	}
	
}
