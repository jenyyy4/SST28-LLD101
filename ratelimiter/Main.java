package ratelimiter;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        RateLimitConfig config = new RateLimitConfig(5, 60000); // 5 requests per minute

        // Switch strategy here
        RateLimitStrategy strategy = new FixedWindowStrategy(config);
        // RateLimitStrategy strategy = new SlidingWindowStrategy(config);

        RateLimiter limiter = new RateLimiter(strategy);
        RateLimiterService service = new RateLimiterService(limiter);

        String user = "T1";

        for (int i = 0; i < 10; i++) {
            boolean allowed = service.canCallExternal(user);
            System.out.println("Request " + i + " allowed: " + allowed);
        }
    }
}