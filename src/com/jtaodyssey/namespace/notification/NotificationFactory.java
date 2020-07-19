package com.jtaodyssey.namespace.notification;

/**
 * this factory is used to create the different types of notifications
 *
 * type:
 *  (1) "add-contact" -> addContactNotification
 *  (2) "lookup-contact" -> lookupContactNotification
 *  (3) "remove-contact" -> removeContactNotification
 */
public class NotificationFactory {
    public JTANotification createNotification(String type, Payload payload) {
        if (type.toLowerCase().equals("add-contact")) {

        }
        else if (type.toLowerCase().equals("lookup-contact")) {

        }
        else if (type.toLowerCase().equals("remove-contact")) {

        }
        return null;
    }
}
