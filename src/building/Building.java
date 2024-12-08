package building;

import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.Map;

import board.Hexagon;
import board.Resource;
import player.Player;

public abstract class Building {
	 	private final String id; // Identifier based on hash of tile number and type
	    private String name; // Name of the building
	    private int hp; // Hit Points of the building
	    private int prosperityPoints; // Points the building contributes to the player
	    private Hexagon position; // Hexagon where the building is constructed
	    private boolean isDestroyed; // Indicates if the building has been destroyed
	    private Player player;
	    private Map<Resource,Integer> cost;
	    
	    public Building(String name, int hp, int prosperityPoints, Hexagon position,Player player) {
	        this.name = name;
	        this.hp = hp;
	        this.prosperityPoints = prosperityPoints;
	        this.position = position;
	        this.player = player;
	        this.isDestroyed = false;
	        this.cost = new HashMap<>();


	        // Generate hash-based ID
	        this.id = generateId(position, name);
	    }

	    // Generate a hash ID based on the tile number and building name
	    private String generateId(Hexagon position, String name) {
	        int tileNumber = position != null ? position.getNumber() : 0;
	        return name + "-" + Integer.toHexString(tileNumber).toUpperCase();
	    }
	    
	    // Getters and setters
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public int getHp() {
	        return hp;
	    }

	    public void setHp(int hp) {
	        this.hp = Math.max(0, hp); // Ensure HP is non-negative
	        if (this.hp == 0) {
	            this.isDestroyed = true;
	        }
	    }

	    public int getProsperityPoints() {
	        return prosperityPoints;
	    }

	    public void setProsperityPoints(int prosperityPoints) {
	        this.prosperityPoints = prosperityPoints;
	    }

	    public Hexagon getPosition() {
	        return position;
	    }

	    public void setPosition(Hexagon position) {
	        this.position = position;
	    }

	    public boolean isDestroyed() {
	        return isDestroyed;
	    }

	    public String getId() {
	        return id;
	    }

	    // Utility methods
	    public void takeDamage(int damage) {
	        setHp(this.hp - damage); // Reduce HP by the damage taken
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
	        if (player == null) {
	            return Color.GRAY; // Default color if no player is assigned
	        }
	        return player.getId() == 1 ? Color.BLUE : Color.GREEN; // Blue for Player 1, Green for Player 2
	    }
		
		public abstract Node createShape(double radius);
}