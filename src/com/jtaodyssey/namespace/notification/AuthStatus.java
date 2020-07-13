package com.jtaodyssey.namespace.notification;

import java.time.LocalDateTime;

/**
 * This class represents the status as a result of trying to log in
 * Results range from simple success, to invalid username/password,
 * to account not found, to suspended account etc
 */
public class AuthStatus implements JTAStatus {
    private String message;
    private boolean isValidated;
    private String timeStamp;

    public AuthStatus() {
        this("", false);
    }

    public AuthStatus(String message, boolean isValidated) {
        setMessage(message);
        setValidated(isValidated);
        setTimeStamp(LocalDateTime.now().toString());
    }

    private void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }

    @Override
    public String getStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ Status Message: ");
        sb.append(message);
        sb.append(", validated credentials: ");
        sb.append(isValidated);
        sb.append(", time: ");
        sb.append(getTime());
        sb.append(" }");
        return sb.toString();
    }

    @Override
    public String getTime() {
        return timeStamp;
    }

    @Override
    public String toString() { return getStatus(); }
}
