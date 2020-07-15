package com.jtaodyssey.namespace.services;

import com.jtaodyssey.namespace.components.JTALogin;

/**
 * This class will handle all authentication related actions
 */
public final class AuthenticationService implements JTAAuthenticator {
    private static volatile AuthenticationService auth = null;

    private AuthenticationService() {}

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
        return false;
    }

    @Override
    public boolean authorize(JTALogin login) {
        return false;
    }
}
