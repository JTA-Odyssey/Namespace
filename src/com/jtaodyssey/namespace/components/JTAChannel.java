package com.jtaodyssey.namespace.components;

import java.io.Serializable;

public interface JTAChannel extends Serializable {
    String getType();
    String getName();
    String getNameIncludePrefix();
}
