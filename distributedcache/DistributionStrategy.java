package distributedcache;

public interface DistributionStrategy {
    CacheNode getNode(String key, java.util.List<CacheNode> nodes);
}