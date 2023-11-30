
package unoquattro;

/**
 * @author Rory Dodd
 */

//  This class extends from the ColourCard class, inheriting the cardCode, 
//  cardColour and the toString() method.
public class StandardColourCard extends ColourCard{
    private int cardNumber;
    
//  Class constructor.
    public StandardColourCard(String cardCode, String cardColour, int cardNumber){
        super(cardCode, cardColour);
        this.cardNumber = cardNumber;
    }

//  Getter method.
    public int getCardNumber() {
        return cardNumber;
    }

//  Overide the extended classes toString() method.
    @Override
    public String toString() {
        return getCardColour() + " " + getCardNumber();
    }
    
    
}