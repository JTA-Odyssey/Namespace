package com.jtaodyssey.namespace.components;

import com.jtaodyssey.namespace.notification.Payload;

/**
 * This class will be used to represent
 */
public class JTALogin implements Payload {
    private String password;
    private String username;

    public JTALogin(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public JTALogin(JTAUser user, String password) {
        setUsername(((BasicUser)user).getUsername());
        setPassword(password);
    }

    private void setPassword(String password) { this.password = password; }
    private void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public String getUsername() { return username; }

    @Override
    public String getType() {
        return "Login";
    }

    @Override
    public String toString() {
        return "{ username : " + username + " }";
    }
}
