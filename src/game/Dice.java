package game;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Random;

public class Dice {

    private static final int DICE_SIZE = 80;
    private static final int FONT_SIZE = 30;

    private final Random random = new Random();
    private final StackPane dicePane;
    private final Text diceValueText;

    public Dice() {
        // Create the dice face
        Rectangle diceFace = new Rectangle(DICE_SIZE, DICE_SIZE);
        diceFace.setFill(Color.WHITE);
        diceFace.setStroke(Color.BLACK);
        diceFace.setArcWidth(15);
        diceFace.setArcHeight(15);

        // Create the text displaying the dice value
        diceValueText = new Text("1");
        diceValueText.setFont(Font.font(FONT_SIZE));

        // StackPane to contain the dice face and value text
        dicePane = new StackPane(diceFace, diceValueText);
        dicePane.setAlignment(Pos.CENTER);
    }

    // Method to roll the dice and update the displayed value
    public int roll() {
        int rollResult = random.nextInt(6) + 1;
        diceValueText.setText(String.valueOf(rollResult));
        return rollResult;
    }

    // Method to get the StackPane for adding to the GUI
    public StackPane getDicePane() {
        return dicePane;
    }
}
