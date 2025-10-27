package error_handler.observer;

import error_handler.ErrorMessage;
import error_handler.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements Publisher{
    private List<Subscriber> subscribers = new ArrayList<>();
    private LoggerFactory loggerFactory;

    public MessageGenerator() {
        initialize();
    }

    private void initialize(){
        loggerFactory = new LoggerFactory();
        addSubscriber(loggerFactory.createLogger("filelogger"));
        addSubscriber(loggerFactory.createLogger("consolelogger"));
    }



    @Override
    public void notifyAll(Object errorMessage) {
        if(!subscribers.isEmpty()){
            for(Subscriber s: subscribers){
                if (errorMessage instanceof ErrorMessage) s.update((ErrorMessage) errorMessage);
            }
        }
    }

    @Override
    public void addSubscriber(Subscriber sub) {
        if (!subscribers.contains(sub)) subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(Subscriber sub) {
        subscribers.remove(sub);
    }

}
