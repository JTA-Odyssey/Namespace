package com.jtaodyssey.namespace.notification;

/**
 * this factory is used to create the different types of notifications
 *
 * type:
 *  (1) "add-contact" -> addContactNotification
 *  (2) "lookup-contact" -> lookupContactNotification
 *  (3) "remove-contact" -> removeContactNotification
 *  (4) "update-user" -> updateUserNotification
 */
public class NotificationFactory {
    public JTANotification createNotification(String type, Payload payload) {
        if (type.toLowerCase().equals("add-contact")) {
            return new AddContactNotification(payload);
        }
        else if (type.toLowerCase().equals("lookup-contact")) {
            return new RemoveContactNotification(payload);
        }
        else if (type.toLowerCase().equals("remove-contact")) {
            return new LookupContactNotification(payload);
        }
        else if (type.toLowerCase().equals("update-user")) {
            return new UpdateUserNotification(payload);
        }
        return null;
    }
}
