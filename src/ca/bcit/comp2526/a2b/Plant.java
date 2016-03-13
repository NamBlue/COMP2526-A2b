package ca.bcit.comp2526.a2b;

import images.ImageLoader;

import java.awt.Image;
import java.util.Random;

/**
 * The Plant, represented as a green Cell.
 * 
 * @author Jia Qi Lee
 * @version 2.0
 */
public class Plant extends Inhabitant {
    private int age;
    public static Image plant = ImageLoader.getImage("plant.png");
    public static Image plant1 = ImageLoader.getImage("plant1.png");
    //RGB values of the color of the Plant
    private static final int red = 19;
    private static final int green = 237;
    private static final int blue = 12;
        
    /**
     * Constructor for objects of type Plant.
     * 
     * @param location the cell to instantiate this Plant on
     */
    public Plant(Cell location) {
        super(location, red, green, blue);
        Random gen = new Random();
        switch (gen.nextInt(1)) {
          case 0:
              setImage(plant);
              break;
          case 1:
              setImage(plant1);
              break;
          default:
              
        }
        age = 0;
        revalidate();
    }
    
    /**
     * Reserved for future implementation (reproduction growth etc). 
     */
    public void takeTurn() {
        final int old = 10;
        
        if (!turnTaken) {
            if (age == old) {
                die();
            } else {
                age ++;
            }
            turnTaken = true;
        }
    }
}
