/*
 * Karin Whiting
 * COP 3330 Object Oriented Programming
 * University of Central Florida
 */
package database;

import inputOutput.ConnectionData;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author kwhiting
 */
public class PostgreSQLConnect 
{
    Connection connect = null;
//    ConnectionData data = null;
    
    /**
     *
     * @param data
     */
    public PostgreSQLConnect(ConnectionData data)
    {
        try
        {
System.out.println("data is " + data.toString());
            Class.forName(data.getType());
            connect = DriverManager.getConnection(data.toString(), data.getLogin(), data.getPassword());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        System.out.println("Opened database successfully");

    }
    
    /**
     *
     * @return Connection to database
     */
    public Connection getConnection()
    {
        return connect;
    }
}
