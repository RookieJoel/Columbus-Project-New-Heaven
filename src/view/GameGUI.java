package view;

import board.Hexagon;  // นำเข้า Hexagon จาก package board
import game.Dice;
import board.Resource;
import building.Colony;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import player.Player;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameGUI extends Application {


    private final Random random = new Random();
    private List<Integer> uniqueNumbers;
    private List<Hexagon> hexagons;
    private final static int GAPX = 10;
    private final static int GAPY = 5;
    private final static int HexagonRadius = 100;

    @Override
    public void start(Stage stage) {
    	
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
        	hexagon.setTranslateY((mainhexagon.getOffsetY()+GAPY)*y);
            hexagon.setTranslateX((mainhexagon.getOffsetX()+GAPX)*x);
            hexagons.add(hexagon);
            
            Hexagon ahexagon = new Hexagon(HexagonRadius,getUniqueNumber(),Resource.randomResource());
            ahexagon.setTranslateY((mainhexagon.getOffsetY()+GAPY)*y);
            ahexagon.setTranslateX((mainhexagon.getOffsetX()+GAPX)*-x);
            hexagons.add(ahexagon);    
        }
        while(n!=0) {
        	int y = 0;
        	x = 0;
        	if(n%2==0)y = n;
        	if(n%2==1)y = -n-1;
        	Hexagon hexagon = new Hexagon(HexagonRadius,getUniqueNumber(),Resource.randomResource());
        	hexagon.setTranslateY((mainhexagon.getOffsetY()+GAPY)*y);
            hexagon.setTranslateX((mainhexagon.getOffsetX()+GAPX)*x);
            hexagons.add(hexagon);
            n--;
        }

        hexagonsGroup.getChildren().addAll(hexagons);
        
        Hexagon leftmostHexagon = hexagons.get(0);
        Hexagon rightmostHexagon = hexagons.get(0);

        for (Hexagon hex : hexagons) {
            if (hex.getTranslateX() < leftmostHexagon.getTranslateX()) {
                leftmostHexagon = hex;
            }
            if (hex.getTranslateX() > rightmostHexagon.getTranslateX()) {
                rightmostHexagon = hex;
            }
        }
        
     // Assign colonies to players
        Colony colony1 = new Colony(leftmostHexagon);
        Colony colony2 = new Colony(rightmostHexagon);

        Player player1 = new Player(1, "Player 1", colony1);
        Player player2 = new Player(2, "Player 2", colony2);


     // Create StatusPane
        StatusPane player1Status = new StatusPane(player1);
        player1Status.setAlignment(Pos.TOP_LEFT);
        player1Status.setPadding(new Insets(10));
        
        
        StatusPane player2Status = new StatusPane(player2);
        player2Status.setAlignment(Pos.TOP_RIGHT);
        player2Status.setPadding(new Insets(10));

        
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
                    Platform.runLater(() -> {
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
                Platform.runLater(() -> {
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

        VBox bottomLeftContainer = new VBox(10, dice1.getDicePane(), rollButton);
        bottomLeftContainer.setPadding(new Insets(10));
        bottomLeftContainer.setAlignment(Pos.CENTER_LEFT);

        VBox bottomRightContainer = new VBox(10, backButton);
        bottomRightContainer.setPadding(new Insets(10));
        bottomRightContainer.setAlignment(Pos.CENTER_RIGHT);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        
        HBox bottomContainer = new HBox();
        bottomContainer.setPadding(new Insets(10));
        bottomContainer.setSpacing(20); // Add spacing between left and right sections
        bottomContainer.setAlignment(Pos.CENTER); // Center align the HBox
        bottomContainer.getChildren().addAll(bottomLeftContainer, spacer,bottomRightContainer);
        
        // Layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(player1Status); // Place Player 1's status on the left
        mainLayout.setRight(player2Status);
        mainLayout.setBottom(bottomContainer);
        mainLayout.setCenter(hexagonsGroup);
        
        //Positioning
        player1Status.setTranslateY(25);
        player2Status.setTranslateY(25);

        hexagonsGroup.setTranslateY(80);
        bottomLeftContainer.setTranslateY(-10);
        bottomRightContainer.setTranslateY(45);
        
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