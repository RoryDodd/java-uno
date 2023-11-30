
package unoquattro;
//Imported packages
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Rory Dodd
 */

public class QuattroGame{
    final private List<String> CARD_COLOURS = Arrays.asList ("Blue", "Yellow", "Red", "Green");;
    final private List<Integer> CARD_NUMBERS = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
    private ArrayList<Card> cardListStack = new ArrayList<>();
    private ArrayList<Card> cardListDiscard = new ArrayList<>();
    private ArrayList<Player> playerList = new ArrayList();
    final private int NUMBER_OF_PLAYERS = 4;
    private Scanner input = new Scanner(System.in);
    
    private String name;
    
    private Card topCard;
    
//  This method is the main use for the application, calls all other methods
//  to play the game
    void gamePlay() {
        createCardDeck(); // Call method to create the deck of cards
        createPlayer(); // Call method to create the array list of players
        playFirstCard(); // Call method to play the first card of the game

        boolean gameRunning = true;
        Player winner = null;
//      GameRunning set to true to allow the game to continue
        while (gameRunning) {
//          Loop through the array list of players
            for (Player player : playerList) {
//              Check if the player is human, human plays
                if (player.isHuman()) {
                    printCurrentCard();
                    System.out.println("------------------------");
                    playPlayerTurn(player);
                    System.out.println("------------------------");
                } 
//              If isHuman() is false, bot to play
                else {
                    printCurrentCard();
                    System.out.println("------------------------");
                    botPlayerTurn(player);
                    System.out.println("------------------------");
                }

//          Deterime if a player has won the game
            if (player.getHand().isEmpty()) { // Check if the players hand is empty
                gameRunning = false; // While loop breaks
                winner = player; // Player is declared the winner
                break;
            }
            }
        }
//      Display winner
        System.out.println("Game over!");
        if (winner != null) {
            System.out.println("The winner is: " + winner.getName());
        }
    }
    
//  This method allows the human player to play the game
    private void playPlayerTurn(Player player) {
//  Display the players name and current hand of cards
    System.out.println("It is " + player.getName() + "'s turn.");
    printPlayerHand(player);
    System.out.println("Which card would you like to play? ");
    printCardIndices(player); //Display players current cards
    
    int choice = -1;
    boolean validInput = false;
//  While loop to alow the player to try again if the card selection is incorrect
    while (!validInput) {
//      Try block to handle input errors
        try {
//          Display card options, how to draw a card and take the user input
            System.out.print("Enter your choice (1-" + player.getHand().size() + "): ");
            System.out.println("Select 0 to draw a new card.");
            choice = input.nextInt();
//          Check if the input is one of the available card option
            if (choice >= 0 && choice <= player.getHand().size()) {
                validInput = true; // Breaks the while loop
            }
//          To throw an Exception if the number enetered is not a valid option
            else {
                throw new IllegalArgumentException("Invalid choice. Please enter a number between 0 and " + player.getHand().size() + ".");
            }
//      2 catch blocks for the try block, first Exception is for an input that is not
//      an Integer, the second compliments the number out of range Exception.
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number between 0 and " + player.getHand().size() + ".");
            input.nextLine(); // Consume the invalid input
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
//  Allows the user to enter 0 to draw a card or play the selected card
    if (choice == 0) {
        drawCard(player); // Draw card method called for 0
    } else {
        Card card = player.getHand().get(choice - 1); // Card will equal the selected card
        
        boolean validCard = false;
//      While loop allows for the user to try again if the selected card is not a match
        while (!validCard) {
//          Card validate method used to check the selected card vs the current playbale card
            if (isValidCardToPlay(card)) {
                validCard = true;
                playCard(player, card); // If valid, play card method called
            } else {
//              Else ask the player to try again
                System.out.println("The selected car is not of the same colour or number. Please choose a valid card.");
                printPlayerHand(player); // Display cards again
                printCardIndices(player);
                choice = input.nextInt(); // Allow for another input attempt
                card = player.getHand().get(choice - 1);
            }
        }
    }
}

//  Non-humans ability to play
    private void botPlayerTurn(Player player) {
        System.out.println("It is " + player.getName() + "'s turn.");
        boolean foundMatchingCard = false;
        
//      Loop through the cards
        for (Card card : player.getHand()) {
//          Check card validity
            if (isValidCardToPlay(card)) {
                playCard(player, card); // Play card method called if a matching card is found
                foundMatchingCard = true;
                break; // Break the turn
            }
        }
//      If no match is found, draw method is called
        if (!foundMatchingCard) {
            drawCard(player);
        }
    }

//  Check card validity, the selected card by the human player and the looped through cards
//  of the non-human player are checked against the current playable cards colour and number
    private boolean isValidCardToPlay(Card card) {
//  Making sure both cards are an instance of the StandardColourCard object
    if (card instanceof StandardColourCard && topCard instanceof StandardColourCard) {
//      Casting both the seleceted card and the current playable card to StandardColourCard
//      objects to allow for method calls on colour and number.
        StandardColourCard standardCard = (StandardColourCard) card;
        StandardColourCard currentStandardCard = (StandardColourCard) topCard;
//      Return true if the colours match or the numbers match.
//      NOTE: Checks the colour first then the number, so in the non-human case it will
//      play the matching colour before checking for the number
        return standardCard.getCardColour().equals(currentStandardCard.getCardColour()) ||
                standardCard.getCardNumber() == currentStandardCard.getCardNumber();
    }
    return false; // Return false if not matching
}

//  Playing the selected card method, takes in the player and valid card
    private void playCard(Player player, Card card) {
        if(card instanceof StandardColourCard){
//          Casting the card perameter to a StandardColourCard to access methods
            StandardColourCard standardCard = (StandardColourCard) card;
//          Display the card that is played
            System.out.println(player.getName() + " played " + standardCard.getCardCode());
            player.getHand().remove(standardCard); // Remove the card from players hand
            topCard = standardCard; // Change the current playable card
            cardListDiscard.add(card); // Add the card to the list of discarded cards
            System.out.println(player.getName() + " now has " + player.getHand().size() + " cards.");
        }
    }
    
//  Drawing a card method, takes in player object
    private void drawCard(Player player) {
//      Check if there is cards in the stack to draw from
        if(cardListStack.isEmpty()){ // If the stack is empty
            cardListStack.addAll(cardListDiscard); // Add all of the cards from discard back into the stack
            cardListDiscard.clear(); // Empty the discard stack
            cardListDiscard.add(topCard); // Return the current playble card to the discard
        }
//      Randomly selecting a card from the stack to draw
        Random random = new Random(); // Random object created
        int randomIndex = random.nextInt(cardListStack.size()); // Random indices in the stack selected
        Card randomCard = cardListStack.get(randomIndex); // New card equals the random card
        player.getHand().add((StandardColourCard) randomCard); // Add it to player hand
        cardListDiscard.add(randomCard); 
        cardListStack.remove(randomIndex); // Remove from stack
        System.out.println(player.getName() + " drew a card!"); // Display the drawn card
        System.out.println(player.getName() + " now has " + player.getHand().size() + " cards.");
    }

//  Simple method to display players hand of cards
    private void printPlayerHand(Player player) {
        System.out.println("Your cards are " + player.getHand());
    }

//  Simple method to display the number of cards available to select from.
//  Used as option for the player to input.
    private void printCardIndices(Player player) {
        for (int i = 0; i < player.getHand().size(); i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();
    }
    
//  Displays the current playable card
    private void printCurrentCard(){
        System.out.println("The top card is: " + topCard);
    }

//  Method that creates the deck of cards from the 2 arrays.
//  Loops through both array lists pairing each colour with each number into the stack array list.
    private void createCardDeck(){
        for (String colour : CARD_COLOURS) {
            for(int number : CARD_NUMBERS){
                Card card = new StandardColourCard((colour + " " + number),colour,number);
                cardListStack.add(card);
            }  
        }
    }
    
//  Method to play the starting card to be played.
    private void playFirstCard() {
        Random random = new Random(); // Random object created
        int randomIndex = random.nextInt(cardListStack.size()); // Random indices selected

        Card randomCard = cardListStack.get(randomIndex);
//      Create the current playbale card object from the randomly selected card
        topCard = new StandardColourCard(randomCard.getCardCode(),((StandardColourCard) randomCard).getCardColour(),((StandardColourCard) randomCard).getCardNumber());
        cardListStack.remove(randomIndex); // Remove the card from the stack
        cardListDiscard.add(randomCard);
    }
    
//  Method to create the players
    private void createPlayer() {
//      While loop used to allow for retrying after errors
        while (true) {
//          Try block to handle any errors entering a name
            try {
                System.out.print("Please enter your name: ");
                String nameInput = input.nextLine().trim(); // Remove white space from input
//              Check if the input is a string from a-z or A-Z using a regular expresion
                if (nameInput.matches("^[a-zA-Z]+$")) {
//                  Convert the input to have and uppercase starting letter (just for looks)!
                    name = Character.toUpperCase(nameInput.charAt(0)) + nameInput.substring(1);
                    ArrayList<Card> hand = new ArrayList<>(); // Creates the hand array list
                    Player humanPlayer = new Player(name, hand, true); // Human player created
                    humanPlayer.getCardList(cardListStack, cardListDiscard); // Method to create players hand called
                    playerList.add(humanPlayer); // Human player add to array list of players
//                  Creating the bot players
                    for (int i = 2; i <= NUMBER_OF_PLAYERS; i++) { // Create players according to variable size
                        Player botPlayer = new Player("Player " + i, hand, false); // Create player object
                        botPlayer.getCardList(cardListStack, cardListDiscard);// Method  called to create the hand of cards
                        playerList.add(botPlayer); // Bot player added to array list of players
                    }
                
                break; // Exit the loop if the input is valid
                }
                else {
                    throw new IllegalArgumentException("Must use a proper name.");
                }
            } // Catch block to hand the invalid name inputs
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
//  End of Class.