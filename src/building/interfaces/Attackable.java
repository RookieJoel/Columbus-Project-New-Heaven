package building.interfaces;

public interface Attackable {
    void takeDamage(int damage);
    boolean isDestroyed();
    int getCurrentHP();
}
