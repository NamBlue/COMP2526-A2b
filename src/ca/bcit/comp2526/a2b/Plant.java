package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The Plant, represented as a green Cell.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public class Plant extends Inhabitant {
    private Cell cell;
        
    /**
     * Constructor for objects of type Plant.
     * 
     * @param location the cell to instantiate this Plant on
     */
    public Plant(Cell location) {
        if (location == null) {
            throw new IllegalArgumentException(
                    "Parameter cannot be null");
        }
        cell = location;
    }
    
    /**
     * Initializes the Plant.
     */
    public void init() {
        setCell(cell);
    }
    
    /**
     * Reserved for future implementation (reproduction growth etc). 
     */
    public void takeTurn() {
        
    }
    
    /**
     * Sets the Plant on the specified cell.
     * @param cell the cell to set this Plant on
     */
    public void setCell(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException(
                    "Parameter cannot be null");
        }
        this.cell = cell;
        cell.setInhabitant(this);
        cell.add(this);
    }
    
    /**
     * Removes the Plant from the specified cell.
     * @param cell the cell to remove this Plant from
     */
    public void removeCell(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException(
                    "Parameter cannot be null");
        }
        cell.removeInhabitant(this);
        cell.remove(this);
    }
    
    /**
     * Draws the Plant.
     * @param draw device context for the Panel to draw on
     */
    public void paintComponent(Graphics draw) {
        final int r = 19;
        final int g = 237;
        final int b = 12;
        draw.setColor(new Color(r, g, b));
        draw.fillRect(0, 0, getWidth(), getHeight());
    }
}
