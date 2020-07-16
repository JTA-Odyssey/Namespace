package com.jtaodyssey.namespace.services;

import com.jtaodyssey.namespace.components.JTALogin;
import com.jtaodyssey.namespace.components.JTAUser;
import com.jtaodyssey.namespace.database.DBManager;
import com.jtaodyssey.namespace.database.SQLDatabase;

/**
 * This class will handle all authentication related actions
 */
public final class AuthenticationService implements JTAAuthenticator {
    private static volatile AuthenticationService auth = null;
    private DBManager manager;

    private AuthenticationService() {
        this.manager = SQLDatabase.getInstance();
    }

    public static AuthenticationService getInstance() {
        if (auth == null) {
            synchronized (AuthenticationService.class) {
                if (auth == null) {
                    auth = new AuthenticationService();
                }
            }
        }
        return auth;
    }

    @Override
    public boolean authorize(String username, String password) {
        return authorize(new JTALogin(username, password));
    }

    @Override
    public boolean authorize(JTALogin login) {
        try {
            JTAUser user = manager.Login(login);
            if (user != null) {
                JTAAppUsers.getInstance().addUser(user);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
