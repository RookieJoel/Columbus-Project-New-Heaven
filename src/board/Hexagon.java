package board;

import building.Building;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;

public class Hexagon extends Group {

    private double radius;
    private int number;
    private Resource resource;
    private Building building;
    private Node buildingShape;
    private Runnable onClick;
    private boolean clickEnabled = false;

    private double offsetY;
    private double offsetX;
    private Polygon hexagonBorder; // Store a direct reference to the border polygon
    private int x;
    private int y;
    
    
    public Hexagon(double radius, int number, Resource resource,int x,int y) {
        this.radius = radius;
        this.number = number;
        this.resource = resource;
        this.building = null;
        setX(x);
        setY(y);

        // Initialize hexagon and set the resource image
        setResource(resource); // Set the image resource first to make sure it’s in the background
        offsetY = calculateApothem();
        offsetX = radius * 1.5;
        	
        // Add border on hexagon
        addHexagonBorder();
        
        // Add centered number text
        addCenteredNumberText();
        
        this.setOnMouseClicked(e -> {
            if (clickEnabled && onClick != null) {
                onClick.run(); // Trigger the callback if set
            }
        });

    }
    
    private void addCenteredNumberText() {
        // Display the number at the center of the hexagon
        Text numberText = new Text(String.valueOf(number));
        
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/KnightWarrior-w16n8.otf"), 30);
        numberText.setFont(customFont);
        numberText.setFill(Color.YELLOW);
        numberText.setStroke(Color.ORANGE);
        numberText.setStrokeWidth(2);
        
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
    
    public void setOnClick(Runnable onClick) {
        this.onClick = onClick;
    }
    
    public void setClickEnabled(boolean enabled) {
        this.clickEnabled = enabled;
    }

    public boolean isClickEnabled() {
        return clickEnabled;
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
    
    public Resource getResource() {
        return resource;
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setBuilding(Building building) {
	    if (this.building != null) {
	        removeBuilding(); // Remove existing building
	    }

	    this.building = building;
	    if (building != null) {
	        this.buildingShape = building.createShape(radius);
	        buildingShape.setTranslateX(radius); // Center shape on hexagon
	        buildingShape.setTranslateY(radius);
	        this.getChildren().add(buildingShape);
	    }
	}
	
	public void removeBuilding() {
	    if (buildingShape != null) {
	        this.getChildren().remove(buildingShape);
	        buildingShape = null;
	    }
	    this.building = null;
	}

	public boolean hasBuilding() {
	    return building != null;
	}
    
	public Building getBuilding() {
		return this.building;
	}
    
}