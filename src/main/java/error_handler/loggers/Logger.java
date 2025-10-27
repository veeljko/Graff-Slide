package error_handler.loggers;

import error_handler.ErrorMessage;
import error_handler.observer.Subscriber;

//posto je logger subscriber, vrv update metoda
//setuje message, tkd ovaj drugi konstruktor moze da se
//izbrise ako je tako
public abstract class Logger implements Subscriber{

    public Logger(){

    }

    protected String formatErrorMessage(ErrorMessage message){
        return message.getFormatedMessage();
    }

}
