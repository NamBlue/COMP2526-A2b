package ca.bcit.comp2526.a2b;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

/**
 * MouseListener class for GameFrame.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public class TurnListener extends MouseAdapter{
    private final GameFrame gameframe;
    private final Timer timer;
    private final int delay = 700;
    
    /**
     * Constructor for objects of type TurnListener.
     * 
     * @param frame the GameFrame to interact with.
     */
    public TurnListener(GameFrame frame) {
        if (frame == null) {
            throw new IllegalArgumentException(
                    "Parameter cannot be null");
        }
        gameframe = frame;
        timer = new Timer(delay, new TimeListener());
    }
    
    /**
     * Moves time within the World by one turn with each mouse click.
     * @param click the mouse click event to listen to.
     */
    public void mouseClicked(MouseEvent click) {
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }
    
    public class TimeListener implements ActionListener {        
        /**
         * Increments the timer whenever the
         * event is received.
         * @param e increments the time when event is passed
         */
        public void actionPerformed(ActionEvent event) {
            gameframe.takeTurn();
        }
    }   
}
