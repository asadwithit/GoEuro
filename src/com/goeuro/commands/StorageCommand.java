package com.goeuro.commands;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.chain.Context;
import org.apache.log4j.Logger;
import com.goeuro.enums.GoEuroContextEnum;
import com.goeuro.helper.ConfigReader;
import com.goeuro.helper.ExceptionHandler;
import com.goeuro.vo.CityDetails;
import com.opencsv.CSVWriter;

/**
 * @author Asad Mohy-ud-din, email: asadwithit@hotmail.com
*/
public class StorageCommand extends BaseCommand {
	
	final static Logger logger = Logger.getLogger(StorageCommand.class);
	
	@SuppressWarnings("unchecked")
	public boolean execute(Context ctx) throws Exception {
		try{
			ConfigReader configuration = ConfigReader.getInstance();
			String OUTPUT_FILE_NAME = configuration.getProperty("goeuro.output.filename");
			
		ArrayList<CityDetails> resultList = (ArrayList<CityDetails>) ctx.get(GoEuroContextEnum.RESULT_LIST.value());
		clearContext(ctx, GoEuroContextEnum.RESULT_LIST.value());

		storeToCSVFile(OUTPUT_FILE_NAME, resultList);
				
		} catch(IOException e){
			ExceptionHandler.handleException(e);
			return true;
		}
		
		return false;
	}
	
	public static void storeToCSVFile(String outputFileName, ArrayList<CityDetails> cityDetailsList) throws IOException{
		CSVWriter writer = new CSVWriter(new FileWriter(outputFileName));
		
		ArrayList<String[]> records = new ArrayList<String[]>();
		for (CityDetails cityDetails : cityDetailsList) {
			
			records.add(new String[] {
					cityDetails.get_id(), 
					cityDetails.getName(), 
					cityDetails.getType(), 
					String.valueOf(cityDetails.getLatitude()), 
					String.valueOf(cityDetails.getLongitude())
					});
		}
		
		logger.info("Successfully stored city details to CSV file: " + outputFileName);
		
		writer.writeAll(records); 
		writer.close();
	}
}
