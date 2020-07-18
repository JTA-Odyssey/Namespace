package com.jtaodyssey.namespace.components;

import com.jtaodyssey.namespace.components.JTAStatus;
import com.jtaodyssey.namespace.notification.Payload;

import java.time.LocalDateTime;

/**
 * This class represents the status as a result of trying to log in
 * Results range from simple success, to invalid username/password,
 * to account not found, to suspended account etc
 */
public class AuthStatus implements JTAStatus, Payload {
    private String message;
    private boolean isValidated;
    private String timeStamp;
    private String statusType;

    public AuthStatus() {
        this("", false, "Default");
    }

    public AuthStatus(String message, boolean isValidated, String statusType) {
        setMessage(message);
        setValidated(isValidated);
        setTimeStamp(LocalDateTime.now().toString());
        setStatusType(statusType);
    }

    private void setStatusType(String statusType) {
        // todo add an enum class
        if (!(statusType.toLowerCase().equals("login") || statusType.toLowerCase().equals("registration"))) {
            throw new IllegalArgumentException(statusType + " not of login or registration");
        }
        this.statusType = statusType;
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

    @Override
    public String getType() {
        return "Authorize Status";
    }

    @Override
    public String getStatusType() {
        return statusType;
    }
}
