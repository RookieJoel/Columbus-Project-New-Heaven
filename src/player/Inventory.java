package player;

import java.util.HashMap;
import java.util.Map;

import board.Resource;

public class Inventory {
    private final Map<Resource, Integer> RESOURCES;

    public Inventory() {
        this.RESOURCES = new HashMap<>();
        initializeResources();
    }

    // Initialize all resources to 0
    private void initializeResources() {
    	RESOURCES.put(Resource.VIBRANIUM, 3);
    	RESOURCES.put(Resource.OIL, 0);
    	RESOURCES.put(Resource.COPPER, 3);
    	RESOURCES.put(Resource.URANIUM, 0);
        RESOURCES.put(Resource.JOJOLIUM, 0);
    }

    public Map<Resource, Integer> getResources() {
        return RESOURCES;
    }

    public void addResource(Resource resource, int amount) {
    	RESOURCES.put(resource, RESOURCES.getOrDefault(resource, 0) + amount);
    }

    public boolean removeResource(Resource resource, int amount) {
        // ตรวจสอบว่ามี Resource เพียงพอ
        if (RESOURCES.getOrDefault(resource, 0) < amount) {
            return false; // ไม่เพียงพอ
        }

        // ลดจำนวน Resource
        RESOURCES.put(resource, RESOURCES.get(resource) - amount);
        return true;
    }

    public int getResource(Resource resource) {
        return RESOURCES.getOrDefault(resource, 0);
    }
}