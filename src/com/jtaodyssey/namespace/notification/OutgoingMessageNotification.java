package com.jtaodyssey.namespace.notification;

/**
 * This class is used to direct all outgoing message from the UI to a
 * socket
 */
public class OutgoingMessageNotification extends JTANotification {
    private String channel;
    /**
     * @param payload is one of our custom data types used to move
     *                data across our system
     */
    public OutgoingMessageNotification(Payload payload, String channel) {
        super(payload);
        setChannel(channel);
    }

    public String getChannel() {
        return channel;
    }

    private void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String getType() {
        return "Outgoing Message Notification";
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
