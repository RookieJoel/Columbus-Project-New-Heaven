package view;

import board.Hexagon;  // นำเข้า Hexagon จาก package board
import game.Dice;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

        // Dice layout
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
                long duration = 1500;

                while (System.currentTimeMillis() - startTime < duration) {
                    javafx.application.Platform.runLater(() -> {
                        dice1.roll();
                        highlightRandomHexagon(); // Randomly highlight a hexagon
                    });

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                javafx.application.Platform.runLater(() -> {
                    resetHexagonBorders(); // Reset all hexagon borders
                    int finalRoll1 = dice1.roll();
                    int sum = finalRoll1;
                    highlightHexagonBySum(sum);
                    highlightHexagonBySum(sum + 10);
                });
            }).start();
        });

        // Back to Main Menu button
        Button backButton = new Button("Back to Main Menu");
        backButton.setFont(Font.font(20));
        backButton.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu();
            Stage mainStage = new Stage();
            try {
                mainMenu.start(mainStage); // Open main menu in a new stage
                stage.close(); // Close the current game stage
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Layout for the dice and roll button on the left
        VBox bottomLeftContainer = new VBox(15, diceBox, rollButton);
        bottomLeftContainer.setPadding(new Insets(20));
        bottomLeftContainer.setAlignment(Pos.BOTTOM_LEFT);

        // Layout for the back button on the right
        VBox bottomRightContainer = new VBox(backButton);
        bottomRightContainer.setPadding(new Insets(20));
        bottomRightContainer.setAlignment(Pos.BOTTOM_RIGHT);

        // Main layout to arrange everything
        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(hexagonsGroup);
        mainLayout.setLeft(bottomLeftContainer); // Align dice and roll button on the bottom left
        mainLayout.setRight(bottomRightContainer); // Align back button on the bottom right

        // StackPane to hold the background and main layout
        StackPane root = new StackPane(bg, mainLayout);

        // Create Scene and show stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Columbus Project");
        stage.setFullScreen(true);
        stage.show();
    }

    private Color getRandomColor() {
        return COLORS.get(random.nextInt(COLORS.size()));
    }

    private int getUniqueNumber() {
        return uniqueNumbers.remove(0);
    }

    private List<Integer> generateUniqueNumbers(int start, int end) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (i != 10) numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    private void highlightRandomHexagon() {
        resetHexagonBorders();
        Hexagon randomHexagon = hexagons.get(random.nextInt(hexagons.size()));
        randomHexagon.highlightBorder();
    }

    private void resetHexagonBorders() {
        for (Hexagon hexagon : hexagons) {
            hexagon.resetBorder();
        }
    }

    private void highlightHexagonBySum(int sum) {
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
