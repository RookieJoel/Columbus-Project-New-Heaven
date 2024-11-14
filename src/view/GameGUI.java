package view;

import board.Hexagon;  // นำเข้า Hexagon จาก package board
import game.Dice;
import board.Resource;

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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.media.AudioClip;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameGUI extends Application {


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
        Hexagon mainhexagon = new Hexagon(HexagonRadius,10,Resource.JOJOLIUM);
//        mainhexagon.setResource();
        hexagons.add(mainhexagon);
        int x = 0;
        int n = 4;
        for(int y = -3; y < 4;y++) {
        	if(y == 1 || y == -1)x =1;
        	if(y == 2 || y == -2)x =2;
        	if(y == 3 || y == -3)x =1;
        	if(y == 0)x =2;
        		
        	Hexagon hexagon = new Hexagon(HexagonRadius, getUniqueNumber(),Resource.randomResource());
        	hexagon.setTranslateY(mainhexagon.getOffsetY()*y);
            hexagon.setTranslateX(mainhexagon.getOffsetX()*x);
            hexagons.add(hexagon);
            
            Hexagon ahexagon = new Hexagon(HexagonRadius,getUniqueNumber(),Resource.randomResource());
            ahexagon.setTranslateY(mainhexagon.getOffsetY()*y);
            ahexagon.setTranslateX(mainhexagon.getOffsetX()*-x);
            hexagons.add(ahexagon);    
        }
        while(n!=0) {
        	int y = 0;
        	x = 0;
        	if(n%2==0)y = n;
        	if(n%2==1)y = -n-1;
        	Hexagon hexagon = new Hexagon(HexagonRadius,getUniqueNumber(),Resource.randomResource());
        	hexagon.setTranslateY(mainhexagon.getOffsetY()*y);
            hexagon.setTranslateX(mainhexagon.getOffsetX()*x);
            hexagons.add(hexagon);
            n--;
        }

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
                    highlightHexagon(sum); // Highlight the hexagon with the final result
                    highlightHexagon(sum+10);
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
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Columbus Project");
        stage.setFullScreen(true); // Maximize window to cover the screen
        stage.show();
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

    private void highlightHexagon(int sum) {
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