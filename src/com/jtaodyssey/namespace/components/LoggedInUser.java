package com.jtaodyssey.namespace.components;

import com.jtaodyssey.namespace.services.JTACachedUser;

/**
 * This class holds all of the information about current user the is
 * logged-in. This will pull based on credentials in the system
 */
public class LoggedInUser {
    private JTACachedUser user;
    private static volatile LoggedInUser loggedInUser = null;

    private LoggedInUser() {}

    public static LoggedInUser getInstance() {
        if (loggedInUser == null) {
            synchronized (LoggedInUser.class) {
                if (loggedInUser == null) {
                    loggedInUser = new LoggedInUser();
                }
            }
        }
        return loggedInUser;
    }

    public void setUser(JTACachedUser cached) {
        this.user = cached;
    }
    // have a function that once a user logs-in, it goes to the local DB
    // and pulls all of the necessary credentials
}
