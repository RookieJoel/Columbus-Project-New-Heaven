package building;

import java.util.Map;

import board.Hexagon;
import building.interfaces.Produceable;
import building.interfaces.Upgradable;

public class Querry extends Building implements Upgradable,Produceable{

	public Querry(String name, int hp, int prosperityPoints, Hexagon position, int maxUpgradeLevel,
			int initialUpgradeCost) {
		super(name, hp, prosperityPoints, position, maxUpgradeLevel, initialUpgradeCost);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, Integer> produceResources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> getProductionRate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isProducing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean upgrade() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
