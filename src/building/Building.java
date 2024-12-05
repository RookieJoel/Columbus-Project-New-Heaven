package building;

import board.Hexagon;

public abstract class Building {
	 	private final String id; // Identifier based on hash of tile number and type
	    private String name; // Name of the building
	    private int hp; // Hit Points of the building
	    private int prosperityPoints; // Points the building contributes to the player
	    private Hexagon position; // Hexagon where the building is constructed
	    private boolean isDestroyed; // Indicates if the building has been destroyed
	    
	    
	 // Upgrade properties
	    private int upgradeLevel; // Current upgrade level
	    private int upgradeCost; // Cost to upgrade
	    
	    public Building(String name, int hp, int prosperityPoints, Hexagon position, int maxUpgradeLevel, int initialUpgradeCost) {
	        this.name = name;
	        this.hp = hp;
	        this.prosperityPoints = prosperityPoints;
	        this.position = position;
	        this.isDestroyed = false;
	        this.upgradeLevel = 1; // Start at level 1
	        this.upgradeCost = initialUpgradeCost;

	        // Generate hash-based ID
	        this.id = generateId(position, name);
	    }

	    // Generate a hash ID based on the tile number and building name
	    private String generateId(Hexagon position, String name) {
	        int tileNumber = position != null ? position.getNumber() : 0;
	        return name + "-" + Integer.toHexString(tileNumber).toUpperCase();
	    }
	    
	    public int getUpgradeCost() {
	        return upgradeCost;
	    }

	    public int getUpgradeLevel() {
	        return upgradeLevel;
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
}
