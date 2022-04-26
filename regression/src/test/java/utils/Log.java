package utils;


import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.LogManager;


public class Log {
    //Initialize Log4j instance
    private static final Logger Log = (Logger) LogManager.getLogger(Log.class);
    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
    }
    //Warn Level Logs
    public static void warn (String message) {

        Log.warn(message);
    }
        //Error Level Logs
        public static void error(String message1) {
            Log.error(message1);
        }
        //Fatal Level Logs
        public static void fatal (String message1) {
            Log.fatal(message1);
        }
        //Debug Level Logs
        public static void debug (String message1) {
            Log.debug(message1);
        }
}
