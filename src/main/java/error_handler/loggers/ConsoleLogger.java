package error_handler.loggers;

import error_handler.ErrorMessage;

public class ConsoleLogger extends Logger {

    public ConsoleLogger() {
        super();
    }


    @Override
    public void update(ErrorMessage errorMessage) {
        System.out.println(super.formatErrorMessage(errorMessage));
    }
}
