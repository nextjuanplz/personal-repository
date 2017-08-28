/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package strikethrough;


import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author kwhiting
 */
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StrikeTwo extends JPanel{

    public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    String s = "\"www.java2s.com\" is great.";

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    Font plainFont = new Font("Times New Roman", Font.PLAIN, 24);

    AttributedString as = new AttributedString(s);
    as.addAttribute(TextAttribute.FONT, plainFont);
    as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, 1,
        15);
    as.addAttribute(TextAttribute.STRIKETHROUGH,
        TextAttribute.STRIKETHROUGH_ON, 18, 25);

    g2.drawString(as.getIterator(), 24, 70);
  }

  public static void main(String[] args) {
    JFrame f = new JFrame();
    f.getContentPane().add(new StrikeTwo());
    f.setSize(850, 250);
    f.setVisible(true);
  }
}