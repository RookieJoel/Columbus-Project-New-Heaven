package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Application {

    private StackPane rootPane; // Root pane to manage multiple layers

    @Override
    public void start(Stage primaryStage) {
        // Set the background image
        String imagePath = ClassLoader.getSystemResource("images/new.png").toString();
        ImageView bg = new ImageView(new Image(imagePath));
        bg.fitWidthProperty().bind(primaryStage.widthProperty());
        bg.fitHeightProperty().bind(primaryStage.heightProperty());
        bg.setPreserveRatio(false);

        // Load custom font
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/KnightWarrior-w16n8.otf"), 100);

        // Game Title
        Text title = new Text("Columbus Project : New Heaven");
        title.setFont(customFont);
        title.setFill(Color.HOTPINK);
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(2);
        title.setTranslateY(-250);

        // Create main buttons
        Button startButton = createMainButton("START");
        Button howToPlayButton = createSecondaryButton("How to Play");
        Button exitButton = createSecondaryButton("EXIT");

        // Button actions
        startButton.setOnAction(e -> openGame(primaryStage));
        howToPlayButton.setOnAction(e -> {
            Help help = new Help(rootPane); // Pass rootPane to Help
            help.showHowToPlayPane(); // Show HowToPlay Pane
        });
        exitButton.setOnAction(e -> primaryStage.close());

        // Arrange buttons horizontally with main button in the center
        HBox buttonLayout = new HBox(40, howToPlayButton, startButton, exitButton);
        buttonLayout.setAlignment(Pos.CENTER);

        // Main layout with background, title, buttons
        VBox mainLayout = new VBox(30, title, buttonLayout);
        mainLayout.setAlignment(Pos.CENTER);

        // Root Pane
        rootPane = new StackPane(bg, mainLayout); // StackPane for layering
        Scene mainScene = new Scene(rootPane, 800, 600);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Columbus Project");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private Button createMainButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", 30));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #2E8B57; -fx-background-radius: 15; -fx-padding: 10px 20px;");
        button.setPrefWidth(220);
        return button;
    }

    private Button createSecondaryButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", 20));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #1E90FF; -fx-background-radius: 10; -fx-padding: 8px 15px;");
        button.setPrefWidth(150);
        return button;
    }

    private void openGame(Stage primaryStage) {
        GameGUI gameGUI = new GameGUI();
        try {
            gameGUI.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
