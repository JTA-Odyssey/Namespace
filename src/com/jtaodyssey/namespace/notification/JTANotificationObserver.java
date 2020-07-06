package com.jtaodyssey.namespace.notification;

import javax.management.Notification;

/**
 * Provides the interface for concrete observers who wish to be alerted
 * during certain types of notifications
 */
public interface JTANotificationObserver {
    void update(Notification notification);
}
