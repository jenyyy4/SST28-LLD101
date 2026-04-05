# Pluggable Rate Limiting System – Explanation

## 1. Overview

This system implements a **rate limiter for external resource usage**, not for incoming API requests. The limiter is invoked **only when the service is about to call a paid external API**.

Goals:

* Prevent overuse of costly external resources
* Support multiple algorithms (pluggable)
* Be thread-safe and extensible

---

## 2. Where Rate Limiting Happens

Flow:

Client Request → Business Logic → (needs external call?) → RateLimiter → External API

* If no external call is needed → rate limiter is NOT used
* If external call is needed → `RateLimiter.allow(key)` is checked

---

## 3. Key Concept: Rate Limiting Key

Rate limiting is applied using a **key**, which can represent:

* User / Customer (e.g., `userId`)
* Tenant
* API key
* External provider

This makes the system flexible for different business rules.

---

## 4. Core Components

### 1. RateLimiter

* Entry point used by services
* Delegates decision to a strategy

### 2. RateLimitStrategy (Interface)

* Defines:

  ```
  boolean allowRequest(String key)
  ```
* Enables pluggable algorithms

### 3. RateLimitConfig

* Holds configuration:

  * max requests
  * window size (time)

### 4. Strategies (Implementations)

* Fixed Window
* Sliding Window

---

## 5. Fixed Window Counter

### Idea:

Time is divided into **fixed intervals (windows)** and each key has a counter per window.

### How it works:

1. Identify current window using time
2. Reset counter when window changes
3. Increment counter for each request
4. Allow request only if count ≤ limit

### Example:

```
Limit: 5 requests/minute

00:00–00:59 → up to 5 requests
01:00–01:59 → counter resets
```

### Pros:

* Simple and fast
* Low memory usage

### Cons:

* Burst issue at boundaries (can allow double traffic near window edges)

---

## 6. Sliding Window Counter

### Idea:

Instead of fixed windows, track requests in a **moving time window**.

### How it works:

1. Store timestamps of requests
2. Remove timestamps older than (current time - window)
3. Count remaining requests
4. Allow only if count < limit

### Example:

```
Limit: 5 requests/minute

At time T:
Count requests between (T - 60s, T)
```

### Pros:

* More accurate
* Smooth rate limiting (no bursts)

### Cons:

* Higher memory usage (stores timestamps)
* Slightly more complex

---

## 7. Thread Safety

* Uses `ConcurrentHashMap` for shared state
* Critical sections protected using `synchronized`

This ensures safe behavior under concurrent requests.

---

## 8. Extensibility

### Add New Algorithm

Create a new class implementing:

```
RateLimitStrategy
```

Example:

```
class TokenBucketStrategy implements RateLimitStrategy
```

No changes required in existing code.

---

## 9. Switching Algorithms

The system allows switching strategies easily:

```
RateLimitStrategy strategy = new FixedWindowStrategy(config);
// or
RateLimitStrategy strategy = new SlidingWindowStrategy(config);
```

Business logic remains unchanged.

---

## 10. Design Principles Used

### 1. Strategy Pattern

* Encapsulates rate limiting algorithms

### 2. Single Responsibility Principle

* Each class has one responsibility

### 3. Open/Closed Principle

* System is open for extension, closed for modification

---

## 11. Trade-offs

| Feature        | Fixed Window | Sliding Window |
| -------------- | ------------ | -------------- |
| Accuracy       | Low          | High           |
| Memory Usage   | Low          | Higher         |
| Complexity     | Simple       | Moderate       |
| Burst Handling | Poor         | Good           |

---

## 12. Conclusion

This design provides a clean, modular, and extensible rate limiting system that:

* Controls expensive external API usage
* Supports multiple algorithms
* Ensures thread safety
* Allows easy future enhancements

It can be extended to support advanced techniques like Token Bucket or distributed rate limiting using Redis.