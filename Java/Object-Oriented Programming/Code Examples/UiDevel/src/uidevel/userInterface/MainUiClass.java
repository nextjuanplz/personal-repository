/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*package userInterface;

import airportmain.Reservation;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author kwhiting
 */
/*public class AirlineReservation {
    private JFrame frame;
    private JMenuBar menuBar;
    private JPanel mainPane;

    private JMenu fileMenu;
    private JMenuItem addAirport;
    private JMenuItem addAirline;
    private JMenuItem addFlight;
    private JMenuItem exit;
            
    private JMenu bookMenu;
    private JMenuItem bookFlight;
    
    private JMenu aboutMenu;
    private JMenuItem aboutInfo;

    ArrayList<Reservation> reservations = new ArrayList<Reservation>();
    
    public AirlineReservation()
    {
        initComponents();
    }
    

    // Private inner class for event handling
    private class FileMenuAction implements ActionListener
    {
        // Process the object events
        public void actionPerformed(ActionEvent e)
        {
            // Remove any previous content
            mainPane.removeAll();

            // gets the text of the JMenuItem
            if (e.getActionCommand() == "Exit")
            {
                System.exit(0);
            }
            else if (e.getActionCommand() == "Add Airport")
            {              
                AddAirport addAirport = new AddAirport(mainPane);
                //frame.add(addAirportPane);
                frame.getContentPane().add(mainPane, BorderLayout.CENTER);
                frame.validate();
                frame.repaint();
                frame.setVisible(true);
            }
            else if (e.getActionCommand() == "Add Airline")
            {
                AddAirline addAirline = new AddAirline(mainPane);
                frame.getContentPane().add(mainPane, BorderLayout.CENTER);
                frame.validate();
                frame.repaint();
                frame.setVisible(true);
            }
            else if (e.getActionCommand() == "Add Flight")
            {
                AddFlight addFlight = new AddFlight(mainPane);
                frame.getContentPane().add(mainPane, BorderLayout.CENTER);
                frame.validate();;
                frame.setVisible(true);
            }
        }    
    }        

    private class BookMenuAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // Remove any previous content
            mainPane.removeAll();
            
            if (e.getActionCommand().equalsIgnoreCase("Flight Reservations"))
            {
                // Set the layout manager
                BorderLayout layout = new BorderLayout();
                // Create the main panel for the JTabbedPane to be added to
                mainPane.setLayout(layout);
                // Create the JTabbedPane
                JTabbedPane tabs = new JTabbedPane();
                // Create the data container to pass to each class and add to the
                // Reservations ArrayList
                Reservation reservation = new Reservation();
                reservations.add(reservation);
                
                // Create the tabs
                SearchFlight search = new SearchFlight(tabs, reservation);
                tabs.add("Search Flight", search);
                tabs.setEnabledAt(0, true);
                
                final SelectFlight select = new SelectFlight(tabs, reservation);
                tabs.add("Select Flight", select);
                tabs.setEnabledAt(1, false);
                
                final FlightPrice price = new FlightPrice(tabs, reservation);
                tabs.add("Flight Price", price);
                tabs.setEnabledAt(2, false);
                
                BookTicket book = new BookTicket(tabs, reservation);
                tabs.add("Book Ticket", book);
                tabs.setEnabledAt(3, false);
                
                final Payment pay = new Payment(tabs, reservation);
                tabs.add("Payment", pay);
                tabs.setEnabledAt(4, false);
                
                final BookingSummary summary = new BookingSummary(tabs, reservation);
                tabs.add("Booking Summary", summary);
                tabs.setEnabledAt(5, false);
                              
                tabs.addChangeListener(new ChangeListener() {
                    // This method is called whenever the selected tab changes
                    public void stateChanged(ChangeEvent evt) {
                        JTabbedPane pane = (JTabbedPane)evt.getSource();
    
                        // Get current tab
                        if(pane.getSelectedIndex() == 1)
                        {
                            System.out.println("Select Flight is active");
                            select.populateDataArrays();      
                        }
                        else if(pane.getSelectedIndex() == 2)
                        {
                            System.out.println("Flight Price is active");
                            price.populateData();      
                        }
                        else if(pane.getSelectedIndex() == 4)
                        {
                            System.out.println("Payment is active");
                            pay.populateData();      
                        }
                        else if(pane.getSelectedIndex() == 5)
                        {
                            System.out.println("Booking Summary is active");
                            summary.populateData();      
                        }
                    }
                });

                // Add the tabbed panel to the main panel
                mainPane.add(tabs, BorderLayout.CENTER);
                // Add the main panel to the frame
                frame.add(mainPane, BorderLayout.CENTER);
                frame.setVisible(true);
                frame.validate();
            }
        }    
    }        

    private class AboutMenuAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand() == "About Application")
            {
                AboutInfo about = new AboutInfo(frame);
            }
        }    
    }        
}*/
package uidevel.userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javafx.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author kwhiting
 */
public class MainUiClass 
{
    JFrame frame;
    JPanel eastPanel;
    JPanel westPanel;
    JPanel centerPanel;
    JPanel buttonPanel;
    JScrollPane scroll;
    JLabel centerLabel;
    JLabel eastLabel;
    JLabel westLabel;
    JMenuBar menuBar;
    JMenu file;
    JMenuItem newFile;
    JMenuItem exit;
    JTextField text;
    JPasswordField password;
    JTextArea textArea;
    
    JButton button;
    
    public MainUiClass()
    {
        initComponents();
    }
    
    private void initComponents()
    {
        BorderLayout layout = new BorderLayout();
        
        // Initialize the JFrame
        frame = new JFrame("COP 3330");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(layout);
        
        // Initialize the JMenuBar and add to the JFrame
        menuBar = new JMenuBar();
        
        // Initialize the File menu and add the JMenuItems with action listener
        file = new JMenu("File");
        file.setMnemonic('F');
        
        newFile = new JMenuItem("New File");
        exit = new JMenuItem("Exit");
        
        file.add(newFile);    
        file.add(exit);    
        menuBar.add(file);
        
        // This is where the individual classes are populated
        eastPanel = new JPanel();
        centerPanel = new JPanel();
        westPanel = new JPanel();
        scroll = new JScrollPane();
        scroll.add(westPanel);
        eastLabel = new JLabel("This is a JLabel in the east");

        button = new JButton("This is a JButton");
        button.addActionListener( new thisListener());
            
        eastPanel.add(eastLabel);
        westLabel = new JLabel("This is a JLabel in the west ");
        text = new JTextField();
        text.setToolTipText("enter data here");
        text.setPreferredSize(new Dimension(50, 25));
        centerLabel = new JLabel("This is a JLabel in the center");
        
        String name = text.getText();
        
        System.out.println("user entered " + name);
        
        centerPanel.add(centerLabel);
        
        centerPanel.add(text);
        westPanel.add(westLabel);
        
        buttonPanel = new JPanel();
        buttonPanel.add(button);
        
        frame.add(menuBar, BorderLayout.NORTH);
        frame.add(westPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(eastPanel, BorderLayout.EAST);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }    

        class thisListener implements ActionListener {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
                    System.out.println(text.getText());
                    eastLabel.setText(text.getText());

        }
                public void actionPerformed(ActionEvent e){
                        ;}}

}
