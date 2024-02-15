package au.id.oconal.general.fixedwindow

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

data class RequestInformation(val clientId: String)

class FixedWindowRateLimiterWithClientIds(
  private val maxRequests: Int,
  private val windowSizeInMillis: Long
) {
  /**
   * A map from `clientId` to another `ConcurrentHashMap` that tracks request counts per window.
   *
   * Space complexity: O(n * m) where n is the number of unique client IDs and m is the number of
   * different windows.
   */
  private val clientsRequests = ConcurrentHashMap<String, ConcurrentHashMap<Long, AtomicInteger>>()

  /**
   * Try to acquire permission to proceed with a request for a specific `clientId`.
   *
   * Time complexity: O(1) on average for [ConcurrentHashMap] operations, but could be `O(n)` in the
   * worst case due to map resizing or hash collisions.
   *
   * Space complexity: Increments with new clients or windows, but is bounded by the cleanup
   * mechanism.
   */
  fun tryAcquire(clientId: String): Boolean {
    val currentWindowKey = System.currentTimeMillis() / windowSizeInMillis

    // Obtain the windows map for the given clientId, initialising it if absent
    //
    // Space complexity: O(m) for each client, m being the number of windows tracked
    val windowsMap = clientsRequests.computeIfAbsent(clientId) { ConcurrentHashMap() }

    // Atomically create or increment the count for the current window
    //
    // Space complexity: O(1) for the AtomicInteger instance
    val requestCount = windowsMap.computeIfAbsent(currentWindowKey) { AtomicInteger(0) }

    // Clean up expired windows for this client
    //
    // Time complexity: O(w), where w is the number of expired keys in the windows map
    cleanup(windowsMap, currentWindowKey)

    // Increment the count and return the appropriate boolean value
    val currentCount = requestCount.incrementAndGet()

    return if (currentCount <= maxRequests) true // within rate limit
    else {
      requestCount.decrementAndGet()
      false // rate limit exceeded
    }
  }

  /**
   * Remove the stale windows from the map for a specific client.
   *
   * Time complexity: O(w) to filter out expired keys, w being the number of keys (windows) for the
   * client
   */
  private fun cleanup(windowsMap: ConcurrentHashMap<Long, AtomicInteger>, currentWindowKey: Long) {
    // Space complexity: The space used is transient and includes holding keys that are expired
    val expiredKeys = windowsMap.keys.filter { it < currentWindowKey }
    expiredKeys.forEach {
      windowsMap.remove(it) // Time complexity: O(1), for each key-value pair removal
    }
  }
}

fun main() {
  val rateLimiter = FixedWindowRateLimiterWithClientIds(maxRequests = 10, windowSizeInMillis = 1000)
  val clientId = "client_1"

  repeat(15) {
    val allowed = rateLimiter.tryAcquire(clientId)
    println("Request for $clientId is " + (if (allowed) "allowed" else "denied"))
  }
}
