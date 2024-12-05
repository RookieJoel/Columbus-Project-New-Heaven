package building.interfaces;

import java.util.Map;

public interface Produceable {
    Map<String, Integer> produceResources();
    Map<String, Integer> getProductionRate();
    boolean isProducing();
}
