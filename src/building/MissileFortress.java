package building;

import board.Hexagon;
import board.Resource;
import building.interfaces.Attackable;
import javafx.scene.Node;
import player.Player;

public class MissileFortress extends Building implements Attackable{
	private int atk;
	
	public MissileFortress(Hexagon position, Player player) {
		super("MissileFortress", 5, position, player);
		this.setAtk(15);
		this.addCost(Resource.URANIUM, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Building target) {
	    if (target != null) {
	        target.takeDamage(atk); // Deal damage to the target
	        if (target.isDestroyed()) {
	            target.getPosition().setBuilding(null); // Remove the building from the board
	        }
	    }
	}


	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	@Override
    public Node createShape(double radius) {
	    return createHexagonalShape(radius, "/images/missile.png");
    }


}