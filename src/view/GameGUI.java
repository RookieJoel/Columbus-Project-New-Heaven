package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class GameGUI extends Application {

    private static final double TILE_SIZE = 50; // size of each hexagon tile

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        createHexBoard(root);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Columbus Project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to create the hexagonal board
    private void createHexBoard(Pane root) {
        int rows = 5; // number of rows in the board
        int cols = 5; // number of columns in the board

        Color[] resourceColors = {
            Color.YELLOW,  // wheat
            Color.GREEN,   // forest
            Color.DARKGRAY, // ore
            Color.SADDLEBROWN, // brick
            Color.GOLD // desert
        };

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if ((row + col) % 2 == 0) { 
                    Polygon hexTile = createHexTile(row, col);
                    hexTile.setFill(resourceColors[(row + col) % resourceColors.length]);
                    root.getChildren().add(hexTile);
                }
            }
        }
    }

    // Method to create a single hexagonal tile
    private Polygon createHexTile(int row, int col) {
        double xOffset = col * TILE_SIZE * 1.5;
        double yOffset = row * TILE_SIZE * Math.sqrt(3) + (col % 2) * TILE_SIZE * Math.sqrt(3) / 2;

        Polygon hexTile = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            double x = xOffset + TILE_SIZE * Math.cos(angle);
            double y = yOffset + TILE_SIZE * Math.sin(angle);
            hexTile.getPoints().addAll(x, y);
        }

        return hexTile;
    }

    public static void main(String[] args) {
        launch();
    }
}
