package pane;

import game.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ActionPane extends VBox {
    private Button buildButton;
    private Button attackButton;
    private Button produceButton;
    private Button alchemizeButton; // New button for Alchemize
    private BuildActionPane buildActionPane;
    private AlchemizePane alchemizePane; // Reference to AlchemizePane

    public ActionPane(String playerName, GameController gameController, BuildActionPane buildActionPane, AlchemizePane alchemizePane) {
        super(10); // Spacing between buttons
        this.buildActionPane = buildActionPane;
        this.alchemizePane = alchemizePane;

        this.setPadding(new Insets(10));
        this.setAlignment(Pos.TOP_CENTER);

        // Create buttons
        buildButton = new Button("Build");
        buildButton.setOnAction(e -> onBuildButtonClicked());
        attackButton = new Button("Attack");
        attackButton.setOnAction(e -> hideBuildPane());
        produceButton = new Button("Produce");
        produceButton.setOnAction(e -> hideBuildPane());
        alchemizeButton = new Button("Alchemize");
        alchemizeButton.setOnAction(e -> onAlchemizeButtonClicked());

        // Style buttons
        buildButton.setPrefWidth(100);
        attackButton.setPrefWidth(100);
        produceButton.setPrefWidth(100);
        alchemizeButton.setPrefWidth(100);

        buildButton.setPrefHeight(30);
        attackButton.setPrefHeight(30);
        produceButton.setPrefHeight(30);
        alchemizeButton.setPrefHeight(30);

        // Add buttons to the pane
        this.getChildren().addAll(buildButton, attackButton, produceButton, alchemizeButton);

        this.setTranslateY(30);
    }

    private void onBuildButtonClicked() {
        // Toggle BuildActionPane visibility
        buildActionPane.setVisible(!buildActionPane.isVisible());
    }

    private void onAlchemizeButtonClicked() {
        // Toggle visibility of AlchemizePane
        boolean isCurrentlyVisible = alchemizePane.isVisible();
        if (!isCurrentlyVisible) {
            alchemizePane.resetFields(); // Reset fields before showing the pane
            alchemizePane.clearFeedback(); // Ensure feedback is cleared
        }
        alchemizePane.setVisible(!isCurrentlyVisible);

        // Hide other panes when AlchemizePane is shown
        if (!isCurrentlyVisible) {
            buildActionPane.setVisible(false);
        }
    }

    private void hideBuildPane() {
        // Hide BuildActionPane when other buttons are clicked
        buildActionPane.setVisible(false);
    }

    public Button getBuildButton() {
        return buildButton;
    }

    public Button getAttackButton() {
        return attackButton;
    }

    public Button getProduceButton() {
        return produceButton;
    }

    public Button getAlchemizeButton() {
        return alchemizeButton;
    }
}
