package building;

import java.util.Map;

import board.Hexagon;
import board.Resource;
import building.interfaces.Attackable;
import building.interfaces.Upgradable;


public class MilitaryCamp extends Building implements Attackable , Upgradable{

	public MilitaryCamp(Hexagon position) {
		super("MilitaryCamp", 6, 2, position);
		this.addCost(Resource.VIBRANIUM, 3);
		this.addCost(Resource.OIL, 1);
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Resource, Integer> getUpgradeCost() {
		// TODO Auto-generated method stub
		MissileFortress m = new MissileFortress(getPosition());
		
		return m.getCost();
	}

	@Override
	public void attack(Building target) {
		// TODO Auto-generated method stub
		if(canAttack(target)) {
			target.takeDamage(2);
		}
		
	}

	@Override
	public boolean canAttack(Building target) {
	    if (target == null || target.isDestroyed()) {
	        return false; // Target is invalid or already destroyed
	    }

	    // Get positions of current building and target
	    Hexagon currentHex = this.getPosition();
	    Hexagon targetHex = target.getPosition();

	    // Get Cartesian coordinates of the hexagons
	    double x1 = currentHex.getTranslateX();
	    double y1 = currentHex.getTranslateY();
	    double x2 = targetHex.getTranslateX();
	    double y2 = targetHex.getTranslateY();

	    // Calculate Euclidean distance
	    double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

	    // Check if within attack range (<= radius * 2 for adjacent tiles)
	    return distance <= currentHex.getOffsetX() + currentHex.getOffsetY();
	}

	
}