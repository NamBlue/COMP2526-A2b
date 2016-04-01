package images;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

/**
 * Loader for image resources.
 * 
 * @author Jia Qi Lee
 * @version 1.0
 */
public class ImageLoader {
    private static ImageLoader imageLoader = new ImageLoader();
    //All URI for images are added here
    private static String[] plants = {"plant.png", "plant1.png", "plant2.png",
        "plant3.png"};
    private static String[] omni = {"omni.png", "omni1.png", "omni2.png"};
    private static String[] carni = {"carni.png", "carni1.png", "carni2.png"};
    private static String[] herbi = {"herbi.png" , "herbi1.png", "herbi2.png",
        "herbi3.png"};
    private static String[] cell = {"cell.png", "cell1.png" , "cell2.png" ,
        "cell3.png", "cell3.png", "cell3.png", "cell3.png", "cell.png",
        "cell1.png", "cell3.png", "cell3.png"};
    
    /**
     * Loads the image.
     * @param name the URI of the image file
     * @return the image
     */
    public static Image getImage(String name) {        
        return Toolkit.getDefaultToolkit().getImage(
                imageLoader.getClass().getResource(name));       
    }
    
    /**
     * Loads a random Plant Image chosen from the array of URI's.
     * @return the image randomly selected
     */
    public static Image getPlant() {
        Random gen = new Random();
        int seed = gen.nextInt(plants.length);
        
        return Toolkit.getDefaultToolkit().getImage(
                imageLoader.getClass().getResource(plants[seed]));
    }
    
    /**
     * Loads a random Herbivore Image chosen from the array of URI's.
     * @return the image randomly selected
     */
    public static Image getHerbi() {
        Random gen = new Random();
        int seed = gen.nextInt(herbi.length);
        
        return Toolkit.getDefaultToolkit().getImage(
                imageLoader.getClass().getResource(herbi[seed]));
    }
    
    /**
     * Loads a random Carnivore Image chosen from the array of URI's.
     * @return the image randomly selected
     */
    public static Image getCarni() {
        Random gen = new Random();
        int seed = gen.nextInt(carni.length);
        
        return Toolkit.getDefaultToolkit().getImage(
                imageLoader.getClass().getResource(carni[seed]));
    }
    
    /**
     * Loads a random Omnivore Image chosen from the array of URI's.
     * @return the image randomly selected
     */
    public static Image getOmni() {
        Random gen = new Random();
        int seed = gen.nextInt(omni.length);
        
        return Toolkit.getDefaultToolkit().getImage(
                imageLoader.getClass().getResource(omni[seed]));
    }
    
    /**
     * Loads a random Cell Image chosen from the array of URI's.
     * @return the image randomly selected
     */
    public static Image getSpace() {
        Random gen = new Random();
        int seed = gen.nextInt(cell.length);
        
        return Toolkit.getDefaultToolkit().getImage(
                imageLoader.getClass().getResource(cell[seed]));
    }
}
