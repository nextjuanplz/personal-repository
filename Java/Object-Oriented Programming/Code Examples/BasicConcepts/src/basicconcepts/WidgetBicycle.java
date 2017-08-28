/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicconcepts;

/**
 *
 * @author kwhiting
 */
public class WidgetBicycle implements IBicycle {

    @Override
    public void changeCadence(int newValue) {
    }

    @Override
    public void changeGear(int newValue) {
    }

    @Override
    public void speedUp(int increment) {
    }

    @Override
    public void applyBrakes(int decrement) {
    }
    
    int cadence = 0;
    int speed = 0;
    int gear = 1;

    // The compiler will now require that methods
    // changeCadence, changeGear, speedUp, and applyBrakes
    // all be implemented. Compilation will fail if those
    // methods are missing from this class.
    void printStates() {
         System.out.println("cadence:" +
             cadence + " speed:" + 
             speed + " gear:" + gear);
    }
}
