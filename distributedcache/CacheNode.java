package distributedcache;

import java.util.HashMap;
import java.util.Map;

public class CacheNode {
    private int capacity;
    private Map<String, String> storage;
    private EvictionPolicy evictionPolicy;

    public CacheNode(int capacity, EvictionPolicy evictionPolicy) {
        this.capacity = capacity;
        this.storage = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public String get(String key) {
        if (!storage.containsKey(key)) return null;

        evictionPolicy.keyAccessed(key);
        return storage.get(key);
    }

    public void put(String key, String value) {
        if (storage.containsKey(key)) {
            storage.put(key, value);
            evictionPolicy.keyAccessed(key);
            return;
        }

        if (storage.size() >= capacity) {
            String evictKey = evictionPolicy.evictKey();
            storage.remove(evictKey);
        }

        storage.put(key, value);
        evictionPolicy.keyAccessed(key);
    }
}