package game;

import board.Hexagon;
import board.Resource;
import building.Building;
import pane.StatusPane;
import player.Player;
import view.GameGUI;

import java.util.List;

public class GameController {
    private Player currentPlayer;
    private final Player player1;
    private final Player player2;
    private final List<Hexagon> hexagons; // List of all hexagons on the board
    private final StatusPane player1Status;
    private final StatusPane player2Status;
    private Build builder;

    public GameController(Player player1, Player player2, List<Hexagon> hexagons, StatusPane player1Status, StatusPane player2Status,GameGUI gui) {
        this.player1 = player1;
        this.player2 = player2;
        this.hexagons = hexagons;
        this.player1Status = player1Status;
        this.player2Status = player2Status;
        this.currentPlayer = player1; // Start with Player 1
        this.builder = new Build(currentPlayer);
    }

    // Switch turn to the other player
    public void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    // Get the current player
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    // Perform roll and add resources to the current player
    public void performRoll(int tile1, int tile2) {
        for (Hexagon hex : hexagons) {
            if (hex.getNumber() == tile1 || hex.getNumber() == tile2) {
                Resource resource = hex.getResource(); // Get resource of the hex
                currentPlayer.getInventory().addResource(resource, 1); // Add resource to player's inventory
            }
        }
        updateStatusPane(); // Update the status pane for the current player
    }

    // Update the corresponding StatusPane
    private void updateStatusPane() {
        if (currentPlayer == player1) {
            player1Status.updateResources(currentPlayer);
        } else {
            player2Status.updateResources(currentPlayer);
        }
    }
    
    public void handleBuildButtonClicked() {
        // Set the game to "building mode" - allow player to select hexagon
    	builder.setBuildingMode(true);
    	System.out.println("On Build Mode");
    }
    
    public void onHexagonSelected(Hexagon hexagon,Building building) {
        if (builder.isBuildingMode()) {
        	System.out.println("Hex");
            // Attempt to build a specific building on the selected hexagon
            if (builder.attemptBuild(hexagon, building)) {
                // Successfully built
            	builder.setBuildingMode(false);
                // Switch to the next player or reset
            } else {
                // Failed to build (not enough resources, etc.)
            }
        }
    }
    
    public void handleBuildButtonClicked(BuildActionPane buildActionPane) {
        buildActionPane.updateButtonStates();
        buildActionPane.setVisible(true);
    }

    public void onBuildingSelected(Building selectedBuilding) {
        // Highlight tiles that can build
        hexagonPane.resetHexagonBorders();

        for (Hexagon hex : hexagonPane.getHexagons()) {
            if (canBuildOnTile(hex)) {
                hex.highlightBorder(); // Highlight valid tiles in green
                hex.setOnClick(() -> onTileSelected(hex, selectedBuilding)); // Add click action
            }
        }
    }

    private boolean canBuildOnTile(Hexagon hex) {
        // Check if tile is empty and adjacent to a player's building
        if (hex.hasBuilding()) return false;

        for (Hexagon adjacentHex : hexagonPane.getAdjacentHexagons(hex)) {
            if (adjacentHex.getBuilding() != null &&
                adjacentHex.getBuilding().getPlayer() == builder.getCurrentPlayer()) {
                return true; // Adjacent to player's building
            }
        }
        return false;
    }

    private void onTileSelected(Hexagon hex, Building building) {
        if (builder.attemptBuild(hex, building)) {
            hex.setBuilding(building);
            hexagonPane.resetHexagonBorders(); // Clear highlights
        }
    }
    
}