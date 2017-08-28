/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timerExample;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author kwhiting
 */
public class TimerExample 
{
    static JFrame frame;
    static JPanel panel;
    static JLabel label;
    
    private static Timer timer;
    private static int minutes = 1;
    private static int seconds = 0;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        setupTimer();
    }
    
    private static void setupTimer()
    {
        frame = new JFrame("Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
                
        label = new JLabel();
        label.setText("1:00");
        label.setMinimumSize(new Dimension(100, 100));
        label.setPreferredSize(new Dimension(100, 100));
        
        panel = new JPanel();
        panel.add(label);
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        
        timer = new Timer(1000, new TimerListener());
        timer.start();
    }
    
    private static class TimerListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(seconds == 0 && minutes == 0)
            {              
                timer.stop();
            }
            else
            {
                if(seconds == 0)
                {
                    seconds = 59;
                    minutes--;
                }
                else
                {
                    seconds--;
                }
            }

            if(seconds < 10)
            {
                String strSeconds = "0" + String.valueOf(seconds);
                label.setText(String.valueOf(minutes) + ":" + strSeconds);
            }
            else
            {
                label.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
            }
        }
    }
}
