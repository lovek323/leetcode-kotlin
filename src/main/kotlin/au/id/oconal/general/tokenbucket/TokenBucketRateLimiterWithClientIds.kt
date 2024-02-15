package au.id.oconal.general.tokenbucket

import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

class TokenBucketRateLimiterWithClientIds(
  private val bucketSize: Int,
  private val bucketRefreshMillis: Long,
  private val coroutineScope: CoroutineScope
) {
  /**
   * A map from `clientId` to a `ConcurrentLinkedDeque` and an `AtomicInteger` that tracks requests
   * ordered from oldest to newest and the request counts.
   */
  private val clientBuckets = ConcurrentHashMap<String, AtomicInteger>()

  private val refreshJob: Job

  init {
    refreshJob =
      coroutineScope.launch {
        while (isActive) {
          delay(bucketRefreshMillis)
          refreshBuckets()
        }
      }
  }

  /** Try to acquire permission to proceed with a request for a specific `clientId`. */
  fun tryAcquire(clientId: String): Boolean {
    // Get the bucket for the current client
    // Time complexity: O(1)
    val tokens = clientBuckets.computeIfAbsent(clientId) { AtomicInteger(bucketSize) }
    // Remove a token from the bucket
    // Time complexity: O(1)
    println("tokens: ${tokens.get()}")
    return tokens.decrementAndGet() >= 0
  }

  private fun refreshBuckets() {
    // If the client hasn't made any requests since the last window, drop them to save memory
    clientBuckets.forEach { (clientId, tokens) ->
      if (tokens.get() == bucketSize) clientBuckets.remove(clientId)
    }
    clientBuckets.forEach { (_, tokens) -> tokens.set(bucketSize) }
  }

  fun shutdown() = refreshJob.cancel()
}
