package building;


import java.util.Map;

import board.Hexagon;
import board.Resource;
import building.interfaces.Attackable;
import building.interfaces.Upgradable;
import player.Player;

public class MilitaryCamp extends Building implements Attackable , Upgradable{
	private int atk;
		
	public MilitaryCamp(Hexagon position, Player player) {
		super("MilitaryCamp", 3, 1, position, player);
		this.setAtk(1);
		this.addCost(Resource.VIBRANIUM, 3);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
	}

	@Override
	public Map<Resource, Integer> getUpgradeCost() {
		MissileFortress missileFortress = new MissileFortress(null, null);
		
		// TODO Auto-generated method stub
		return missileFortress.getCost();
	}

	@Override
	public void attack(Building target) {
		// TODO Auto-generated method stub
		
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	@Override
	public boolean canAttack(Building target) {
		// TODO Auto-generated method stub
		return false;
	}

}