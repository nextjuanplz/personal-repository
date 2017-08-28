/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package operators;

import javax.swing.JOptionPane;


/**
 *
 * @author kwhiting
 */
public class Operators {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Arithmetic
        
        
        int result = 1 + 2;
        int fResult = result + (int)1.0;

// result is now 3
        System.out.println("1 + 2 = " + result);
        int original_result = result;

        result = result - 1;
        // result is now 2
        System.out.println(original_result + " - 1 = " + result);
        original_result = result;

        result = result * 2;
        // result is now 4
        System.out.println(original_result + " * 2 = " + result);
        original_result = result;

        result = result / 2;
        // result is now 2
        System.out.println(original_result + " / 2 = " + result);
        original_result = result;

        result = result + 8;
        // result is now 10
        System.out.println(original_result + " + 8 = " + result);
        original_result = result;

        result = result % 7;
        // result is now 3
        System.out.println(original_result + " % 7 = " + result);
        
        // concatenation
        String firstString = "This is";
        String secondString = " a concatenated string.";
        String thirdString = firstString + secondString;
        System.out.println(thirdString);
        
        // unary operators
        int value = +1;
        // result is now 1
        System.out.println(value);

        value--;
        // result is now 0
        System.out.println(value);

        value++;
        // result is now 1
        System.out.println(value);

        value = -value;
        // result is now -1
        System.out.println(value);

        boolean success = false;
        // false
        System.out.println(success);
        // true
        System.out.println(!success);
        
        // prefix and postfix 
        int i = 3;
        i++;
        // prints 4
        System.out.println(i);
        ++i;			   
        // prints 5
        System.out.println(i);
        // prints 6
        System.out.println(++i);
        // prints 6
        System.out.println(i++);
        // prints 7
        System.out.println(i);
        
        // Equality and relational
        
        int value1 = 1;
        int value2 = 2;
        if(value1 == value2)
            System.out.println("value1 == value2");
        if(value1 != value2)
            System.out.println("value1 != value2");
        if(value1 > value2)
            System.out.println("value1 > value2");
        if(value1 < value2)
            System.out.println("value1 < value2");
        if(value1 <= value2)
            System.out.println("value1 <= value2");
        
        // conditional
        int value3 = 1;
        int value4 = 2;
        
        if((value3 == 1) && (value4 == 2))
            System.out.println("value3 is 1 AND value4 is 2");
        if((value3 == 1) || (value4 == 1))
            System.out.println("value3 is 1 OR value4 is 1");
        
        // ternary 
        int value5 = 1;
        int value6 = 2;
        int result1 = JOptionPane.showConfirmDialog(null, "Are you ready to code?", "Ready", JOptionPane.YES_NO_OPTION);
        boolean someCondition = true;
        System.out.print(result1);
        System.out.println((result1 == JOptionPane.YES_OPTION) ? "You are ready" : "You are not ready");

        System.out.println(result1);
        
        // instanceof
        Integer variable = new Integer(5);
        
        Parent obj1 = new Parent();
        Parent obj2 = new Child();

        System.out.println("obj1 instanceof Parent: "
            + (variable instanceof Integer));

        System.out.println("obj1 instanceof Parent: "
            + (obj1 instanceof Parent));
        System.out.println("obj1 instanceof Child: "
            + (obj1 instanceof Child));
        System.out.println("obj1 instanceof MyInterface: "
            + (obj1 instanceof MyInterface));
        System.out.println("obj2 instanceof Parent: "
            + (obj2 instanceof Parent));
        System.out.println("obj2 instanceof Child: "
            + (obj2 instanceof Child));
        System.out.println("obj2 instanceof MyInterface: "
            + (obj2 instanceof MyInterface));
    
    
        if(value1 == value2)
            if(value3 == value4)
                    System.out.println("all values are equal");
    }
    
    static class Parent {}

    static class Child extends Parent implements MyInterface {}

    interface MyInterface {}
}
