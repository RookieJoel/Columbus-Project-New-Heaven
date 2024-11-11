package board;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Hexagon extends Group {

    private Polygon polygon;
    private double radius;
    private double radianStep = (2 * Math.PI) / 6;
    private int number;

    private double offsetY;
    private double offsetX;

    public Hexagon(double radius, Paint color, int number) {
        this.radius = radius;
        this.number = number;
        makeHexagon(radius, color, number);
        offsetY = calculateApothem();
        offsetX = radius * 1.5;
        changeTitle();
    }

    private void makeHexagon(double radius, Paint color, int number) {
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

        Text numberText = new Text(String.valueOf(number));
        numberText.setFill(Color.BLACK);
        numberText.setFont(Font.font(20));
        numberText.setTranslateX(-10);
        numberText.setTranslateY(5);
        this.getChildren().add(numberText);
    }

    public void highlightBorder() {
        polygon.setStroke(Color.RED);
    }

    public void resetBorder() {
        polygon.setStroke(Color.WHITESMOKE);
    }

    public int getNumber() {
        return number;
    }

    public void changeTitle() {
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
