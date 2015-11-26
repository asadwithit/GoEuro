package com.goeuro.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * @author Asad Mohy-ud-din, email: asadwithit@hotmail.com
*/
public class ConfigReader {
	
	private static ConfigReader instance = null;
	private static Properties properties = new Properties();
	final static Logger logger = Logger.getLogger(ConfigReader.class);
		
	private ConfigReader(){
    	
    	InputStream inputStream = null;
    	
    	try {
        
    		String filename = "config.properties";
    		inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(filename);
    		
    		if(inputStream==null){
    	        logger.info("Sorry, unable to find " + filename);
    		    return;
    		}

    		properties.load(inputStream);
    	} catch (IOException ex) {
    		ExceptionHandler.handleException(ex);
        } finally{
        	if(inputStream!=null){
        		try {
				inputStream.close();
			} catch (IOException e) {
				ExceptionHandler.handleException(e);
				return;
			}
        	}
        }
	}
	
	public static ConfigReader getInstance(){
		synchronized(ConfigReader.class){
			if(instance == null){
				instance = new ConfigReader();
			}
		}
		return instance;
	}
	
	public String getProperty(String key){
		return properties.getProperty(key);
	}
	
	/*
	@SuppressWarnings("static-access")
	public static void main( String[] args ){
		ConfigReader configuration = ConfigReader.getInstance();
		System.out.println(configuration.getProperty("goeuro.request.uri"));
 
    }
    */
}
