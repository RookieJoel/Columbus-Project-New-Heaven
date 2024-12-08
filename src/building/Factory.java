package building;

import board.Hexagon;
import board.Resource;
import building.interfaces.Produceable;
import javafx.scene.Node;

import player.Player;

public class Factory extends Building implements Produceable{

	public Factory( Hexagon position, Player player) {
		super("Factory", 10, 3, position, player);
		this.addCost(Resource.OIL, 4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void produceResources(Player player) {
		player.getInventory().addResource(this.getPosition().getResource(), 3);
	}

	  @Override
	    public Node createShape(double radius) {
		    return createHexagonalShape(radius, "/images/factory.png");

	    }



}