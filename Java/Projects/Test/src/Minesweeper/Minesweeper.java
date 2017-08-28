/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

import MinesweeperUi.MinesweeperUi;
import core.Board;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan
 */
public class Minesweeper {
     public static void main(String[] args) {
        //Output to the console
        System.out.println("Welcome to Minesweeper!\n");
        
        //Pop-up window
        JOptionPane.showMessageDialog(null, "Let's Play Minesweeper!");
        
        //Start-up UI
        Board board = new Board();
        MinesweeperUi userInterface = new MinesweeperUi(board);
     }
}
