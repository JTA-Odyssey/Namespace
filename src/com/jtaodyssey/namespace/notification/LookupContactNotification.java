package com.jtaodyssey.namespace.notification;

import com.jtaodyssey.namespace.components.JTARegistration;

public class LookupContactNotification extends JTANotification {
    public LookupContactNotification(Payload payload) { super(payload);}

    @Override
    public void writePayload(Payload payload) {
        if (!(payload instanceof JTARegistration)) {
            throw new IllegalArgumentException("LookupContact message must " +
                    "be of type JTAContact");
        }
        super.writePayload(payload);
    }

    @Override
    public String getType() { return "lookup-contact"; }
}
