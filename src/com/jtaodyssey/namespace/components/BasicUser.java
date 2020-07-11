package com.jtaodyssey.namespace.components;

import java.util.UUID;

/**
 * This class represents the basic user of Namespace users and
 * is the predominant concrete class used in the project
 */
public class BasicUser extends JTAUser {
    public BasicUser(String fn, String ln) {
        this(fn, ln, "");
    }

    public BasicUser(String fn, String ln, String alias){
        super(fn, ln, alias);
        setId(UUID.randomUUID().toString());
    }

    @Override
    protected void setId(String id) {
        if (!isUUID(id)) {
            throw new IllegalArgumentException(id + " for basic user is not a UUID");
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
        return super.toString();
    }
}
