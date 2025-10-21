package error_handler;

import error_handler.loggers.ConsoleLogger;
import error_handler.loggers.FileLogger;
import error_handler.loggers.Logger;

public class LoggerFactory {


    public Logger createLogger(String name){
        if (name.equals("filelogger")) return new FileLogger();
        if (name.equals("consolelogger")) return new ConsoleLogger();
        return null;
    }
}
