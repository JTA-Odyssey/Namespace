package com.jtaodyssey.namespace.components;

import com.jtaodyssey.namespace.notification.Payload;

import java.util.Objects;
import java.util.UUID;

public class BasicRegistration implements JTARegistration, Payload {
    private JTAUser user;
    private JTALogin login;

    public BasicRegistration(String fn, String ln, String username, String password, String id, String alias) {
        this(new BasicUser(fn, ln, alias), new JTALogin(username, password));
        user.setId(id);
    }

    public BasicRegistration(String fn, String ln, String username, String password, String id) {
        this(fn, ln, username, password, id, "");
    }

    public BasicRegistration(String fn, String ln, String username, String password) {
        this(fn, ln, username, password, UUID.randomUUID().toString());
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
    public String getID() { return user.getId(); }
    @Override
    public String getAlias() { return user.getAlias(); }
    @Override
    public String getType() { return "Basic Registration"; }

    @Override
    public int hashCode() { return Objects.hash(user.hashCode()); }

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
        sb.append(", ID: ");
        sb.append(getID());
        sb.append(" }");
        return sb.toString();
    }
}
