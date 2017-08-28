/*Juan Manuel Alzate Vanegas
 * COP 3330
 * Boggle Assignment 3
 * 6/11/2017
 */
package boggle;

import core.Board;
import inputOutput.ReadDataFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import userInterface.BoggleUi;

/**
 *
 * @author Juan Manuel Alzate Vanegas
 */
public class Boggle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        Boggle Assignment 1
        */
        //Output to the console
        System.out.println("Welcome to Boggle!\n");
        
        //Pop-up window
        JOptionPane.showMessageDialog(null, "Let's Play Boggle!");
        
        /*
        Boggle Assignment 2
        */
        //Instances of class ReadDataFile
        ReadDataFile dataFile = new ReadDataFile(inputFile);
        ReadDataFile dictionaryFile = new ReadDataFile(dictionary);
        
        //populateData method
        dataFile.populateData();
        dictionaryFile.populateData();
        
        //Board
        Board board = new Board(dataFile.getFileData(),dictionaryFile.getFileData());
        
        //populateDice method
        board.populateDice();
        
        //Ouput number of elements in dictionaryData
        System.out.println("There are " + dictionaryFile.getFileData().size() + " entries in the dictionary");        
    
        /*
        Boggle Assignment 3
        */
        //Set game data to shakeDice()
        diceData = board.shakeDice();
        
        //Display data
        board.displayGameData();
        
        /*
        Boggle Assignment 4
        */
        BoggleUi boggleUi = new BoggleUi(board);
    }
    
    //Member variables
    private static ArrayList<String> diceData = new ArrayList();                   //Stores dice data from file
    private static final String inputFile = "../data/BoggleData.txt";              //Input file name
    private static ArrayList<String> dictionaryData = new ArrayList();             //Stores dictionary data from file
    private static final String dictionary = "../data/Dictionary.txt";             //Dictionary file name
}
