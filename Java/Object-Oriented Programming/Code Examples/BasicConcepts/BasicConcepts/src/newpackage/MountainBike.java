/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newpackage;

import basicconcepts.Bicycle;
import basicconcepts.IBicycle;

/**
 *
 * @author kwhiting
 */
public class MountainBike extends Bicycle implements IBicycle{
   // the MountainBike subclass has
    // one field
    public int seatHeight;

    public MountainBike()
    {
        
    }
    // the MountainBike subclass has
    // one constructor
    public MountainBike(int startHeight, int startCadence,
                        int startSpeed, int startGear) 
    {
        // calling the parent class's constructor
        super(startCadence, startSpeed, startGear);
        seatHeight = startHeight;
    }   
        
    // the MountainBike subclass has
    // one method
    public void setHeight(int newValue) {
        seatHeight = newValue;
    }   
    
    
    @Override 
    public void changeCadence(int newValue)
    {
        super.changeCadence(newValue);
    }

    public void changeGear(int newValue){}

    public void speedUp(int increment){}

    public void applyBrakes(int decrement){}  
    
    public IBicycle getInterface()
    {
        return this;
    }
}
