package view;

import board.Hexagon;  // นำเข้า Hexagon จาก package board
import game.Dice;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameGUI extends Application {

    private static final List<Color> COLORS = Arrays.asList(
            Color.CADETBLUE, Color.MEDIUMPURPLE, Color.MEDIUMSEAGREEN, Color.CORNFLOWERBLUE,
            Color.DARKGOLDENROD, Color.ORANGE, Color.SKYBLUE, Color.INDIANRED, Color.LIGHTGREEN
    );

    private final Random random = new Random();
    private List<Integer> uniqueNumbers;
    private List<Hexagon> hexagons;

    @Override
    public void start(Stage stage) {
        double HexagonRadius = 100;

        // Load the dice rolling sound effect
        String audioPath = ClassLoader.getSystemResource("sounds/DrumRoll.mp3").toString();
        AudioClip diceSound = new AudioClip(audioPath);

        // Generate unique numbers from 1 to 19
        uniqueNumbers = generateUniqueNumbers(1, 19);
        hexagons = new ArrayList<>();

        // Background Image
        String imagePath = ClassLoader.getSystemResource("images/Space.png").toString();
        ImageView bg = new ImageView(new Image(imagePath));
        bg.setPreserveRatio(false);
        bg.fitWidthProperty().bind(stage.widthProperty());
        bg.fitHeightProperty().bind(stage.heightProperty());

        // Create hexagons with random colors and unique numbers
        Group hexagonsGroup = new Group();
        Hexagon hexagon1 = new Hexagon(HexagonRadius, getRandomColor(), 10);
        hexagons.add(hexagon1);
        
        Hexagon hexagon2 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon2.setTranslateY(hexagon1.getOffsetY() * 2);
        hexagons.add(hexagon2);

        Hexagon hexagon3 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon3.setTranslateY(-hexagon1.getOffsetY() * 2);
        hexagons.add(hexagon3);

        Hexagon hexagon4 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon4.setTranslateY(-hexagon1.getOffsetY());
        hexagon4.setTranslateX(hexagon1.getOffsetX());
        hexagons.add(hexagon4);

        Hexagon hexagon5 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon5.setTranslateY(-hexagon1.getOffsetY());
        hexagon5.setTranslateX(-hexagon1.getOffsetX());
        hexagons.add(hexagon5);

        Hexagon hexagon6 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon6.setTranslateY(hexagon1.getOffsetY());
        hexagon6.setTranslateX(-hexagon1.getOffsetX());
        hexagons.add(hexagon6);

        Hexagon hexagon7 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon7.setTranslateY(hexagon1.getOffsetY());
        hexagon7.setTranslateX(hexagon1.getOffsetX());
        hexagons.add(hexagon7);

        Hexagon hexagon8 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon8.setTranslateY(hexagon1.getOffsetY() * 4);
        hexagons.add(hexagon8);

        Hexagon hexagon9 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon9.setTranslateY(hexagon1.getOffsetY() * 2);
        hexagon9.setTranslateX(hexagon1.getOffsetX() * 2);
        hexagons.add(hexagon9);

        Hexagon hexagon10 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon10.setTranslateY(-hexagon1.getOffsetY() * 2);
        hexagon10.setTranslateX(hexagon1.getOffsetX() * 2);
        hexagons.add(hexagon10);

        Hexagon hexagon11 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon11.setTranslateY(hexagon1.getOffsetY() * 2);
        hexagon11.setTranslateX(-hexagon1.getOffsetX() * 2);
        hexagons.add(hexagon11);

        Hexagon hexagon12 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon12.setTranslateY(-hexagon1.getOffsetY() * 2);
        hexagon12.setTranslateX(-hexagon1.getOffsetX() * 2);
        hexagons.add(hexagon12);

        Hexagon hexagon13 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon13.setTranslateY(-hexagon1.getOffsetY() * 4);
        hexagons.add(hexagon13);

        Hexagon hexagon14 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon14.setTranslateX(hexagon1.getOffsetX() * 2);
        hexagons.add(hexagon14);

        Hexagon hexagon15 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon15.setTranslateX(-hexagon1.getOffsetX() * 2);
        hexagons.add(hexagon15);

        Hexagon hexagon16 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon16.setTranslateY(-hexagon1.getOffsetY() * 3);
        hexagon16.setTranslateX(hexagon1.getOffsetX());
        hexagons.add(hexagon16);

        Hexagon hexagon17 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon17.setTranslateY(hexagon1.getOffsetY() * 3);
        hexagon17.setTranslateX(-hexagon1.getOffsetX());
        hexagons.add(hexagon17);

        Hexagon hexagon18 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon18.setTranslateY(hexagon1.getOffsetY() * 3);
        hexagon18.setTranslateX(hexagon1.getOffsetX());
        hexagons.add(hexagon18);

        Hexagon hexagon19 = new Hexagon(HexagonRadius, getRandomColor(), getUniqueNumber());
        hexagon19.setTranslateY(-hexagon1.getOffsetY() * 3);
        hexagon19.setTranslateX(-hexagon1.getOffsetX());
        hexagons.add(hexagon19);

        hexagonsGroup.getChildren().addAll(hexagons);

        // Dice and button layout
        Dice dice1 = new Dice();
        HBox diceBox = new HBox(15, dice1.getDicePane());
        diceBox.setPadding(new Insets(10));
        // Roll button
        Button rollButton = new Button("Let's Rock n Roll!");
        rollButton.setFont(Font.font(20));
        rollButton.setOnAction(e -> {
            diceSound.play(); // Play dice roll sound

            // Create a new thread to roll the dice continuously and randomly highlight hexagons for 5 seconds
            new Thread(() -> {
                long startTime = System.currentTimeMillis();
                long duration = 1500; // Roll and highlight for 5 seconds

                while (System.currentTimeMillis() - startTime < duration) {
                    // Run dice roll and hexagon highlight update on the JavaFX Application Thread
                    javafx.application.Platform.runLater(() -> {
                        dice1.roll();
                        highlightRandomHexagon(); // Randomly highlight a hexagon
                    });

                    // Delay between updates to make it look like a rolling and flashing effect
                    try {
                        Thread.sleep(100); // Delay for 100 ms between each update
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                // After 5 seconds, stop rolling and calculate the result
                javafx.application.Platform.runLater(() -> {
                    resetHexagonBorders(); // Reset all hexagon borders
                    int finalRoll1 = dice1.roll();
                    int sum = finalRoll1;
                    highlightHexagonBySum(sum); // Highlight the hexagon with the final result
                    highlightHexagonBySum(sum+10);
                });
            }).start();
        });

        // Dice and button container
        VBox bottomContainer = new VBox(15, diceBox, rollButton);
        bottomContainer.setAlignment(Pos.BOTTOM_LEFT);
        bottomContainer.setPadding(new Insets(20, 20, 50, 20));

        // Create a StackPane to layer the background, hexagons, and dice/button container
        StackPane mainPane = new StackPane(bg, hexagonsGroup, bottomContainer);

        // Create Scene and show stage
        var scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.setTitle("Columbus Project");
        stage.setMaximized(true); // Maximize window to cover the screen
        stage.show();
    }

    private Color getRandomColor() {
        return COLORS.get(random.nextInt(COLORS.size()));
    }

    private int getUniqueNumber() {
        return uniqueNumbers.remove(0); // Retrieve and remove the first number
    }

    private List<Integer> generateUniqueNumbers(int start, int end) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = start; i <= end; i++) {
        	if(i != 10) numbers.add(i);
        }
        Collections.shuffle(numbers); // Randomize the order
        return numbers;
    }

    private void highlightRandomHexagon() {
        resetHexagonBorders(); // Reset all hexagon borders

        // Randomly select a hexagon to highlight
        Hexagon randomHexagon = hexagons.get(random.nextInt(hexagons.size()));
        randomHexagon.highlightBorder();
    }

    private void resetHexagonBorders() {
        // Reset all hexagons' borders to default
        for (Hexagon hexagon : hexagons) {
            hexagon.resetBorder();
        }
    }

    private void highlightHexagonBySum(int sum) {
        // Find and highlight the hexagon with the matching number
        for (Hexagon hexagon : hexagons) {
            if (hexagon.getNumber() == sum) {
                hexagon.highlightBorder();
                break;
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
