package com.jtaodyssey.namespace.services;

import com.jtaodyssey.namespace.components.JTALogin;

/**
 * Provides the behavior of any JTA Authenticator class
 */
public interface JTAAuthenticator {
    boolean authorize(String username, String password);
    boolean authorize(JTALogin login);
}
