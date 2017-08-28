/*
 * Karin Whiting
 * COP 3330 Object Oriented Programming
 * University of Central Florida
 */
/*
    @version 1.1
    11/4/16 ~ KAW
    1.  Modified main to include a call to the Database UI
    2.  Deleted commented out code, no longer needed

    @version 1.2

*/


package database;

import inputOutput.ConnectionData;
import inputOutput.XmlParser;
import java.sql.Connection;
import userInterface.DatabaseUi;

/**
 *
 * @author kwhiting
 */
public class Database {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // read in the database properties from XML         
        // pass the location of the XML file as an argument
        XmlParser xml = new XmlParser("inputOutput/properties.xml");
        ConnectionData data = xml.getConnectionData();

        // create the connection using the data from the XML file
        PostgreSQLConnect connect = new PostgreSQLConnect(data);
        Connection dbConnect = connect.getConnection();
        
//        DatabaseUi ui = new DatabaseUi(dbConnect);
    }
}
