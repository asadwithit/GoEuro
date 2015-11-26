package com.goeuro.chain;

import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;
import com.goeuro.commands.InitCommand;
import com.goeuro.commands.ResponseHandlerCommand;
import com.goeuro.commands.StorageCommand;
import com.goeuro.enums.GoEuroContextEnum;
import com.goeuro.helper.ExceptionHandler;

/**
 * @author Asad Mohy-ud-din, email: asadwithit@hotmail.com
*/
public class GoEuroChain extends ChainBase {
	
	private InitCommand initCommand;
	private ResponseHandlerCommand responseHandlerCommand;
	private StorageCommand storageCommand;
	
	
	private GoEuroChain() {
		super();
		initCommand = new InitCommand();
		addCommand(initCommand);
		responseHandlerCommand = new ResponseHandlerCommand();
		addCommand(responseHandlerCommand);
		storageCommand = new StorageCommand();
		addCommand(storageCommand);
	}
	
	@SuppressWarnings("unchecked")
	public static void executeChain(String cityName){
		Command process = GoEuroChainBuilder.build();
		Context ctx = new ContextBase();
		ctx.put(GoEuroContextEnum.CITY_NAME.value(), cityName);
		try {
			boolean isCommandFailed = process.execute(ctx);
			if(isCommandFailed){
				return;
			}
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		}
	}
	
	
	public static class GoEuroChainBuilder{
		
		public static GoEuroChain build(){
			return new GoEuroChain();
		}
		
	}
	
}