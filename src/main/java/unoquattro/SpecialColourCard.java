
package unoquattro;

/**
 *
 * @author Rory Dodd
 */

// Class to create the SpecialColourCard, extends the ColourCard class, 
// implements the SpecialAbility interface
class SpecialColourCard extends ColourCard implements SpecialAbility{
    private String specialAbility;
    
//  Constructor which includes the super class cardCode and cardColour
//  also the interface specialAbility
    public SpecialColourCard(String cardCode, String cardColour, String specialAbility){
        super(cardCode, cardColour);
        this.specialAbility = specialAbility;
    }
    
//  Overriden method from the interface SpecialAbility
    @Override
    public void activateAbility() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
