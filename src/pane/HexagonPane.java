package pane;

import board.Hexagon;
import board.Resource;
import javafx.scene.Group;

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
            hexagons.add(hexagon);

            Hexagon mirrorHexagon = new Hexagon(HexagonRadius, getUniqueNumber(), Resource.randomResource(), -x, y);
            mirrorHexagon.setTranslateY((mainHexagon.getOffsetY() + GAPY) * y);
            mirrorHexagon.setTranslateX((mainHexagon.getOffsetX() + GAPX) * -x);
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

    public void highlightHexagon(int sum) {
        // Find and highlight the hexagon with the matching number
        for (Hexagon hexagon : hexagons) {
            if (hexagon.getNumber() == sum) {
                hexagon.highlightBorder();
                break;
            }
        }
    }
}
