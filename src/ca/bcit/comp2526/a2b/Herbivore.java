package ca.bcit.comp2526.a2b;

import images.ImageLoader;

import java.awt.Point;

/**
 * The Herbivore, represented as a yellow Cell.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public class Herbivore extends Inhabitant implements OmniEdible {
    //RGB values of the color of the Herbivore
    private static final int red = 255;
    private static final int green = 255;
    private static final int blue = 0;
    private static final int starve = 10;
    
    /**
     * Constructor for objects of type Herbivore.
     * 
     * @param location the cell to instantiate this Herbivore on
     */
    public Herbivore(Cell location) {
        super(location, red, green, blue, ImageLoader.getHerbi());
        hunger = 0;
    }
       
    /**
     * Herbivore takes its turn.
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
     * Herbivore moves if space is unoccupied or moves to a Plant and eats.
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
                if (inhabitant == null || inhabitant instanceof Plant) {
                    if (inhabitant instanceof Plant) { 
                        eat(cells[y1][x1]);
                        rejuvenate(red, green, blue);
                    }
                    removeCell(cell);
                    setCell(cells[y1][x1]);
                    moved = true;
                }
            }  
            //if it is surrounded by impassable objects or cannot 
            //find a valid path after ten tries, give up
            moved = (stuck == tooStuck);
            stuck++;
        }
    }
    
    /**
     * Herbivore reproduces. 
     */
    protected void reproduce() {
        Cell there = cell.getRandomEmptyCell();
        Herbivore herb = new Herbivore(there);
        herb.init();
        herb.turnTaken = true;
        herb.revalidate();
    }
    
    /**
     * Checks nearby cells for neighbors if reproduction conditions are met.
     * @return boolean true if conditions are met and false if not
     */
    protected boolean checkNeighbors() {
        final int one = 1;
        final int two = 2;
        final int three = 3;
        int herbs = 0;
        int empty = 0;
        int food = 0;
        final Cell[][] cells = cell.getAdjacentCells();
        
        for (int row = 0; row < three; row++) {
            for (int col = 0; col < three; col++) {
                if (cells[row][col] != null) {
                    if (cells[row][col].getInhabitant() == null) {
                        empty++;
                    } else if (cells[row][col].getInhabitant() 
                                instanceof Herbivore) {
                        herbs++;
                    } else if (cells[row][col].getInhabitant() 
                                instanceof Plant) {
                        food++;
                    }
                }
            }
        }
        if (herbs >= one && empty >= one && food >= two) {
            return true;
        } else {
            return false;
        }        
    }
}
