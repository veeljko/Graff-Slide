package error_handler.loggers;

import error_handler.ErrorMessage;

public class ConsoleLogger extends Logger {
    public ConsoleLogger(ErrorMessage message) {
        super(message);
    }

    public ConsoleLogger(){

    }

    @Override
    public void log(String message) {
        System.out.println(super.formatErrorMessage());
    }
}
