package ratelimiter;

public class RateLimitConfig {
    private int maxRequests;
    private long windowSizeInMillis;

    public RateLimitConfig(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getWindowSizeInMillis() {
        return windowSizeInMillis;
    }
}