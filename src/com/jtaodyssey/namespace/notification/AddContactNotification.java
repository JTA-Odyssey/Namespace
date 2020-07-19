package com.jtaodyssey.namespace.notification;

import com.jtaodyssey.namespace.components.JTAContact;

public class AddContactNotification extends JTANotification {
    public AddContactNotification(Payload payload) {
        super(payload);
    }

    @Override
    public void writePayload(Payload payload) {
        if (!(payload instanceof JTAContact)) {
            throw new IllegalArgumentException("Payload for" +
                    " AddContactNotification message must " +
                    "be of type JTAContact");
        }
        super.writePayload(payload);
    }

    @Override
    public String getType() {
        return "add-contact";
    }
}
