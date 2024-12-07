package pane;

import game.Dice;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BottomPane extends HBox {
    private final Dice dice;

    public BottomPane(Runnable highlightRandomHexagonAction, 
                      Runnable resetHexagonBordersAction, 
                      BiConsumer<Integer, Integer> highlightHexagonsAndUpdateResourcesAction, 
                      Runnable openMainMenuAction) {
        super(20); // Spacing between children
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.BOTTOM_CENTER);

        // Load the dice rolling sound effect
        String audioPath = ClassLoader.getSystemResource("sounds/DrumRoll.mp3").toString();
        AudioClip diceSound = new AudioClip(audioPath);

        // Dice Box
        dice = new Dice();
        VBox diceBox = new VBox(15, dice.getDicePane());
        diceBox.setAlignment(Pos.CENTER_LEFT);

        // Roll Button
        Button rollButton = new Button("Let's Rock n Roll!");
        rollButton.setFont(Font.font(20));
        rollButton.setOnAction(e -> {
            diceSound.play(); // Play dice roll sound

            // Create a new thread to roll the dice continuously and randomly highlight hexagons
            new Thread(() -> {
                long startTime = System.currentTimeMillis();
                long duration = 1500; // Roll and highlight for 1.5 seconds

                while (System.currentTimeMillis() - startTime < duration) {
                    // Run dice roll and hexagon highlight update on the JavaFX Application Thread
                    Platform.runLater(() -> {
                        dice.roll();
                        rollButton.setDisable(true);
                        highlightRandomHexagonAction.run(); // Randomly highlight a hexagon
                    });

                    // Delay between updates to make it look like a rolling and flashing effect
                    try {
                        Thread.sleep(100); // Delay for 100 ms between each update
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                // After 1.5 seconds, stop rolling and calculate the result
                Platform.runLater(() -> {
                    resetHexagonBordersAction.run(); // Reset all hexagon borders
                    rollButton.setDisable(false);

                    int rollResult = dice.roll();
                    int tile1 = rollResult;        // First rolled tile
                    int tile2 = rollResult + 10;  // Second rolled tile

                    // Highlight the two tiles and update resources
                    highlightHexagonsAndUpdateResourcesAction.accept(tile1, tile2);
                });
            }).start();
        });

        // Back Button
        Button backButton = new Button("Back to Main Menu");
        backButton.setFont(Font.font(20));
        backButton.setOnAction(e -> openMainMenuAction.run());

        // Left Container: Dice and Roll Button
        VBox bottomLeftContainer = new VBox(10, dice.getDicePane(), rollButton);
        bottomLeftContainer.setPadding(new Insets(10));
        bottomLeftContainer.setAlignment(Pos.CENTER_LEFT);

        // Right Container: Back Button
        VBox bottomRightContainer = new VBox(10, backButton);
        bottomRightContainer.setPadding(new Insets(10));
        bottomRightContainer.setAlignment(Pos.CENTER_RIGHT);

        // Spacer to separate left and right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        // Add components to the HBox
        this.getChildren().addAll(bottomLeftContainer, spacer, bottomRightContainer);
        
        //Positioning
        bottomLeftContainer.setTranslateY(-50);
    }

    public Dice getDice() {
        return dice;
    }
}
