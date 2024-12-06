package game;

import board.Hexagon;
import board.Resource;
import pane.StatusPane;
import player.Player;

import java.util.List;

public class GameController {
    private Player currentPlayer;
    private final Player player1;
    private final Player player2;
    private final List<Hexagon> hexagons; // List of all hexagons on the board
    private final StatusPane player1Status;
    private final StatusPane player2Status;

    public GameController(Player player1, Player player2, List<Hexagon> hexagons, StatusPane player1Status, StatusPane player2Status) {
        this.player1 = player1;
        this.player2 = player2;
        this.hexagons = hexagons;
        this.player1Status = player1Status;
        this.player2Status = player2Status;
        this.currentPlayer = player1; // Start with Player 1
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
}
