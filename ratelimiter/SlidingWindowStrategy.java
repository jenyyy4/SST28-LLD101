package ratelimiter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowStrategy implements RateLimitStrategy {

    private ConcurrentHashMap<String, Deque<Long>> map = new ConcurrentHashMap<>();
    private RateLimitConfig config;

    public SlidingWindowStrategy(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public synchronized boolean allowRequest(String key) {
        long now = System.currentTimeMillis();

        map.putIfAbsent(key, new LinkedList<>());
        Deque<Long> queue = map.get(key);

        while (!queue.isEmpty() && now - queue.peekFirst() > config.getWindowSizeInMillis()) {
            queue.pollFirst();
        }

        if (queue.size() < config.getMaxRequests()) {
            queue.addLast(now);
            return true;
        }

        return false;
    }
}