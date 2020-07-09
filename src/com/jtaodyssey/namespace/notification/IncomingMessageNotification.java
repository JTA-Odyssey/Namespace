package com.jtaodyssey.namespace.notification;

/**
 * This notification is used to direct all and any incoming message for a client
 * to the UI Module. This class is should never be created by the UI rather
 * it will only read this message type
 */
public class IncomingMessageNotification extends JTANotification {
    /**
     * @param payload is one of our custom data types used to move
     *                data across our system
     */
    public IncomingMessageNotification(Payload payload) {
        super(payload);
    }

    /**
     * This ctor will init a null notification. Must manually set the payload
     */
    public IncomingMessageNotification() {}

    @Override
    public String getType() {
        return "Incoming Message";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\'Message type\' : \'");
        sb.append(getType());
        sb.append("\', ");
        sb.append(super.toString());
        sb.append("}");
        return sb.toString();
    }
}
