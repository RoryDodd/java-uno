
package unoquattro;


/**
 * @author Rory Dodd
 */

public class QuattroGameTester {
    

        public static void main(String[] args) 
        {
//          Print a welcome
            System.out.println("******************************");
            System.out.println("Welcome to Quattro Games 'UNO'");
            System.out.println("******************************");
            
//          Create the game object, then run the game
            QuattroGame game = new QuattroGame();
            game.gamePlay();
            
        }
}
