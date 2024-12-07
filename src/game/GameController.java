package game;

import pane.HexagonPane;
import player.Player;

public class GameController {
    private static GameController instance; // Singleton instance
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private GameController(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1; // Default to Player 1
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
