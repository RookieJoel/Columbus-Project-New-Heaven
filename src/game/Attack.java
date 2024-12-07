package game;

import board.Hexagon;
import building.Building;
import building.interfaces.Attackable;
import player.Player;

public class Attack {
	
	private boolean attackMode = false;
	private Hexagon selectedBuilding = null;
	
	public void enterAttackMode() {
	    attackMode = true;
	    System.out.println("Select a building to attack from!");
	
	}
	
	public void selectBuilding(Hexagon hexagon,Player CurrentPlayer) {
	    if (attackMode && hexagon.getBuilding() instanceof Attackable && hexagon.getBuilding().getPlayer() == CurrentPlayer) {
	        selectedBuilding = hexagon ;
	        //building.highlight(); // Optional: visually indicate the selection
	    } else {
	        System.out.println("This building cannot attack or does not belong to you.");
	    }
	}
}