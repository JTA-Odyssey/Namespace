package com.jtaodyssey.namespace.database;

import com.jtaodyssey.namespace.components.BasicUser;
import com.jtaodyssey.namespace.components.JTALogin;
import com.jtaodyssey.namespace.components.JTARegistration;
import com.jtaodyssey.namespace.components.JTAUser;

import java.sql.SQLException;

public interface DBManager
{
    String getUsername(int id) throws SQLException;

    int getUniqueID(JTALogin JTALogin) throws Exception;

    BasicUser Login(JTALogin userLogin) throws Exception;

    Boolean Registration(JTARegistration registration) throws Exception;

    Boolean addBasicUser(BasicUser basicUser) throws Exception;
}
