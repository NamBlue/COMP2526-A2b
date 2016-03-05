package ca.bcit.comp2526.a2b;

import javax.swing.JPanel;

/**
 * The type of all Inhabitants of a particular universe.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public abstract class Inhabitant extends JPanel{
    
    /**
     * Initializes the Inhabitant in the universe.
     */
    public abstract void init();
    
    /**
     * Inhabitant moves forward in time.
     */
    public abstract void takeTurn();
    
    /**
     * Sets the Inhabitant on the specified cell.
     * @param cell the cell to set this Inhabitant on
     */
    public abstract void setCell(Cell cell);
    
    /**
     * Removes the Inhabitant from the specified cell.
     * @param cell the cell to remove this Inhabitant from
     */
    public abstract void removeCell(Cell cell);
}
