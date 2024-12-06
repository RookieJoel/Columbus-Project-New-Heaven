package building.interfaces;

import java.util.Map;

import board.Resource;

public interface Upgradable {
    void upgrade();
    Map<Resource, Integer> getUpgradeCost(); 
}
