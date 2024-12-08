package player;

import java.util.ArrayList;
import java.util.List;

import building.Building;
import building.Colony;

public class Player {
    private final int id; // Unique ID for the player (1 or 2)
    private final String name; // Player's name
    private final Inventory inventory; // Player's inventory
    private Colony colony; // Colony owned by the player
    private List<Building> buildings = new ArrayList<>();

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
    
    public List<Building> getBuildings() {
        if (buildings.isEmpty()) {
            System.out.println("Player has no buildings.");
        } else {
            System.out.println("Player buildings: " + buildings.size());
            for (Building building : buildings) {
                System.out.println("Building: " + building.getName());
            }
        }
        return buildings;
    }


    public void addBuilding(Building building) {
        if (building != null) {
            buildings.add(building);
            System.out.println("Added building: " + building.getName() + " for player: " + name);
        } else {
            System.out.println("Attempted to add null building.");
        }
    }
    
    public void printInventory() {
        System.out.println("Inventory for Player: " + name);
        inventory.getResources().forEach((resource, amount) -> {
            System.out.println(resource + ": " + amount);
        });
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
