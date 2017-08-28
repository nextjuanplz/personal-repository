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
public interface ICell {
    
    //Constant fields
    public static final int PERCENTAGE_OF_BOMB_CELLS = 30;
    
    //Method signatures
    public void init();
    public void updateCell();
}
