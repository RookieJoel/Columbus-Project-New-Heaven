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
        resources.put(Resource.VIBRANIUM, 999);
        resources.put(Resource.OIL, 999);
        resources.put(Resource.COPPER, 999);
        resources.put(Resource.URANIUM, 999);
        resources.put(Resource.JOJOLIUM, 999);
    }

    public Map<Resource, Integer> getResources() {
        return resources;
    }

    public void addResource(Resource resource, int amount) {
        resources.put(resource, resources.getOrDefault(resource, 0) + amount);
    }

    public boolean removeResource(Resource resource, int amount) {
        // ตรวจสอบว่ามี Resource เพียงพอ
        if (resources.getOrDefault(resource, 0) < amount) {
            return false; // ไม่เพียงพอ
        }

        // ลดจำนวน Resource
        resources.put(resource, resources.get(resource) - amount);
        return true;
    }



    public int getResource(Resource resource) {
        return resources.getOrDefault(resource, 0);
    }
}