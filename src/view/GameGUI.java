package view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class GameGUI extends Application {

    @Override
    public void start(Stage stage) {

        double HexagonRadius = 100;

        Hexagon hexagon1 = new Hexagon(HexagonRadius, Color.CADETBLUE);

        Hexagon hexagon2 = new Hexagon(HexagonRadius, Color.MEDIUMPURPLE);
        hexagon2.setTranslateY(hexagon1.getOffsetY() * 2);

        Hexagon hexagon3 = new Hexagon(HexagonRadius, Color.MEDIUMSEAGREEN);
        hexagon3.setTranslateY(-hexagon1.getOffsetY() * 2);

        Hexagon hexagon4 = new Hexagon(HexagonRadius, Color.CORNFLOWERBLUE);
        hexagon4.setTranslateY(-hexagon1.getOffsetY());
        hexagon4.setTranslateX(hexagon1.getOffsetX());

        Hexagon hexagon5 = new Hexagon(HexagonRadius, Color.YELLOW);
        hexagon5.setTranslateY(-hexagon1.getOffsetY());
        hexagon5.setTranslateX(-hexagon1.getOffsetX());

        Hexagon hexagon6 = new Hexagon(HexagonRadius, Color.ORANGE);
        hexagon6.setTranslateY(hexagon1.getOffsetY());
        hexagon6.setTranslateX(-hexagon1.getOffsetX());

        Hexagon hexagon7 = new Hexagon(HexagonRadius, Color.SKYBLUE);
        hexagon7.setTranslateY(hexagon1.getOffsetY());
        hexagon7.setTranslateX(hexagon1.getOffsetX());
        
        Hexagon hexagon8 = new Hexagon(HexagonRadius, Color.SKYBLUE);
        hexagon8.setTranslateY(hexagon1.getOffsetY() *4);
        
        Hexagon hexagon9 = new Hexagon(HexagonRadius, Color.SKYBLUE);
        hexagon9.setTranslateY(hexagon1.getOffsetY()*2);
        hexagon9.setTranslateX(hexagon1.getOffsetX()*2);
        
        Hexagon hexagon10 = new Hexagon(HexagonRadius, Color.CORNFLOWERBLUE);
        hexagon10.setTranslateY(-hexagon1.getOffsetY()*2);
        hexagon10.setTranslateX(hexagon1.getOffsetX()*2);
        
        Hexagon hexagon11 = new Hexagon(HexagonRadius, Color.ORANGE);
        hexagon11.setTranslateY(hexagon1.getOffsetY()*2);
        hexagon11.setTranslateX(-hexagon1.getOffsetX()*2);
        
        Hexagon hexagon12 = new Hexagon(HexagonRadius, Color.YELLOW);
        hexagon12.setTranslateY(-hexagon1.getOffsetY()*2);
        hexagon12.setTranslateX(-hexagon1.getOffsetX()*2);
        
        Hexagon hexagon13 = new Hexagon(HexagonRadius, Color.MEDIUMSEAGREEN);
        hexagon13.setTranslateY(-hexagon1.getOffsetY() * 4);
        
        Hexagon hexagon14 = new Hexagon(HexagonRadius, Color.CORNFLOWERBLUE);
        hexagon14.setTranslateX(hexagon1.getOffsetX()*2);
        
        Hexagon hexagon15 = new Hexagon(HexagonRadius, Color.CORNFLOWERBLUE);
        hexagon15.setTranslateX(-hexagon1.getOffsetX()*2);
        
        Hexagon hexagon16 = new Hexagon(HexagonRadius, Color.SKYBLUE);
        hexagon16.setTranslateY(-hexagon1.getOffsetY()*3);
        hexagon16.setTranslateX(hexagon1.getOffsetX());
        
        Hexagon hexagon17 = new Hexagon(HexagonRadius, Color.SKYBLUE);
        hexagon17.setTranslateY(hexagon1.getOffsetY()*3);
        hexagon17.setTranslateX(-hexagon1.getOffsetX());
        
        Hexagon hexagon18 = new Hexagon(HexagonRadius, Color.RED);
        hexagon18.setTranslateY(hexagon1.getOffsetY()*3);
        hexagon18.setTranslateX(hexagon1.getOffsetX());
        
        Hexagon hexagon19= new Hexagon(HexagonRadius, Color.RED);
        hexagon19.setTranslateY(-hexagon1.getOffsetY()*3);
        hexagon19.setTranslateX(-hexagon1.getOffsetX());
        
        Group hexagonsGroup = new Group(hexagon1, hexagon2, hexagon3, hexagon4, hexagon5, hexagon6, 
        		hexagon7, hexagon8,hexagon9,hexagon10,hexagon11,hexagon12,hexagon13,hexagon14,hexagon15,
        		hexagon16,hexagon17,hexagon18,hexagon19);

        StackPane stackPane = new StackPane(hexagonsGroup);

        var scene = new Scene(stackPane, 640, 480);
        scene.setFill(Color.ANTIQUEWHITE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public class Hexagon extends Group {

        private Polygon polygon;
        private double radius;
        private double radianStep = (2 * Math.PI) / 6;

        private double offsetY;
        private double offsetX;

        public Hexagon(double radius, Paint color) {
            this.radius = radius;
            makeHexagon(radius, color);
            offsetY = calculateApothem();
            
            
            
            offsetX = radius * 1.5;
            changeTittle();

        }

        private void makeHexagon(double radius, Paint color) {
            polygon = new Polygon();
            this.getChildren().add(polygon);
            polygon.setFill(color);
            polygon.setStroke(Color.WHITESMOKE);
            polygon.setEffect(new DropShadow(10, Color.BLACK));
            polygon.setStrokeWidth(10);
            polygon.setStrokeType(StrokeType.INSIDE);

            for (int i = 0; i < 6; i++) {
                double angle = radianStep * i;

                polygon.getPoints().add(Math.cos(angle) * radius / 1.1);
                polygon.getPoints().add(Math.sin(angle) * radius / 1.1);

            }
        }

        public void changeTittle() {

            polygon.setOnMouseClicked(e -> {
                Stage stage = (Stage) this.getScene().getWindow();
                stage.setTitle(polygon.getFill().toString());
            });

        }

        public double getOffsetY() {
            return offsetY;
        }

        public double getOffsetX() {
            return offsetX;
        }

        private double calculateApothem() {
            
            return (Math.tan(radianStep) * radius) / 2;

        }

    }

}