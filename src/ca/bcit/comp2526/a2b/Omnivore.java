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
        final int hungry = 2;
        
        if (!turnTaken) {
            if (hunger == hungry) {
                die();
            } else {
                hunger++;
                darken();
                move();
            }
            turnTaken = true;
        }
    }
    
    /**
     * Inhabitant reproduces when its conditions are met.
     */
    protected void reproduce() {
        
    }
    
    /**
     * Omnivore moves if space is unoccupied or moves to a Plant and eats.
     */
    private void move() {
        Cell[][] cells = cell.getAdjacentCells();
        boolean moved = false;
        int stuck = 0;
        final int tooStuck = 5;
        
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
            //if it is surrounded by impassable objects or cannot 
            //find a valid path after ten tries, give up
            } else if (stuck == tooStuck) {  
                moved = true;
            }
            stuck++;
        }
    }
}
