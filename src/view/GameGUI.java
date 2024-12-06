package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pane.ActionPane;
import pane.BottomPane;
import pane.HexagonPane;
import pane.StatusPane;
import player.Player;
import board.Resource;
import board.Hexagon;

import java.util.List;

public class GameGUI extends Application {

    private Player currentPlayer; // Current turn player
    private Player player1;
    private Player player2;

    @Override
    public void start(Stage stage) {
        // Background Image
        String imagePath = ClassLoader.getSystemResource("images/Space.png").toString();
        ImageView bg = new ImageView(new Image(imagePath));
        bg.setPreserveRatio(false);
        bg.fitWidthProperty().bind(stage.widthProperty());
        bg.fitHeightProperty().bind(stage.heightProperty());

        // Create HexagonPane
        HexagonPane hexagonPane = new HexagonPane();

        // Create Players and StatusPane
        player1 = new Player(1, "Player 1", null);
        player2 = new Player(2, "Player 2", null);
        StatusPane player1Status = new StatusPane(player1);
        StatusPane player2Status = new StatusPane(player2);

        // Set current player to Player 1 at the start
        currentPlayer = player1;

        // Create ActionPane
        ActionPane player1Actions = new ActionPane("Player 1");
        ActionPane player2Actions = new ActionPane("Player 2");

        // Create BottomPane
        BottomPane bottomPane = new BottomPane(
            // Highlight random hexagon
            () -> hexagonPane.highlightRandomHexagon(),
            // Reset hexagon borders
            () -> hexagonPane.resetHexagonBorders(),
            // Highlight final hexagons and update resources
            (tile1, tile2) -> {
                // Highlight the two tiles
                hexagonPane.highlightHexagon(tile1);
                hexagonPane.highlightHexagon(tile2);

                // Add resources from these tiles to the currentPlayer
                addResourcesToPlayer(hexagonPane.getHexagons(), tile1, tile2);

                // Update StatusPane for the current player
                if (currentPlayer == player1) {
                    player1Status.updateResources(currentPlayer);
                } else {
                    player2Status.updateResources(currentPlayer);
                }

                // Switch turn to the other player
                switchTurn();
            },
            // Open main menu
            () -> {
                MainMenu mainMenu = new MainMenu();
                Stage mainStage = new Stage();
                try {
                    mainMenu.start(mainStage);
                    stage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        );

        // Layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(new VBox(10, player1Status, player1Actions));
        mainLayout.setRight(new VBox(10, player2Status, player2Actions));
        mainLayout.setCenter(hexagonPane);
        mainLayout.setBottom(bottomPane);

        // StackPane to hold the background and main layout
        StackPane root = new StackPane(bg, mainLayout);
        
        // Create Scene and show stage
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Columbus Project");
        stage.setFullScreen(true);
        stage.show();
    }

    // Add resources from highlighted tiles to the current player's inventory
    private void addResourcesToPlayer(List<Hexagon> hexagons, int tile1, int tile2) {
        for (Hexagon hex : hexagons) {
            if (hex.getNumber() == tile1 || hex.getNumber() == tile2) {
                Resource resource = hex.getResource(); // Get the resource of the tile
                currentPlayer.getInventory().addResource(resource, 1); // Add 1 unit of the resource to the player's inventory
            }
        }
    }

    // Switch turn between Player 1 and Player 2
    private void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public static void main(String[] args) {
        launch();
    }
}
