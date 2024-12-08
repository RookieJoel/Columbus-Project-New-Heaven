package game;

import pane.BuildActionPane;
import pane.ActionPane;
import pane.HexagonPane;
import pane.StatusPane;
import player.Player;

public class GameController {
    private static GameController instance; // Singleton instance
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private HexagonPane hexagonPane;

    private BuildActionPane buildPane1;
    private BuildActionPane buildPane2;
    private ActionPane actionPane1;
    private ActionPane actionPane2;

    private Build build1; // Add Build for Player 1
    private Build build2; // Add Build for Player 2
    
    private StatusPane player1StatusPane;
    private StatusPane player2StatusPane;
    
    private boolean turnActionCompleted; // Flag to track if an action is completed in the current turn

    private GameController(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1; // Default to Player 1
        this.hexagonPane = new HexagonPane();
    }

    public HexagonPane getHexagonPane() {
        return hexagonPane;
    }

    public static GameController getInstance(Player player1, Player player2) {
        if (instance == null) {
            instance = new GameController(player1, player2);
        }
        return instance;
    }

    public static GameController getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GameController is not initialized. Call getInstance(Player, Player) first.");
        }
        return instance;
    }

    public void setBuilds(Build build1, Build build2) {
        this.build1 = build1;
        this.build2 = build2;
    }

    public void setBuildActionPanes(BuildActionPane pane1, BuildActionPane pane2) {
        this.buildPane1 = pane1;
        this.buildPane2 = pane2;
    }

    public void setActionPanes(ActionPane pane1, ActionPane pane2) {
        this.actionPane1 = pane1;
        this.actionPane2 = pane2;
    }
    public void setStatusPanes(StatusPane player1StatusPane, StatusPane player2StatusPane) {
        this.player1StatusPane = player1StatusPane;
        this.player2StatusPane = player2StatusPane;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        
        turnActionCompleted = false; // Reset the flag for the new turn
        // Reset build status for the new turn
        if (currentPlayer == player1 && build1 != null) {
            build1.resetBuildStatus(); // Reset for Player 1
            actionPane1.enableBuildButton(); // Enable build button for Player 1
        } else if (currentPlayer == player2 && build2 != null) {
            build2.resetBuildStatus(); // Reset for Player 2
            actionPane2.enableBuildButton(); // Enable build button for Player 2
        }

        getCurrentActionPane().enableAllButtons();

        updatePaneVisibility();
        currentPlayer.printInventory(); // Debug inventory at the start of the turn
    }
    
    public boolean isTurnActionCompleted() {
        return turnActionCompleted;
    }

    public void markActionCompleted() {
        this.turnActionCompleted = true;

        // Disable all buttons in the current ActionPane
        getCurrentActionPane().disableAllButtons();
    }

    public static void reset() {
        instance = null; // Clear the existing instance
    }

    
    public ActionPane getCurrentActionPane() {
        return (currentPlayer == player1) ? actionPane1 : actionPane2;
    }
    
    public StatusPane getCurrentStatusPane() {
        return (currentPlayer == player1) ? player1StatusPane : player2StatusPane;
    }

    private void updatePaneVisibility() {
        // Hide all panes
        if (buildPane1 != null) buildPane1.setVisible(false);
        if (buildPane2 != null) buildPane2.setVisible(false);
        if (actionPane1 != null) actionPane1.setVisible(false);
        if (actionPane2 != null) actionPane2.setVisible(false);

        // Show only the current player's panes
        if (currentPlayer == player1) {
            if (actionPane1 != null) actionPane1.setVisible(true);
            if (buildPane1 != null) buildPane1.updateButtonStates(); // Update button states
        } else {
            if (actionPane2 != null) actionPane2.setVisible(true);
            if (buildPane2 != null) buildPane2.updateButtonStates(); // Update button states
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
