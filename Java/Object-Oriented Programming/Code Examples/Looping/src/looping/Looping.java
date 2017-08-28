/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package looping;

/**
 *
 * @author kwhiting
 */
public class Looping {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        WhileLoop whileLoop = new WhileLoop();
        whileLoop.example();
        whileLoop.infinite();
        
        DoWhileLoop doWhileLoop = new DoWhileLoop();
        doWhileLoop.example();
        doWhileLoop.infinite();

        ForLoop forLoop = new ForLoop();
        forLoop.example();
        forLoop.infinite();
        
        // using static methods
        // do not have to create an instance of the class
        // call by using class name, dot operator, static method name
        EnhancedForLoop.example();
    }
    
}
