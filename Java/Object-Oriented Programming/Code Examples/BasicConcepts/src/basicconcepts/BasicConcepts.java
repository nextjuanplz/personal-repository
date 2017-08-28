/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicconcepts;

import newpackage.Testing;

/**
 *
 * @author kwhiting
 */
public class BasicConcepts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create two different 
        // Bicycle objects
        Bicycle trik;
        
        // declaration
        Bicycle bike = new Bicycle();
        
        bike.applyBrakes(12);
        bike.changeCadence(23);
        
        bike.setCadence(10);
        System.out.println(bike.getCadence());
        System.out.println(bike.getCounter());
        
        // Instantiating a class
        Bicycle bike1 = new Bicycle();
        bike1.setCadence(20);
        System.out.println(bike1.getCadence());
        System.out.println(bike1.getCounter());
        
        Bicycle bike2 = new Bicycle();
        bike2.setCadence(30); 
        System.out.println(bike2.getCadence());
        System.out.println("*********** counter versus count **************");
        System.out.println(bike2.getCounter());
        System.out.println(bike2.getCount());
        // Initializing an object
//        Bicycle bike3 = new Bicycle(10,12,13);

        // Invoke methods on 
        // those objects
        bike1.changeCadence(50);
        bike1.speedUp(10);
        bike1.changeGear(2);
        
        bike2.changeCadence(50);
        bike2.speedUp(10);
        bike2.changeGear(2);
        bike2.changeCadence(40);
        bike2.speedUp(10);
        bike2.changeGear(3);
        
        Testing testTestTest = new Testing();
        
    }
}
