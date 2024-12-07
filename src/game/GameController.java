package game;

import board.Resource;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pane.HexagonPane;
import player.Player;

public class GameController {
    private Player currentPlayer;

    public GameController(Player player1, Player player2, HexagonPane hexagonPane) {
        this.currentPlayer = player1; // Default to Player 1
    }

}
