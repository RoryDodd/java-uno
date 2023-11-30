
package unoquattro;

/**
 *
 * @author Rory Dodd
 */

// Class to create the WildCard, extends the abstract Card class, 
// implements the SpecialAbility interface
class WildCard extends Card implements SpecialAbility{
    private String specialAbility;
    
//  Constructor which includes the super class cardCode and interface specialAbility
    public WildCard(String specialAbility, String cardCode) {
        super(cardCode);
        this.specialAbility = specialAbility;
    }

//  Getter method for specialAbility
    public String getString(){
        return specialAbility;
    }
    
//  Overriden method from the SpecialAbility interface
    @Override
    public void activateAbility() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
