package com.jtaodyssey.namespace.components;

import java.util.UUID;

/**
 * This class represents the basic user of Namespace users and
 * is the predominant concrete class used in the project
 */
public class BasicUser extends JTAUser {
    private String username;
    private String imgPath;

    public BasicUser(String fn, String ln) {
        this(fn, ln, "", "");
    }

    public BasicUser(String fn, String ln, String alias){
        this(fn, ln, alias, "");
    }

    public BasicUser(String fn, String ln, String alias, String username) {
        this (fn, ln, alias, username, null);
    }

    public BasicUser(String fn, String ln, String alias, String username, String imgPath) {
        super(fn, ln, alias);
        setUsername(username);
        setImgPath(imgPath);
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getImgPath() { return imgPath; }
    public void setImgPath(String imgPath) { this.imgPath = imgPath; }

    @Override
    public void setId(String id) {
//        if (!isUUID(id)) {
//            throw new IllegalArgumentException("BasicUser ID must be a UUID");
//        }
//        else
        if (super.id != null) {
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
