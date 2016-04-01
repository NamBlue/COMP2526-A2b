package ca.bcit.comp2526.a2b;

import images.ImageLoader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * The Cell. Represents a square space within a World. 
 * Can hold objects thats exists in the World. 
 * Represented by a brown Cell.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public class Cell extends JPanel {
    private final int row;
    private final int col;
    private final World world;
    private final Image image;
    private Inhabitant inhabitant;
    private Cell[][] cell;
    
    /**
     * Constructor for objects of type Cell.
     * 
     * @param world the World this Cell is placed on
     * @param row the row position of the Cell
     * @param col the column position of the Cell
     */
    public Cell(World world, int row, int col) {
        if (row < 0 || col < 0 || world == null) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative or null");
        }
        final int three = 3;
        
        this.world = world;
        this.row = row;
        this.col = col;
        image = ImageLoader.getSpace();
        cell = new Cell[three][three];
        setLayout(new GridLayout(1, 1));
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    /**
     * Initializes the Cell and places the inhabitant on the Cell randomly 
     * using the RandomGenerator class. Stores adjacent Cells from World.
     */
    public void init() {
        final int twenty = 20;
        final int forty = 40;
        final int fifty = 50;
        final int sixty = 60;
        final int hundred = 100;
        final Random gen = new Random();
        int seed = gen.nextInt(hundred);
        
        if (seed < twenty) {
            inhabitant = new Herbivore(this);
            inhabitant.init();
        } else if (seed < forty) {
            inhabitant = new Plant(this);
            inhabitant.init();
        } else if (seed < fifty) {
            inhabitant = new Carnivore(this);
            inhabitant.init();
        } else if (seed < sixty) {
            inhabitant = new Omnivore(this);
            inhabitant.init();
        } else {
            inhabitant = null;
        }
        cell = world.getAdjacentCells(this);        
    }
    
    /**
     * Draws the cell.
     * @param draw device context for the Panel to draw on
     */
    public void paintComponent(Graphics draw) {
        final int r = 108;
        final int g = 54;
        final int b = 0;
        draw.setColor(new Color(r, g, b));
        draw.fillRect(0, 0, getWidth(), getHeight());
        draw.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        repaint();
    }
    
    /**
     * Sets the Inhabitant to this Cell.
     * @param object the Inhabitant to set
     */
    public void setInhabitant(Inhabitant object) {
        if (inhabitant == null) {
            inhabitant = object;
        }   
    }
    
    /**
     * Removes the Inhabitant of this Cell.
     * @param object the Inhabitant to remove
     */
    public void removeInhabitant(Inhabitant object) {
        if (inhabitant != null) {
            inhabitant = null;
        }
    }
    
    /**
     * Returns the current Inhabitant of the Cell.
     * @return the Inhabitant of the Cell
     */
    public Inhabitant getInhabitant() {
        return inhabitant;
    }
    
    /**
     * Returns the location of the cell as a Pointer object.
     * @return the Point object of the location of the cell.
     */
    public Point getLocation() {
        Point location = new Point(col, row);
        return (location);
    }
    
    /**
     * Returns all adjacent cells in a 2D array, null = no Cell. 
     * Cell[1][1] is always null as it is its own Cell.
     * @return the 2D Cell array of adjacent cells.
     */
    public Cell[][] getAdjacentCells() {
        return cell;
    }
    
    /**
     * Returns a random empty cell from all empty adjacent cells.
     * @return the random empty cell
     */
    public Cell getRandomEmptyCell() {
        final int three = 3;
        ArrayList<Cell> cellList = new ArrayList<Cell>();
        Random gen = new Random();
        Cell emptycell;
        int seed;
        
        for (int row = 0; row < three; row++) {
            for (int col = 0; col < three; col++) {
                if (cell[row][col] != null) {
                    if (cell[row][col].getInhabitant() == null) {
                        cellList.add(cell[row][col]);
                    }
                }
            }
        }
        
        if (cellList.size() == 0) {
            return null;
        } else {
            seed = gen.nextInt(cellList.size());
            emptycell = cellList.get(seed);
            return emptycell;
        }
    }
    
    /**
     * Advances time within the Cell by one turn.
     */
    public void takeTurn() {
        if (inhabitant != null) {
            inhabitant.takeTurn();
        }
    }
    
    /**
     * Resets the Inhabitants' turn so that they 
     * are ready to take the next turn.
     */
    public void resetTurn() {
        if (inhabitant != null) {
            inhabitant.resetTurn();
        }
    }
}