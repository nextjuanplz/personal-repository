package strikethrough;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author kwhiting
 */
public class StrikeThrough {
    public static void main(String[] args)
    {
        JFrame f = new JFrame("Strikethrough text")
        {
            public void paint(Graphics g)
            {
                Graphics2D g2 = (Graphics2D)g;
                String texte = "Examples Java et Python";
                Dimension d = getSize();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Create a font
                Font font = new Font("Times New Roman", Font.PLAIN, 24);
                AttributedString str_attribut = new AttributedString(texte);
                // Apply the font to the text
                str_attribut.addAttribute(TextAttribute.FONT, font);
                // Undeline the text
                str_attribut.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, 0, 8);
                // strikethrough text
                str_attribut.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON, 17, texte.length());
                g2.drawString(str_attribut.getIterator(), 40, 80);
            }
        };
    
        f.setSize(500,150);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
