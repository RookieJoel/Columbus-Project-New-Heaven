package building;

import java.util.HashMap;
import java.util.Map;

import board.Hexagon;
import board.Resource;
import building.interfaces.Produceable;
import building.interfaces.Upgradable;
import player.Player;

public class Quarry extends Building implements Upgradable,Produceable{

	public Quarry(Hexagon position) {
		super("Quarry", 3, 1, position);
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
		Map<Resource,Integer> m = new HashMap<>();
		m.put(Resource.OIL, 3);
		return m;
		
	}

}