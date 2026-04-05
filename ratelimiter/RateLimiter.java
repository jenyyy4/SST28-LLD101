package ratelimiter;

public class RateLimiter {

    private RateLimitStrategy strategy;

    public RateLimiter(RateLimitStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean allow(String key) {
        return strategy.allowRequest(key);
    }
}