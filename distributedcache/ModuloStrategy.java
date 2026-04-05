package distributedcache;

public class ModuloStrategy implements DistributionStrategy {
    @Override
    public CacheNode getNode(String key, java.util.List<CacheNode> nodes) {
        int index = Math.abs(key.hashCode()) % nodes.size();
        return nodes.get(index);
    }
}