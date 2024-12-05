package player;

import building.Colony;

public class PlayerStats {
    private Colony colony; // Colony associated with the player
    private int prosperityPoints; // Player's prosperity points

    public PlayerStats(Colony colony, int prosperityPoints) {
        this.colony = colony;
        this.prosperityPoints = prosperityPoints;
    }

    // HP is dynamically retrieved from the Colony
    public int getHp() {
        return colony != null ? colony.getHp() : 0; // Return 0 if no colony is assigned
    }

    public void setHp(int hp) {
        if (colony != null) {
            colony.setHp(hp); // Update the colony's HP
        }
    }

    public void reduceHp(int damage) {
        if (colony != null) {
            colony.takeDamage(damage); // Apply damage to the colony
        }
    }

    public int getProsperityPoints() {
        return prosperityPoints;
    }

    public void addProsperityPoints(int points) {
        this.prosperityPoints += points;
    }

    public void setColony(Colony colony) {
        this.colony = colony;
    }

    public Colony getColony() {
        return colony;
    }
}
