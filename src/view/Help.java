package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;

public class Help {
    private final StackPane rootPane; // Reference to root StackPane

    public Help(StackPane rootPane) {
        this.rootPane = rootPane; // Receive the root pane for displaying the HowToPlay pane
    }

    public void showHowToPlayPane() {
        // Create the HowToPlay Pane
        StackPane howToPlayPane = new StackPane();
        howToPlayPane.setPadding(new Insets(20));
        howToPlayPane.setAlignment(Pos.CENTER);

        // Add a background with rounded corners
        Rectangle background = new Rectangle(600, 400); // Width x Height
        background.setArcWidth(30); // Rounded corners
        background.setArcHeight(30);
        background.setFill(Color.DARKSLATEBLUE); // Background color
        background.setStroke(Color.LIGHTGRAY); // Border color
        background.setStrokeWidth(5); // Border thickness

        // Load custom font
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Steelar-j9Vnj.otf"), 30);

        // HowToPlay Text
        Text howToPlayText = new Text(
            "How to Play:\n" +
            "1. กดปุ่ม START เพื่อเริ่มเกม\n" +
            "2. กดปุ่ม Roll เพื่อทอยลูกเต๋า\n" +
            "3. ลูกเต๋าจะแสดงผลคะแนน\n" +
            "4. คะแนนจะถูกคำนวณและแสดงผล\n\n" +
            "เล่นให้สนุก!"
        );
        howToPlayText.setFont(customFont);
        howToPlayText.setFill(Color.LIGHTCYAN);
        howToPlayText.setStroke(Color.DARKCYAN);
        howToPlayText.setStrokeWidth(1);

        // Close Button
        Button closeButton = new Button("Close");
        closeButton.setFont(Font.font("Arial", 20));
        closeButton.setTextFill(Color.WHITE);
        closeButton.setStyle("-fx-background-color: #FF4500; -fx-background-radius: 10; -fx-padding: 10px 20px;");
        closeButton.setOnAction(e -> rootPane.getChildren().remove(howToPlayPane)); // Remove the pane when closed

        // Layout for the content
        VBox contentLayout = new VBox(20, howToPlayText, closeButton);
        contentLayout.setAlignment(Pos.CENTER);

        // Add background and content to HowToPlay Pane
        howToPlayPane.getChildren().addAll(background, contentLayout);

        // Add HowToPlay Pane to the root pane
        rootPane.getChildren().add(howToPlayPane);
    }
}
