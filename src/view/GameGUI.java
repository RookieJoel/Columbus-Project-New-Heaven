package view;

import game.GameController;
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
import building.Colony;

public class GameGUI extends Application {

    private GameController gameController;

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

        // Create Players
        Player player1 = new Player(1, "Player 1", null);
        Player player2 = new Player(2, "Player 2", null);

        // Initialize GameController
        gameController = GameController.getInstance(player1, player2);

        // Assign colonies
        Hexagon leftmostHexagon = hexagonPane.getLeftmostHexagon();
        Hexagon rightmostHexagon = hexagonPane.getRightmostHexagon();

        Colony colony1 = new Colony(leftmostHexagon, player1);
        Colony colony2 = new Colony(rightmostHexagon, player2);

        leftmostHexagon.setBuilding(colony1);
        rightmostHexagon.setBuilding(colony2);

        player1.setColony(colony1);
        player2.setColony(colony2);

        // Create StatusPanes
        StatusPane player1Status = new StatusPane(player1);
        StatusPane player2Status = new StatusPane(player2);

        // Create ActionPane and other panes
        AlchemizePane alchemizePane = new AlchemizePane(gameController.getCurrentPlayer(), () -> {
            if (gameController.getCurrentPlayer() == player1) {
                player1Status.updateResources(player1);
            } else {
                player2Status.updateResources(player2);
            }
        });

        ActionPane player1Actions = new ActionPane("Player 1", null, new BuildActionPane(), alchemizePane);
        ActionPane player2Actions = new ActionPane("Player 2", null, new BuildActionPane(), alchemizePane);

        // Create BottomPane
        BottomPane bottomPane = new BottomPane(
            () -> hexagonPane.highlightRandomHexagon(),
            () -> hexagonPane.resetHexagonBorders(),
            (tile1, tile2) -> {
                hexagonPane.addResourcesToPlayer(gameController.getCurrentPlayer(), tile1, tile2);
                if (gameController.getCurrentPlayer() == player1) {
                    player1Status.updateResources(player1);
                } else {
                    player2Status.updateResources(player2);
                }
                gameController.switchTurn();
                player1Actions.setVisible(gameController.getCurrentPlayer() == player1);
                player2Actions.setVisible(gameController.getCurrentPlayer() == player2);
                alchemizePane.updateCurrentPlayer(gameController.getCurrentPlayer());
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
        VBox leftPane = new VBox(10, player1Status, player1Actions);
        VBox rightPane = new VBox(10, player2Status, player2Actions);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(leftPane);
        mainLayout.setRight(rightPane);
        mainLayout.setCenter(hexagonPane);
        mainLayout.setBottom(bottomPane);

        StackPane root = new StackPane(bg, mainLayout, alchemizePane);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Columbus Project");
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
