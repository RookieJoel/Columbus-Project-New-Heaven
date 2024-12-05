package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import player.Player;

public class StatusPane extends VBox {
    private Label hpLabel;
    private VBox resourceBox;

    public StatusPane(Player player) {
        super(10); // Spacing between elements
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.TOP_CENTER);

        // Load the custom font
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/KnightWarrior-w16n8.otf"), 30);


        // Player name
        Label nameLabel = new Label(player.getName());
        nameLabel.setFont(customFont);
        nameLabel.setTextFill(Color.WHITESMOKE); // Set text color to whitesmoke

        // HP
        hpLabel = new Label("HP: " + player.getHp());
        hpLabel.setFont(customFont);
        hpLabel.setTextFill(Color.WHITESMOKE); // Set text color to whitesmoke

        // Resources display
        resourceBox = new VBox(5); // Spacing between resources
        updateResources(player);

        // Add all elements to VBox
        this.getChildren().addAll(nameLabel, hpLabel, resourceBox);
    }

    // Update HP
    public void updateHp(int hp) {
        hpLabel.setText("HP: " + hp);
    }

    // Update Resources from Player's inventory
    public void updateResources(Player player) {
        resourceBox.getChildren().clear();
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/KnightWarrior-w16n8.otf"), 20); // Smaller font for resources
        player.getInventory().getResources().forEach((resource, amount) -> {
            Label resourceLabel = new Label(resource + ": " + amount);
            resourceLabel.setFont(customFont);
            resourceLabel.setTextFill(Color.WHITESMOKE); // Set text color to whitesmoke
            resourceBox.getChildren().add(resourceLabel);
        });
    }
}
