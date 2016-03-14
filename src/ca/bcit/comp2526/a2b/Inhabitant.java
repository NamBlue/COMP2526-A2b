package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

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
    private Image image;
    //RGB values of the color of the Inhabitant
    private int red;
    private int blue;
    private int green;
    
    /**
     * Constructor for objects of type Inhabitant.
     * 
     * @param cell the cell to instantiate this Inhabitant on
     * @param rd the R value of RGB for this Inhabitant
     * @param gr the G value of RGB for this Inhabitant
     * @param bl the B value of RGB for this Inhabitant
     * @param img the Image of the Inhabitant
     */
    protected Inhabitant(Cell cell, int rd, int gr, int bl, Image img) {
        if (cell == null) {
            throw new IllegalArgumentException(
                    "Parameter cannot be null");
        }
        this.cell = cell;
        turnTaken = false;
        red = rd;
        green = gr;
        blue = bl;
        image = img;
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
            turnTaken = true;
        }   
    }
    
    /**
     * Inhabitant is ready to take the next turn.
     */
    protected void resetTurn() {
        turnTaken = false;
    }
    
    /**
     * Inhabitant reproduces when its conditions are met.
     */
    protected void reproduce(){}
    
    /**
     * Checks nearby cells for neighbors if reproduction conditions are met.
     * @return boolean true if conditions are met and false if not
     */
    protected boolean checkNeighbors(){return false;} 
    
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
        draw.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        repaint();
    }
    
    /**
     * Darkens the colors of the Inhabitant as it looses life.
     */
    protected void darken() {
        final float factor = .80f;
        red *= factor;
        green *= factor;
        blue *= factor;
    }
    
    /**
     * Restores the colors of the Inhabitant as it regains life.
     * @param rd the original red color value of the Inhabitant
     * @param gr the original green color value of the Inhabitant
     * @param bl the original blue color value of the Inhabitant
     */
    protected void rejuvenate(int rd, int gr ,int bl) {
        red = rd;
        green = gr;
        blue = bl;
    }
    
    /**
     * Sets the Image of the Inhabitant.
     * @param img the image to set
     */
    protected void setImage(Image img) {
        image = img;
    }
}
