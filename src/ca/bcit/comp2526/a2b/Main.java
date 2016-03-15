package ca.bcit.comp2526.a2b;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Main.
 * @author BCIT, Jia Qi Lee
 * @version 2.0
 */
public final class Main {
    private static final Toolkit TOOLKIT;

    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }

    /**
     * The main method, entry point of the JVM.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(final String[] args) {
        final GameFrame frame;
        final World     world;
        final int worldSize = 25;

        world = new World(worldSize, worldSize);
        world.init();
        frame = new GameFrame(world);
        position(frame);
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Sets size and position of the GameFrame on screen.
     * @param frame the target GameFrame position
     */
    private static void position(final GameFrame frame) {
        final Dimension size;
        final float percent = 0.90f;

        size = calculateScreenArea(percent, percent);
        frame.setSize(size);
        frame.setLocation(centreOnScreen(size));
    }

    /**
     * Centers the GameFrame on the screen.
     * @param size the size of the screen displayed on
     */
    public static Point centreOnScreen(final Dimension size) {
        final Dimension screenSize;
        final int two = 2;

        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }

        screenSize = TOOLKIT.getScreenSize();

        return (new Point((screenSize.width - size.width) / two,
                          (screenSize.height - size.height) / two));
    }

    /**
     * Calculates the screen area.
     * @param widthPercent the width of the screen in percentage
     * @param heightPercent the height of the screen in percentage
     */
    public static Dimension calculateScreenArea(final float widthPercent,
                                                final float heightPercent) {
        final Dimension screenSize;
        final Dimension area;
        final int       width;
        final int       height;
        final int       size;
        final float hundred = 100.0f;

        if ((widthPercent <= 0.0f) || (widthPercent > hundred)) {
            throw new IllegalArgumentException("widthPercent cannot be " 
                            + "<= 0 or > 100 - got: " + widthPercent);
        }

        if ((heightPercent <= 0.0f) || (heightPercent > hundred)) {
            throw new IllegalArgumentException("heightPercent cannot be " 
                            + "<= 0 or > 100 - got: " + heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width      = (int)(screenSize.width * widthPercent);
        height     = (int)(screenSize.height * heightPercent);
        size       = Math.min(width, height);
        area       = new Dimension(size, size);

        return (area);
    }
}
