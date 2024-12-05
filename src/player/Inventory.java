package player;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<String, Integer> resources;

    public Inventory() {
        this.resources = new HashMap<>();
        initializeResources();
    }

    // Initialize all resources to 0
    private void initializeResources() {
        resources.put("VIBRANIUM", 0);
        resources.put("OIL", 0);
        resources.put("COPPER", 0);
        resources.put("URANIUM", 0);
        resources.put("JOJOLIUM", 0);
    }

    public Map<String, Integer> getResources() {
        return resources;
    }

    public void addResource(String resource, int amount) {
        resources.put(resource, resources.getOrDefault(resource, 0) + amount);
    }

    public boolean removeResource(String resource, int amount) {
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
