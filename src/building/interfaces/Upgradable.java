package building.interfaces;

public interface Upgradable {
    boolean upgrade();
    int getUpgradeCost();
    boolean canUpgrade();
}
