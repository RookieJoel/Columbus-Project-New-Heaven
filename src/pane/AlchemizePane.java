package pane;

import board.Resource;
import game.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import player.Player;

public class AlchemizePane extends VBox {
    private final ComboBox<Resource> fromResource;
    private final ComboBox<Resource> toResource;
    private final TextField amountField;
    private final Button alchemizeButton;
    private final Button closeButton;
    private final Label feedbackLabel;

    private Player currentPlayer;
    private final Runnable onUpdateStatusPane; // Callback to update the status pane

    public AlchemizePane(Player currentPlayer, Runnable onUpdateStatusPane) {
        super(10); // Spacing between components
        this.currentPlayer = currentPlayer;
        this.onUpdateStatusPane = onUpdateStatusPane;

        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);

        // Instructions
        Label instructionLabel = new Label("Alchemize Resources:");
        instructionLabel.setStyle("-fx-font-size: 16; -fx-text-fill: white;");
        
        Label fromResourceLabel = new Label("Give Resource:");
        fromResourceLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;");

        Label toResourceLabel = new Label("Receive Resource:");
        toResourceLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;");

        // Dropdowns for selecting resources
        fromResource = new ComboBox<>();
        fromResource.getItems().addAll(Resource.values());
        fromResource.setMaxWidth(150);
        fromResource.setPrefHeight(50);


        toResource = new ComboBox<>();
        toResource.getItems().addAll(Resource.values());
        toResource.setMaxWidth(150);
        toResource.setPrefHeight(50);

        HBox fromResourceBox = new HBox(10, fromResourceLabel, fromResource);
        fromResourceBox.setAlignment(Pos.CENTER);

        HBox toResourceBox = new HBox(10, toResourceLabel, toResource);
        toResourceBox.setAlignment(Pos.CENTER);

        // Input for the amount
        amountField = new TextField();
        amountField.setPromptText("Amount to exchange");
        amountField.setMaxWidth(150);
        amountField.setPrefHeight(50);

        
        // Button for confirming the alchemy
        alchemizeButton = new Button("Alchemize");
        alchemizeButton.setOnAction(e -> handleAlchemize());
        alchemizeButton.setPrefHeight(50);
        alchemizeButton.setPrefWidth(100);

        // Close button
        closeButton = new Button("Close");
        closeButton.setOnAction(e -> this.setVisible(false)); // Hide the pane when clicked
        closeButton.setPrefHeight(50);
        closeButton.setPrefWidth(100);

        
        // Feedback label
        feedbackLabel = new Label();
        feedbackLabel.setStyle("-fx-text-fill: lime;");

        // Add all elements to the pane
        this.getChildren().addAll(
            instructionLabel, fromResourceBox, toResourceBox, amountField, alchemizeButton, closeButton, feedbackLabel
        );

        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-border-color: white; -fx-border-width: 2px;");

        // Initially hidden
        this.setVisible(false);
    }

    private void handleAlchemize() {
        Resource from = fromResource.getValue();
        Resource to = toResource.getValue();
        String amountText = amountField.getText();

        if (from == null || to == null || amountText.isEmpty()) {
            feedbackLabel.setText("Please select resources and enter a valid amount.");
            return;
        }

        if (from == to) {
            feedbackLabel.setText("You cannot exchange the same resource.");
            return;
        }

        try {
            int amountToReceive = Integer.parseInt(amountText);
            if (amountToReceive <= 0) {
                feedbackLabel.setText("Amount must be greater than zero.");
                return;
            }

            // Calculate the cost based on the exchange rate
            int cost = calculateExchangeCost(from, to, amountToReceive);

            if (currentPlayer.getInventory().getResource(from) < cost) {
                feedbackLabel.setText("Not enough resources for this exchange.");
                return;
            }

            // Perform the alchemy
            currentPlayer.getInventory().removeResource(from, cost);
            currentPlayer.getInventory().addResource(to, amountToReceive);
            
            GameController.getInstance().getStatusPaneForPlayer(currentPlayer).updateResources(currentPlayer);

            feedbackLabel.setText("Successfully exchanged " + cost + " " + from + " for " + amountToReceive + " " + to + ".");
            GameController.getInstance().markActionCompleted(); // Mark action as completed
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Amount must be a valid number.");
        }
    }



    private int calculateExchangeCost(Resource from, Resource to, int amountToReceive) {
        // Conversion rates based on resource rarity
        int fromRate = getResourceRate(from);
        int toRate = getResourceRate(to);

        // Formula: (amountToReceive * toRate / fromRate)
        return (int) Math.ceil((double) (amountToReceive * toRate) / fromRate);
    }

    private int getResourceRate(Resource resource) {
        return switch (resource) {
            case COPPER -> 1;
            case OIL -> 2;
            case URANIUM -> 4;
            case VIBRANIUM -> 6;
            case JOJOLIUM -> 8;
        };
    }

    public void updateCurrentPlayer(Player player) {
        this.currentPlayer = player;
        resetFields(); // Reset fields whenever the current player is updated
    }

    public void resetFields() {
        // Clear selections and reapply prompt text
        fromResource.setValue(null);
        fromResource.setPromptText("Give Resource");

        toResource.setValue(null);
        toResource.setPromptText("Receive Resource");

        // Clear the amount field and feedback label
        amountField.clear();
        feedbackLabel.setText(""); // Clear feedback
    }

    
    public void clearFeedback() {
        feedbackLabel.setText("");
    }



}