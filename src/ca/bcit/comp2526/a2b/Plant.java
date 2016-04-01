package ca.bcit.comp2526.a2b;

import images.ImageLoader;

/**
 * The Plant, represented as a green Cell.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public class Plant extends Inhabitant implements OmniEdible{
    //RGB values of the color of the Plant
    private static final int red = 0;
    private static final int green = 255;
    private static final int blue = 0;
    private static final int lifespan = 10;
        
    /**
     * Constructor for objects of type Plant.
     * 
     * @param location the cell to instantiate this Plant on
     */
    public Plant(Cell location) {
        super(location, red, green, blue, ImageLoader.getPlant());
    }
    
    /**
     * Plant takes a turn. 
     */
    public void takeTurn() {
        
        if (!turnTaken) {
            if (age == lifespan) {
                die();
            } else {
                age ++;
                darken();
                if (checkNeighbors()) {
                    reproduce();
                }
            }
            turnTaken = true;
        }
        repaint();
    }
    
    /**
     * Plant reproduces. 
     */
    protected void reproduce() {
        Cell there = cell.getRandomEmptyCell();
        Plant plant = new Plant(there);
        plant.init();
        plant.turnTaken = true;
        plant.revalidate();
    }
    
    /**
     * Checks nearby cells for neighbors if reproduction conditions are met.
     * @return boolean true if conditions are met and false if not
     */
    protected boolean checkNeighbors() {
        final int two = 2;
        final int three = 3;
        int plants = 0;
        int empty = 0;
        final Cell[][] cells = cell.getAdjacentCells();
        
        for (int row = 0; row < three; row++) {
            for (int col = 0; col < three; col++) {
                if (cells[row][col] != null) {
                    if (cells[row][col].getInhabitant() == null) {
                        empty++;
                    } else if (cells[row][col].getInhabitant() 
                                instanceof Plant) {
                        plants++;
                    }
                }
            }
        }
        if (plants >= three && empty >= two) {
            return true;
        } else {
            return false;
        }        
    }
}
