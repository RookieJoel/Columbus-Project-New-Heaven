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
        resources.put(Resource.VIBRANIUM, 0);
        resources.put(Resource.OIL, 0);
        resources.put(Resource.COPPER, 0);
        resources.put(Resource.URANIUM, 0);
        resources.put(Resource.JOJOLIUM, 0);
    }

    public Map<Resource, Integer> getResources() {
        return resources;
    }

    public void addResource(Resource resource, int amount) {
        resources.put(resource, resources.getOrDefault(resource, 0) + amount);
    }

    public boolean removeResource(Resource resource, int amount) {
        if (resources.getOrDefault(resource, 0) >= amount) {
            resources.put(resource, resources.get(resource) - amount);
            return true;
        }
        return false; 
    }

    public int getResource(String resource) {
        return resources.getOrDefault(resource, 0);
    }
}
