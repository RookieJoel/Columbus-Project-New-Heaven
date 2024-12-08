package game;

import building.Building;
import building.interfaces.Produceable;
import pane.StatusPane;
import player.Player;

public class Produce {
    private final Player player;
    private final StatusPane statusPane;

    public Produce(Player player, StatusPane statusPane) {
        this.player = player;
        this.statusPane = statusPane;
    }

    public boolean execute() {
        boolean hasProduced = false;

        // Iterate through all buildings of the player
        for (Building building : player.getBuildings()) {
            if (building instanceof Produceable) {
                System.out.println("Producing resources from building: " + building.getName());
                ((Produceable) building).produceResources(player);
                hasProduced = true;
            }
        }

        if (hasProduced) {
            System.out.println("Resources produced. Updating status pane.");
            statusPane.updateResources(player);
        } else {
            System.out.println("No produceable buildings found.");
        }

        return hasProduced; // Return whether any resources were produced
    }
}
