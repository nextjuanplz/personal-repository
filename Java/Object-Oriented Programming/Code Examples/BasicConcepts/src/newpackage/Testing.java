/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author kwhiting
 */
public class Testing extends Example implements ActionListener, ItemListener {

    private int dataValue;
    private String dataString;
    private String publicString;
    private int publicInt;
    
    public Testing()
    {
        
    }
    
    public Testing(String data)
    {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement the functionality 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void theData()
    {
        // TODO implement calculation to do someting with data... 
    }

    public void theData(int data)
    {
        // TODO implement calculation to do someting with data... 
    }

    /**
     * @return the dataValue
     */
    public int getDataValue() {
        return dataValue;
    }

    /**
     * @param dataValue the dataValue to set
     */
    public void setDataValue(int inDataValue) {
        this.dataValue = inDataValue;
    }

    /**
     * @return the dataString
     */
    public String getDataString() {
        return dataString;
    }

    /**
     * @param dataString the dataString to set
     */
    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    /**
     * @return the publicString
     */
    public String getPublicString() {
        return publicString;
    }

    /**
     * @param publicString the publicString to set
     */
    public void setPublicString(String publicString) {
        this.publicString = publicString;
    }

    /**
     * @return the publicInt
     */
    public int getPublicInt() {
        return publicInt;
    }

    /**
     * @param publicInt the publicInt to set
     */
    public void setPublicInt(int publicInt) {
        this.publicInt = publicInt;
    }
}
