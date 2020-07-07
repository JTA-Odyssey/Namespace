package com.jtaodyssey.namespace.notification;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the interface for concrete subjects who will publish different
 * types of notifications
 */
public abstract class JTANotificationSubject implements JTASubject {
    protected List<JTANotificationObserver> observers;

    public void addObserver(JTANotificationObserver o) {
        observers.add(o);
    }

    public void removeObserver(JTANotificationObserver o) {
        observers.removeIf(i -> i == o);
    }

    public abstract void notify(JTANotification notification);
}
