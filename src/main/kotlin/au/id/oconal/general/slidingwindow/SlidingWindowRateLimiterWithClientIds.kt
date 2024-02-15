package au.id.oconal.general.slidingwindow

import java.lang.System.currentTimeMillis
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedDeque
import java.util.concurrent.atomic.AtomicInteger

data class RequestInformation(val clientId: String)

class SlidingWindowRateLimiterWithClientIds(
  private val maxRequests: Int,
  private val windowSizeInMillis: Long
) {
  /**
   * A map from `clientId` to a `ConcurrentLinkedDeque` and an `AtomicInteger` that tracks requests
   * ordered from oldest to newest and the request counts.
   */
  private val clientsRequests =
    ConcurrentHashMap<String, Pair<AtomicInteger, ConcurrentLinkedDeque<Long>>>()

  /** Try to acquire permission to proceed with a request for a specific `clientId`. */
  fun tryAcquire(clientId: String): Boolean {
    // Obtain the list of requests for the current client.
    // Time complexity: O(1)
    val (requestCount, requestTimestamps) =
      clientsRequests.computeIfAbsent(clientId) { AtomicInteger(0) to ConcurrentLinkedDeque() }

    val windowStart = currentTimeMillis() - windowSizeInMillis

    // Drop all requests that occurred before the current window
    // Time complexity: O(l) where l is the request limits per window
    requestTimestamps.removeIf { it < windowStart }

    return if (requestCount.incrementAndGet() <= maxRequests) {
      // Time complexity: O(1)
      requestTimestamps.add(currentTimeMillis())
      true // within rate limit
    } else {
      // Time complexity: O(1)
      requestCount.decrementAndGet()
      false // rate limit exceeded
    }
  }
}
