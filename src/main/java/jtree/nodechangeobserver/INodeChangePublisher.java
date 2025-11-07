package jtree.nodechangeobserver;

import error_handler.observer.Subscriber;

public interface INodeChangePublisher {
    void updateAll(Object notification, NotificationType type);
    void addSubscriber(INodeChangeSubscriber sub);
    void removeSubscriber(INodeChangeSubscriber sub);
}
