package building;



import board.Hexagon;
import board.Resource;
import building.interfaces.Attackable;
import game.GameController;
import javafx.scene.Node;
import player.Player;

public class MilitaryCamp extends Building implements Attackable{
	private int atk;
		
	public MilitaryCamp(Hexagon position, Player player) {
		super("MilitaryCamp", 3, 1, position, player);
		this.setAtk(3);
		this.addCost(Resource.VIBRANIUM, 3);
		
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
	    return createHexagonalShape(radius, "/images/MilitaryCamp.png");

	}


}