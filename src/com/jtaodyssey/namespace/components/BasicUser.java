package com.jtaodyssey.namespace.components;

import java.util.UUID;

/**
 * This class represents the basic user of Namespace users and
 * is the predominant concrete class used in the project
 */
public class BasicUser extends JTAUser {
    private String username;

    public BasicUser(String fn, String ln) {
        this(fn, ln, "", "");
    }

    public BasicUser(String fn, String ln, String alias){
        this(fn, ln, alias, "");
    }

    public BasicUser(String fn, String ln, String alias, String username) {
        super(fn, ln, alias);
        setUsername(username);
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @Override
    public void setId(String id) {
        if (!isUUID(id)) {
            throw new IllegalArgumentException("BasicUser ID must be a UUID");
        }
        else if (super.id != null) {
            throw new IllegalArgumentException("ID has already been set");
        }
        super.id = id;
    }

    private boolean isUUID(String id) {
        try{
            UUID uuid = UUID.fromString(id);
        } catch (IllegalArgumentException exception){
            return false;
        }
        return true;
    }

    public String getFullName() { return getFirstName() + " " + getLastName(); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ username: ");
        sb.append(username);
        sb.append(",");
        sb.append(super.toString().substring(1));
        return sb.toString();
    }
}
