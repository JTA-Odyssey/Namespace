package com.jtaodyssey.namespace.components;

import java.util.Objects;

/**
 * This represents a basic user that can be extended to add additional
 * functionality
 */
public abstract class JTAUser {
    private String fn;
    private String ln;
    private String alias;
    private String id;

    public JTAUser() {
        this("", "", "", null);
    }

    public JTAUser(String fn, String ln) {
        this(fn, ln, "", null);
    }

    public JTAUser(String fn, String ln, String alias) {
        this(fn, ln, alias, null);
    }

    protected JTAUser(String fn, String ln, String alias, String id) {
        setFirstName(fn);
        setLastName(ln);
        setAlias(alias);
        setId(id);
    }

    public String getFn() { return fn; }
    public String getLn() { return ln; }
    public String getAlias() { return alias; }
    public String getId() { return id; }
    public void setAlias(String alias) { this.alias = alias; }

    public void setFirstName(String fn) {
        if (!verifyString(fn, "[a-zA-Z]+")) {
            throw new IllegalArgumentException("Illegal first name");
        }
        this.fn = fn;
    }

    public void setLastName(String ln) {
        if (!verifyString(fn, "[a-zA-Z]+")) {
            throw new IllegalArgumentException("Illegal first name");
        }
        this.ln = ln;
    }

    protected abstract void setId(String id);

    private boolean verifyString(String str, String pattern) {
        return str.matches(pattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fn, ln, id);
    }

    @Override
    public boolean equals(Object obj) {
       if (obj == null) {
           return false;
       }
       else if (!(obj instanceof JTAUser)) {
           return false;
       }
       else {
           return this.id.equals(((JTAUser) obj).id);
       }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ First Name: ");
        sb.append(fn);
        sb.append(", Last Name: ");
        sb.append(ln);
        sb.append(", alias: ");
        sb.append(alias);
        sb.append(", ID: ");
        sb.append(id);
        sb.append("}");
        return sb.toString();
    }
}
