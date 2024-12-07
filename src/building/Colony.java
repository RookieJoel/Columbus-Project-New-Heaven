package building;

import board.Hexagon;
import player.Player;

public class Colony extends Building{

	public Colony(Hexagon position, Player player) {
        super("Colony", 8, 10, position, player); // Max upgrade level = 3, initial cost = 100
    }
	
}