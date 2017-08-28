/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Random;

/**
 *
 * @author Juan
 */
public class Cell implements ICell {
    
    //Constructor
    Cell(){
        init();
    }
    
    //Fields
    private Boolean bombState;
    private Boolean visibleState;
    private int bombsSurround;
    private String cellIcon;
    
    //Methods
    @Override
    public void init(){ 
        //Set as invisible
        this.visibleState = false;        
        //Randomly selected bomb cells
        Random randomNumbers = new Random();        
        if (randomNumbers.nextInt() > Cell.PERCENTAGE_OF_BOMB_CELLS - 1) {
            this.bombState = true;
            this.cellIcon = "*";
        }
        //No text
        else
            this.cellIcon = "";        
    }
    
    @Override
    public void updateCell(){
        //If visible...
        if (this.visibleState == true) {
            //Bomb
            if(this.bombState == true)
                this.cellIcon = "*";
            //Not a bomb; only if it has bomb neighbors
            else if (this.bombsSurround > 0)
                //Set cell icon to number of neighbors
                this.cellIcon = Integer.toString(this.bombsSurround);
        }
    }

    //Setters and getters
    public Boolean getBombState() {
        return bombState;
    }

    public void setBombState(Boolean bombState) {
        this.bombState = bombState;
    }

    public int getBombsSurround() {
        return bombsSurround;
    }

    public void setBombsSurround(int bombsSurround) {
        this.bombsSurround = bombsSurround;
    }

    public void setVisibleState(Boolean visibleState) {
        this.visibleState = visibleState;
    }

    public Boolean getVisibleState() {
        return visibleState;
    }

    public String getCellIcon() {
        return cellIcon;
    }
}
