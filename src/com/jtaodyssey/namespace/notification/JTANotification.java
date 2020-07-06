package com.jtaodyssey.namespace.notification;

/**
 * This Interface simply wraps intra-system communication data. Meaning
 * Objects which move around the system are passed via this interface
 */
public interface JTANotification {
    String getType();
}
