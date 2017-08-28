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
public class DoWhileLoop 
{
    public void example()
    {
        int count = 1;
        do 
        {
            System.out.println("Count is: " + count);
            count++;
        } while (count < 11);
    }
    
    public void infinite()
    {
        do
        {
            // code goes here
        } while(true);
    }
}
