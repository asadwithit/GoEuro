package com.goeuro.commands;

import org.apache.commons.chain.Context;
import org.apache.log4j.Logger;
import com.goeuro.enums.GoEuroContextEnum;
import com.goeuro.helper.ConfigReader;
import com.goeuro.helper.ExceptionHandler;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Asad Mohy-ud-din, email: asadwithit@hotmail.com
*/
public class InitCommand extends BaseCommand {
	
	final static Logger logger = Logger.getLogger(InitCommand.class);
	
	@SuppressWarnings("unchecked")
	public boolean execute(Context ctx) throws Exception {
		String CITY_NAME = "";
		ConfigReader configuration = ConfigReader.getInstance();
		String RESOURCE_URI = configuration.getProperty("goeuro.request.uri");
		
	
		try{
		CITY_NAME = (String) ctx.get(GoEuroContextEnum.CITY_NAME.value());
		clearContext(ctx, GoEuroContextEnum.CITY_NAME.value());
		
		Client client = Client.create();
		client.setConnectTimeout(new Integer(configuration.getProperty("goeuro.connection.timeout")));
		client.setReadTimeout(new Integer(configuration.getProperty("goeuro.read.timeout")));
		
		if(logger.isDebugEnabled()){
			logger.debug("Resource URI: " + RESOURCE_URI + CITY_NAME);
		}
		
		WebResource webResource = client.resource(RESOURCE_URI + CITY_NAME);
		
		ClientResponse response = webResource.accept(configuration.getProperty("goeuro.accept.media.type")).get(ClientResponse.class);
		
		if(response.getStatus() != 200){
			throw new RuntimeException("Failed - HTTP error code: " + response.getStatus());
		}
		
		String output = response.getEntity(String.class);
		
		if(logger.isDebugEnabled()){
			logger.debug("Response output data: " + output);
		}
		ctx.put(GoEuroContextEnum.OUTPUT.value(), output);
								
		} catch(UniformInterfaceException e){
			ExceptionHandler.handleException(e);
			return true;
		} catch(ClientHandlerException e){
			ExceptionHandler.handleException(e);
			return true;
		} catch(RuntimeException e){
			ExceptionHandler.handleException(e);
			return true;
		}
		
		return false;
	}
}
