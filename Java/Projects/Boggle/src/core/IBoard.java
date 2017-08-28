/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public interface IBoard {
    /*
    Boggle Assignment 2
    */
    
    //Constant fields
    public static final int NUMBER_OF_DICE = 16;
    public static final int GRID = 4;
    
    //Method signatures
    public void populateDice();
    public ArrayList<String> shakeDice();
}
