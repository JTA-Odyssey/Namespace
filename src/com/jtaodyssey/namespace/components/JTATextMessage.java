package com.jtaodyssey.namespace.components;

import com.jtaodyssey.namespace.notification.Payload;

/**
 * This wrapper will represent all text-messages that will communicate between
 * different users of the system
 */
public class JTATextMessage implements Payload {
    private String message;
    private String timestamp;
    private String userID;
    // private transient JTAUser user; // info is stored if contact exists for id
    

    @Override
    public String getType() {
        return "Text Message";
    }
}
