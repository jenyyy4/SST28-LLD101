# Distributed Cache Design – Explanation

## 1. Overview

The system is a distributed cache composed of multiple cache nodes. It supports two operations:

* `get(key)`
* `put(key, value)`

The design focuses on scalability, modularity, and extensibility by separating responsibilities across different components.

---

## 2. Data Distribution Across Nodes

A **Distribution Strategy** is used to decide which cache node stores a given key.

### Current Approach:

* Uses modulo-based hashing:

  ```
  index = hash(key) % numberOfNodes
  ```

### Flow:

1. Compute hash of the key
2. Apply modulo with number of nodes
3. Route the key to the selected node

### Why this works:

* Simple and fast
* Provides reasonably even distribution

### Extensibility:

The design allows replacing this logic with other strategies such as:

* Consistent hashing
* Custom routing logic

---

## 3. Cache Miss Handling

The system follows the **Cache-Aside (Lazy Loading)** pattern.

### Flow:

1. Check the appropriate cache node
2. If key exists → return value (cache hit)
3. If key does not exist → (cache miss):

   * Fetch value from database
   * Store value in cache
   * Return value

### Benefits:

* Reduces database load
* Only caches frequently accessed data
* Keeps cache efficient

---

## 4. Put Operation

### Flow:

1. Determine the correct node using the distribution strategy
2. Store the key-value pair in that node
3. Also update the database

### Assumption:

* The system uses **write-through caching**
* Data is written to both cache and database simultaneously

### Benefit:

* Ensures consistency between cache and database

---

## 5. Eviction Policy (LRU)

Each cache node has limited capacity, so an eviction policy is required.

### Policy Used:

* **LRU (Least Recently Used)**

### Behavior:

* When a key is accessed → it becomes most recently used
* When cache is full → remove the least recently used key

### Example:

```
Cache: [A, B, C]
Access A → [B, C, A]
Insert D → Evict B
New Cache: [C, A, D]
```

### Implementation:

* Uses a `LinkedHashSet` to track usage order

---

## 6. Extensibility

### 6.1 Pluggable Distribution Strategy

* Defined via `DistributionStrategy` interface
* New strategies can be added without modifying existing code

### 6.2 Pluggable Eviction Policy

* Defined via `EvictionPolicy` interface
* Can switch to:

  * LFU (Least Frequently Used)
  * MRU (Most Recently Used)

---

## 7. Design Principles

### 1. Strategy Pattern

* Used for distribution and eviction logic

### 2. Single Responsibility Principle

* Each class handles one responsibility

### 3. Open/Closed Principle

* System can be extended without modifying existing code

---

## 8. Assumptions

* Keys are unique
* Database is always available
* System is in-memory (no real network calls)
* Write-through consistency is used

---

## 9. Future Improvements

* Add consistent hashing for better scalability
* Introduce replication for fault tolerance
* Add TTL (expiration)
* Make system thread-safe
* Add monitoring (hit/miss ratio)

---

## 10. Conclusion

The design provides a clean and modular distributed cache system that efficiently distributes data, handles cache misses, and manages eviction while remaining flexible for future enhancements.