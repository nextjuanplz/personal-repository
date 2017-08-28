/*
 * Karin Whiting
 * COP 3330 Object Oriented Programming
 * University of Central Florida
 */
package classesindepth;

/**
 *
 * @author kwhiting
 */
public class ClassesInDepth {
    public static void main(String[] args) {

    /**
     * @param args the command line arguments
     */
        // TODO code application logic here
        NewClass newClass = new NewClass();
        NewClass newClassTwo = new NewClass(45);
        
        Game game = new Game();
        Game gameToo = new Game();
        // game is an object reference
        // from this point forward we us the object reference
        // not the class name
        game.displayPlayers();
        
        game.playGame();
        
        // accessing member variable of class game for this
        // instance using the object reference
        game.data = "Karin";
        gameToo.data = "Whiting";
    }
    
}
