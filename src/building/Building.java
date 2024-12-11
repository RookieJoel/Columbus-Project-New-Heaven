package building;

import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Map;

import board.Hexagon;
import board.Resource;
import game.GameController;
import player.Player;

public abstract class Building {
	 	private final String ID; // Identifier based on hash of tile number and type
	    private String NAME; // Name of the building
	    private int hp; // Hit Points of the building
	    private Hexagon position; // Hexagon where the building is constructed
	    private boolean isDestroyed; // Indicates if the building has been destroyed
	    private Player player;
	    private Map<Resource,Integer> cost;
	    private Text hpText; // Text to display HP
	    
	    public Building(String name, int hp, Hexagon position,Player player) {
	        this.NAME = name;
	        this.hp = hp;
	        this.position = position;
	        this.player = player;
	        this.isDestroyed = false;
	        this.cost = new HashMap<>();

	        initializeHpText();
	        
	        // Generate hash-based ID
	        this.ID = generateId(position, name);
	    }

	    
	    private void initializeHpText() {
	        hpText = new Text(String.valueOf(hp));
	        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Steelar-j9Vnj.otf"), 30); 
		    hpText.setFont(customFont); 
		    hpText.setFill(Color.WHITESMOKE);
		    
	    }

	    // Generate a hash ID based on the tile number and building name
	    private String generateId(Hexagon position, String name) {
	        int tileNumber = position != null ? position.getNumber() : 0;
	        return name + "-" + Integer.toHexString(tileNumber).toUpperCase();
	    }
	    
	    // Getters and setters
	    public String getName() {
	        return NAME;
	    }


	    public int getHp() {
	        return hp;
	    }

	    public void setHp(int hp) {
	        this.hp = Math.max(0, hp);
	        if (this.hp == 0) {
	            this.isDestroyed = true;
	            
	        }
	        updateHpDisplay();
	    }

	    public Hexagon getPosition() {
	        return position;
	    }

	    public void setPosition(Hexagon position) {
	        this.position = position;
	    }

	    

	    public String getId() {
	        return ID;
	    }

	    // Utility methods
	    public void takeDamage(int damage) {
	        this.hp = Math.max(0, this.hp - damage); // Reduce HP, ensure it doesn’t go below 0
	        if (this.hp == 0) {
	            this.isDestroyed = true;
	            this.getPosition().setBuilding(null);
	            this.getPlayer().getBuildings().remove(this);	        
	        }
	        updateHpDisplay();

	        // Notify GameController if this is a Colony
	        if (this instanceof Colony) {
	            Player owner = this.getPlayer();
	            if (owner != null) {
	                GameController.getInstance()
	                    .getStatusPaneForPlayer(owner)
	                    .updateHp(this.getHp());
	            }
	        }
	    }


	    private void updateHpDisplay() {
	        if (hpText != null) {
	            hpText.setText(String.valueOf(hp));
	        }
	    }
	    public void addCost(Resource resource, int amount) {
	        cost.put(resource, amount);
	    }

	    // Get the cost of a building
	    public Map<Resource, Integer> getCost() {
	        return cost;
	    }

		public Player getPlayer() {
			return player;
		}

		public void setPlayer(Player player) {
			this.player = player;
		}
		
		protected Color getPlayerColor() {
	        return player.getId() == 1 ? Color.BLUE : Color.GREEN; // Blue for Player 1, Green for Player 2
	    }
		
		
		public Text getHpText() {
			return hpText;
		}

		public void setHpText(Text hpText) {
			this.hpText = hpText;
		}

		public abstract Node createShape(double radius);
		
		protected Node createHexagonalShape(double radius, String imagePath) {
		    // Scale down for visibility of resource tile
		    double scale = 0.8; // Scale factor for smaller size

		    // Outer hexagon
		    Polygon hexagon = new Polygon();
		    for (int i = 0; i < 6; i++) {
		        double angle = Math.toRadians(60 * i);
		        double x = radius * scale * Math.cos(angle);
		        double y = radius * scale * Math.sin(angle);
		        hexagon.getPoints().addAll(x, y);
		    }
		    hexagon.setFill(getPlayerColor());
		    hexagon.setStroke(Color.BLACK);
		    hexagon.setStrokeWidth(1.5); // Thinner stroke for smaller size

		    // Inner hexagon for the image
		    Polygon innerHexagon = new Polygon();
		    double innerRadius = radius * scale * 0.9; // Slightly smaller for the inner hexagon
		    for (int i = 0; i < 6; i++) {
		        double angle = Math.toRadians(60 * i);
		        double x = innerRadius * Math.cos(angle);
		        double y = innerRadius * Math.sin(angle);
		        innerHexagon.getPoints().addAll(x, y);
		    }

		    // Load and set the image
		    Image image = new Image(getClass().getResourceAsStream(imagePath));
		    innerHexagon.setFill(new ImagePattern(image));

		    // Positioning the HP text
		    Text hpText = getHpText();
		    hpText.setTranslateX(-radius * scale * 0.3); // Adjust position based on scaled radius
		    hpText.setTranslateY(radius * scale * 0.9);  // Position below the scaled hexagon

		    // Group the hexagon shape, inner hexagon, and HP text
		    return new Group(hexagon, innerHexagon, hpText);
		}


		public void setDestroyed(boolean isDestroyed) {
			this.isDestroyed = isDestroyed;
		}


		public boolean isDestroyed() {
			if(getHp() <= 0) return true;
			return false;
		}
		
		
		
		
	

}