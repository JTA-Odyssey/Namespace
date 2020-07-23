package com.jtaodyssey.namespace.database;

import com.jtaodyssey.namespace.components.*;
import com.jtaodyssey.namespace.services.JTACachedUser;

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
    private int status         = 7;
    private int profilePicture = 8;

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
            this.connection = DriverManager.getConnection("jdbc:sqlite:.storage/users.db");

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
            user.setId(rs.getString(this.uniqueID));

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
            ps = connection.prepareStatement("INSERT INTO USER(uniqueID, Username, Password, FirstName, LastName, Alias, Status, Picture) VALUES(?,?,?,?,?,?,?,?)");
            ps.setString(1, registration.getID());
            ps.setString(2, registration.getUsername());
            ps.setString(3, registration.getPassword());
            ps.setString(4, registration.getFirstName());
            ps.setString(5, registration.getLastName());
            ps.setString(6, "");
            ps.setString(7, "Active");
            ps.setString(8, "");

            ps.executeUpdate();
            return true;

        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            throw new Exception("This username already exists!");
        }
    }

    @Override
    public void updateUser(JTARegistration regUser) throws Exception
    {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            ps = connection.prepareStatement("UPDATE USER SET Username = ?, Password = ?, FirstName = ?, LastName = ? WHERE UniqueID = ?");
            ps.setString(1, regUser.getUsername());
            ps.setString(2, regUser.getPassword());
            ps.setString(3, regUser.getFirstName());
            ps.setString(4, regUser.getLastName());
            ps.setString(5, regUser.getID());

            ps.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            throw new Exception("Was not a unique username");
        }
    }


//    @Override
//    public JTAContactsList addContact(JTAContact contact) throws Exception
//    {
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try
//        {
//            ps = connection.prepareStatement("SELECT FROM USER(FirstName, LastName, Alias, Username, UniqueID, Picture) VALUES(?,?,?,?,?,?)");
//            ps.setString(1, contact.getFirstName());
//            ps.setString(2, contact.getLastName());
//            ps.setString(3, contact.getAlias());
//            ps.setString(4, contact.getUsername());
//            ps.setString(5, contact.getID());
//            ps.setString(6, contact.getImgPath());
//
//            rs = ps.executeQuery();
//
//            JTAContactsList list = new JTAContactsList(rs.getString(this.username));
//
//            return list;
//        }
//        catch(Exception e)
//        {
//            System.out.println(e.toString());
//            throw new Exception("Contact does not exist!");
//        }
//    }
//
//    @Override
//    public void removeContact(String username) throws Exception
//    {
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try
//        {
//            ps = connection.prepareStatement("DELETE FROM CONTACT WHERE Username = ?");
//            ps.setString(1, username);
//
//            rs = ps.executeQuery();
//        }
//        catch(Exception e)
//        {
//            System.out.println(e.toString());
//            throw new Exception("Cannot remove contact, because that contact does not exist!");
//        }
//    }
//
    @Override
    public void createTable()
    {
        String url = "jdbc:sqlite:.storage/users.db";

        String sql = "CREATE TABLE IF NOT EXISTS USER (\n"
                + "     UniqueID text PRIMARY KEY,\n"
                + "     Username text NOT NULL,\n"
                + "     Password text NOT NULL,\n"
                + "     FirstName text NOT NULL,\n"
                + "     LastName text NOT NULL,\n"
                + "     Alias text NOT NULL,\n"
                + "     Status text NOT NULL,\n"
                + "     Picture text NULL"
                + ");";

        try
        {
            Connection connection = DriverManager.getConnection(url);
            Statement stmt = connection.createStatement();
            stmt.execute(sql);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}




