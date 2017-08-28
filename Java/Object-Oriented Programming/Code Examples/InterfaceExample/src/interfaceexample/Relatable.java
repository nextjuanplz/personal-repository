/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaceexample;

/**
 *
 * @author kwhiting
 */
public interface Relatable 
{
    enum Result {GREATER_THAN, EQUAL_TO, LESS_THAN};
// this (object calling isLargerThan)
    // and other must be instances of 
    // the same class returns 0, 1, 2 
    // if this is greater than, 
    // equal to, or less than other
    public int isLargerThan(Relatable other);
    
//    int largest(Relatable first, Relatable second, Relatable third);
    default int largest(Relatable first, Relatable second, Relatable third)
    {
        // comparison code
        return 0;
    };
}
