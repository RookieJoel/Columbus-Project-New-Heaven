package board;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Hexagon extends Group {

    private double radius;
    private int number;
    private Resource resource;

    private double offsetY;
    private double offsetX;
    private Polygon hexagonBorder; // Store a direct reference to the border polygon

    public Hexagon(double radius, int number, Resource resource) {
        this.radius = radius;
        this.number = number;
        this.resource = resource;

        // Initialize hexagon and set the resource image
        setResource(resource); // Set the image resource first to make sure it’s in the background
        offsetY = calculateApothem();
        offsetX = radius * 1.5;

        // Add border on hexagon
        addHexagonBorder();
        
        // Add centered number text
        addCenteredNumberText();
        
        // Title change on click
        changeTitle();
    }

    private void addCenteredNumberText() {
        // Display the number at the center of the hexagon
        Text numberText = new Text(String.valueOf(number));
        
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/KnightWarrior-w16n8.otf"), 30);
        numberText.setFont(customFont);
        numberText.setFill(Color.YELLOW);
        
        // Position text in the center of the hexagon using StackPane
        StackPane centeredText = new StackPane(numberText);
        centeredText.setPrefSize(radius * 2, radius * 2); // Set size to match hexagon
        centeredText.setAlignment(Pos.CENTER); // Center the text within the hexagon

        this.getChildren().add(centeredText); // Add centered text on top
    }

    public void setResource(Resource resource) {
        this.resource = resource;

        // Load the image from the resource
        Image image = resource.getImage();
        ImageView imageView = new ImageView(image);

        // Set ImageView to fill the hexagon bounds
        imageView.setFitWidth(radius * 2);
        imageView.setFitHeight(radius * 2);
        imageView.setPreserveRatio(false); // Disable preserve ratio to ensure it fills the shape

        // Create the hexagon shape to act as a clip for the image
        Polygon hexagonClip = createHexagonClip(radius);
        imageView.setClip(hexagonClip);

        // Create a StackPane to align the image within the hexagon
        StackPane hexagonPane = new StackPane(imageView);
        hexagonPane.setAlignment(Pos.CENTER);

        this.getChildren().add(0, hexagonPane); // Add image pane at the back
    }

    private Polygon createHexagonClip(double radius) {
        Polygon hexagon = new Polygon();
        double angleStep = Math.PI / 3; // 60 degrees in radians

        for (int i = 0; i < 6; i++) {
            double angle = i * angleStep;
            double x = radius + radius * Math.cos(angle);
            double y = radius + radius * Math.sin(angle);
            hexagon.getPoints().addAll(x, y);
        }

        hexagon.setStroke(Color.WHITESMOKE);
        hexagon.setStrokeWidth(3);
        return hexagon;
    }

    private void addHexagonBorder() {
        hexagonBorder = createHexagonClip(radius); // Store the reference to hexagon border
        hexagonBorder.setFill(Color.TRANSPARENT);
        hexagonBorder.setStroke(Color.WHITESMOKE);
        hexagonBorder.setStrokeWidth(3);
        this.getChildren().add(hexagonBorder); // Add border on top of image
    }

    public void highlightBorder() {
        hexagonBorder.setStroke(Color.RED);
    }

    public void resetBorder() {
        hexagonBorder.setStroke(Color.WHITESMOKE);
    }

    public int getNumber() {
        return number;
    }

    public void changeTitle() {
        hexagonBorder.setOnMouseClicked(e -> {
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setTitle("Resource: " + resource.name());
        });
    }

    public double getOffsetY() {
        return offsetY;
    }

    public double getOffsetX() {
        return offsetX;
    }

    private double calculateApothem() {
        return Math.cos(Math.PI / 6) * radius;
    }
}
