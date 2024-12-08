package pane;

import game.Attack;
import game.Build;
import game.GameController;
import game.Produce;
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
        attackButton.setOnAction(e -> onAttackButtonClicked());
        produceButton = new Button("Produce");
        produceButton.setOnAction(e -> onProduceButtonClicked());
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
    
    private void onAttackButtonClicked() {
    	 if (GameController.getInstance().isTurnActionCompleted()) {
    	        System.out.println("You cannot perform another action this turn!");
    	        return;
    	    }

    	GameController.getInstance().resetHexagonBorders();
    	Attack attack = new Attack(GameController.getInstance().getCurrentPlayer(),GameController.getInstance().getHexagonPane(),null);
    	GameController.getInstance().getHexagonPane().setAttackingState(1);
    	attack.showAttackableBuilding();
    	
    }

    private void onBuildButtonClicked() {
        if (GameController.getInstance().isTurnActionCompleted()) {
            System.out.println("You cannot perform another action this turn!");
            return;
        }

        GameController.getInstance().resetHexagonBorders(); // Clear borders and state
        buildActionPane.setVisible(!buildActionPane.isVisible());
        Build build = new Build(GameController.getInstance().getCurrentPlayer(),
                                GameController.getInstance().getHexagonPane(),
                                null);
        build.canBuildHex();
    }

    
    private void onProduceButtonClicked() {
        System.out.println("Produce button clicked.");

        // Get the current player's StatusPane
        StatusPane currentStatusPane = GameController.getInstance().getCurrentStatusPane();
        if (currentStatusPane == null) {
            System.out.println("Error: Current StatusPane is null.");
            return;
        }

        // Create and execute the Produce logic
        Produce produce = new Produce(GameController.getInstance().getCurrentPlayer(), currentStatusPane);
        boolean produced = produce.execute();

        if (produced) {
            // Disable the produce button after successful production
            System.out.println("Production successful. Disabling produce button.");
            GameController.getInstance().markActionCompleted();
            } else {
            System.out.println("Production failed or no resources produced.");
        }
    }


    private void onAlchemizeButtonClicked() {
        if (GameController.getInstance().isTurnActionCompleted()) {
            System.out.println("You cannot perform another action this turn!");
            return;
        }

        // Toggle visibility of AlchemizePane
        boolean isCurrentlyVisible = alchemizePane.isVisible();
        alchemizePane.setVisible(!isCurrentlyVisible);

        if (!isCurrentlyVisible) {
            alchemizePane.resetFields();
            alchemizePane.clearFeedback();
        }
    }

    
    public void disableAllButtons() {
        buildButton.setDisable(true);
        attackButton.setDisable(true);
        produceButton.setDisable(true);
        alchemizeButton.setDisable(true);
    }

    public void enableAllButtons() {
        buildButton.setDisable(false);
        attackButton.setDisable(false);
        produceButton.setDisable(false);
        alchemizeButton.setDisable(false);
    }

    
    public void disableBuildButton() {
        buildButton.setDisable(true);
    }

    public void enableBuildButton() {
        buildButton.setDisable(false);
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