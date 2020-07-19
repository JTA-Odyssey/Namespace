package com.jtaodyssey.namespace.components;

import com.jtaodyssey.namespace.notification.Payload;

public class JTAContact implements Payload {
    @Override
    public String getType() { return "Contact"; }
}
