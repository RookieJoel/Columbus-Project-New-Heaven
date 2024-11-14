package board;

import javafx.scene.image.Image;
import java.util.Random;

public enum Resource {
    VIBRANIUM("/images/vibranium.png"),
    OIL("/images/oil.png"),
    COPPER("/images/copper.png"),
    URANIUM("/images/uranium.png"),
    JOJOLIUM("/images/jojolium.png");

    private final Image image;  // Instance field to store the image
    private static final Random PRNG = new Random();

    // Constructor that initializes the image for each resource
    Resource(String imagePath) {
        // Load the image from the specified path
        this.image = new Image(getClass().getResourceAsStream(imagePath));
    }

    // Getter method to retrieve the image of the resource
    public Image getImage() {
        return image;
    }

    // Static method to get a random resource
    public static Resource randomResource() {
        Resource[] resources = values();
        int count = resources.length - 1; // Exclude JOJOLIUM from random selection
        int randomIndex = PRNG.nextInt(count);
        return resources[randomIndex];
    }
}
