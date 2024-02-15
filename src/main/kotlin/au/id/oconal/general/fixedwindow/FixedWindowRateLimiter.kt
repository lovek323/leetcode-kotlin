package au.id.oconal.general.fixedwindow

import java.lang.System.currentTimeMillis
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

class FixedWindowRateLimiter(private val maxRequests: Int, private val windowSizeInMillis: Long) {
  /** Store the timestamp (as the key), and the count of requests (as the value) for that window. */
  private val requests = ConcurrentHashMap<Long, AtomicInteger>()

  /** Try to acquire permission to proceed with a request. */
  fun tryAcquire(): Boolean {
    // Calculate the current window key
    val currentWindowKey = currentTimeMillis() / windowSizeInMillis

    // Use `computeIfAbsent` to handle thread-safe lazy initialisation
    val requestCount = requests.computeIfAbsent(currentWindowKey) { AtomicInteger(0) }

    // Clean up the expired windows
    cleanup(currentWindowKey)

    // Atomically increment the count and check if the request is within the rate limits
    val currentCount = requestCount.incrementAndGet()
    return if (currentCount <= maxRequests) true
    else {
      requestCount.decrementAndGet()
      false // Rate limit exceeded
    }
  }

  private fun cleanup(currentWindowKey: Long) {
    val expiredKeys = requests.keys.filter { it < currentWindowKey }
    expiredKeys.forEach { requests.remove(it) }
  }
}

fun main() {
  val rateLimiter = FixedWindowRateLimiter(maxRequests = 10, windowSizeInMillis = 1000)

  // Simulation of traffic
  val duration = measureTimeMillis {
    repeat(15) {
      if (rateLimiter.tryAcquire()) {
        println("Request allowed")
      } else {
        println("Request denied")
      }
    }
  }

  println("The rate limiter was evaluated in $duration ms")
}
