package com.jtaodyssey.namespace.services;

/**
 * Provides the behavior of any JTA Authenticator class
 */
public interface JTAAuthenticator {
    boolean authorize(String username, String password);
    // boolean authorize(JTACredentials credential);
}
