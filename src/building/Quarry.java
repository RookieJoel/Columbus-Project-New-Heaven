package building;

import board.Hexagon;
import board.Resource;
import building.interfaces.Produceable;
import javafx.scene.Node;
import player.Player;

public class Quarry extends Building implements Produceable{

	public Quarry(Hexagon position, Player player) {
		super("Quarry", 3, 1, position, player);
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

}


