package error_handler.observer;

import lombok.Getter;

@Getter
public interface Publisher {

    void notifyAll(Object errorMessage);
    void addSubscriber(Subscriber sub);
    void removeSubscriber(Subscriber sub);
}
