package com.jtaodyssey.namespace.notification;

/**
 * Interface that just contains the basic subject functionality that you
 * would find in the observer pattern
 */
public interface JTASubject {
    void addObserver(JTANotificationObserver o);
    void removeObserver(JTANotificationObserver o);
    void notify(JTANotification notification);
}
