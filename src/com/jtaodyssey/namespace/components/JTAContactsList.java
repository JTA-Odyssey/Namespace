package com.jtaodyssey.namespace.components;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class will be used to manage the contacts of a user
 */
public class JTAContactsList implements Serializable {
    private Map<String, JTAContact> contacts;

    public JTAContactsList() { this.contacts = new HashMap<>(); }

    public void add(String fn, String ln, String alias,
                    String username, String id, String imgPath) {
        add(new JTAContact(fn, ln, alias, username, id, imgPath));
    }

    // add functions
    public void add(String fn, String ln, String id) { add(fn, ln, "", "", id, ""); }
    public void add(JTAContact contact) { contacts.putIfAbsent(contact.getFirstName(), contact); }

    // remove functions
    public void remove(String firstname) { contacts.remove(firstname); }
    public void remove(JTAContact contact) { contacts.remove(contact.getFirstName(), contact); }

    // lookup functions
    public JTAContact lookup(String firstname){ return contacts.get(firstname); }
}
