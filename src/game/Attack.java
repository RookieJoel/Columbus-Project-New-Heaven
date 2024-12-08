package game;

import board.Hexagon;
import building.Building;
import building.MilitaryCamp;
import building.MissileFortress;
import building.interfaces.Attackable;
import pane.HexagonPane;
import pane.StatusPane;
import player.Player;

public class Attack {
	
	private Player currentPlayer;
    private HexagonPane hexagonPane;
    private StatusPane statusPane;
    public static boolean attackMode = false;
    
    public Attack(Player player, HexagonPane hexagonPane,StatusPane statusPane) {
        this.currentPlayer = player;
        this.hexagonPane = hexagonPane;
        this.statusPane = statusPane;
    }
    
    public void showAttackableBuilding() {
    	hexagonPane.setAllHexagonsClickEnabled(false);
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
    	if(hexagonPane.getSeletedHexagon().getBuilding() instanceof MilitaryCamp) {
    		for(Hexagon hexagon : hexagonPane.getAdjacentHexagons(hexagonPane.getSeletedHexagon())) {
    			if(hexagon.getBuilding() != null && hexagon.getBuilding().getPlayer() != currentPlayer) {
    				hexagon.highlightBorder();
    				hexagon.setClickEnabled(true);
    				GameController.getInstance().getHexagonPane().setAttackingState(2);
    			}
    		}
    		
    	}
    	if(hexagonPane.getSeletedHexagon().getBuilding() instanceof MissileFortress) {
    		for(Hexagon hexagon : hexagonPane.getHexagons()) {
    			if(hexagon.getBuilding() != null && hexagon.getBuilding().getPlayer() != currentPlayer) {
    				hexagon.highlightBorder();
    				hexagon.setClickEnabled(true);
    				GameController.getInstance().getHexagonPane().setAttackingState(2);
    			}
    		}
    	}
    }

}