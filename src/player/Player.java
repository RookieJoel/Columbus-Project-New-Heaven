package player;

import building.Colony;

public class Player {
    private final int id; // Unique ID for the player (1 or 2)
    private final String name; // Player's name
    private final Inventory inventory; // Player's inventory
    private Colony colony; // Colony owned by the player

    public Player(int id, String name,Colony colony) {
        this.id = id;
        this.name = name;
        this.inventory = new Inventory();
        setColony(colony);
    }

    // Set the colony owned by the player
    public void setColony(Colony colony) {
        this.colony = colony;
    }

    // Get the current HP from the colony
    public int getHp() {
        return colony != null ? colony.getHp() : 0;
    }

    // Take damage by damaging the colony
    public void takeDamage(int damage) {
        if (colony != null) {
            colony.takeDamage(damage);
        }
    }

    // Repair HP by repairing the colony
    public void repair(int amount) {
        if (colony != null) {
            colony.setHp(colony.getHp() + amount);
        }
    }

    public Colony getColony() {
        return colony;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
