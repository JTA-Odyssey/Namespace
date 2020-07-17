package com.jtaodyssey.namespace.database;

import com.jtaodyssey.namespace.components.BasicUser;
import com.jtaodyssey.namespace.components.JTALogin;
import com.jtaodyssey.namespace.components.JTARegistration;

import java.nio.file.attribute.BasicFileAttributes;
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
    private int status  = 8;

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
            this.connection = DriverManager.getConnection("jdbc:sqlite:.storage/database.db");

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

        try
        {
            ps = connection.prepareStatement("Select * FROM USER WHERE Username = ? AND Password = ?");
            ps.setString(1, JTALogin.getUsername());
            ps.setString(2, JTALogin.getPassword());

            rs = ps.executeQuery();

            return rs.getInt(this.uniqueID);
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            throw new Exception("User Does not exist");
        }
    }

    @Override
    public BasicUser Login(JTALogin userLogin) throws Exception
    {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            ps = connection.prepareStatement("Select * FROM USER WHERE Username = ? AND Password = ?");
            ps.setString(1, userLogin.getUsername());
            ps.setString(2, userLogin.getPassword());

            rs = ps.executeQuery();

            if (rs.getString(this.status).equals("Inactive"))
            {
                throw new Exception("This user is inactive!");
            }

            BasicUser user = new BasicUser(rs.getString(this.firstName), rs.getString(this.lastName), rs.getString(this.alias), rs.getString(this.username));

            return user;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            throw new Exception("Incorrect Username or Password or user is Inactive!");
        }
    }

    @Override
    public Boolean Registration(JTARegistration registration) throws Exception
    {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            ps = connection.prepareStatement("Select * FROM USER WHERE Username = ?");
            ps.setString(1, registration.getUsername());

            rs = ps.executeQuery();

            if(rs.getString(this.username).equals(registration.getUsername()))
            {
                return false;
            }

            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            throw new Exception("This username already exists!");
        }
    }
}




