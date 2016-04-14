package ca.bcit.comp2526.a2b;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * GameFrame.
 * @author  BCIT, Jia Qi Lee
 * @version 2.0
 */
public class GameFrame extends JFrame {
    private final World world;

    /**
     * Constructor for objects of type GameFrame.
     * 
     * @param world the World this GameFrame is tied to
     */
    public GameFrame(final World world) {
        this.world = world;
    }

    /**
     * Initializes the GameFrame.
     */
    public void init() {
        final int delay = 1000;
        setTitle("Assignment 2b");
        setLayout(new GridLayout(world.getRowCount(),
                                 world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row,
                                    col));
            }
        }
        addMouseListener(new TurnListener(this));
        new java.util.Timer().schedule( 
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        validate();
                    }
                }, delay);    
    }

    /**
     * Advances time within the World by one turn.
     */
    public void takeTurn() {
        world.takeTurn();
        repaint();
        validate();
    }
}