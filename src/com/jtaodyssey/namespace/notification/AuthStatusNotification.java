package com.jtaodyssey.namespace.notification;

import com.jtaodyssey.namespace.components.JTAStatus;

public class AuthStatusNotification extends JTANotification {
    @Override
    public void writePayload(Payload payload) {
        if (!(payload instanceof JTAStatus)) {
            throw new IllegalArgumentException("Authentication message must " +
                    "be of type JTAStatus");
        }
        super.writePayload(payload);
    }

    @Override
    public String getType() {
        return "status";
    }
}
