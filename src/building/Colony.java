package building;

import board.Hexagon;
import javafx.scene.Node;
import player.Player;

public class Colony extends Building{

	public Colony(Hexagon position, Player player) {
        super("Colony", 15, 10, position, player); // Max upgrade level = 3, initial cost = 100
    }

	@Override
	public Node createShape(double radius) {
	    return createHexagonalShape(radius, "/images/colony.png");
	}

	
}