package images;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * Loader for image resources.
 * 
 * @author Jia Qi Lee
 * @version 1.0
 */
public class ImageLoader {
    private static ImageLoader imageLoader = new ImageLoader();
    
    /**
     * Loads the image.
     * @param name the name of the image file
     * @return the image
     */
    public static Image getImage(String name) {        
        return Toolkit.getDefaultToolkit().getImage(
                imageLoader.getClass().getResource(name));
    }
}
