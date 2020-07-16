package com.jtaodyssey.namespace.components;

public class BasicRegistration implements JTARegistration {
    private JTAUser user;
    private JTALogin login;

    public BasicRegistration(String fn, String ln, String username, String password) {
        this(new BasicUser(fn, ln), new JTALogin(username, password));
    }

    public BasicRegistration(JTAUser user, JTALogin login) {
        setUser(user);
        setLogin(login);
    }

    public void setUser(JTAUser user) { this.user = user; }
    public void setLogin(JTALogin login) { this.login = login; }

    @Override
    public String getFirstName() { return user.getFirstName(); }
    @Override
    public String getLastName() { return user.getLastName(); }
    @Override
    public String getUsername() { return login.getUsername(); }
    @Override
    public String getPassword() { return login.getPassword(); }
}
