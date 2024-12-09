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

    private static final int DICE_SIZE = 100;
    private static final int FONT_SIZE = 50;

    private final Random RAND = new Random();
    private final StackPane DICE_PANE;
    private final Text DICE_TEXT;

    public Dice() {
        // Create the dice face
        Rectangle diceFace = new Rectangle(DICE_SIZE, DICE_SIZE);
        diceFace.setFill(Color.WHITE);
        diceFace.setStroke(Color.BLACK);
        diceFace.setArcWidth(15);
        diceFace.setArcHeight(15);

        // Create the text displaying the dice value
        DICE_TEXT = new Text("1");
        DICE_TEXT.setFont(Font.font(FONT_SIZE));

        // StackPane to contain the dice face and value text
        DICE_PANE = new StackPane(diceFace, DICE_TEXT);
        DICE_PANE.setAlignment(Pos.CENTER);
    }

    // Method to roll the dice and update the displayed value
    public int roll() {
        int rollResult = RAND.nextInt(10);
        DICE_TEXT.setText(String.valueOf(rollResult));
        return rollResult;
    }

    // Method to get the StackPane for adding to the GUI
    public StackPane getDicePane() {
        return DICE_PANE;
    }
}
