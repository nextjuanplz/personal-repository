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
public interface OperateCar 
{
    enum Direction {RIGHT, LEFT};

    // constant declarations, if any

    // method signatures
    int turn(Direction direction,
            double radius,
            double startSpeed,
            double endSpeed);
    
    int changeLanes(Direction direction,
                   double startSpeed,
                   double endSpeed);
    
    int signalTurn(Direction direction,
                  boolean signalOn);
    
    int getRadarFront(double distanceToCar,
                     double speedOfCar);
    
    int getRadarRear(double distanceToCar,
                    double speedOfCar);
    
    // more method signatures
}    
