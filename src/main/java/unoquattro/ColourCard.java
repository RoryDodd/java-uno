
package unoquattro;

/**
 * @author Rory Dodd
 */

// This class extends from the Card class, inheriting the cardCode and the 
// toString method.
public class ColourCard extends Card{
    private String cardColour;
    
//    Class constructor.
    public ColourCard(String cardCode, String cardColour){
        super(cardCode);
        this.cardColour = cardColour;
    }

//  Getter for cardColour.
    public String getCardColour() {
        return cardColour;
    }

//  Overide the extended classes toString() method.
    @Override
    public String toString() {
        return getCardColour();
    }
    
}