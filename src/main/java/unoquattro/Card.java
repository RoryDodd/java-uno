
package unoquattro;

/**
 * @author Rory Dodd
 */

//  Abstract class used as the basis for all cards created in this program.
abstract class Card{
    private String cardCode;

//  Class constructor.
    public Card(String cardCode){
        this.cardCode = cardCode;
    }

//  Getter for the cardCode.
    public String getCardCode() {
        return cardCode;
    }

//  toString() override method.
    @Override
    public String toString() {
        return getCardCode();
    }
    
    
}

