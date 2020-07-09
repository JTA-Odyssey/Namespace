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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\'Payload\' : \'");
        sb.append(readPayload().toString());
        sb.append("\'");
        return sb.toString();
    }
}
