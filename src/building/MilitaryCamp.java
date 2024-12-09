package building;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import board.Hexagon;
import board.Resource;
import building.interfaces.Attackable;
import building.interfaces.Upgradable;
import javafx.scene.Node;
import pane.HexagonPane;
import player.Player;

public class MilitaryCamp extends Building implements Attackable,Upgradable{
	private int atk;
		
	public MilitaryCamp(Hexagon position, Player player) {
		super("MilitaryCamp", 5, position, player);
		this.setAtk(3);
		this.addCost(Resource.VIBRANIUM, 3);
	}

	@Override
	public void upgrade() {
		MissileFortress missileFortress = new MissileFortress(getPosition(), getPlayer());
		this.getPosition().setBuilding(missileFortress);
		
	}

	@Override
	public boolean canUpgrade() {
		MissileFortress m = new MissileFortress(null, null);
		Map<Resource, Integer> cost = m.getCost();

        for (Map.Entry<Resource, Integer> entry : cost.entrySet()) {
            Resource resource = entry.getKey();
            int requiredAmount = entry.getValue();

            // Check if player has enough resources in their inventory
            if (getPlayer().getInventory().getResource(resource) < requiredAmount) {
            	System.out.println(getPlayer().getInventory().getResource(resource));
            	System.out.println(requiredAmount);
                return false; // Not enough resources
            }
        }

        return true; // Enough resources
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
		this.atk = Math.max(0, atk);
	}

	@Override
	public Node createShape(double radius) {
	    return createHexagonalShape(radius, "/images/MilitaryCamp.png");

	}
	
	 @Override
	    public List<Hexagon> getTargetableHexagons(HexagonPane hexagonPane, Player currentPlayer) {
	        List<Hexagon> targetableHexagons = new ArrayList<>();
	        for (Hexagon hexagon : hexagonPane.getAdjacentHexagons(hexagonPane.getSeletedHexagon())) {
	            if (hexagon.getBuilding() != null && hexagon.getBuilding().getPlayer() != currentPlayer) {
	                targetableHexagons.add(hexagon);
	            }
	        }
	        return targetableHexagons;
	    }


	
}