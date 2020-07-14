package com.jtaodyssey.namespace.notification;

import com.jtaodyssey.namespace.components.JTAChannel;
import com.jtaodyssey.namespace.components.MessagingChannel;

/**
 * This class is used to direct all outgoing message from the UI to a
 * socket
 */
public class OutgoingMessageNotification extends JTANotification {
    private JTAChannel channel;
    /**
     * @param payload is one of our custom data types used to move
     *                data across our system
     */
    public OutgoingMessageNotification(Payload payload, String channel) {
        super(payload);
        setChannel(channel);
    }

    public OutgoingMessageNotification(Payload payload, MessagingChannel channel) {
        super(payload);
        setChannel(channel);
    }

    public MessagingChannel getChannelInfo() { return (MessagingChannel)channel; }

    public void setChannel(MessagingChannel channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel.getName();
    }

    private void setChannel(String channel) {

        this.channel = new MessagingChannel(channel);
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
