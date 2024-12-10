package building;

import java.util.Map;

import board.Hexagon;
import board.Resource;
import building.interfaces.Produceable;
import building.interfaces.Upgradable;
import javafx.scene.Node;
import player.Player;

public class Quarry extends Building implements Produceable,Upgradable{

	public Quarry(Hexagon position, Player player) {
		super("Quarry", 3, position, player);
		this.addCost(Resource.COPPER, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void produceResources(Player player) {
	    if (getPosition() != null) {
	        Resource resource = getPosition().getResource();
	        player.getInventory().addResource(resource, 1);
	        System.out.println("Produced 1 unit of " + resource + " for player " + player.getName());
	    } else {
	        System.out.println("Error: Quarry position is null.");
	    }
	}

	@Override
	public Node createShape(double radius) {
	    return createHexagonalShape(radius, "/images/quarry.png");

	}

	@Override
	public void upgrade() {
		Factory factory = new Factory(getPosition(), getPlayer());
		getPlayer().getBuildings().remove(this);
		this.getPosition().setBuilding(factory);
		
	}


	@Override
	public boolean canUpgrade() {
		Factory f = new Factory(null, null);
		Map<Resource, Integer> cost = f.getCost();

        for (Map.Entry<Resource, Integer> entry : cost.entrySet()) {
            Resource resource = entry.getKey();
            int requiredAmount = entry.getValue();

            // Check if player has enough resources in their inventory
            if (getPlayer().getInventory().getResource(resource) < requiredAmount) {
            	System.out.println(getPlayer().getInventory().getResource(resource));
            	System.out.println(requiredAmount);
                return false; // Not enough resources
            }
        }

        return true; // Enough resources
	}

}