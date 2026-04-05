package distributedcache;

import java.util.LinkedHashSet;

public class LRUCachePolicy implements EvictionPolicy {
    private LinkedHashSet<String> order = new LinkedHashSet<>();

    @Override
    public void keyAccessed(String key) {
        order.remove(key);
        order.add(key);
    }

    @Override
    public String evictKey() {
        String oldest = order.iterator().next();
        order.remove(oldest);
        return oldest;
    }
}