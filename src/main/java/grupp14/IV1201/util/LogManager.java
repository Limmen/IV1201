/*
 * Classname: LogManager
 * Version: 0.1
 * Date: 15-3-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */
package grupp14.IV1201.util;

import java.util.logging.Level;

/**
 * LogManager that handles logging of application exceptions that occur in the business layer.
 * @author Kim Hammar
 */
public class LogManager {
    
    private static final java.util.logging.Logger
            LOGGER = java.util.logging.Logger.getLogger("grupp14.IV1201");
       
    /**
     * Class constructor
     */
    public LogManager(){
        
    }
   
    /**
     * Will logg to the application-server default log
     * @param message to log
     * @param level level to log
     */
    public void log(String message, Level level){
        LOGGER.log(level, message);
    }
}
