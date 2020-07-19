package com.jtaodyssey.namespace.components;

import com.jtaodyssey.namespace.notification.Payload;

import java.io.Serializable;
import java.util.Objects;

public class JTAContact implements Payload, Serializable {
    private BasicUser user;

    public JTAContact(String fn, String ln, String alias, String username, String id, String imgPath) {
        this(new BasicUser(fn, ln, alias, username, imgPath), id);
    }

    private JTAContact(BasicUser user, String id) {
        this.user = user;
        this.user.setId(id);
    }

    // Getter Methods
    public String getFirstName() { return user.getFirstName(); }
    public String getLastName() { return user.getLastName(); }
    public String getAlias() { return user.getAlias(); }
    public String getUsername() { return user.getUsername(); }
    public String getID() { return user.getId(); }
    public String getImgPath() { return user.getImgPath(); }

    // Setter Methods
    public void setFirstName(String fn) { user.setFirstName(fn);}
    public void setLastName(String ln) { user.setLastName(ln);}
    public void setAlias(String alias) { user.setAlias(alias);}
    public void setUsername(String username) { user.setUsername(username);}
    public void setID(String id) { user.setId(id);}
    public void setImgPath(String imgPath) { user.setImgPath(imgPath);}

    // payload overriden methods
    @Override
    public String getType() { return "Contact"; }

    // Object Overloaded methods

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
