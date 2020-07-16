package com.jtaodyssey.namespace.components;

import java.util.Objects;

public class BasicRegistration implements JTARegistration {
    private JTAUser user;
    private JTALogin login;

    public BasicRegistration(String fn, String ln, String username, String password) {
        this(new BasicUser(fn, ln), new JTALogin(username, password));
    }

    public BasicRegistration(JTAUser user, JTALogin login) {
        setUser(user);
        setLogin(login);
    }

    public void setUser(JTAUser user) { this.user = user; }
    public void setLogin(JTALogin login) { this.login = login; }

    @Override
    public String getFirstName() { return user.getFirstName(); }
    @Override
    public String getLastName() { return user.getLastName(); }
    @Override
    public String getUsername() { return login.getUsername(); }
    @Override
    public String getPassword() { return login.getPassword(); }

    @Override
    public int hashCode() { return Objects.hash(login.getUsername(), login.getPassword()); }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (!(obj instanceof BasicRegistration)) {
            return false;
        }
        else {
            return login.getUsername().equals(((BasicRegistration) obj).getUsername());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ firstname: ");
        sb.append(user.getFirstName());
        sb.append(", lastname: ");
        sb.append(user.getLastName());
        sb.append(", username: ");
        sb.append(login.getUsername());
        sb.append(" }");
        return sb.toString();
    }
}
