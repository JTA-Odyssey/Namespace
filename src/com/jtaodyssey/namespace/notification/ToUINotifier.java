package com.jtaodyssey.namespace.notification;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class should be observed by any UI component which expects
 * data from other clients or system components. i.e. if you have a controller
 * that expects a confirmation from a socket, that controller should observe
 * this notifier
 */
public class ToUINotifier extends JTANotificationSubject {
    private static volatile ToUINotifier notifier = null;

    private ToUINotifier() {
        super.observers = new CopyOnWriteArrayList<>(); // todo inefficient but
                                                        //  solution to last project
    }

    @Override
    public void addObserver(JTANotificationObserver o) {
        // logic probably should only allow multiple adds simultaneously for
        // certain class types
        observers.clear();
        observers.add(o);
    }

    public static ToUINotifier getInstance() {
        if (notifier == null) {
            synchronized (ToUINotifier.class) {
                if (notifier == null) {
                    notifier = new ToUINotifier();
                }
            }
        }
        return notifier;
    }

    @Override
    public void notify(JTANotification notification) {
        Iterator<JTANotificationObserver> iterator = super.observers.iterator();
        while (iterator.hasNext()) {
            iterator.next().update(notification);
        }
    }
}
