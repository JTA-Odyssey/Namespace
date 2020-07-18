package com.jtaodyssey.namespace.notification;

import com.jtaodyssey.namespace.components.JTAChannel;
import com.jtaodyssey.namespace.components.MessagingChannel;

/**
 * This notification is used to direct all and any incoming message for a client
 * to the UI Module. This class is should never be created by the UI rather
 * it will only read this message type
 */
public class IncomingMessageNotification extends JTANotification {
    private JTAChannel channel;

    /**
     * @param payload is one of our custom data types used to move
     *                data across our system
     */
    public IncomingMessageNotification(Payload payload, String channel) {
        super(payload);
        setChannel(channel);
    }

    public IncomingMessageNotification(Payload payload, MessagingChannel channel) {
        super(payload);
        setChannel(channel);
    }

    public String getChannel() {
        return channel.getName();
    }

    public MessagingChannel getChannelInfo() {
        return (MessagingChannel)channel;
    }

    private void setChannel(MessagingChannel channel) {
        this.channel = channel;
    }

    private void setChannel(String channel) {
        this.channel = new MessagingChannel(channel);
    }

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
