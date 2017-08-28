/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Juan
 */
public class Die implements IDie {
    /*
    Boggle Assignment 3
    */
    //Custom constructor
    Die(){
        //Mark as unused
        used = false;
    }
    
    /*
    Boggle Assignment 2
    */
    
    //Methods
    @Override
    public String rollDie() {
        //Randomly select 1 letter/6 from ArrayList
        Random randomNumbers = new Random();
        int randomValue = randomNumbers.nextInt(NUMBER_OF_SIDES);
        String randomLetter = sides.get(randomValue);
        
        //Return letter value
        return (randomLetter);
    }

    @Override
    public void addLetter(String str1) {
        sides.add(str1);      
    }
    
    @Override
    public void displayLetters() {
        //Loop through sides ArrayList
        for (String str : sides) {
            //print each letter
            System.out.print(" " + str + " ");
        }
        //Add new line
        System.out.print("\n");        
    }

    /**
     * @return the used
     */
    public Boolean getUsed() {
        return used;
    }

    /**
     * @param used the used to set
     */
    public void setUsed(Boolean used) {
        this.used = used;
    }

    //Member variables
    private ArrayList<String> sides = new ArrayList();      //Letters
    private Boolean used;                                   //Whether or not dice has been used
}
