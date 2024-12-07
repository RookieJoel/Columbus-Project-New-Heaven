package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class BuildActionPane extends VBox {

    private Button factoryButton;
    private Button militaryCampButton;
    private Button missileFortressButton;
    private Button quarryButton;

    public BuildActionPane() {
        super(10); // Spacing between buttons
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);

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
    }

    // Getter methods for the buttons to attach action handlers
    public Button getFactoryButton() {
        return factoryButton;
    }

    public Button getMilitaryCampButton() {
        return militaryCampButton;
    }

    public Button getMissileFortressButton() {
        return missileFortressButton;
    }

    public Button getQuarryButton() {
        return quarryButton;
    }
}
