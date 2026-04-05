package ratelimiter;

public class RateLimiterService {

    private RateLimiter rateLimiter;

    public RateLimiterService(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    public boolean canCallExternal(String key) {
        return rateLimiter.allow(key);
    }
}