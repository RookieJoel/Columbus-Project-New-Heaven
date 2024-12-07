package player;

import java.util.HashMap;
import java.util.Map;

import board.Resource;

public class Inventory {
    private final Map<Resource, Integer> resources;

    public Inventory() {
        this.resources = new HashMap<>();
        initializeResources();
    }

    // Initialize all resources to 0
    private void initializeResources() {
        resources.put(Resource.VIBRANIUM, 1);
        resources.put(Resource.OIL, 0);
        resources.put(Resource.COPPER, 2);
        resources.put(Resource.URANIUM, 1);
        resources.put(Resource.JOJOLIUM, 0);
    }

    public Map<Resource, Integer> getResources() {
        return resources;
    }

    public void addResource(Resource resource, int amount) {
        resources.put(resource, resources.getOrDefault(resource, 0) + amount);
    }

    public boolean removeResources(Map<Resource, Integer> cost) {
        // Check if the player has enough resources for all the required amounts
        for (Map.Entry<Resource, Integer> entry : cost.entrySet()) {
            Resource resource = entry.getKey();
            int amountRequired = entry.getValue();

            if (getResource(resource) < amountRequired) {
                return false; // Not enough resources
            }
        }

        // Deduct the resources after confirming availability
        for (Map.Entry<Resource, Integer> entry : cost.entrySet()) {
            Resource resource = entry.getKey();
            int amountRequired = entry.getValue();
            resources.put(resource, resources.get(resource) - amountRequired);
        }

        return true;
    }


    public int getResource(Resource resource) {
        return resources.getOrDefault(resource, 0);
    }
}