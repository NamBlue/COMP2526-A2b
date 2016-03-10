package ca.bcit.comp2526.a2b;

/**
 * The Plant, represented as a green Cell.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public class Plant extends Inhabitant {
    //RGB values of the color of the Plant
    private static final int red = 19;
    private static final int green = 237;
    private static final int blue = 12;
        
    /**
     * Constructor for objects of type Plant.
     * 
     * @param location the cell to instantiate this Plant on
     */
    public Plant(Cell location) {
        super(location, red, green, blue);
    }
    
    /**
     * Reserved for future implementation (reproduction growth etc). 
     */
    public void takeTurn() {
        
    }
}
