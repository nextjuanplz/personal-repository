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
public class EnhancedForLoop 
{
    // static methods can be called WITHOUT creating an instance of theh class
    public static void example()
    {
         int[] numbers = {1,2,3,4,5,6,7,8,9,10};
         
         for (int item : numbers) 
         {
             System.out.println("Count is: " + item);
         }
    }
}
