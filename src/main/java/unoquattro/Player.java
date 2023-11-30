
package unoquattro;
//Imported packages
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Rory Dodd
 */

//  Player calss that is used to create the players for the game.
class Player{
    private String name;
    private ArrayList<Card> hand;
    private boolean isHuman;
    
//  Class constructor to intialise the player object.
    public Player(String name, ArrayList<Card> hand, boolean isHuman){
        this.name = name;
        this.hand = new ArrayList<>();
        this.isHuman = isHuman;
    }

//  Getter for player name.
    public String getName() {
        return name;
    }

//  Setter for player name.
    public void setName(String name) {
        this.name = name;
    }

//  Boolean to return wether the player is human or not.
    public boolean isHuman() {
        return isHuman;
    }

//  Getter for the player hand (cards).
    public ArrayList<Card> getHand() {
        return hand;
    }
    
//  This method sets the starting hand for each player from the set value of 
//  cards allowed.
    public void getCardList(List<Card> cards, List<Card> discardPile){
        Random random = new Random();// Initialise the Random class.
        final int STARTING_CARD_NUMBER = 3; // Number of cards to add to the hand
        
//      Using the Random class, selects cards for each players hand
//      and places them in the discard list.
        for (int i = 0; i < STARTING_CARD_NUMBER; i++) {
            int randomIndex = random.nextInt(cards.size());
            Card card = cards.get(randomIndex);
            hand.add((StandardColourCard) card);
            cards.remove(randomIndex);
            discardPile.add(card);
        }
    }
}