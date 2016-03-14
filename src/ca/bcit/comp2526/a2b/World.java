package ca.bcit.comp2526.a2b;

import java.util.ArrayList;
import java.util.Random;

/**
 * The World built with Cells, does not wrap around the edges.
 * It is flat but nothing falls off the edge either.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public class World {
    private final int rows;
    private final int cols;
    private final Random gen;
    private Cell[][] map;
    private int time; //Stores the amount of time(turns) that has passed for this world
    private boolean bc; //True for year in BC, false for year in AD    
    
    /**
     * Constructor for objects of type World.
     * 
     * @param rows the amount of rows of Cells of the World
     * @param cols the amount of columns of Cells of the World
     */
    public World(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative or zero");
        }
        this.rows = rows;
        this.cols = cols;
        map = new Cell[rows][cols];
        time = 5000;
        bc = true;
        gen = new Random();
        System.out.println("Dawn of Life! This is year " 
                + time + (bc ? " BC" : " AD"));
    }
    
    /**
     * Instantiates the Cells on the World then initializes them.
     */
    public void init() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                map[row][col] = new Cell(this, row, col);
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                map[row][col].init();
            }
        }        
    }
    
    /**
     * Advances time within the World by one turn.
     * Calls the spawn method.
     */
    public void takeTurn() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                map[row][col].takeTurn();
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                map[row][col].resetTurn();
            }
        }
        spawn();
        turnCount();
    }
    
    /**
     * Creates the Inhabitants into the World based on random probability.
     */
    private void spawn() {
        Cell cell;
        final int ten = 10;
        final int twenty = 20;
        final int hundred = 100;
        
        if (gen.nextInt(hundred) < twenty) {
            cell = getRandomEmptyCell();
            
            if (cell != null) {
                Herbivore herbivore = new Herbivore(cell);
                herbivore.init();
                herbivore.revalidate();
            }
        }
        if (gen.nextInt(hundred) < twenty) {
            cell = getRandomEmptyCell();
            
            if (cell != null) {
                Plant plant = new Plant(cell);
                plant.init();
                plant.revalidate();
            }
        }
        if (gen.nextInt(hundred) < ten) {
            cell = getRandomEmptyCell();
            
            if (cell != null) {
                Carnivore carn = new Carnivore(cell);
                carn.init();
                carn.revalidate();
            }
        }
        if (gen.nextInt(hundred) < ten) {
            cell = getRandomEmptyCell();
            
            if (cell != null) {
                Omnivore omni = new Omnivore(cell);
                omni.init();
                omni.revalidate();
            }
        }
    }
    
    /**
     * Returns a random empty cell from all empty cells for spawning.
     * @return the random empty cell
     */
    public Cell getRandomEmptyCell() {
        ArrayList<Cell> cellList = getAllEmptyCells();
        Cell cell;
        int seed;
        
        if (cellList.size() == 0) {
            return null;
        } else {
            seed = gen.nextInt(cellList.size());
            cell = cellList.get(seed);
            return cell;
        }
    }
    
    /**
     * Returns all empty cells' (no Inhabitants) 
     * location as a ArrayList of Cells.
     * @return the ArrayList of the cells that are empty
     */
    private ArrayList<Cell> getAllEmptyCells() {
        ArrayList<Cell> cellList = new ArrayList<Cell>();
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (map[row][col].getInhabitant() == null) {
                    cellList.add(map[row][col]);
                }
            }
        }
        return cellList;
    }
            
    /**
     * Counts the number of time passed in the World.
     */
    private void turnCount() {
        final int hundred = 100;
        final int ten = 10;
        
        if (bc) {
            time -= hundred;
            if (time <= 0) {
                bc = false;
            }
        } else if (!bc) {
            time += ten; 
        }
        System.out.println("This is year " 
                + time + (bc ? " BC" : " AD"));
    }
    
    /**
     * Returns the number of rows in this world.
     * @return number of rows in this world.
     */
    public int getRowCount() {
        return rows;
    }
    
    /**
     * Returns the number of columns in this world.
     * @return number of columns in this world.
     */
    public int getColumnCount() {
        return cols;
    }
    
    /**
     * Returns the Cell with the location specified.
     * @param row the row of the Cell
     * @param col the column of the Cell
     * @return the Cell at that location.
     */
    public Cell getCellAt(int row, int col) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative");
        }
        return map[row][col];
    }
    
    /**
     * Returns all adjacent Cells in a 2D array, null = no Cell. 
     * Cell[1][1] is always null as it is its own Cell.
     * @param cell the Cell to get adjacent Cells from
     * @return the 2D Cell array of adjacent cells.
     */
    public Cell[][] getAdjacentCells(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException(
                    "Parameter cannot be null");
        }
        final int three = 3;
        int x1 = (int)cell.getLocation().getX();
        int y1 = (int)cell.getLocation().getY();
        Cell[][] adjCell = new Cell[three][three];
        
        checkCornerCells(y1, x1, adjCell);
        checkSideCells(y1, x1, adjCell);
        checkOtherCells(y1, x1, adjCell);
        return adjCell;
    }
    
    /**
     * Support method for getAdjacentCells, checks and stores
     * the Cells adjacent to the corner Cells.
     * @param row the y position of the Cell to check
     * @param col the x position of the Cell to check
     * @param cell the 3x3 2D array cell to store with
     */
    private void checkCornerCells(int row, int col, Cell[][] cell) {
        final int two = 2;
        final int three = 3;
        
        if (row < 0 || col < 0 || cell.length != three || cell[two].length != three) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative and cell "
                    + "array passed must be size [3][3]");
        }
        
        /* Map of 2D array for reference in y,x index format
         * 00   01     02
         * 10   CELL   12
         * 20   21     22
         */
        if (row == 0 && col == 0) { //Top left
            cell[1][two] = map[row][col + 1];
            cell[two][two] = map[row + 1][col + 1];
            cell[two][1] = map[row + 1][col];
        } else if (row == 0 && col == cols - 1) { //Top right
            cell[1][0] = map[row][col - 1];
            cell[two][0] = map[row + 1][col - 1];
            cell[two][1] = map[row + 1][col];
        } else if (row == rows - 1 && col == 0) { //Bottom left
            cell[0][1] = map[row - 1][col];
            cell[0][two] = map[row - 1][col + 1];
            cell[1][two] = map[row][col + 1];
        } else if (row == rows - 1 && col == cols - 1) { //Bottom right
            cell[0][1] = map[row - 1][col];
            cell[0][0] = map[row - 1][col - 1];
            cell[1][0] = map[row][col - 1];
        }
    }
    
    /**
     * Support method for getAdjacentCells, checks and stores
     * the Cells adjacent to the corner Cells.
     * @param row the y position of the Cell to check
     * @param col the x position of the Cell to check
     * @param cell the 3x3 2D array cell to store with
     */
    private void checkSideCells(int row, int col, Cell[][] cell) {
        final int two = 2;
        final int three = 3;
        
        if (row < 0 || col < 0 
                || cell.length != three || cell[two].length != three) {
            
            throw new IllegalArgumentException(
                    "Parameters cannot be negative and cell "
                    + "array passed must be size [3][3]");
        }
        
        if (row == 0 && (col > 0 && col < cols - 1)) { //Top side
            cell[1][two] = map[row][col + 1];
            cell[two][two] = map[row + 1][col + 1];
            cell[two][1] = map[row + 1][col];
            cell[two][0] = map[row + 1][col - 1];
            cell[1][0] = map[row][col - 1];
        } else if ((row > 0 && row < rows - 1) && col == cols - 1) { //Right side
            cell[0][1] = map[row - 1][col];
            cell[two][1] = map[row + 1][col];
            cell[two][0] = map[row + 1][col - 1];
            cell[1][0] = map[row][col - 1];
            cell[0][0] = map[row - 1][col - 1];
        } else if (row == rows - 1 && (col > 0 && col < cols - 1)) { //Bottom side
            cell[0][1] = map[row - 1][col];
            cell[0][two] = map[row - 1][col + 1];
            cell[1][two] = map[row][col + 1];
            cell[1][0] = map[row][col - 1];
            cell[0][0] = map[row - 1][col - 1];
        } else if ((row > 0 && row < rows - 1) && col == 0) { //Left side
            cell[0][1] = map[row - 1][col];
            cell[0][two] = map[row - 1][col + 1];
            cell[1][two] = map[row][col + 1];
            cell[two][two] = map[row + 1][col + 1];
            cell[two][1] = map[row + 1][col];
        }
    }
    
    /**
     * Support method for getAdjacentCells, checks and stores
     * the Cells adjacent to the corner Cells.
     * @param row the y position of the Cell to check
     * @param col the x position of the Cell to check
     * @param cell the 3x3 2D array cell to store with
     */
    private void checkOtherCells(int row, int col, Cell[][] cell) {
        final int two = 2;
        final int three = 3;
        
        if (row < 0 || col < 0 || cell.length != three || cell[two].length != three) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative and cell "
                    + "array passed must be of size [3][3]");
        }
        
        if ((row > 0 && row < rows - 1) && (col > 0 && col < cols - 1)) {
            cell[0][1] = map[row - 1][col];
            cell[0][two] = map[row - 1][col + 1];
            cell[1][two] = map[row][col + 1];
            cell[two][two] = map[row + 1][col + 1];
            cell[two][1] = map[row + 1][col];
            cell[two][0] = map[row + 1][col - 1];
            cell[1][0] = map[row][col - 1];
            cell[0][0] = map[row - 1][col - 1];
        }
    }
}
