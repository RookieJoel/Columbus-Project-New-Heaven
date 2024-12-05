package building.interfaces;

public interface Attackable {
    void attack(int damage);
    boolean isDestroyed();
    int getCurrentHP();
}
