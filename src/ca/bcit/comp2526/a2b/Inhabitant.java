package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;

import javax.swing.JPanel;

/**
 * The type of all Inhabitants of a particular universe.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public abstract class Inhabitant extends JPanel{
    // the amount of move tries to attempt before giving up
    protected final int tooStuck = 0; 
    protected Cell cell;
    protected boolean turnTaken;
    protected int hunger;
    protected int age;
    private final Image image;
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
        hunger = 0;
        age = 0;
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
     * Inhabitant moves forward in time.
     */
    protected abstract void takeTurn();
    
    /**
     * Inhabitant is ready to take the next turn.
     */
    protected void resetTurn() {
        turnTaken = false;
    }
    
    /**
     * Inhabitant eats the target Inhabitant when on the 
     * same cell as that Inhabitant.
     * Resets its hunger back to 0(full stomach).
     * @param cell the cell with the Inhabitant to eat
     */
    protected void eat(Cell cell) {
        cell.getInhabitant().removeCell(cell);
        hunger = 0;
    }
    
    /**
     * Inhabitant reproduces when its conditions are met.
     */
    protected abstract void reproduce();
    
    /**
     * Checks nearby cells for neighbors if reproduction conditions are met.
     * @return boolean true if conditions are met and false if not
     */
    protected abstract boolean checkNeighbors();
    
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
     * Inhabitant decides which direction to go before moving.
     * @return the Point direction decided
     */
    protected Point direction() {
        int direction;
        int y1 = 1;
        int x1 = 1;
        final int two = 2;
        final int ten = 10;
        final int twenty = 20;
        final int thirty = 30;
        final int forty = 40;
        final int fifty = 50;
        final int sixty = 60;
        final int seventy = 70;
        final int eighty = 80;
        final Random gen = new Random();
        
        /* Map of 2D array for reference in y,x index format
         * 00   01     02
         * 10   CELL   12
         * 20   21     22
         */
        direction = gen.nextInt(eighty);
        if (direction < ten) { //moves north
            y1 = 0;
            x1 = 1;
        } else if (direction < twenty) { //moves north east
            y1 = 0;
            x1 = two;
        } else if (direction < thirty) { //moves east
            y1 = 1;
            x1 = two;
        } else if (direction < forty) { //moves south east
            y1 = two;
            x1 = two;
        } else if (direction < fifty) { //moves south
            y1 = two;
            x1 = 1;
        } else if (direction < sixty) { //moves south west
            y1 = two;
            x1 = 0;
        } else if (direction < seventy) { //moves west
            y1 = 1;
            x1 = 0;
        } else if (direction < eighty) { //moves north west
            y1 = 0;
            x1 = 0;
        }
        Point point = new Point(x1, y1);
        return point;
    }
}
