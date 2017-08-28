/*
 * Karin Whiting
 * COP 3330 Object Oriented Programming
 * University of Central Florida
 */
package classesindepth;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author kwhiting
 */
public class NewClass extends Object //implements ActionListener {
{
    private int varOne;
    private String varTwo;
    private float varThree;
    
    // by default the member variable is package-private
    boolean varFourBool;
    
    String varFive;
    int varSix[] = new int[10];
    
    // this is a no argument constructor
    // NOTE NO RETURN TYPE
    public NewClass()
    {}
   public NewClass(int data)
   {
       
   }
   public void game()
   {
       NewClass thisClass = new NewClass();
       
   }
    // this is a method, NOT a constructor
    // NOTE a return type of void
    public void newClass()
    {
        
    }
    
//    // method with exception list
//    // overloading methods, three methods called methodOne()
//    void methodOne() throws IOException, SQLException 
//    {
//        
//    }
//    
//    void methodOne(int var)
//    {
//        
//    }
//    
//    int methodOne(int var, int varTwo)
//    {
//        int data = 0;
//        return data;
//        
//    }
//    
    @Override
    public String toString()
    {
        return "data";
    }
                    
    
    /**
     * @return the varOne
     */
    public int getVarOne() {
        return varOne;
    }

    /**
     * @param varOne the varOne to set
     */
    public void setVarOne(int varOne) {
        this.varOne = varOne;
    }

    /**
     * @return the varTwo
     */
    public String getVarTwo() {
        return varTwo;
    }

    /**
     * @param varTwo the varTwo to set
     */
    public void setVarTwo(String varTwo) {
        this.varTwo = varTwo;
    }

    /**
     * @return the varThree
     */
    public float getVarThree() {
        return varThree;
    }

    /**
     * @param varThree the varThree to set
     */
    public void setVarThree(float varThree) {
        this.varThree = varThree;
    }
}
