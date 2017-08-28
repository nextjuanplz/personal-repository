/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package branching;

/**
 *
 * @author kwhiting
 */
public class Branching 
{
/**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Break breakEx = new Break();
        breakEx.example();
        
        Continue continueEx = new Continue();
        continueEx.example();       
        
        Return returnEx = new Return();
        returnEx.example();
        returnEx.exampleValue();
        
    }    
}
