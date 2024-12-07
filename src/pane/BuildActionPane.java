package pane;

import board.Hexagon;
import board.Resource;
import building.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildActionPane extends VBox {
    private Button factoryButton;
    private Button militaryCampButton;
    private Button missileFortressButton;
    private Button quarryButton;

    private Building selectedBuilding;
    private final Player player;
    private final List<Hexagon> hexagons;

    public BuildActionPane(Player player, List<Hexagon> hexagons) {
        super(10);
        this.player = player;
        this.hexagons = hexagons;
        initializeButtons();
        setAlignment(Pos.CENTER);
        setTranslateY(25);
        setVisible(false);
    }

    private void initializeButtons() {
        // Initialize buttons
        factoryButton = createButton("Factory", () -> onSelectBuilding(new Factory(null, player)));
        militaryCampButton = createButton("Military Camp", () -> onSelectBuilding(new MilitaryCamp(null, player)));
        missileFortressButton = createButton("Missile Fortress", () -> onSelectBuilding(new MissileFortress(null, player)));
        quarryButton = createButton("Quarry", () -> onSelectBuilding(new Quarry(null, player)));

        // Add buttons to the VBox
        this.getChildren().addAll(factoryButton, militaryCampButton, missileFortressButton, quarryButton);
    }

    private Button createButton(String text, Runnable onClickAction) {
        Button button = new Button(text);
        button.setPrefWidth(150);
        button.setOnAction(e -> onClickAction.run());
        return button;
    }

    private void onSelectBuilding(Building building) {
        selectedBuilding = building;
        highlightValidTiles();
    }

    private void highlightValidTiles() {
        resetHexagonBorders();

        for (Hexagon hex : hexagons) {
            if (canBuildOnTile(hex)) {
                hex.highlightBorder(); // Highlight in green for valid tiles
                hex.setOnClick(() -> onTileSelected(hex)); // Add click action
            }
        }
    }

    private boolean canBuildOnTile(Hexagon hex) {
        if (hex.getBuilding() != null) {
            return false; // Tile already has a building
        }
        // Check adjacency to existing player buildings
        for (Hexagon adjacentHex : getAdjacentHexagons(hex)) {
            if (adjacentHex.getBuilding() != null &&
                adjacentHex.getBuilding().getPlayer() == player) {
                return true;
            }
        }
        return false;
    }

    private List<Hexagon> getAdjacentHexagons(Hexagon hex) {
        List<Hexagon> adjacentHexes = new ArrayList<>();
        int x = hex.getX();
        int y = hex.getY();

        // Add adjacent hexagons (implement your logic for grid adjacency)
        for (Hexagon h : hexagons) {
            if (Math.abs(h.getX() - x) <= 1 && Math.abs(h.getY() - y) <= 1 && !(h.getX() == x && h.getY() == y)) {
                adjacentHexes.add(h);
            }
        }
        return adjacentHexes;
    }

    private void onTileSelected(Hexagon hex) {
        if (selectedBuilding == null) {
            return; // No building selected
        }

        // Deduct resources and place building
        if (player.getInventory().removeResources(selectedBuilding.getCost())) {
            selectedBuilding.setPosition(hex);
            hex.setBuilding(selectedBuilding);

            // Add visual representation of the building
            Shape buildingShape = createBuildingShape(selectedBuilding);
            hex.getChildren().add(buildingShape);

            resetHexagonBorders(); // Clear highlights
            selectedBuilding = null; // Reset selected building
        } else {
            System.out.println("Not enough resources to build " + selectedBuilding.getName());
        }
    }


    private Shape createBuildingShape(Building building) {
        if (building instanceof Quarry) {
            return new javafx.scene.shape.Circle(15, Color.BROWN); // Circle for Quarry
        } else if (building instanceof Factory) {
            return new javafx.scene.shape.Rectangle(30, 30, Color.GRAY); // Square for Factory
        } else if (building instanceof MilitaryCamp) {
            javafx.scene.shape.Polygon triangle = new javafx.scene.shape.Polygon(0, 0, 20, 40, 40, 0);
            triangle.setFill(Color.RED); // Triangle for Military Camp
            return triangle;
        } else if (building instanceof MissileFortress) {
            Polygon hexagon = new javafx.scene.shape.Polygon(
                10, 0, 20, 10, 10, 20, 0, 10, 0, 0); // Adjust points for a small hexagon
            hexagon.setFill(Color.BLUE);
            return hexagon;
        }
        return null;
    }

    public void updateButtonStates() {
        updateButtonState(factoryButton, new Factory(null, player));
        updateButtonState(militaryCampButton, new MilitaryCamp(null, player));
        updateButtonState(missileFortressButton, new MissileFortress(null, player));
        updateButtonState(quarryButton, new Quarry(null, player));
    }

    private void updateButtonState(Button button, Building building) {
        boolean canAfford = player.getInventory().getResources().entrySet()
            .stream()
            .allMatch(entry -> entry.getValue() >= building.getCost().getOrDefault(entry.getKey(), 0));
        button.setDisable(!canAfford);
    }

    private void resetHexagonBorders() {
        for (Hexagon hex : hexagons) {
            hex.resetBorder();
        }
    }
}
