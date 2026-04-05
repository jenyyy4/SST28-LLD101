package ratelimiter;

public interface RateLimitStrategy {
    boolean allowRequest(String key);
}