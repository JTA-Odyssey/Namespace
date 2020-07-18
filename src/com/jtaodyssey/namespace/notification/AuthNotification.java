package com.jtaodyssey.namespace.notification;

import com.jtaodyssey.namespace.components.JTALogin;

/**
 * This notification type will be used to send login credentials
 * to the appropriate component
 */
public class AuthNotification extends JTANotification {
    public AuthNotification(Payload payload) {
        super(payload);
    }

    @Override
    public void writePayload(Payload payload) {
        if (!(payload instanceof JTALogin)) {
            throw new IllegalArgumentException("Authentication message must " +
                    "be of type JTALogin");
        }
        super.writePayload(payload);
    }

    @Override
    public String getType() {
        return "Authentication";
    }
}
