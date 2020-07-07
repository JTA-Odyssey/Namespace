package com.jtaodyssey.namespace.notification;

/**
 * UI Components should use this class to transfer data from GUI
 * to other components of the client system such as a database,
 * sockets, etc
 */
public class FromUINotifier extends JTANotificationSubject {
    private static volatile FromUINotifier notifier = null;

    private FromUINotifier() {}

    public static FromUINotifier getInstance() {
        if (notifier == null) {
            synchronized (FromUINotifier.class) {
                if (notifier == null) {
                    notifier = new FromUINotifier();
                }
            }
        }
        return notifier; 
    }

    @Override
    public void notify(JTANotification notification) {

    }
}
