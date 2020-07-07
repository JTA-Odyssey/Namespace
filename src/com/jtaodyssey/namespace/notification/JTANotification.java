package com.jtaodyssey.namespace.notification;

/**
 * This is the base for all notification types
 */
public abstract class JTANotification {
    private Payload payload;

    public JTANotification() {
        this(null);
    }

    public JTANotification(Payload payload) {
        writePayload(payload);
    }

    public abstract String getType();
    public void writePayload(Payload payload) { this.payload = payload; }
    public Payload readPayload() { return payload; }

    // implement a toString method once Payload class complete
}
