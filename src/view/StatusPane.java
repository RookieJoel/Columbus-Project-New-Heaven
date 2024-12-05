package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

public class StatusPane extends VBox {
    private Label hpLabel;
    private Label prosperityLabel;
    private HBox resourceBox;

    public StatusPane(String playerName, int initialHP, int initialProsperity, Map<String, Integer> resources) {
        super(10); // Spacing between elements
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.TOP_CENTER);

        // Player name
        Label nameLabel = new Label(playerName);
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // HP and Prosperity
        hpLabel = new Label("HP: " + initialHP);
        prosperityLabel = new Label("Prosperity: " + initialProsperity);

        // Resource display
        resourceBox = new HBox(5); // Spacing between resources
        resourceBox.setAlignment(Pos.CENTER);
        for (Map.Entry<String, Integer> resource : resources.entrySet()) {
            ImageView resourceIcon = new ImageView(new Image(ClassLoader.getSystemResource(resource.getKey()).toString()));
            resourceIcon.setFitWidth(20);
            resourceIcon.setFitHeight(20);
            Label resourceLabel = new Label(resource.getValue().toString());
            resourceBox.getChildren().addAll(resourceIcon, resourceLabel);
        }

        // Add elements to VBox
        this.getChildren().addAll(nameLabel, hpLabel, prosperityLabel, resourceBox);
    }

    // Update HP
    public void updateHP(int newHP) {
        hpLabel.setText("HP: " + newHP);
    }

    // Update Prosperity Points
    public void updateProsperity(int newProsperity) {
        prosperityLabel.setText("Prosperity: " + newProsperity);
    }

    // Update Resources
    public void updateResources(Map<String, Integer> newResources) {
        resourceBox.getChildren().clear();
        for (Map.Entry<String, Integer> resource : newResources.entrySet()) {
            ImageView resourceIcon = new ImageView(new Image(ClassLoader.getSystemResource(resource.getKey()).toString()));
            resourceIcon.setFitWidth(20);
            resourceIcon.setFitHeight(20);
            Label resourceLabel = new Label(resource.getValue().toString());
            resourceBox.getChildren().addAll(resourceIcon, resourceLabel);
        }
    }
}
