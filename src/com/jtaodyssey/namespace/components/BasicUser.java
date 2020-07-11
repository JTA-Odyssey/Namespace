package com.jtaodyssey.namespace.components;

import java.util.UUID;

/**
 * This class represents the basic user of Namespace users and
 * is the predominant concrete class used in the project
 */
public class BasicUser extends JTAUser{
    private static String UUID_REGEX = "/^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/";

    public BasicUser(String fn, String ln) {
        this(fn, ln, "");
    }

    public BasicUser(String fn, String ln, String alias){
        super(fn, ln, alias);
        setId(UUID.randomUUID().toString());
    }

    @Override
    protected void setId(String id) {
        if (!isUUID(id, UUID_REGEX)) {
            throw new IllegalArgumentException(id + " for basic user is not a UUID");
        }
        super.id = id;
    }

    private boolean isUUID(String id, String regex) {
        return id.matches(regex);
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
