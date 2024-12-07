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
    private BuildActionPane buildActionPane;

    public ActionPane(String playerName, GameController gameController, BuildActionPane buildActionPane) {
        super(10); // Spacing between buttons
        this.buildActionPane = buildActionPane;

        this.setPadding(new Insets(10));
        this.setAlignment(Pos.TOP_CENTER);

        // Create buttons
        buildButton = new Button("Build");
        buildButton.setOnAction(e -> onBuildButtonClicked());
        attackButton = new Button("Attack");
        attackButton.setOnAction(e -> hideBuildPane());
        produceButton = new Button("Produce");
        produceButton.setOnAction(e -> hideBuildPane());

        // Style buttons
        buildButton.setPrefWidth(100);
        attackButton.setPrefWidth(100);
        produceButton.setPrefWidth(100);

        buildButton.setPrefHeight(30);
        attackButton.setPrefHeight(30);
        produceButton.setPrefHeight(30);

        // Add buttons to the pane
        this.getChildren().addAll(buildButton, attackButton, produceButton);

        this.setTranslateY(30);
    }

    private void onBuildButtonClicked() {
        // Toggle BuildActionPane visibility
        buildActionPane.setVisible(!buildActionPane.isVisible());
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
}
