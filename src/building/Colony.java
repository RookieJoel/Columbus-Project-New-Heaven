package building;

import board.Hexagon;
import javafx.scene.Node;
import player.Player;

public class Colony extends Building{

	public Colony(Hexagon position, Player player) {
        super("Colony", 20, position, player); 
    }

	@Override
	public Node createShape(double radius) {
	    return createHexagonalShape(radius, "/images/colony.png");
	}

	
}