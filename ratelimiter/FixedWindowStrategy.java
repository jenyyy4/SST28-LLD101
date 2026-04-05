package ratelimiter;

import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowStrategy implements RateLimitStrategy {

    private static class Window {
        int count;
        long windowStart;
    }

    private ConcurrentHashMap<String, Window> map = new ConcurrentHashMap<>();
    private RateLimitConfig config;

    public FixedWindowStrategy(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public synchronized boolean allowRequest(String key) {
        long now = System.currentTimeMillis();

        map.putIfAbsent(key, new Window());
        Window window = map.get(key);

        if (now - window.windowStart >= config.getWindowSizeInMillis()) {
            window.windowStart = now;
            window.count = 0;
        }

        if (window.count < config.getMaxRequests()) {
            window.count++;
            return true;
        }

        return false;
    }
}