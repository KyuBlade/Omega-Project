package net.team.omega.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogHandler
{
 
    private static Logger logger;
    
    static {
        logger = Logger.getLogger("MainLogger");
        logger.setLevel(Level.ALL);        
    }
    
    public static void setLevel(Level level)
    {
        logger.setLevel(level);
    }
    
    private static void addLog(String message)
    {
        // TODO : Maybe add file writer
    }
    
    public static void system(String message)
    {
	addLog(message);
	System.out.println(message);
    }
    
    public static void info(String message)
    {
        addLog(message);
        logger.info(message);
    }
    
    public static void warning(String message)
    {
        addLog(message);
        logger.warning(message);
    }
    
    public static void config(String message)
    {
        addLog(message);
        logger.config(message);
    }
    
    public static void severe(String message)
    {
        addLog(message);
        logger.severe(message);
    }
    
    public static void println(Object message)
    {
	System.out.println(message);
    }
    
}
