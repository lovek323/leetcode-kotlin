@file:OptIn(ExperimentalCoroutinesApi::class)

package au.id.oconal.general

import au.id.oconal.general.tokenbucket.TokenBucketRateLimiterWithClientIds
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TokenBucketRateLimiterWithClientIdsTest {
  @Test
  fun testAcquireSingleClient() = runTest {
    val limiter =
      TokenBucketRateLimiterWithClientIds(
        bucketSize = 5,
        bucketRefreshMillis = 1_000L, // 1 second for easy testing
        coroutineScope = this // Use the test's coroutine scope
      )

    // Use the same client ID for these tests, as we're only testing single-client behavior
    val clientId = "test-client"

    // Ensure that we can acquire the full bucket size without being limited
    repeat(5) { assertTrue(limiter.tryAcquire(clientId)) }

    // Next request should fail as the bucket should be empty
    assertFalse(limiter.tryAcquire(clientId))

    // CoroutineScope provided by runTest has a TestCoroutineScheduler that controls time
    advanceTimeBy(1_200L) // Advance virtual time

    // Now the bucket should be refilled and we can acquire one token again
    assertTrue(limiter.tryAcquire(clientId))

    // Shut down the rate limiter
    limiter.shutdown()
  }

  @Test
  fun testAcquireMultipleClients() = runTest {
    val limiter =
      TokenBucketRateLimiterWithClientIds(
        bucketSize = 5,
        bucketRefreshMillis = 1_000L, // 1 second for easy testing
        coroutineScope = this // Use the test's coroutine scope
      )

    val clientA = "client-A"
    val clientB = "client-B"

    // Ensure that both clients can use up their limits independently
    repeat(5) {
      assertTrue(limiter.tryAcquire(clientA))
      assertTrue(limiter.tryAcquire(clientB))
    }

    // Both clients should now be limited
    assertFalse(limiter.tryAcquire(clientA))
    assertFalse(limiter.tryAcquire(clientB))

    // CoroutineScope provided by runTest has a TestCoroutineScheduler that controls time
    advanceTimeBy(1_200L) // Advance virtual time

    // Both clients should have their tokens refilled
    assertTrue(limiter.tryAcquire(clientA))
    assertTrue(limiter.tryAcquire(clientB))

    // Shut down the rate limiter
    limiter.shutdown()
  }
}
