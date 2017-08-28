/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Juan
 */
public class Board implements IBoard {
    /*
    Boggle Assignment 2
    */
    
    //Custom constructor
    public Board(ArrayList<String> str1, ArrayList<String> str2) {
        //Initialize strings
        this.diceData = str1;
        this.dictionaryData = str2;
        //Initialize game dice
        this.gameDice = new ArrayList<>();
    }

    //Implement IBoard methods
    @Override
    public void populateDice() {
        //Declare new instance of Die
        Die die;
        //Counters
        int i;      //diceData index
        int j;      //dictionaryData index
        int k = 0;  //Separate index so that letters are not repeated
        //Loop through each die
        for (i = 0; i < NUMBER_OF_DICE; i++) {
            //Instantiate die
            die = new Die();
            //Loop through each side
            for (j = 0; j < Die.NUMBER_OF_SIDES; j++) {
                //Add a letter to the side
                die.addLetter(diceData.get(k));
                //Increment k
                k++;
            }
            
            //Display letters of each die on a separate row
            System.out.print("Dice " + i + ":");
            die.displayLetters();
            
            //Add to ArrayList
            gameDice.add(die);
        }   
    }

    /*
    Boggle Assignment 3
    */
    
    @Override
    public ArrayList<String> shakeDice() {  
         //Loop through each dice
        for(int i = 0; i < NUMBER_OF_DICE; i++){        
            //Randomly select 1/16 dice
            Random randomNumbers = new Random();
            int randomValue = randomNumbers.nextInt(NUMBER_OF_DICE);            
            //While the current randomly-selected dice has been used
            while(gameDice.get(randomValue).getUsed() == true)
                //Look for a new randomly-selected die in remaining set
                randomValue = randomNumbers.nextInt(NUMBER_OF_DICE);         
          
            //Roll die and store value
            gameData.add(i,gameDice.get(randomValue).rollDie());
            
            //Mark dice as used
            gameDice.get(randomValue).setUsed(Boolean.TRUE);
        }
        
        //Reset used
        for (int i = 0; i < NUMBER_OF_DICE; i++)
           gameDice.get(i).setUsed(Boolean.FALSE);
        
        
        return gameData;
    }
    
    //Methods
    public void displayGameData() {
        //Header
        System.out.print("Boggle board");
        //Print each dice to console
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
           // 4 by 4 grid
           //Check every 4th dice
            if ((i % GRID) == 0)
                //Add new line
                System.out.print("\n");
            
            //Print value
            System.out.print(gameData.get(i) + " ");
        }
        //Add new line
        System.out.print("\n");
    }
    
    //Member variables
    private ArrayList<String> diceData;
    private ArrayList<String> dictionaryData;
    private ArrayList<Die> gameDice;
    private ArrayList<String> gameData = new ArrayList();

    //Getters and setters
    public ArrayList<String> getGameData() {
        return gameData;
    }
    public void setGameData(ArrayList<String> gameData) {
        this.gameData = gameData;
    }

    public ArrayList<String> getDictionaryData() {
        return dictionaryData;
    }
}