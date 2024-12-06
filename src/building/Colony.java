package building;

import board.Hexagon;

public class Colony extends Building{

	public Colony(Hexagon position) {
        super("Colony", 8, 10, position); // Max upgrade level = 3, initial cost = 100
    }
	
}