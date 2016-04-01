package ca.bcit.comp2526.a2b;

import images.ImageLoader;

import java.awt.Point;

/**
 * The Omnivore, represented as a blue Cell.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public class Omnivore extends Inhabitant {
    //RGB values of the color of the Omnivore
    private static final int red = 78;
    private static final int green = 78;
    private static final int blue = 255;
    private static final int starve = 2;
    
    /**
     * Constructor for objects of type Omnivore.
     * 
     * @param location the cell to instantiate this Omnivore on
     */
    public Omnivore(Cell location) {
        super(location, red, green, blue, ImageLoader.getOmni());
        hunger = 0;
    }
    
    /**
     * Omnivore takes its turn.
     */
    public void takeTurn() {
        
        if (!turnTaken) {
            if (hunger == starve) {
                die();
            } else {
                hunger++;
                darken();
                if (checkNeighbors()) {
                    reproduce();
                }
                move();
            }
            turnTaken = true;
        }
    }
    
    /**
     * Omnivore moves if space is unoccupied or moves to a Plant and eats.
     */
    private void move() {
        Cell[][] cells = cell.getAdjacentCells();
        boolean moved = false;
        int stuck = 0;
        
        while (!moved) {
            Point point = direction();
            int y1 = (int)point.getY();
            int x1 = (int)point.getX();
                       
            if (cells[y1][x1] != null) {
                Inhabitant inhabitant = cells[y1][x1].getInhabitant(); 
                if (inhabitant == null || inhabitant instanceof OmniEdible ) {
                    if (inhabitant instanceof OmniEdible) { 
                        eat(cells[y1][x1]);
                        rejuvenate(red, green, blue);
                    }
                    removeCell(cell);
                    setCell(cells[y1][x1]);
                    moved = true;
                } 
            } 
            //if it is surrounded by impassable objects or cannot 
            //find a valid path after specified tries, give up
            moved = (stuck == tooStuck);
            stuck++;
        }
    }
    
    /**
     * Omnivore reproduces. 
     */
    protected void reproduce() {
        Cell there = cell.getRandomEmptyCell();
        Omnivore omni = new Omnivore(there);
        omni.init();
        omni.turnTaken = true;
        omni.revalidate();
    }
    
    /**
     * Checks nearby cells for neighbors if reproduction conditions are met.
     * @return boolean true if conditions are met and false if not
     */
    protected boolean checkNeighbors() {
        final int one = 1;
        final int three = 3;
        int omnis = 0;
        int empty = 0;
        int food = 0;
        final Cell[][] cells = cell.getAdjacentCells();
        
        for (int row = 0; row < three; row++) {
            for (int col = 0; col < three; col++) {
                if (cells[row][col] != null) {
                    if (cells[row][col].getInhabitant() == null) {
                        empty++;
                    } else if (cells[row][col].getInhabitant() 
                                instanceof Omnivore) {
                        omnis++;
                    } else if (cells[row][col].getInhabitant() 
                                instanceof OmniEdible) {
                        food++;
                    }
                }
            }
        }
        if (omnis >= one && empty >= three && food >= three) {
            return true;
        } else {
            return false;
        }        
    }
}
