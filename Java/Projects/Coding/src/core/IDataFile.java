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
public interface IDataFile {
    //Excel file order
    static final int TIME_COL = 0;
    static final int BRAKE_COL = 1;
    static final int SPEED_COL = 2;
    static final int ACCEL_COL = 3;
    
    //Reference point
    static final String REFERENCE_POINT = "final";
    
    //Constants
    final static int BRAKE_THRESHOLD = 15;
    
}
