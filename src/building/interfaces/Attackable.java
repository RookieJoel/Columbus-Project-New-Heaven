package building.interfaces;

import java.util.List;

import board.Hexagon;
import building.Building;
import pane.HexagonPane;
import player.Player;

public interface Attackable {
    void attack(Building target);
    List<Hexagon> getTargetableHexagons(HexagonPane hexagonPane, Player currentPlayer);
}
