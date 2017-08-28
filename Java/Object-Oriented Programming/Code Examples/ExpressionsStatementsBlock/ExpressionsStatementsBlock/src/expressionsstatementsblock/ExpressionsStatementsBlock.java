/*
 * Karin Whiting
 * COP 3330 Object Oriented Programming
 * University of Central Florida
 */
package expressionsstatementsblock;

/**
 *
 * @author kwhiting
 */
public class ExpressionsStatementsBlock {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // this is an expression because variable result 
        // evaluates to a single value of 3
        int result = 1 + 2; // result is now 3 
       
        int value1 = 5;
        int value2 = 3;
        // this is an expression because operators are used
        // to evaluate a condition based on variables
        if (value1 == value2) 	
            System.out.println("value1 == value2");
        
        // this is an expression because is creates an instance
        // of class TestClass
        TestClass test = new TestClass();
        
        // this is an expression becuase it is a method invocation
        
        test.compare();
        
        //compound expressions
        int value = 1 * 2 * 3;
        
        
        int x = 5;
        int y = 20;
        // be specific about evaluation with compound expressions
        int ambiguous = x + y / 100; // ambiguous 
        int unambiguous = (x + y) / 100; // unambiguous 

    }
    
}
