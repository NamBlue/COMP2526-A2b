package ca.bcit.comp2526.a2b;

import images.ImageLoader;

import java.awt.Point;

/**
 * The Carnivore, represented as a magenta Cell.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public class Carnivore extends Inhabitant implements OmniEdible{
    //RGB values of the color of the Carnivore
    private static final int red = 255;
    private static final int green = 0;
    private static final int blue = 255;
    
    /**
     * Constructor for objects of type Carnivore.
     * 
     * @param location the cell to instantiate this Carnivore on
     */
    public Carnivore(Cell location) {
        super(location, red, green, blue, ImageLoader.getCarni());
    }
    
    /**
     * Carnivore takes its turn.
     */
    public void takeTurn() {
        final int hungry = 3;
        
        if (!turnTaken) {
            if (hunger == hungry) {
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
     * Carnivore moves if space is unoccupied or moves to a Plant and eats.
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
                if (inhabitant == null || inhabitant instanceof Herbivore) {
                    if (inhabitant instanceof Herbivore) { 
                        eat(cells[y1][x1]);
                        rejuvenate(red, green, blue);
                    }
                    removeCell(cell);
                    setCell(cells[y1][x1]);
                    moved = true;
                } 
            //if it is surrounded by impassable objects or cannot 
            //find a valid path after specified tries, give up
            } else if (stuck == tooStuck) {  
                moved = true;
            }
            stuck++;
        }
    }
    
    /**
     * Carnivore reproduces. 
     */
    protected void reproduce() {
        Cell there = cell.getRandomEmptyCell();
        Carnivore carni = new Carnivore(there);
        carni.init();
        carni.turnTaken = true;
        carni.revalidate();
    }
    
    /**
     * Checks nearby cells for neighbors if reproduction conditions are met.
     * @return boolean true if conditions are met and false if not
     */
    protected boolean checkNeighbors() {
        final int one = 1;
        final int three = 3;
        int carni = 0;
        int empty = 0;
        int food = 0;
        final Cell[][] cells = cell.getAdjacentCells();
        
        for (int row = 0; row < three; row++) {
            for (int col = 0; col < three; col++) {
                if (cells[row][col] != null) {
                    if (cells[row][col].getInhabitant() == null) {
                        empty++;
                    } else if (cells[row][col].getInhabitant() 
                                instanceof Carnivore) {
                        carni++;
                    } else if (cells[row][col].getInhabitant() 
                            instanceof Herbivore) {
                        food++;
                    }
                }
            }
        }
        if (carni >= one && empty >= three && food >= three) {
            return true;
        } else {
            return false;
        }        
    }
}
