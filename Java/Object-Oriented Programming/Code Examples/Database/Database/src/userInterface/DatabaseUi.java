/*
 * Karin Whiting
 * COP 3330 Object Oriented Programming
 * University of Central Florida
 */
package userInterface;

import java.awt.BorderLayout;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author kwhiting
 */
public class DatabaseUi 
{
    private JFrame frame;
    private JPanel panel;
    private Connection connect;
    
    /**
     *
     * @param dbConnect
     */
    public DatabaseUi(Connection dbConnect)
    {
        connect = dbConnect;
  
        initComponents();
    }
    
    private void initComponents()
    {
        BorderLayout layout = new BorderLayout();
        
        // Initialize the JFrame
        frame = new JFrame("Database User Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(layout);
        
        // This is where the individual classes are populated
        panel = new JPanel();

        // Add the CRUD panels
        CreateTableUi create = new CreateTableUi(connect);
        ReadTableUi read = new ReadTableUi(connect);
        UpdateTableUi update  = new UpdateTableUi(connect);
        DeleteTableUi delete = new DeleteTableUi(connect);
        
        // Create the JTabbedPane and add tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Create Record", create);
        tabs.add("Read Records", read);
        tabs.add("Update Record", update);
        tabs.add("Delete Record", delete);

        // add tabs to the panel
        //panel.setLayout(layout);
        panel.setSize(400, 400);
        panel.add(tabs);
        
        // add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
