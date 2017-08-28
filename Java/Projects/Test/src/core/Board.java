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
public class Board implements IBoard {
    //Constructor
    public Board() {
        init();
    }
    
    //Fields
    private Cell cellBoard[][];
      
    //Methods
    private void init() {
        cellBoard = new Cell[Board.NUMBER_OF_ROWS][Board.NUMBER_OF_COLS];
        //Create cells
        for (int i = 0; i < Board.NUMBER_OF_ROWS; i++) {
            this.cellBoard[i] = new Cell[Board.NUMBER_OF_ROWS];
            for (int j = 0; j < Board.NUMBER_OF_COLS; j++)
                this.cellBoard[i][j] = new Cell();            
        }
        //Count neighbors
        this.countNeighbors();
    }

    @Override
    public void countNeighbors() {      
        //Inbound checks
        Boolean xBound, yBound;
        //Checking coordinates
        int x, y;
        //Check every row
        for(int i = 0; i < Board.NUMBER_OF_ROWS; i++) {
            //Check each column
            for (int j = 0; j < Board.NUMBER_OF_COLS; j++) {
                //Check every adjacent direction
                for (int k = 0; k < Board.NUMBER_OF_DIRECTIONS; k++) {
                    x = i + DX[k];
                    y = j + DY[k];
                    
                    //Debug
                    System.out.println("Checking (" + y + ", " + x + ")");
                    
                    //If out of bounds, set out of bounds
                    xBound = !(x < 0 || x > Board.NUMBER_OF_ROWS);
                    yBound = !(y < 0 || y > Board.NUMBER_OF_COLS);

                    //If inbounds
                    if (xBound && yBound)
                        //Add one to current number of neighbor bombs
                        //if neighbor is bomb
                        if (this.cellBoard[x][y].getBombState()) {
                            this.cellBoard[i][j].setBombsSurround(
                            this.cellBoard[i][j].getBombsSurround() + 1
                            );
                        }
                }
            }
        }
    }   

    public Cell[][] getCellBoard() {
        return cellBoard;
    }
}
