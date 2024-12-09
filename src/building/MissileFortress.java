package building;

import java.util.ArrayList;
import java.util.List;

import board.Hexagon;
import board.Resource;
import building.interfaces.Attackable;
import javafx.scene.Node;
import pane.HexagonPane;
import player.Player;

public class MissileFortress extends Building implements Attackable{
	private int atk;
	
	public MissileFortress(Hexagon position, Player player) {
		super("MissileFortress", 8, position, player);
		this.setAtk(3);
		this.addCost(Resource.URANIUM, 8);
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
	
	@Override
    public List<Hexagon> getTargetableHexagons(HexagonPane hexagonPane, Player currentPlayer) {
        List<Hexagon> targetableHexagons = new ArrayList<>();
        for (Hexagon hexagon : hexagonPane.getHexagons()) {
            if (hexagon.getBuilding() != null && hexagon.getBuilding().getPlayer() != currentPlayer) {
                targetableHexagons.add(hexagon);
            }
        }
        return targetableHexagons;
    }


	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = Math.max(0, atk);
	}

	@Override
    public Node createShape(double radius) {
	    return createHexagonalShape(radius, "/images/missile.png");
    }


}