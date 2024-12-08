package building;


import java.util.Map;

import board.Hexagon;
import board.Resource;
import building.interfaces.Attackable;
import building.interfaces.Upgradable;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import player.Player;

public class MilitaryCamp extends Building implements Attackable , Upgradable{
	private int atk;
		
	public MilitaryCamp(Hexagon position, Player player) {
		super("MilitaryCamp", 3, 1, position, player);
		this.setAtk(1);
		this.addCost(Resource.VIBRANIUM, 3);
		
		// TODO Auto-generated constructor stub
	}


	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
	}

	@Override
	public Map<Resource, Integer> getUpgradeCost() {
		MissileFortress missileFortress = new MissileFortress(null, null);
		
		// TODO Auto-generated method stub
		return missileFortress.getCost();
	}

	@Override
	public void attack(Building target) {
		// TODO Auto-generated method stub
		
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	@Override
	public Node createShape(double radius) {
		Polygon star = new Polygon();
        double outerRadius = radius * 0.4;
        double innerRadius = radius * 0.2;

        int points = 10; // Star with 10 points (5 outer, 5 inner)
        for (int i = 0; i < points; i++) {
            double angle = Math.toRadians((360 / points) * i - 90);
            double r = (i % 2 == 0) ? outerRadius : innerRadius;
            star.getPoints().addAll(
                r * Math.cos(angle), // x
                r * Math.sin(angle)  // y
            );
        }

        star.setFill(getPlayerColor());
        star.setStroke(Color.WHITE);
        star.setStrokeWidth(2);
        return star;
	}

	@Override
	public boolean canAttack(Building target) {
		// TODO Auto-generated method stub
		return false;
	}

}