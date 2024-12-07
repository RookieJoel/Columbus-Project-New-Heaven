package game;

import java.util.Map;

import board.Hexagon;
import board.Resource;
import building.Building;
import player.Player;

public class Build {
	private boolean isBuildingMode = false;
    private Player currentPlayer;

    public Build(Player player) {
        this.currentPlayer = player;
    }

    // Method to attempt building on a hexagon
    public boolean attemptBuild(Hexagon hexagon, Building building) {
    	
        if (building == null || !canAffordBuilding(building)) {
            return false; // Cannot afford the building
        }
        
        // If enough resources, proceed with building
        // Deduct cost from player's inventory
        Map<Resource, Integer> cost = building.getCost();
    	for (Map.Entry<Resource, Integer> entry : cost.entrySet()) {
            Resource resource = entry.getKey();
            int requiredAmount = entry.getValue();
            
			currentPlayer.getInventory().removeResource(resource,requiredAmount);
    	}
        
        
        // Build the building (e.g., add it to a list of built buildings)
        hexagon.setBuilding(building); // Assume the Hexagon class has a `setBuilding()` method

        return true;
    }

    // Check if the player can afford the building
    private boolean canAffordBuilding(Building building) {
        Map<Resource, Integer> cost = building.getCost();

        for (Map.Entry<Resource, Integer> entry : cost.entrySet()) {
            Resource resource = entry.getKey();
            int requiredAmount = entry.getValue();

            // Check if player has enough resources in their inventory
            if (currentPlayer.getInventory().getResource(resource) < requiredAmount) {
                return false; // Not enough resources
            }
        }

        return true; // Enough resources
    }

    public void setBuildingMode(boolean isBuilding) {
        this.isBuildingMode = isBuilding;
    }

    // Check if the game is in building mode
    public boolean isBuildingMode() {
        return isBuildingMode;
    }
    

}