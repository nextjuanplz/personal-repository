/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Juan
 */
public interface IDie {
    /*
    Boggle Assignment 2
    */
    
    //Constant fields
    public static final int NUMBER_OF_SIDES = 6;
    
    //Method signatures
    public String rollDie();
    public void addLetter(String str1);
    public void displayLetters();    
}
