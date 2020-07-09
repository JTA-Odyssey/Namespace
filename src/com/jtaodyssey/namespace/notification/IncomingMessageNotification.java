package com.jtaodyssey.namespace.notification;

public class IncomingMessageNotification extends JTANotification {
    @Override
    public String getType() {
        return "Incoming Message";
    }
}
