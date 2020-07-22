package com.jtaodyssey.namespace.notification;

import com.jtaodyssey.namespace.components.JTARegistration;

/**
 * This notification is used when the client wants to update any and
 * all user information on the database. This class's payload will accept
 * the BasicRegistration as a way to update
 */
public class UpdateUserNotification extends JTANotification {
    public UpdateUserNotification(Payload payload) { super(payload);}

    @Override
    public void writePayload(Payload payload) {
        if (!(payload instanceof JTARegistration)) {
            throw new IllegalArgumentException("Payload for" +
                    " UpdateUserNotification must be of type JTARegistration");
        }
        super.writePayload(payload);
    }

    @Override
    public String getType() { return "update-user"; }
}
