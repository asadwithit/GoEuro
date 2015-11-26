package com.goeuro.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.chain.Context;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import com.goeuro.enums.GoEuroContextEnum;
import com.goeuro.enums.GoEuroEnum;
import com.goeuro.helper.ExceptionHandler;
import com.goeuro.vo.CityDetails;

/**
 * @author Asad Mohy-ud-din, email: asadwithit@hotmail.com
*/
public class ResponseHandlerCommand extends BaseCommand {
	
	final static Logger logger = Logger.getLogger(ResponseHandlerCommand.class);
	
	@SuppressWarnings("unchecked")
	public boolean execute(Context ctx) throws Exception {

		try{
		String responseOutput = (String) ctx.get(GoEuroContextEnum.OUTPUT.value());
		clearContext(ctx, GoEuroContextEnum.OUTPUT.value());
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonParser parser = mapper.getJsonFactory().createJsonParser(responseOutput);
		JsonNode rootNode = parser.readValueAsTree();
		
		ArrayList<CityDetails> resultList = readJsonData(rootNode);
		
		if(resultList.size() > 0){
			for (CityDetails cityDetails : resultList) {
				logger.info(cityDetails.toString());
			}
		} else{
			logger.info("Unable to find city details.");
			return true;
		} 
		ctx.put(GoEuroContextEnum.RESULT_LIST.value(), resultList);
		
		} catch(JsonParseException e){
			ExceptionHandler.handleException(e);
			return true;
		} catch(JsonProcessingException e){
			ExceptionHandler.handleException(e);
			return true;
		}
		
		return false;
	}
	
	public static ArrayList<CityDetails> readJsonData(JsonNode rootNode) {
		ArrayList<CityDetails> cityDetailsList = new ArrayList<CityDetails>();
		CityDetails cityDetails = null;
		if(rootNode.isArray()){
		for(int i=0;i<rootNode.size();i++){
		JsonNode jsonNode = rootNode.get(i);
		cityDetails = new CityDetails();
		Iterator<Map.Entry<String, JsonNode>> ite = jsonNode.getFields();
		while(ite.hasNext()){
			Map.Entry<String, JsonNode> entry = ite.next();
			if(entry.getValue().isObject()) {
				
				JsonNode objectNode = entry.getValue();
				Iterator<Map.Entry<String, JsonNode>> objIte = objectNode.getFields();
				while(objIte.hasNext()){
					Map.Entry<String, JsonNode> objEntry = objIte.next();
					
					if(objEntry.getKey().equalsIgnoreCase(GoEuroEnum.LATITUDE.value())){
						cityDetails.setLatitude(objEntry.getValue().asDouble());
					}
					if(objEntry.getKey().equalsIgnoreCase(GoEuroEnum.LONGITUDE.value())){
						cityDetails.setLongitude(objEntry.getValue().asDouble());
					}
					
					/*
					if(logger.isDebugEnabled()){
						logger.debug("inner object's key:"+entry.getKey()+", inner object's value:"+entry.getValue());
					}
					*/
					
				}
			} else {
				
				if(entry.getKey().equalsIgnoreCase(GoEuroEnum.ID.value())){
					cityDetails.set_id(entry.getValue().asText());
				}
				if(entry.getKey().equalsIgnoreCase(GoEuroEnum.NAME.value())){
					cityDetails.setName(entry.getValue().asText());
				}
				if(entry.getKey().equalsIgnoreCase(GoEuroEnum.TYPE.value())){
					cityDetails.setType(entry.getValue().asText());
				}
				
				/*
				if(logger.isDebugEnabled()){
					logger.debug("key:"+entry.getKey()+", value:"+entry.getValue());
				}
				*/

			}
		}
		cityDetailsList.add(cityDetails);
		}
		} 
		return cityDetailsList;
	}
}
