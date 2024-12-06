package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ActionPane extends VBox {
    private Button buildButton;
    private Button attackButton;
    private Button produceButton;

    public ActionPane(String playerName) {
        super(10); // Spacing between buttons
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.TOP_CENTER);

        // Create buttons
        buildButton = new Button("Build");
        attackButton = new Button("Attack");
        produceButton = new Button("Produce");

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
