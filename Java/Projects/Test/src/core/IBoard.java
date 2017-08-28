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
public interface IBoard {
    //Constants
    static final int NUMBER_OF_ROWS = 10;
    static final int NUMBER_OF_COLS = 10;
    
    //UDLR  DX & DY arrays
    static final int DX[] = {0,0,-1,1};  //DX array for movement along a row
    static final int DY[] = {1,-1,0,0};  //DY array for movement along a column
    static final int NUMBER_OF_DIRECTIONS = 4;
    
    //Methods
    void countNeighbors();
}
