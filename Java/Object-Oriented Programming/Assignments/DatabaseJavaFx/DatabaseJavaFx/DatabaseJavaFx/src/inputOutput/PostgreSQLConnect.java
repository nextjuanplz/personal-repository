/*Juan Manuel Alzate Vanegas
 * COP 3330
 * DatabaseJavaFx
 * 8/1/2017
 */
package inputOutput;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author nextjuanplz
 */

public class PostgreSQLConnect {
    //Constructor
    public PostgreSQLConnect(ConnectionData conData) {
        try {
            //Try to open connection
            System.out.println("data is " + conData.toString());
            //System.out.println("Class name  = "+ conData.getType());
            Class.forName(conData.getType());
            connect = DriverManager.getConnection(
                      conData.toString(),conData.getLogin(),conData.getPassword()
                      );
            //Successful connection
            JOptionPane.showMessageDialog(null,SUCCESSFUL_CONNECTION); 
            System.out.println(SUCCESSFUL_CONNECTION);
        } catch (HeadlessException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            //Logger.getLogger(PostgreSQLConnect.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,FAILED_CONNECTION); 
        }    
    }
    
    //Messages
    private final static String SUCCESSFUL_CONNECTION = "Opened database successfully";
    private final static String FAILED_CONNECTION = "Connection to the database was not successful";
    
    //Fields
    private Connection connect = null;

    //Getter
    public Connection getConnect() {
        return connect;
    }
}
