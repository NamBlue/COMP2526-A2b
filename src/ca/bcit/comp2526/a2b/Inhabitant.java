package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * The type of all Inhabitants of a particular universe.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public abstract class Inhabitant extends JPanel{
    protected Cell cell;
    protected boolean turnTaken;
    //RGB values of the color of the Inhabitant
    private int red;
    private int blue;
    private int green;
    
    /**
     * Constructor for objects of type Inhabitant.
     * 
     * @param cell the cell to instantiate this Inhabitant on
     * @param rd the R value of RGB for this object
     * @param gr the G value of RGB for this object
     * @param bl the B value of RGB for this object
     */
    protected Inhabitant(Cell cell, int rd, int gr, int bl) {
        if (cell == null) {
            throw new IllegalArgumentException(
                    "Parameter cannot be null");
        }
        this.cell = cell;
        turnTaken = false;
        red = rd;
        green = gr;
        blue = bl;
    }
    
    /**
     * Initializes the Inhabitant in the universe.
     */
    protected void init() {
        setCell(cell);
    }
    
    /**
     * Inhabitant moves forward in time. Usually overridden.
     */
    protected void takeTurn() {
        if (!turnTaken) {
            //Do Something
        }
        turnTaken = true;
    }
    
    /**
     * Inhabitant is ready to take the next turn.
     */
    protected void resetTurn() {
        turnTaken = false;
    }
    
    /**
     * Inhabitant "dies".
     */
    protected void die() {
        removeCell(cell);
    }
    
    /**
     * Sets the Inhabitant on the specified cell.
     * @param cell the cell to set this Inhabitant on
     */
    protected void setCell(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException(
                    "Parameter cannot be null");
        }
        this.cell = cell;
        cell.setInhabitant(this);
        cell.add(this);
    }
    
    /**
     * Removes the Inhabitant from the specified cell.
     * @param cell the cell to remove this Inhabitant from
     */
    protected void removeCell(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException(
                    "Parameter cannot be null");
        }
        cell.removeInhabitant(this);
        cell.remove(this);
    }
    
    /**
     * Draws the Inhabitant.
     * @param draw device context for the Panel to draw on
     */
    public void paintComponent(Graphics draw) {
        draw.setColor(new Color(red, green, blue));
        draw.fillRect(0, 0, getWidth(), getHeight());
    }
}
