package com.jtaodyssey.namespace.notification;

import javax.management.Notification;

/**
 * Provides the interface for concrete subjects who will publish different
 * types of notifications
 */
public interface JTANotificationSubject {
    void addObserver(JTANotificationObserver o);
    void removeObserver(JTANotificationObserver o);
    void notify(JTANotification notification);
}
