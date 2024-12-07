package building;

import board.Hexagon;
import board.Resource;
import building.interfaces.Attackable;
import player.Player;

public class MissileFortress extends Building implements Attackable{
	private int atk;
	
	public MissileFortress(Hexagon position, Player player) {
		super("MissileFortress", 5, 3, position, player);
		this.setAtk(3);
		this.addCost(Resource.URANIUM, 3);
		// TODO Auto-generated constructor stub
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