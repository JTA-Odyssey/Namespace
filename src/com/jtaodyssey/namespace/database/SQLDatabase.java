package com.jtaodyssey.namespace.database;

import com.jtaodyssey.namespace.components.JTALogin;

import java.sql.*;

public class SQLDatabase implements DBManager
{
    // *************************
    // * Database Component(s) *
    // *************************

    private static SQLDatabase instance;
    private Connection connection = null;

    // ***********************
    // * User Table Field(s) *
    // ***********************

    private int tableID        = 1;
    private int uniqueID       = 2;
    private int username       = 3;
    private int password       = 4;
    private int firstName      = 5;
    private int lastName       = 6;
    private int alias          = 7;
    private int profilePicture = 8;
    private int accountStatus  = 9;

    // ***************
    // * Constructor *
    // ***************

    private SQLDatabase()
    {
        this.getConnection();
    }

    // **************
    // * Connection *
    // **************

    private Connection getConnection()
    {
        try
        {
            // This is the connection to the database holding the user table
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:JTAOdyssey.db");

            System.out.println("Connected!");

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        return connection;
    }

    // ****************************
    // * SQL Database Function(s) *
    // ****************************

    public static SQLDatabase getInstance()
    {
        if(SQLDatabase.instance == null)
        {
            SQLDatabase.instance = new SQLDatabase();
        }

        return SQLDatabase.instance;
    }

    @Override
    public String getUsername(int id) throws SQLException
    {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            ps = connection.prepareStatement("SELECT * FROM USER WHERE UniqueID=?");
            ps.setInt(1, id);

            rs = ps.executeQuery();
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        }

        return rs.getString(this.username);
    }

    @Override
    public int getUniqueID(JTALogin JTALogin) throws Exception
    {
        PreparedStatement ps = null;
        ResultSet rs = null;

        int uniqueID;

        try
        {
            ps = connection.prepareStatement("Select * FROM USER WHERE Username = ? AND Password = ?");
            ps.setString(1, JTALogin.getUsername());
            ps.setString(2, JTALogin.getPassword());

            rs = ps.executeQuery();

            return rs.getInt(this.tableID);
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            throw new Exception("User Does not exist");
        }

    }

}
