package com.jtaodyssey.namespace.database;

import com.jtaodyssey.namespace.components.JTAUser;

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

    private int uniqueID       = 1;
    private int username       = 2;
    private int password       = 3;
    private int firstName      = 4;
    private int lastName       = 5;
    private int alias          = 6;
    private int profilePicture = 7;
    private int accountStatus  = 8;

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
    public int getUniqueID(JTAUser JTAUser) throws Exception
    {
        PreparedStatement ps = null;
        ResultSet rs = null;

        int uniqueID;

        try
        {
            ps = connection.prepareStatement("Select * FROM USER WHERE Username = ? AND Password = ? AND FirstName = ? AND LastName = ?");
//            ps.setString(1, JTAUser.getUsername());
//            ps.setString(2, JTAUser.getPassword());
            ps.setString(3, JTAUser.getFirstName());
            ps.setString(4, JTAUser.getLastName());

            rs = ps.executeQuery();

            return rs.getInt(this.uniqueID);
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            throw new Exception("User Does not exist");
        }

    }




}
