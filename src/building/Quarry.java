package building;

import java.util.Map;

import board.Hexagon;
import board.Resource;
import building.interfaces.Produceable;
import building.interfaces.Upgradable;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import player.Player;

public class Quarry extends Building implements Upgradable,Produceable{

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
	public void upgrade() {
		 
	}

	@Override
	public Map<Resource, Integer> getUpgradeCost() {
		Factory factory = new Factory(null, null);
		return factory.getCost();
	}

	@Override
	public Node createShape(double radius) {
		 Polygon triangle = new Polygon();
	        double size = radius * 0.4;
	        triangle.getPoints().addAll(
	            0.0, -size,  // Top point
	            -size, size, // Bottom-left
	            size, size   // Bottom-right
	        );
	        triangle.setFill(getPlayerColor());
	        triangle.setStroke(Color.WHITE);
	        triangle.setStrokeWidth(2);
	        return triangle;
	}
}


