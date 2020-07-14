package com.jtaodyssey.namespace.database;

import com.jtaodyssey.namespace.components.JTAUser;

import java.sql.SQLException;

public interface DBManager
{
    String getUsername(int id) throws SQLException;

    int getUniqueID(JTAUser JTAUser) throws Exception;
}
