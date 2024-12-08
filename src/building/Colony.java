package building;

import board.Hexagon;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import player.Player;

public class Colony extends Building{

	public Colony(Hexagon position, Player player) {
        super("Colony", 8, 10, position, player); // Max upgrade level = 3, initial cost = 100
    }

	@Override
	public Node createShape(double radius) {
		Circle circle = new Circle(radius * 0.3); // Circle is 20% of hexagon size
        circle.setFill(getPlayerColor());
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(2);
        return circle;
	}
	
}