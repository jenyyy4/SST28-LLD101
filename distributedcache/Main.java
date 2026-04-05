package distributedcache;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<CacheNode> nodes = Arrays.asList(
            new CacheNode(2, new LRUCachePolicy()),
            new CacheNode(2, new LRUCachePolicy())
        );

        DistributedCache cache = new DistributedCache(
            nodes,
            new ModuloStrategy(),
            new Database()
        );

        cache.put("a", "1");
        cache.put("b", "2");

        System.out.println(cache.get("a")); // hit
        System.out.println(cache.get("c")); // miss → DB
    }
}