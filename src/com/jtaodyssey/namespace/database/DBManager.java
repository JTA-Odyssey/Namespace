package com.jtaodyssey.namespace.database;

import com.jtaodyssey.namespace.components.*;

import java.sql.SQLException;

public interface DBManager
{
    String getUsername(int id) throws SQLException;

    int getUniqueID(JTALogin JTALogin) throws Exception;

    BasicUser Login(JTALogin userLogin) throws Exception;

    Boolean Registration(JTARegistration registration) throws Exception;

//    JTAContactsList addContact(JTAContact contact) throws Exception;
//
//    void removeContact(String username) throws Exception;
}
