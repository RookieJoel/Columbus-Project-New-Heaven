package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ConfirmationDialog {
    private final StackPane rootPane; // Reference to the root StackPane

    public ConfirmationDialog(StackPane rootPane) {
        this.rootPane = rootPane;
    }

    public void show(String message, Runnable onConfirmAction) {
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
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Steelar-j9Vnj.otf"), 20);

        // Confirmation Text
        Text confirmationText = new Text(message);
        confirmationText.setFont(customFont);
        confirmationText.setFill(Color.LIGHTCYAN);
        confirmationText.setStroke(Color.DARKCYAN);
        confirmationText.setStrokeWidth(1);

        // Yes Button
        Button yesButton = new Button("Yes");
        yesButton.setFont(Font.font("Arial", 16));
        yesButton.setTextFill(Color.WHITE);
        yesButton.setStyle("-fx-background-color: #FF4500; -fx-background-radius: 10; -fx-padding: 10px 20px;");
        yesButton.setOnAction(e -> {
            rootPane.getChildren().remove(confirmationPane); // Remove the dialog
            onConfirmAction.run(); // Execute the confirm action
        });

        // No Button
        Button noButton = new Button("No");
        noButton.setFont(Font.font("Arial", 16));
        noButton.setTextFill(Color.WHITE);
        noButton.setStyle("-fx-background-color: #32CD32; -fx-background-radius: 10; -fx-padding: 10px 20px;");
        noButton.setOnAction(e -> rootPane.getChildren().remove(confirmationPane)); // Close the dialog

        // Layout for buttons
        VBox buttonLayout = new VBox(20, yesButton, noButton);
        buttonLayout.setAlignment(Pos.CENTER);

        // Layout for content
        VBox contentLayout = new VBox(20, confirmationText, buttonLayout);
        contentLayout.setAlignment(Pos.CENTER);

        // Add background and content to the confirmation pane
        confirmationPane.getChildren().addAll(background, contentLayout);

        // Add the confirmation pane to the root pane
        rootPane.getChildren().add(confirmationPane);
    }
}
