package pane;

import board.Hexagon;
import building.*;
import building.interfaces.Upgradable;
import game.Build;
import game.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class BuildActionPane extends VBox {

    private Button militaryCampButton;
    private Button quarryButton;
    private Button upgradeButton;
    private Build build;
	

    public BuildActionPane(Build build) {
        super(10); // Spacing between buttons
        this.build = build;
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setTranslateY(20);

        // Initialize buttons
        militaryCampButton = new Button("Military Camp");
        quarryButton = new Button("Quarry");
        upgradeButton = new Button("Upgrade");

        // Style buttons
        militaryCampButton.setPrefWidth(150);
        quarryButton.setPrefWidth(150);
        upgradeButton.setPrefWidth(150);
        // Add buttons to the pane
        this.getChildren().addAll( quarryButton, militaryCampButton, upgradeButton);

        // Initially hidden
        this.setVisible(false);

        // Add action listeners
        quarryButton.setOnAction(e -> handleBuild(new Quarry(null, GameController.getInstance().getCurrentPlayer())));
        //factoryButton.setOnAction(e -> handleBuild(new Factory(null, GameController.getInstance().getCurrentPlayer())));
        militaryCampButton.setOnAction(e -> handleBuild(new MilitaryCamp(null, GameController.getInstance().getCurrentPlayer())));
        //missileFortressButton.setOnAction(e -> handleBuild(new MissileFortress(null, GameController.getInstance().getCurrentPlayer())));
        upgradeButton.setOnAction(e -> handleBuild(new Colony(null, null)));
    }

    private void handleBuild(Building building) {
        Hexagon selectedHexagon = GameController.getInstance().getHexagonPane().getSeletedHexagon();

        if (selectedHexagon == null) {
            System.out.println("Error: No hexagon selected for building!");
            return; // Prevent further execution if no hexagon is selected
        }
        if(selectedHexagon.getBuilding() instanceof Upgradable && building instanceof Colony) {
        	Upgradable uBuilding = ((Upgradable) selectedHexagon.getBuilding());
        	if(uBuilding.canUpgrade()) {
        		uBuilding.upgrade();
        		build.attemptBuild(selectedHexagon,selectedHexagon.getBuilding());
        		disableButtons(); // Disable all buttons after successful build
        		GameController.getInstance().markActionCompleted();
        	}// Mark the action as completed        
        }else if(selectedHexagon.getBuilding() == null && !(building instanceof Colony)) {
        	if (build.attemptBuild(selectedHexagon, building)) {
                disableButtons(); // Disable all buttons after successful build
                GameController.getInstance().markActionCompleted(); // Mark the action as completed        
                }
        }
        
    }


    public void disableButtons() {
        militaryCampButton.setDisable(true);
        quarryButton.setDisable(true);
        upgradeButton.setDisable(true);
    }

    public void enableButtons() {
        militaryCampButton.setDisable(false);
        quarryButton.setDisable(false);
        upgradeButton.setDisable(false);
    }

    public void updateButtonStates() {
        if (build.isHasBuilt()) {
            disableButtons(); // Disable if already built
        } else {
            // Enable/disable based on resources
            militaryCampButton.setDisable(!build.canAffordBuilding(new MilitaryCamp(null, GameController.getInstance().getCurrentPlayer())));
            quarryButton.setDisable(!build.canAffordBuilding(new Quarry(null, GameController.getInstance().getCurrentPlayer())));
            upgradeButton.setDisable(false);
        }
    }
}