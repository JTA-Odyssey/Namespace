package com.jtaodyssey.namespace.notification;

/**
 * This notification is used to notify other system components that the user
 * has closed the application
 */
public class ExitNotification extends JTANotification {
    @Override
    public String getType() { return "exit"; }
}
