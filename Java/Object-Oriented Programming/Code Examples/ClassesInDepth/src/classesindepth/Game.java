/*
 * Karin Whiting
 * COP 3330 Object Oriented Programming
 * University of Central Florida
 */
package classesindepth;

import java.sql.SQLException;

/**
 *
 * @author kwhiting
 */
public class Game {


    
    public String data;
    
    // we want the users to use this one
    public Game(String gameData)
    {
        data = gameData;
    }

    // we rewrite default no-argument constructor when we want behavior other 
    // than just allocating memory.
    
    // if we want the user to be able to call a no-argument constructor
    // but we want to make sure member variables are being initialized
    public Game()
    {
        // this is calling the constructor at line 33, passing an argument to
        // make sure our data is initialized
        this("Game");
    }
    

    public int displayPlayers()
    {
        /// do some work
        // when done return back to where called from
        float data =5;
        
        // type cast data
        String strData = String.valueOf(data);
        String strDataToo = Float.toString(data);
        // explicit type cast
        // even though the variable is of type float, return
        // as an int
        return (int)data;
    }
    
    public String playGame()
    {
        return data;
    }
    
    // required elements included
    private void startGame(String firstPlayer, int playerNum) throws Exception, SQLException
    {
        
    }
    private void startGame(String firstPlayer) throws Exception, SQLException
    {
        
    }
    private void startGame(int playerNum) throws Exception, SQLException
    {
        
    }
    
    private void startGame() throws Exception, SQLException
    {
        
    }

}
