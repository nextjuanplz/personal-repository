/*
 * Karin Whiting
 * COP 3330 Object Oriented Programming
 * University of Central Florida
 */
package userInterface;

import java.sql.Connection;
import javax.swing.JPanel;

/**
 *
 * @author kwhiting
 */
public class CreateTableUi extends JPanel
{
    Connection connect;
    
    /**
     *
     * @param dbConnect
     */
    public CreateTableUi(Connection dbConnect)
    {
        connect = dbConnect;
    }
}