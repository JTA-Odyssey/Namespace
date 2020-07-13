package com.jtaodyssey.namespace.components;

import com.jtaodyssey.namespace.notification.Payload;

/**
 * This class will be used to represent
 */
public class JTALogin implements Payload {
    private String password;
    private String username;

    public JTALogin(String username, String password) {

    }

    public String getPassword() { return password; }
    public String getUsername() { return username; }

    @Override
    public String getType() {
        return "Login";
    }
}
