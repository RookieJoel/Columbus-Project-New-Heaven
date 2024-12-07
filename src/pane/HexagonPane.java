package pane;

import board.Hexagon;
import board.Resource;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HexagonPane extends Group {
    private final List<Hexagon> hexagons = new ArrayList<>();
    private List<Integer> uniqueNumbers;
    private final Random random = new Random();
    private static final int GAPX = 10;
    private static final int GAPY = 5;
    private static final int HexagonRadius = 100;

    public HexagonPane() {
        createHexagons();
    }

    private void createHexagons() {
    	
    	uniqueNumbers = generateUniqueNumbers(1, 19);
    	
        Hexagon mainHexagon = new Hexagon(HexagonRadius, 10, Resource.JOJOLIUM, 0, 0);
        mainHexagon.setOnClick(() -> onHexagonClick(mainHexagon));
        hexagons.add(mainHexagon);

        int x = 0;
        int n = 4;

        for (int y = -3; y < 4; y++) {
            if (y == 1 || y == -1) x = 1;
            if (y == 2 || y == -2) x = 2;
            if (y == 3 || y == -3) x = 1;
            if (y == 0) x = 2;

            Hexagon hexagon = new Hexagon(HexagonRadius, getUniqueNumber(), Resource.randomResource(), x, y);
            hexagon.setTranslateY((mainHexagon.getOffsetY() + GAPY) * y);
            hexagon.setTranslateX((mainHexagon.getOffsetX() + GAPX) * x);
            hexagon.setOnClick(() -> onHexagonClick(hexagon));
            hexagons.add(hexagon);

            Hexagon mirrorHexagon = new Hexagon(HexagonRadius, getUniqueNumber(), Resource.randomResource(), -x, y);
            mirrorHexagon.setTranslateY((mainHexagon.getOffsetY() + GAPY) * y);
            mirrorHexagon.setTranslateX((mainHexagon.getOffsetX() + GAPX) * -x);
            mirrorHexagon.setOnClick(() -> onHexagonClick(mirrorHexagon));
            hexagons.add(mirrorHexagon);
        }

        while (n != 0) {
            int y = 0;
            x = 0;
            if (n % 2 == 0) y = n;
            if (n % 2 == 1) y = -n - 1;

            Hexagon hexagon = new Hexagon(HexagonRadius, getUniqueNumber(), Resource.randomResource(), x, y);
            hexagon.setTranslateY((mainHexagon.getOffsetY() + GAPY) * y);
            hexagon.setTranslateX((mainHexagon.getOffsetX() + GAPX) * x);
            hexagon.setOnClick(() -> onHexagonClick(hexagon));
            hexagons.add(hexagon);
            n--;
        }

        this.getChildren().addAll(hexagons);
        this.setTranslateY(100);
    }

    public List<Hexagon> getHexagons() {
        return hexagons;
    }

    private int getUniqueNumber() {
        return uniqueNumbers.remove(0); // Retrieve and remove the first number
    }

    private List<Integer> generateUniqueNumbers(int start, int end) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = start; i <= end; i++) {
        	if(i != 10) numbers.add(i);
        }
        Collections.shuffle(numbers); // Randomize the order
        return numbers;
    }
    
    public void highlightRandomHexagon() {
        resetHexagonBorders(); // Reset all hexagon borders

        // Randomly select a hexagon to highlight
        Hexagon randomHexagon = hexagons.get(random.nextInt(hexagons.size()));
        randomHexagon.highlightBorder();
    }

    public void resetHexagonBorders() {
        // Reset all hexagons' borders to default
        for (Hexagon hexagon : hexagons) {
            hexagon.resetBorder();
        }
    }
    
    private void onHexagonClick(Hexagon hexagon) {
        int hexX = hexagon.getX();
        int hexY = hexagon.getY();
        System.out.println("Hexagon clicked at position: (" + hexX + ", " + hexY + ")");
    }

    public void setAllHexagonsClickEnabled(boolean enabled) {
        for (Hexagon hexagon : hexagons) {
            hexagon.setClickEnabled(enabled);
        }
    }

    public void highlightHexagon(int sum) {
        // Find and highlight the hexagon with the matching number
        for (Hexagon hexagon : hexagons) {
            if (hexagon.getNumber() == sum) {
                hexagon.highlightBorder();
                break;
            }
        }
    }
    
    public Hexagon getLeftmostHexagon() {
    	for (Hexagon hexagon : hexagons) {
            if(hexagon.getX() == 2 && hexagon.getY() == 0) {
            	return hexagon;
            }   
    	}
    	return null;
    }

    public Hexagon getRightmostHexagon() {
    	for (Hexagon hexagon : hexagons) {
            if(hexagon.getX() == -2 && hexagon.getY() == 0) {
            	return hexagon;
            }   
    	}
    	return null;
    }
    public List<Hexagon> getAdjacentHexagons(Hexagon hex) {
        List<Hexagon> adjacentHexes = new ArrayList<>();
        int x = hex.getX();
        int y = hex.getY();

        for (Hexagon h : hexagons) {
            if (Math.abs(h.getX() - x) <= 1 && Math.abs(h.getY() - y) <= 2 && !(h.getX() == x && h.getY() == y)) {
                adjacentHexes.add(h);
            }
        }
        return adjacentHexes;
    }
    
    // Add resources from highlighted tiles to the current player's inventory
    public void addResourcesToPlayer(Player currentPlayer, int tile1, int tile2) {
        for (Hexagon hex : hexagons) {
            if (hex.getNumber() == tile1 || hex.getNumber() == tile2) {
                Resource resource = hex.getResource(); // Get the resource of the tile
                currentPlayer.getInventory().addResource(resource, 1); // Add 1 unit of the resource to the player's inventory
            }
        }
    }

    
    
    
}