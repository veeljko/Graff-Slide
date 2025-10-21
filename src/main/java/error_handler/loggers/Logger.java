package error_handler.loggers;

import error_handler.ErrorMessage;

import java.io.IOException;
//posto je logger subscriber, vrv update metoda
//setuje message, tkd ovaj drugi konstruktor moze da se
//izbrise ako je tako
public abstract class Logger {
    ErrorMessage message;

    public Logger(){

    }

    public Logger(ErrorMessage message) {
        this.message = message;
    }

    protected String formatErrorMessage(){
        return "[" + message.getType() + "][" + message.getDate().getTime() + "] " + message.getContent();
    }

    public abstract void log(String message) throws IOException;
}
