/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author kwhiting
 */
public class JDialogUI //extends JFrame
{
    private JFrame frame;
    private static JMenuBar menuBar;    
    private static JMenu menu;
    private static JMenuItem exit;	
    private static JMenuItem menuItem;
    
    public JDialogUI()
    {
        initComponents();
    }
    
    private void initComponents()
    {
        frame = new JFrame();
        frame.setTitle("JDialog Example");		
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 300));
        frame.setMinimumSize(new Dimension(300, 300));

        menuBar = new JMenuBar();    
        menu = new JMenu("Menu");
        exit = new JMenuItem("Exit");	
        exit.addActionListener(new ExitListener());
        menuItem = new JMenuItem("Menu Item");
        menuItem.addActionListener(new MenuListener());
        
        menuBar.add(menu);
        menu.add(menuItem);
        menu.add(exit);
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

//    private JFrame getThisParent()
//    {
//        return this;
//    }
    
    // exit menu item selected
    private class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int response = JOptionPane.showConfirmDialog(null, "Confirm to exit!", 
                    "Exit?", JOptionPane.YES_NO_OPTION);
            
            if (response == JOptionPane.YES_OPTION)
                System.exit(0);	
        }	
    }

    //listener for game menu selection		
    private class MenuListener implements ActionListener
    {	
        JDialogExample example;
        
        public void actionPerformed(ActionEvent e)
        {	
           if(e.getActionCommand().equalsIgnoreCase("Menu Item"))
                example = new JDialogExample(frame);
        }	
    }	
}
