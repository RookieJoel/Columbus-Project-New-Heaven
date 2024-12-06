package building;

import board.Hexagon;
import board.Resource;
import building.interfaces.Produceable;
import player.Player;

public class Factory extends Building implements Produceable{

	public Factory( Hexagon position) {
		super("Factory", 10, 3, position);
		this.addCost(Resource.OIL, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void produceResources(Player player) {
		player.getInventory().addResource(this.getPosition().getResource(), 3);
	}



}