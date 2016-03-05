package ca.bcit.comp2526.a2b;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * MouseListener class for GameFrame.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public class TurnListener extends MouseAdapter{
    private final GameFrame gameframe;
    
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
    }
    
    /**
     * Moves time within the World by one turn with each mouse click.
     * @param click the mouse click event to listen to.
     */
    public void mouseClicked(MouseEvent click) {
        gameframe.takeTurn();
    }
}
