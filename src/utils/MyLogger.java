package utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
	
	private static MyLogger instance = null;
	private static Logger LOGGER;
	private static FileHandler fileHandler;
	
	private MyLogger(){
		try {
			LOGGER = Logger.getLogger( MyLogger.class.getName() );
			fileHandler = new FileHandler("C:\\Users\\Ronnie Nieva\\Documents\\Dydy\\Eclipse EE Projects\\SECURDE_OLS\\logs\\logs.txt", true);
			fileHandler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fileHandler);
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized MyLogger getInstance() {
		if(instance == null) {  
	        instance = new MyLogger ();  
	    }  
	    return instance; 
	}
	
	public void info(String msg) {
		LOGGER.log(Level.INFO, msg);
	}
	
	public void warn(String msg) {
		LOGGER.log(Level.WARNING, msg);
	}
	
	public void severe(String msg) {
		LOGGER.log(Level.SEVERE, msg);
	}

}
