package com.jtaodyssey.namespace.components;

import com.jtaodyssey.namespace.notification.Payload;

import java.time.LocalDateTime;

/**
 * This wrapper will represent all text-messages that will communicate between
 * different users of the system. Please note that timestamp is a Java
 * LocalDateTime
 */
public class JTATextMessage implements Payload {
    private String message;
    private String timestamp;
    private String userID;
    // private transient JTAUser user; // info is stored if contact exists for id

    public JTATextMessage() {
        this(null);
    }

    public JTATextMessage(String message) {
        setMessage(message);
        setUserID(null); //todo tbd how we are going to do this
        setTimestamp(LocalDateTime.now().toString());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    private void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserID() {
        return userID;
    }

    // todo we need to figure out how we want this to be set
    //  inlucde checks that make sure its valid
    void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String getType() {
        return "Text Message";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ message: ");
        sb.append(message);
        sb.append(", userID: ");
        sb.append(userID);
        sb.append(", timestamp: ");
        sb.append(timestamp);
        sb.append(" }");
        return sb.toString();
    }
}
