/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import core.Board;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author Juan
 */
public class BoggleUi {
    //Custom constructor
    public BoggleUi(Board boardSent){  
        //Get board
        boardReceived = boardSent;
        //Instantiate action listeners
        exitEvent = new ExitAction();
        resetBoard = new ResettingBoard();
        timeEvent = new TimerAction();
        submitWord = new submitAction();
        //Initialize components
        initComponents();
    }
    
    //Member variables
    
    /*
    Boggle Assignment 4
    */
    
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu Boggle;
    private JMenuItem exit;
    private JMenuItem newGame;
    private JPanel currWord;
    private JButton[][] diceButtons;
    private JPanel buttonsPanel;
    private JScrollPane scrollPane;
    private JTextPane textArea;
    private JLabel playerScore;
    private JButton submitButton;
    private JPanel boxPanel;
    private JLabel createdWord;
    private JButton shakeDiceButton;
    private JLabel timeLeft;
    private Board boardReceived;
    
    /*
    Boggle Assignment 5
    */

    Timer timer;
    int minutes;
    int seconds;
    ExitAction exitEvent;
    ResettingBoard resetBoard;
    TimerAction timeEvent;
    
    
    /*
    Boggle Assignment 6
    */
    
    int score;
    int computerScore;
    int numWords;
    public String wordInput;
    public submitAction submitWord;
    public static Boolean usedCheck[][];
    
    //Constants
    static final int MINUTES = 3;
    static final int SECONDS = 0;
    static final String EMPTY_SPACE = " ";
    static final int DX[] = {-1,-1,-1,0,0,1,1,1};
    static final int DY[] = {-1,0,1,-1,1,-1,0,1};
    static final int POINTS_ARRAY[] = {0,0,1,1,2,3,5};
    static final int MIN_LENGTH = 3;
    
    //Methods
    private void initComponents() {
        //Initialize JFrame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Size configurations    
        Dimension frameSize = new Dimension(900,700);
        frame.setSize(frameSize);
        frame.setMinimumSize(frameSize);
        frame.setPreferredSize(frameSize);
        frame.setResizable(false);
        
        //JMenuBar menuBar
        menuBar = new JMenuBar();
        //JMenu Boggle
        Boggle = new JMenu("Boggle");
        Boggle.setMnemonic('B');  
        //Add newGame and exit to Boggle
        newGame = new JMenuItem("New Game");
        newGame.setMnemonic('N');
        newGame.addActionListener(resetBoard);
        exit = new JMenuItem("Exit");
        exit.setMnemonic('E'); 
        exit.addActionListener(exitEvent);
        Boggle.add(newGame);    
        Boggle.add(exit);
        //Add Boggle to menuBar
        menuBar.add(Boggle);
        //Add menuBar to frame
        frame.add(menuBar,BorderLayout.NORTH);
        
        //JPanel currWord
        currWord = new JPanel();
        currWord.setLayout(new FlowLayout());
        //currWord.setPreferredSize(new Dimension(300,75));
        //Components
        Dimension wordSizes = new Dimension(200,50);
        score = 0;
        computerScore = 0;
        playerScore = new JLabel("",JLabel.CENTER);
        playerScore.setPreferredSize(wordSizes);
        playerScore.setBorder(BorderFactory.createTitledBorder("Player Score"));
        playerScore.setFont(new Font("",Font.BOLD,20));
        playerScore.setForeground(Color.BLUE);
        playerScore.setText(Integer.toString(score));
        createdWord = new JLabel("",JLabel.CENTER);
        createdWord.setPreferredSize(wordSizes);
        createdWord.setFont(new Font("",Font.PLAIN,20));
        createdWord.setForeground(Color.BLUE);
        createdWord.setBorder(BorderFactory.createTitledBorder("Current Word"));
        submitButton = new JButton("Submit Word");
        submitButton.addActionListener(submitWord);
        //Add components
        currWord.add(playerScore,FlowLayout.LEFT);
        currWord.add(submitButton,FlowLayout.CENTER);
        currWord.add(createdWord,FlowLayout.RIGHT);
        currWord.setBorder(
                BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(
                    EtchedBorder.RAISED, Color.GRAY,Color.LIGHT_GRAY
                    ),
                "Current Word",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                null
                )
        );
        
        //JPanel buttonsPanel
        buttonsPanel = new JPanel();
        Dimension buttonsPanelSize = new Dimension(10,10);
        Dimension buttonSize = new Dimension(1,1);
        GridLayout gridLayout = new GridLayout(4,4,2,2);
        buttonsPanel.setPreferredSize(buttonsPanelSize);
        gridLayout.preferredLayoutSize(buttonsPanel);        
        buttonsPanel.setLayout(gridLayout);
               
        //Components
        diceButtons = new JButton[Board.GRID][Board.GRID];
        //Create buttons and add letters
        int k = 0;
        for (int i = 0; i < Board.GRID; i++) {
            diceButtons[i] = new JButton[Board.GRID];
            for (int j = 0; j < Board.GRID; j++) {
                diceButtons[i][j] = new JButton();
                diceButtons[i][j].setMaximumSize(buttonSize);
                diceButtons[i][j].setPreferredSize(buttonSize);
                diceButtons[i][j].setText(boardReceived.getGameData().get(k));
                diceButtons[i][j].setFont(new Font("Georgia",Font.PLAIN,60));
                diceButtons[i][j].addActionListener(new ButtonAction(diceButtons[i][j],i,j));
                usedCheck[i][j] = true;
                buttonsPanel.add(diceButtons[i][j]);
                k++;
            }      
        }
        buttonsPanel.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(
                    EtchedBorder.RAISED, Color.GRAY,Color.LIGHT_GRAY
                ),
                "Boggle Board",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                null
            )
        );
        
        //JPanel boxPanel
        boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel,BoxLayout.Y_AXIS));
        boxPanel.setOpaque(true);
        boxPanel.setBorder(BorderFactory.createEtchedBorder());
        Dimension boxPanelSize = new Dimension(200,50);
        boxPanel.setPreferredSize(boxPanelSize);
        boxPanel.setSize(boxPanelSize);
        boxPanel.setMinimumSize(boxPanelSize);
        //Components     
        textArea = new JTextPane();
        Dimension textAreaSize = new Dimension(50,15);
        textArea.setPreferredSize(textAreaSize);
        textArea.setToolTipText("Enter a word.");
        textArea.setBorder(
            BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(
                    EtchedBorder.RAISED, Color.GRAY
                    , Color.LIGHT_GRAY), "Enter Words Found"));
        scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(textAreaSize);
        
        //Timer
        minutes = BoggleUi.MINUTES;
        seconds = BoggleUi.SECONDS;
        timer = new Timer(1000,timeEvent);
        timer.start();  
        //Time left
        timeLeft = new JLabel();
        Dimension timeLeftSize = new Dimension (100,54);
        timeLeft.setText(EMPTY_SPACE + Integer.toString(minutes) + ":" + Integer.toString(
        seconds) + "0" + EMPTY_SPACE);
        timeLeft.setFont(new Font("",Font.BOLD,30));
        timeLeft.setForeground(Color.RED);              
        timeLeft.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(
                    EtchedBorder.RAISED, Color.GRAY,Color.LIGHT_GRAY
                ),
                "Time Left",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                null
            )
        );
        timeLeft.setPreferredSize(timeLeftSize);
        timeLeft.setMinimumSize(timeLeftSize);  
        timeLeft.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        shakeDiceButton = new JButton("Shake Dice");
        shakeDiceButton.setFont(new Font("Georgia",Font.BOLD,25));
        shakeDiceButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        shakeDiceButton.addActionListener(resetBoard);
        shakeDiceButton.setPreferredSize(new Dimension (30,50));
        textArea.setEditable(false);
        boxPanel.add(timeLeft,JPanel.RIGHT_ALIGNMENT);
        boxPanel.add(shakeDiceButton,JPanel.RIGHT_ALIGNMENT);
        boxPanel.add(Box.createRigidArea(new Dimension(0,2)));
        boxPanel.add(scrollPane,JPanel.CENTER_ALIGNMENT);       
        
        //Add components to frame
        frame.add(boxPanel,BorderLayout.EAST);
        frame.add(buttonsPanel,BorderLayout.CENTER);
        frame.add(currWord,BorderLayout.SOUTH);
        
        //Set visible
        frame.pack();
        frame.setLocation(250,75);
        frame.setVisible(true);            
    }
    
    //Inner classes (ActionListeners)
    public class ResettingBoard implements ActionListener {   
        //Methods
        @Override
        public void actionPerformed(ActionEvent e) {
            
            boardReceived.shakeDice();
            //Add new letters
            int k = 0;
            for (int i = 0; i < Board.GRID; i++) {
                for (int j = 0; j < Board.GRID; j++) {
                    String addWord = boardReceived.getGameData().get(k);
                    diceButtons[i][j].setText(addWord);
                    
                    //Reset action listener
                    ButtonAction buttonListener = (ButtonAction) diceButtons[i][j].getActionListeners()[0];
                    diceButtons[i][j].removeActionListener(diceButtons[i][j].getActionListeners()[0]);                                      
                    buttonListener.setLetter(addWord);
                    diceButtons[i][j].addActionListener(buttonListener);
                    
                    //Increase letter count                    
                    k++;
                }      
            }
            //Display data in console
            boardReceived.displayGameData();
            
            //Reset text
            textArea.setDocument(new DefaultStyledDocument());
            
            //Reset scores
            score = 0;
            computerScore = 0;
            playerScore.setText(Integer.toString(score));
            createdWord.setText("");
            
            //Re-enable all buttons and mark as unused
            for (int i = 0; i < Board.GRID; i++) {
                for (int j = 0; j < Board.GRID; j++) {
                    diceButtons[i][j].setEnabled(true);
                    usedCheck[i][j] = false;                    
                }
            }
            
            //Reset frame
            frame.revalidate();
            frame.repaint();
            
            //Reset timer
            timer.stop();
            minutes = BoggleUi.MINUTES;
            seconds = BoggleUi.SECONDS;
            //Reset number of words
            numWords = 0;
            //Reset timer look
            timeLeft.setText(EMPTY_SPACE + Integer.toString(minutes) + ":"
            + Integer.toString(seconds) + "0" + EMPTY_SPACE);
            timer.start();
        }
    }
    
    public class ExitAction implements ActionListener {
        //Member variables
        JOptionPane messagePane;
        //Methods
        @Override
        public void actionPerformed(ActionEvent e) {
            messagePane = new JOptionPane();
            int userResponse = JOptionPane.showConfirmDialog(messagePane,
            "Would you like to exit Boggle?","Exiting",
            JOptionPane.YES_NO_OPTION);

            //Exit if leaving
            if (userResponse == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }   
    }

    public class TimerAction implements ActionListener {
        //Constructor
        TimerAction() {
            numWords = 0;
        }
        //Fields
        private String explicitZero;
        
        //Methods
        @Override
        public void actionPerformed(ActionEvent e) {
            //Time has ended
            if (minutes == 0 && seconds == 0) {   
                
                //Advance to time 0
                timeLeft.setText(EMPTY_SPACE + Integer.toString(minutes) + ":" + explicitZero
                + Integer.toString(seconds) + EMPTY_SPACE);
                
                //Message dialog
                JOptionPane.showMessageDialog(frame, "GAME OVER!\n\nThe computer is comparing words");
                
                //Stop timer
                timer.stop();
                
                //Copy current words on text pane onto array
                //String[] getPane = new String[numWords];  
                String[] getPane = null;
                try {
                    getPane = textArea.getDocument().getText(0,textArea.getDocument().getLength()).split("\n");
                } catch (BadLocationException ex) {
                    Logger.getLogger(BoggleUi.class.getName()).log(Level.SEVERE, null, ex);
                }
//                for (int i = 0; i < getPane.length; i++) {
//                    //getPane[i] = textArea.getText().split("\n")[i];
//                    try {
//                        getPane[i] = textArea.getDocument().getText(0,textArea.getDocument().getLength()).split("\n")[i];
//                    } catch (BadLocationException ex) {
//                        Logger.getLogger(BoggleUi.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
                
                //Randomly determines how many of the player’s words computer found
                Random random = new Random();
                int randomLim = random.nextInt(numWords);
                int randomVal;
            
                //Computer words found
                String[] computerWords = new String[randomLim];
                
                int counter = 0;
                DefaultStyledDocument doc = new DefaultStyledDocument();
                StyleContext sc = new StyleContext();
                Style style = sc.addStyle("strikethru", null);
                StyleConstants.setStrikeThrough (style,true);                 
                //Find randomLim words
                while(counter < randomLim) {   
                    //Used word flag
                    Boolean used = false;
                    //Select random integer
                    randomVal = random.nextInt(randomLim);
                    
                    //Check if randomly-selected word is already in computer list
                    for (String computerWord : computerWords)
                        if (getPane[randomVal].equalsIgnoreCase(computerWord)) used = true;
                    
                    //Check if word was used
                    if (used == false) {            
                        //Word was used; add
                        computerWords[counter] = getPane[randomVal];
                        //Count up
                        counter++;
                    }
                }
                
//                for (String word : computerWords)
//                    System.out.println(word);
                
                //Re-insert words to textArea, striking through computer-found words
                for(String userWord : getPane) {
                    Boolean found = false;
                    //Look in computerwords
                    for (String word: computerWords)
                        if(word.equalsIgnoreCase(userWord)) found = true;
                    //Word was not found in computer words
                    if(found == false)
                        try {
                            //Keep word
                            doc.insertString(0,userWord.toUpperCase() + "\n",null);
                            //Update score
                            score += scoreWord(userWord);
                    } catch (BadLocationException ex) {
                        Logger.getLogger(BoggleUi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //Word was found in computer words
                    else
                        try {
                            //Keep word
                            doc.insertString(0,userWord.toUpperCase() + "\n",style);
                            //Subtract from score
                            score -= scoreWord(userWord);
                            playerScore.setText(Integer.toString(score));
                    } catch (BadLocationException ex) {
                        Logger.getLogger(BoggleUi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                //Reset text area
                textArea.setDocument(doc);
                
                //Computer notification
                JOptionPane.showMessageDialog(
                    frame, "The computer found " + randomLim
                    + " of Player's " + numWords
                );
                
//                //Score computer
//                for (String str : computerWords)
//                    computerScore += scoreWord(str);
//                
//                //Score comparison
//                String scoreComparison = "Player score = " + score + "\n"
//                + "Computer score = " + computerScore;
//
//                //Lose
//                if (computerScore > score)
//                    JOptionPane.showMessageDialog(
//                    frame, "You lose!\n\n" + scoreComparison
//                    );
//                
//                //Win
//                else
//                    JOptionPane.showMessageDialog(
//                    frame, " You win!\n\n" + scoreComparison
//                    );

                //Update number of words found
                numWords = 0;
            }
        
            //A minute has passed
            else if (seconds == 0) {
                seconds = 59;
                minutes--;                
            }
            
            //<10 s. remaining
            if(seconds < 10)
                explicitZero = "0";
            else
                explicitZero = "";
            
            //Set timer
            timeLeft.setText(EMPTY_SPACE + Integer.toString(minutes) + ":" + explicitZero
            + Integer.toString(seconds) + EMPTY_SPACE);
            
            seconds--;
        }   
        
        public int scoreWord(String str) {
            //Check every word in the array
            if (str.length() >= 8)
                return 11;           
            else
                //Score according to length
                return POINTS_ARRAY[str.length()];    
        }
  }
       
    
    public class submitAction implements ActionListener {        
        //Methods
        @Override
        public void actionPerformed(ActionEvent e) {
            //Fetch word
            wordInput = createdWord.getText();
            //Repeated word
            if (checkRepeats() == false) {
                 //Notify the user the word was not valid
                 JOptionPane.showMessageDialog(frame, wordInput + " is a repeat. Try again.");  
            }
            
            //Validate if word can be used from dictionary
            //Word is in dictionary
            else if (checkWord() && wordInput.length() >= MIN_LENGTH) {
               //Update current word
                String extraLine;
                if (numWords == 0) extraLine = "";
                else extraLine = "\n";
                
                DefaultStyledDocument doc = (DefaultStyledDocument) textArea.getDocument();
                try {
                    
                    doc.insertString(doc.getLength(),extraLine+wordInput.toUpperCase(),null);
                } catch (BadLocationException ex) {
                    Logger.getLogger(BoggleUi.class.getName()).log(Level.SEVERE, null, ex);
                }
                textArea.setDocument(doc);
                //Update number of words found
                numWords++;
                //Update score
                score += timeEvent.scoreWord(wordInput);
            }
            //Word is too short
            else if (checkWord()) {
                //Notify the user the word was too short
                JOptionPane.showMessageDialog(frame,"\"" + wordInput + "\"" + " is too short of a word.\n\nPlease try again.");
            }

            //Word is not in dictionary
            else {
                //Notify the user the word was not valid
                 JOptionPane.showMessageDialog(frame, "\"" + wordInput + "\"" + " is not a valid word.\n\nPlease try again.");               
            }
            //Clear text pane
            createdWord.setText("");
            
            //Re-enable all buttons and mark as unused
            for (int i = 0; i < Board.GRID; i++) {
                for (int j = 0; j < Board.GRID; j++) {
                    diceButtons[i][j].setEnabled(true);
                    usedCheck[i][j] = false;                    
                }
            }
            
            //Update score
            playerScore.setText(Integer.toString(score));            
        }
        public Boolean checkWord() {
            //Return true if word is in dictionary, ignoring caps
            return boardReceived.getDictionaryData().stream().anyMatch((str) -> (wordInput.equalsIgnoreCase(str)));
        }
        
        public Boolean checkRepeats() {
            //No words yet
            if (numWords == 0) return true;
            //Return true if word is in text pane, ignoring caps
            //Copy current words on text pane onto array
                String[] getPane = new String[numWords];       
                for (int i = 0; i < getPane.length; i++) {
                    //getPane[i] = textArea.getText().split("\n")[i];
                    try {
                        getPane[i] = textArea.getDocument().getText(0,textArea.getDocument().getLength()).split("\n")[i];
                    } catch (BadLocationException ex) {
                        Logger.getLogger(BoggleUi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (getPane[i].equalsIgnoreCase(wordInput)) return false;
                }
            //Word was not found
            return true;
        }
    }
    
    public class ButtonAction implements ActionListener {
        private String letter;
        private final Boolean adjacentCheck[][];
        private final int xCoor;
        private final int yCoor;
        
        
        //Custom constructor
        ButtonAction(JButton button, int x, int y) {
            this.letter = button.getText();
            this.xCoor = x;
            this.yCoor = y;
            //Initialize check arrays
            adjacentCheck = new Boolean[Board.GRID][Board.GRID];
            usedCheck = new Boolean[Board.GRID][Board.GRID];
            for (int i = 0; i < Board.GRID; i++) {
                adjacentCheck[i] = new Boolean[Board.GRID];
                usedCheck[i] = new Boolean[Board.GRID];
                for (int j = 0; j < Board.GRID; j++) {
                    adjacentCheck[i][j] = false;
                    usedCheck[i][j] = false;
                }
            }
            
            this.checkAdjacent();
        }

        //Methods
        @Override
        public void actionPerformed(ActionEvent e) {
            //Update JLabel
            createdWord.setText(createdWord.getText() + letter.toUpperCase());
            
            //Disable current button
            diceButtons[xCoor][yCoor].setEnabled(false);
            usedCheck[xCoor][yCoor] = true;
            
            //Update available buttons based on Boggle rules
            for (int i = 0; i < Board.GRID; i++) {
                for (int j = 0; j < Board.GRID; j++) {
                    if (adjacentCheck[i][j] && !usedCheck[i][j]) diceButtons[i][j].setEnabled(true);
                    else diceButtons[i][j].setEnabled(false);
                }
            }
        }  
        
        private void checkAdjacent() {                   
            //Check all directions
            int xNext, yNext;
            for (int i = 0; i < DX.length; i++) {
                xNext = xCoor + DX[i];
                yNext = yCoor + DY[i];
                //Check within bounds
                if (xNext >= 0 && xNext < Board.GRID && yNext >= 0  && yNext < Board.GRID)
                    //Mark as adjacent
                    adjacentCheck[xNext][yNext] = true;                 
            }   
        }    
            
        //Setter
        public void setLetter(String letter) {
            this.letter = letter;
        }
    }    
}