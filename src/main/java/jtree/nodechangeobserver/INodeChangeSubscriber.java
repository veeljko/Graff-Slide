package jtree.nodechangeobserver;

import java.util.prefs.NodeChangeEvent;

public interface INodeChangeSubscriber {
    void update(Object notification, NotificationType type);
}
