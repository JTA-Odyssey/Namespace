package com.jtaodyssey.namespace.notification;

import com.jtaodyssey.namespace.components.JTARegistration;

public class RemoveContactNotification extends JTANotification {
    public RemoveContactNotification(Payload payload) { super(payload);}

    @Override
    public void writePayload(Payload payload) {
        if (!(payload instanceof JTARegistration)) {
            throw new IllegalArgumentException("Remove Contact message must " +
                    "be of type JTAContact");
        }
        super.writePayload(payload);
    }

    @Override
    public String getType() { return "remove-contact"; }
}
