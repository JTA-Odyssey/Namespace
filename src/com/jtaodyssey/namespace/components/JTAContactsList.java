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
    private DBManager database;

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
    public void add(JTAContact contact) {
        contacts.putIfAbsent(contact.getFirstName(), contact);
        // also add the contacts to the database here at the same time
    }

    // remove functions
    public void remove(String firstname) {
        contacts.remove(firstname);
        // remove it from the db here
    }

    public void remove(JTAContact contact) {
        contacts.remove(contact.getFirstName(), contact);
        // remove from the db here
    }

    // lookup functions
    public JTAContact lookup(String firstname){ return contacts.get(firstname); }
    public JTAContact lookup(JTAContact contact) { return lookup(contact.getFirstName()); }
}
