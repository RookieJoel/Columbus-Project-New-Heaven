package game;

import java.util.List;

import board.Hexagon;
import building.Building;
import building.MilitaryCamp;
import building.MissileFortress;
import building.interfaces.Attackable;
import pane.HexagonPane;
import player.Player;

public class Attack {
	
	private Player currentPlayer;
    private HexagonPane hexagonPane;
    public static boolean attackMode = false;
    
    public Attack(Player player, HexagonPane hexagonPane) {
        this.currentPlayer = player;
        this.hexagonPane = hexagonPane;
    }
    
    public void showAttackableBuilding() {
    	hexagonPane.resetHexagonBorders();
    	hexagonPane.setAllHexagonsClickEnabled(false);
    	hexagonPane.setAttackingState(1);
    	for(Hexagon hexagon : hexagonPane.getHexagons()) {
    		if(hexagon.getBuilding() instanceof Attackable && hexagon.getBuilding().getPlayer() == currentPlayer) {
    			hexagon.highlightBorder();
    			hexagon.setClickEnabled(true);
    			attackMode = true;
    		}
    	}
    }
    
    public void showTarketBuilding() {
        hexagonPane.resetHexagonBorders();
        hexagonPane.setAllHexagonsClickEnabled(false);

        Hexagon selectedHexagon = hexagonPane.getSeletedHexagon();
        if (selectedHexagon == null || selectedHexagon.getBuilding() == null) {
            System.out.println("No building selected or building is null.");
            return;
        }

        Building selectedBuilding = selectedHexagon.getBuilding();
        if (selectedBuilding instanceof Attackable attackableBuilding) {
            List<Hexagon> targetableHexagons = attackableBuilding.getTargetableHexagons(hexagonPane, currentPlayer);
            for (Hexagon targetHexagon : targetableHexagons) {
                targetHexagon.highlightBorder();
                targetHexagon.setClickEnabled(true);
            }
            hexagonPane.setAttackingState(2); // Update the attacking state
        } else {
            System.out.println("Selected building is not attackable.");
        }
    }

    

    

}