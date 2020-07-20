package com.jtaodyssey.namespace.components;

import com.jtaodyssey.namespace.database.DBManager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class will be used to manage the contacts of a user
 */
public class JTAContactsList implements Serializable {
    private Map<String, JTAContact> contacts;

    public JTAContactsList(String username) {
        this.contacts = new HashMap<>();
        // load up all the contacts in this step as well
    }

    public void add(String fn, String ln, String alias,
                    String username, String id, String imgPath) {
        add(new JTAContact(fn, ln, alias, username, id, imgPath));
    }

    // add functions
    public void add(String fn, String ln, String id) { add(fn, ln, "", "", id, ""); }
    public void add(String username, String id) {
        add("","", "", username, id, "");
    }

    public void add(JTAContact contact) {
        contacts.putIfAbsent(contact.getUsername(), contact);
        if (contact.getID() != null) {
            contacts.putIfAbsent(contact.getID(), contact);
        }
    }

    // remove functions
    public void remove(String username) {
        contacts.remove(username);
        // remove it from the db here
    }

    // lookup functions
    public JTAContact lookup(String username){ return contacts.get(username); }
    public JTAContact lookup(JTAContact contact) { return lookup(contact.getUsername()); }
    public JTAContact lookupByID(String id) { return contacts.get(id); }
}
