package game;

import java.util.Map;

import board.Hexagon;
import board.Resource;
import building.Building;
import building.interfaces.Upgradable;
import pane.HexagonPane;
import pane.StatusPane;
import player.Player;

public class Build {
    private Player currentPlayer;
    private HexagonPane hexagonPane;
    private StatusPane statusPane;
    private boolean hasBuilt;
    
    public Build(Player player, HexagonPane hexagonPane,StatusPane statusPane) {
        this.currentPlayer = player;
        this.hexagonPane = hexagonPane;
        this.statusPane = statusPane;
        setHasBuilt(false);
    }
    
    // Method to attempt building on a hexagon
    public void canBuildHex() {
    	for(Hexagon hexagon : hexagonPane.getHexagons()) {
    		if(hexagon.getBuilding() != null  && hexagon.getBuilding().getPlayer() == currentPlayer ) {
    			for(Hexagon h : this.hexagonPane.getAdjacentHexagons(hexagon)) {
    				if(h.getBuilding() == null || (h.getBuilding() instanceof Upgradable && h.getBuilding().getPlayer() == currentPlayer)) {
    				h.highlightBorder();
    				h.setClickEnabled(true);
    				}
    			}
    		}
    	}
    }
    public void selectBuilding(Building building) {
    	if (building != null && hexagonPane.getSeletedHexagon() != null) {
    		attemptBuild(hexagonPane.getSeletedHexagon(), building);  // Ensure that this isn't null
    	} else {
    	    System.out.println("No building selected.");
    	}
    }
    
    public void resetBuildStatus() {
       setHasBuilt(false);// Reset build status at the start of a new turn
    }

    public boolean attemptBuild(Hexagon hexagon, Building building) {
        if (hasBuilt) {
            System.out.println("You have already built this turn!");
            return false; // Prevent multiple builds in a single turn
        }

        if (building == null || !canAffordBuilding(building)) {
            return false; // Cannot afford the building
        }

        // Deduct resources and build the building
        Map<Resource, Integer> cost = building.getCost();
        for (Map.Entry<Resource, Integer> entry : cost.entrySet()) {
            currentPlayer.getInventory().removeResource(entry.getKey(), entry.getValue());
        }

        hexagon.setBuilding(building);
        building.setPosition(hexagon); // Ensure the position is set
        currentPlayer.addBuilding(building); // Add the building to the player's list
        statusPane.updateResources(currentPlayer);

        // Reset hexagons and mark as built
        hexagonPane.setSeletedHexagon(null);
        hexagonPane.setAllHexagonsClickEnabled(false);
        hexagonPane.resetHexagonBorders();

        setHasBuilt(true); // Mark as built for this turn
        return true;
    }




    // Check if the player can afford the building
    public boolean canAffordBuilding(Building building) {
        Map<Resource, Integer> cost = building.getCost();

        for (Map.Entry<Resource, Integer> entry : cost.entrySet()) {
            Resource resource = entry.getKey();
            int requiredAmount = entry.getValue();

            // Check if player has enough resources in their inventory
            if (currentPlayer.getInventory().getResource(resource) < requiredAmount) {
            	System.out.println(currentPlayer.getInventory().getResource(resource));
            	System.out.println(requiredAmount);
                return false; // Not enough resources
            }
        }

        return true; // Enough resources
    }

	public boolean isHasBuilt() {
		return hasBuilt;
	}

	public void setHasBuilt(boolean hasBuilt) {
		this.hasBuilt = hasBuilt;
	}
    
    



}