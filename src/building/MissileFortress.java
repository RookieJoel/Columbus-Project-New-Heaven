package building;

import board.Hexagon;
import board.Resource;
import building.interfaces.Attackable;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
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
	public Node createShape(double radius) {
		Polygon missileShape = new Polygon();
        double size = radius * 0.3;
        missileShape.getPoints().addAll(
            0.0, -size,       // Tip
            -size / 2, size,  // Bottom-left
            size / 2, size    // Bottom-right
        );
        missileShape.setFill(Color.RED);
        missileShape.setStroke(Color.BLACK);
        missileShape.setStrokeWidth(2);
        return missileShape;
    }

	@Override
	public boolean canAttack(Building target) {
		// TODO Auto-generated method stub
		return false;
	}

}