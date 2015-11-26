package com.goeuro.commands;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * @author Asad Mohy-ud-din, email: asadwithit@hotmail.com
*/
public abstract class BaseCommand implements Command {

	public abstract boolean execute(Context ctx) throws Exception;
	
	public final void clearContext(Context ctx, Object key){
		ctx.remove(key);
	}
	
}
