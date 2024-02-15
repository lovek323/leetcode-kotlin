package au.id.oconal.general

import au.id.oconal.general.slidingwindow.SlidingWindowRateLimiterWithClientIds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

class SlidingWindowRateLimiterWithClientIdsTest {

  @Test
  fun `test rate limiting under high concurrency`() = runBlocking {
    val rateLimiter = SlidingWindowRateLimiterWithClientIds(10, 1000)

    val clientCount = 100
    val requestCountPerClient = 50

    val successfulRequests = ConcurrentHashMap<String, AtomicInteger>()

    // Run a large number of clients concurrently
    val tasks =
      (1..clientCount).map { clientNumber ->
        async(Dispatchers.Default) {
          val clientId = "client$clientNumber"
          repeat(requestCountPerClient) {
            if (rateLimiter.tryAcquire(clientId))
              successfulRequests.computeIfAbsent(clientId) { AtomicInteger(0) }.incrementAndGet()
          }
        }
      }

    // Wait for all tasks to finish
    withContext(Dispatchers.Default) { tasks.forEach { it.await() } }

    // Validate that no client has exceeded the max requests in any given window
    successfulRequests.values.forEach { assertTrue(it.get() <= 10) }
  }
}
