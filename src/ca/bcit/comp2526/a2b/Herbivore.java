package ca.bcit.comp2526.a2b;

import java.awt.Point;
import java.util.Random;

/**
 * The Herbivore, represented as a yellow Cell.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public class Herbivore extends Inhabitant {
    private int hunger;
    //RGB values of the color of the Herbivore
    private static final int red = 255;
    private static final int green = 255;
    private static final int blue = 0;
    
    /**
     * Constructor for objects of type Herbivore.
     * 
     * @param location the cell to instantiate this Herbivore on
     */
    public Herbivore(Cell location) {
        super(location, red, green, blue, null);
        hunger = 0;
    }
       
    /**
     * Herbivore takes its turn.
     */
    public void takeTurn() {
        final int hungry = 10;
        
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
     * Herbivore moves if space is unoccupied or moves to a Plant and eats.
     */
    private void move() {
        Cell[][] cells = cell.getAdjacentCells();
        boolean moved = false;
        int stuck = 0;
        final int ten = 10;
        
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
            //if it is surrounded by impassable objects or cannot 
            //find a valid path after ten tries, give up
            } else if (stuck == ten) {  
                moved = true;
            }
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
    
    /**
     * Herbivore decides which direction to go before moving.
     * @return the Point direction decided
     */
    private Point direction() {
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
    
    /**
     * Herbivore eats the Plant when on the same cell as the plant.
     * Resets its hunger back to 0(full stomach).
     * @param cell the cell with the plant to eat
     */
    private void eat(Cell cell) {
        cell.getInhabitant().removeCell(cell);
        hunger = 0;
    }
}
