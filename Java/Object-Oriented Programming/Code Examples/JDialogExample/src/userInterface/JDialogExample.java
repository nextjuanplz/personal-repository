/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userInterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author kwhiting
 */
public class JDialogExample 
{
    private JDialog dialog;
    private JPanel panel;
    private JLabel cbLabel;
    private JLabel tfLabel;
    private JTextField textField;
    private JComboBox comboBox;
    private JButton button;
    private JButton close;
    String[] data = {"Option 1", "Option 2", "Option 3"};

    public JDialogExample(JFrame parent)
    {
        initComponents(parent);
    }
    
    private void initComponents(JFrame parent)
    {
        dialog = new JDialog(parent, true);
        dialog.setTitle("JDialog modal example");
        dialog.setMinimumSize(new Dimension(300, 200));
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
//        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        panel = new JPanel();
        cbLabel = new JLabel("ComboBox to the right");
        comboBox = new JComboBox(data);
        tfLabel = new JLabel("TextField to the right");
        textField = new JTextField("Text is entered here");
        button = new JButton("JButton");
        close = new JButton("Close Dialog");
        close.addActionListener(new ExitListener());
        
        panel.add(cbLabel);
        panel.add(comboBox);
        panel.add(tfLabel);
        panel.add(textField);
        panel.add(button);
        panel.add(close);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    public void seeDialog()
    {
        dialog.setVisible(true);
    }
    
    private class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
//            System.exit(0);	
            dialog.dispose();
        }	
    }
}
