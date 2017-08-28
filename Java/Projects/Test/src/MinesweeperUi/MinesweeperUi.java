/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinesweeperUi;

import core.Board;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Juan
 */
public class MinesweeperUi implements IMinesweeperUi {
    //Constructor
    public MinesweeperUi(Board board) {
        this.boardReceived = board;
        exitEvent = new exitAction();
        resetBoard = new newAction();
        initComponents();
    }
    
    //Fields
    Board boardReceived;
    JFrame frame;
    JMenuBar menuBar;
    JMenu Minesweeper;
    JMenuItem exit;
    JMenuItem newGame;
    JButton[][] buttons;
    clickBoard[][] clickButtons;
    JPanel buttonsPanel;
    exitAction exitEvent;
    newAction resetBoard;
    
    
    private void initComponents() {
        //Initialize JFrame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Size configurations    
        Dimension frameSize = new Dimension(900,700);
        frame.setSize(MinesweeperUi.FRAME_SIZE);
        frame.setMinimumSize(MinesweeperUi.FRAME_SIZE);
        frame.setPreferredSize(MinesweeperUi.FRAME_SIZE);
        frame.setResizable(false);
        
        //JMenuBar menuBar
        menuBar = new JMenuBar();
        //JMenu Minesweeper
        Minesweeper = new JMenu("Minesweeper");
        Minesweeper.setMnemonic('M');  
        //Add newGame and exit to Minesweeper
        newGame = new JMenuItem("New Game");
        newGame.setMnemonic('N');
        newGame.addActionListener(resetBoard);
        exit = new JMenuItem("Exit");
        exit.setMnemonic('E'); 
        exit.addActionListener(exitEvent);
        Minesweeper.add(newGame);    
        Minesweeper.add(exit);
        //Add Minesweeper to menuBar
        menuBar.add(Minesweeper);
        //Add menuBar to frame
        frame.add(menuBar,BorderLayout.NORTH);
        
        buttonsPanel = new JPanel();
        Dimension buttonsPanelSize = new Dimension(10,10);
        GridLayout gridLayout = new GridLayout(4,4,2,2);
        buttonsPanel.setPreferredSize(buttonsPanelSize);
        gridLayout.preferredLayoutSize(buttonsPanel);        
        buttonsPanel.setLayout(gridLayout);
               
        //Components
        buttons = new JButton[Board.NUMBER_OF_ROWS][Board.NUMBER_OF_ROWS];
        
        //Add grid of buttons
        int k = 0;
        for (int i = 0; i < Board.NUMBER_OF_ROWS; i++) {
            buttons[i] = new JButton[Board.NUMBER_OF_ROWS];
            clickButtons[i] = new clickBoard[Board.NUMBER_OF_ROWS];
            for (int j = 0; j < Board.NUMBER_OF_COLS; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setMaximumSize(MinesweeperUi.BUTTON_SIZE);
                buttons[i][j].setPreferredSize(MinesweeperUi.BUTTON_SIZE);
                clickButtons[i][j] = new clickBoard(i,j);
                buttons[i][j].addActionListener(clickButtons[i][j]);
                buttonsPanel.add(buttons[i][j]);
                k++;
            }      
        }
        
        //Buttons panel border
        buttonsPanel.setBorder(
                BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(
                    EtchedBorder.RAISED, Color.GRAY,Color.LIGHT_GRAY
                    ),
                "Minesweeper Board",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                null
                )
        );
        
        //Add components to frame
        frame.add(buttonsPanel,BorderLayout.CENTER);
        frame.setVisible(true);
        frame.pack();
        frame.setLocation(250,75);
    }
    
    private void updateGrid() {
        //Check all rows
        for (int i = 0; i < Board.NUMBER_OF_ROWS; i++) {
            //Check all columns
            for (int j = 0; j < Board.NUMBER_OF_COLS; j++) {
                //If visible...
                if (this.boardReceived.getCellBoard()[i][j].getVisibleState())
                    this.buttons[i][j].setText(this.boardReceived.getCellBoard()[i][j].getCellIcon());
            }
        }
    }
    
    //Inner classes
    //Click
    public class clickBoard implements ActionListener {
        //Constructor
        //Fields
        private final int x;
        private final int y;
        
        //Constructor
        private clickBoard(int i, int j) {
            this.x = i;
            this.y = j; 
        }
        
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            //Update status of current button
            boardReceived.getCellBoard()[x][y].setVisibleState(true);
            //Refresh board
            updateGrid();
        }
        
        
    }
    
    public class newAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //Reset board
            
            //Set up frame again
            frame.revalidate();
            frame.repaint();
        }
    }
    
    
    //Exit
    public class exitAction implements ActionListener {
                //Member variables
        JOptionPane messagePane;
        //Methods
        @Override
        public void actionPerformed(ActionEvent e) {
            messagePane = new JOptionPane();
            int userResponse = JOptionPane.showConfirmDialog(messagePane,
            "Would you like to exit Minesweeper?","Exiting",
            JOptionPane.YES_NO_OPTION);

            //Exit if leaving
            if (userResponse == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }  
    }
    
}
