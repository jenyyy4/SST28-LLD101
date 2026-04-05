package distributedcache;

public interface EvictionPolicy {
    void keyAccessed(String key);
    String evictKey();
}