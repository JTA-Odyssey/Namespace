package com.jtaodyssey.namespace.notification;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the interface for concrete subjects who will publish different
 * types of notifications
 */
public abstract class JTANotificationSubject implements JTASubject {
    private List<JTANotificationObserver> observers;

    public JTANotificationSubject() {
        observers = new ArrayList<>();
    }

    public void addObserver(JTANotificationObserver o) {
        observers.add(o);
    }

    public void removeObserver(JTANotificationObserver o) {
        observers.remove(o);
    }

    public abstract void notify(JTANotification notification);
}
