package game;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Random;

public class Dice {
    private final StackPane dicePane;
    private final Text diceValue;
    private final Random random;

    public Dice() {
        random = new Random();
        dicePane = new StackPane();

        // Create a square dice with rounded corners
        Rectangle diceFace = new Rectangle(80, 80);
        diceFace.setFill(Color.WHITE);
        diceFace.setStroke(Color.BLACK);
        diceFace.setArcWidth(15);
        diceFace.setArcHeight(15);

        diceValue = new Text("1");
        diceValue.setFont(Font.font(30));

        dicePane.getChildren().addAll(diceFace, diceValue);
    }

    // Method to roll the dice and get a random value between 1 and 6
    public int roll() {
        int roll = random.nextInt(6) + 1;
        diceValue.setText(String.valueOf(roll));
        return roll;
    }

    // Method to return the StackPane for this dice
    public StackPane getDicePane() {
        return dicePane;
    }
}
