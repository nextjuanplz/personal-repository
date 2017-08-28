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
public class WhileLoop 
{
    public void example()
    {
        int count = 1;
        while (count < 11) 
        {
            System.out.println("Count is: " + count);
            count++;
        }
    }
    
    public void infinite()
    {
        while (true)
        {
         // your code goes here
        }
    }
}
