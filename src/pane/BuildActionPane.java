package pane;

import board.Hexagon;
import building.*;
import game.Build;
import game.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class BuildActionPane extends VBox {

    private Button factoryButton;
    private Button militaryCampButton;
    private Button missileFortressButton;
    private Button quarryButton;
    private Build build;

    public BuildActionPane(Build build) {
        super(10); // Spacing between buttons
        this.build = build;
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setTranslateY(20);

        // Initialize buttons
        factoryButton = new Button("Factory");
        militaryCampButton = new Button("Military Camp");
        missileFortressButton = new Button("Missile Fortress");
        quarryButton = new Button("Quarry");

        // Style buttons
        factoryButton.setPrefWidth(150);
        militaryCampButton.setPrefWidth(150);
        missileFortressButton.setPrefWidth(150);
        quarryButton.setPrefWidth(150);

        // Add buttons to the pane
        this.getChildren().addAll(factoryButton, militaryCampButton, missileFortressButton, quarryButton);

        // Initially hidden
        this.setVisible(false);

        // Add action listeners
        factoryButton.setOnAction(e -> handleBuild(new Factory(null, GameController.getInstance().getCurrentPlayer())));
        militaryCampButton.setOnAction(e -> handleBuild(new MilitaryCamp(null, GameController.getInstance().getCurrentPlayer())));
        missileFortressButton.setOnAction(e -> handleBuild(new MissileFortress(null, GameController.getInstance().getCurrentPlayer())));
        quarryButton.setOnAction(e -> handleBuild(new Quarry(null, GameController.getInstance().getCurrentPlayer())));
    }

    private void handleBuild(Building building) {
        Hexagon selectedHexagon = GameController.getInstance().getHexagonPane().getSeletedHexagon();

        if (selectedHexagon == null) {
            System.out.println("Error: No hexagon selected for building!");
            return; // Prevent further execution if no hexagon is selected
        }

        if (build.attemptBuild(selectedHexagon, building)) {
            disableButtons(); // Disable all buttons after successful build
            GameController.getInstance().markActionCompleted(); // Mark the action as completed        
            }
    }


    public void disableButtons() {
        factoryButton.setDisable(true);
        militaryCampButton.setDisable(true);
        missileFortressButton.setDisable(true);
        quarryButton.setDisable(true);
    }

    public void enableButtons() {
        factoryButton.setDisable(false);
        militaryCampButton.setDisable(false);
        missileFortressButton.setDisable(false);
        quarryButton.setDisable(false);
    }

    public void updateButtonStates() {
        if (build.isHasBuilt()) {
            disableButtons(); // Disable if already built
        } else {
            // Enable/disable based on resources
            factoryButton.setDisable(!build.canAffordBuilding(new Factory(null, GameController.getInstance().getCurrentPlayer())));
            militaryCampButton.setDisable(!build.canAffordBuilding(new MilitaryCamp(null, GameController.getInstance().getCurrentPlayer())));
            missileFortressButton.setDisable(!build.canAffordBuilding(new MissileFortress(null, GameController.getInstance().getCurrentPlayer())));
            quarryButton.setDisable(!build.canAffordBuilding(new Quarry(null, GameController.getInstance().getCurrentPlayer())));
        }
    }
}
