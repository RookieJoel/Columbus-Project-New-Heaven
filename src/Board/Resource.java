package board;

import java.util.Random;
import javafx.scene.paint.Color;

public enum Resource {
    VIBRANIUM(Color.GREEN),
    OIL(Color.LIGHTGRAY),
    COPPER(Color.BROWN),
    URANIUM(Color.YELLOW),
    JOJOLIUM(Color.MAGENTA);

    private final Color color;  // Instance field to store the color
    private static final Random PRNG = new Random();

    // Constructor that initializes the color for each resource
    Resource(Color color) {
        this.color = color;
    }

    // Getter method to retrieve the color of the resource
    public Color getColor() {
        return color;
    }

    // Static method to get a random resource
    public static Resource randomResource() {
        Resource[] resources = values();  // Get all enum values

        // Count how many resources are available excluding JOJOLIUM
        int count = resources.length - 1; // Subtract one for JOJOLIUM

        // Pick a random index excluding JOJOLIUM
        int randomIndex = PRNG.nextInt(count);  // Pick a random number from 0 to count - 1

        // Adjust the index to skip JOJOLIUM
        if (randomIndex >= 4) {  // JOJOLIUM is at index 4
            randomIndex++;  // Skip JOJOLIUM by increasing the index
        }

        return resources[randomIndex];  // Return the random resource
    }
}