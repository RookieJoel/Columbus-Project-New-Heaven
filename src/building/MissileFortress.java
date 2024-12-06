package building;

import board.Hexagon;
import board.Resource;
import building.interfaces.Attackable;

public class MissileFortress extends Building implements Attackable{
	private int atk;
	
	public MissileFortress(Hexagon position) {
		super("MissileFortress", 5, 3, position);
		this.atk = 3;
		this.addCost(Resource.URANIUM, 4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Building target) {
		target.takeDamage(1);
	}

	@Override
	public boolean canAttack(Building target) {
		// TODO Auto-generated method stub
		return true;
	}


}