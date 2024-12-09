package pane;

import game.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EndGamePane {
    private final StackPane rootPane; // Reference to the root StackPane
    private MediaPlayer endGameMusicPlayer; // MediaPlayer for end game music
    private MediaPlayer mainMenuMusicPlayer; // MediaPlayer for main menu music

    public EndGamePane(StackPane rootPane) {
        this.rootPane = rootPane;
    }

    public void show(String message, Runnable onConfirmAction) {
    	
        GameController.stopAllMusic();
        // Create the confirmation dialog pane
        StackPane confirmationPane = new StackPane();
        confirmationPane.setPadding(new Insets(20));
        confirmationPane.setAlignment(Pos.CENTER);

        // Add a background with rounded corners
        Rectangle background = new Rectangle(600, 200);
        background.setArcWidth(30);
        background.setArcHeight(30);
        background.setFill(Color.DARKSLATEBLUE);
        background.setStroke(Color.LIGHTGRAY);
        background.setStrokeWidth(5);

        // Load custom font
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Steelar-j9Vnj.otf"), 45);

        // Confirmation Text
        Text confirmationText = new Text(message);
        confirmationText.setFont(customFont);
        confirmationText.setFill(Color.LIGHTCYAN);
        confirmationText.setStroke(Color.DARKCYAN);
        confirmationText.setStrokeWidth(1);

        // Play end game background music
        String endGameMusicPath = getClass().getResource("/sounds/victory.wav").toExternalForm();
        Media endGameMedia = new Media(endGameMusicPath);
        endGameMusicPlayer = new MediaPlayer(endGameMedia);
        endGameMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        endGameMusicPlayer.setVolume(0.3);
        endGameMusicPlayer.play();
       
        GameController.setEndGameMusicPlayer(endGameMusicPlayer);

        // Yes Button
        Button yesButton = new Button("Back to Main Menu");
        yesButton.setFont(Font.font("Arial", 16));
        yesButton.setTextFill(Color.WHITE);
        yesButton.setStyle("-fx-background-color: #FF4500; -fx-background-radius: 10; -fx-padding: 10px 20px;");
        yesButton.setOnAction(e -> {
            // Stop end game music
            if (endGameMusicPlayer != null) {
                endGameMusicPlayer.stop();
            }

            // Stop any other music players
            stopAllMusicPlayers();

            // Play main menu music
            playMainMenuMusic();

            // Remove the dialog and execute the confirm action
            rootPane.getChildren().remove(confirmationPane);
            onConfirmAction.run();
        });

        // Layout for buttons
        VBox buttonLayout = new VBox(20, yesButton);
        buttonLayout.setAlignment(Pos.CENTER);

        // Layout for content
        VBox contentLayout = new VBox(20, confirmationText, buttonLayout);
        contentLayout.setAlignment(Pos.CENTER);

        // Add background and content to the confirmation pane
        confirmationPane.getChildren().addAll(background, contentLayout);

        // Add the confirmation pane to the root pane
        rootPane.getChildren().add(confirmationPane);
    }

    private void playMainMenuMusic() {
        String mainMenuMusicPath = getClass().getResource("/sounds/bg.wav").toExternalForm();
        Media mainMenuMedia = new Media(mainMenuMusicPath);
        mainMenuMusicPlayer = new MediaPlayer(mainMenuMedia);
        mainMenuMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mainMenuMusicPlayer.setVolume(0.3);
        mainMenuMusicPlayer.play();
    }

    private void stopAllMusicPlayers() {
        // Stop end game music if it exists
        if (endGameMusicPlayer != null) {
            endGameMusicPlayer.stop();
        }

        // Stop main menu music if it exists (to prevent overlapping)
        if (mainMenuMusicPlayer != null) {
            mainMenuMusicPlayer.stop();
        }
    }
}
