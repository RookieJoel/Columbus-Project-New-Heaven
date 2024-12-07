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
import pane.AlchemizePane;
import pane.BuildActionPane;
import pane.BottomPane;
import pane.HexagonPane;
import pane.StatusPane;
import player.Player;
import board.Hexagon;
import board.Resource;
import building.Colony;
import game.GameController;

import java.util.List;

public class GameGUI extends Application {

    private Player currentPlayer;
    private Player player1;
    private Player player2;
    private BuildActionPane player1BuildPane;
    private BuildActionPane player2BuildPane;

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
        Hexagon leftmostHexagon = hexagonPane.getLeftmostHexagon();
        Hexagon rightmostHexagon = hexagonPane.getRightmostHexagon();

        player1 = new Player(1, "Player 1", null);
        player2 = new Player(2, "Player 2", null);

        Colony colony1 = new Colony(leftmostHexagon, player1);
        Colony colony2 = new Colony(rightmostHexagon, player2);

        leftmostHexagon.setBuilding(colony1);
        rightmostHexagon.setBuilding(colony2);

        player1.setColony(colony1);
        player2.setColony(colony2);

        StatusPane player1Status = new StatusPane(player1);
        StatusPane player2Status = new StatusPane(player2);

        // Set current player to Player 1 initially
        currentPlayer = player1;

        // Initialize BuildActionPanes for both players
        player1BuildPane = new BuildActionPane();
        player2BuildPane = new BuildActionPane();

     // Create AlchemizePane with a callback to update the StatusPane
        AlchemizePane alchemizePane = new AlchemizePane(currentPlayer, () -> {
            if (currentPlayer == player1) {
                player1Status.updateResources(currentPlayer);
            } else {
                player2Status.updateResources(currentPlayer);
            }
        });



        // Create ActionPanes for both players
        ActionPane player1Actions = new ActionPane("Player 1", null, player1BuildPane, alchemizePane);
        ActionPane player2Actions = new ActionPane("Player 2", null, player2BuildPane, alchemizePane);

        // Create BottomPane
        BottomPane bottomPane = new BottomPane(
            () -> hexagonPane.highlightRandomHexagon(),
            () -> hexagonPane.resetHexagonBorders(),
            (tile1, tile2) -> {
                hexagonPane.highlightHexagon(tile1);
                hexagonPane.highlightHexagon(tile2);
                addResourcesToPlayer(hexagonPane.getHexagons(), tile1, tile2);
                if (currentPlayer == player1) {
                    player1Status.updateResources(currentPlayer);
                } else {
                    player2Status.updateResources(currentPlayer);
                }
                switchTurn();
                player1Actions.setVisible(currentPlayer == player1);
                player2Actions.setVisible(currentPlayer == player2);
                alchemizePane.updateCurrentPlayer(currentPlayer);

            },
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
        VBox leftPane = new VBox(10, player1Status, player1Actions, player1BuildPane);
        VBox rightPane = new VBox(10, player2Status, player2Actions, player2BuildPane);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(leftPane);
        mainLayout.setRight(rightPane);
        mainLayout.setCenter(hexagonPane);
        mainLayout.setBottom(bottomPane);

        // Add AlchemizePane to the root layout
        StackPane root = new StackPane(bg, mainLayout, alchemizePane);

        // Create Scene and show stage
        Scene scene = new Scene(root);
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
        // Update currentPlayer
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public static void main(String[] args) {
        launch();
    }
    
    
}
