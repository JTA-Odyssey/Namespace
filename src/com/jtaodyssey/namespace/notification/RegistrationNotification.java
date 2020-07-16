package com.jtaodyssey.namespace.notification;

import com.jtaodyssey.namespace.components.JTARegistration;

public class RegistrationNotification extends JTANotification {
    public RegistrationNotification(Payload payload) {
        super(payload);
    }

    @Override
    public void writePayload(Payload payload) {
        if (!(payload instanceof JTARegistration)) {
            throw new IllegalArgumentException("Authentication message must " +
                    "be of type JTARegistration");
        }
        super.writePayload(payload);
    }
    @Override
    public String getType() {
        return "Registration Notification";
    }
}
