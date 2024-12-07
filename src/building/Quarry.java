package building;

import java.util.Map;

import board.Hexagon;
import board.Resource;
import building.interfaces.Produceable;
import building.interfaces.Upgradable;
import player.Player;

public class Quarry extends Building implements Upgradable,Produceable{

	public Quarry(Hexagon position, Player player) {
		super("Quarry", 3, 1, position, player);
		this.addCost(Resource.COPPER, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void produceResources(Player player) {
		// TODO Auto-generated method stub
		player.getInventory().addResource(this.getPosition().getResource(), 1);
	}


	@Override
	public void upgrade() {
		 
	}

	@Override
	public Map<Resource, Integer> getUpgradeCost() {
		Factory factory = new Factory(null, null);
		return factory.getCost();
	}

}