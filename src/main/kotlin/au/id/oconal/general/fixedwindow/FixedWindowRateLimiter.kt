package au.id.oconal.general.fixedwindow

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

data class RequestInformation(val clientId: String)

class FixedWindowRateLimiter(private val clientRequestsPerSecond: HashMap<String, Int>) {
  enum class RateLimitResult {
    Accepted,
    Rejected
  }

  companion object {
    const val DEFAULT_REQUESTS_PER_SECOND = 100
  }

  private val currentTimestamp = AtomicLong(0)

  /** A hash map of client IDs to request counts. */
  private val requestCounts = ConcurrentHashMap<String, Int>()

  /**
   * Rate limit the client identified by the `clientId` in the `requestInformation` parameter. Limit
   * to the number of requests allowed in the `clientConfiguration` map or default to
   * `DEFAULT_REQUESTS_PER_SECOND`.
   */
  public fun rateLimit(requestInformation: RequestInformation): RateLimitResult {
    val timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
    if (currentTimestamp.getAndSet(timestamp) != timestamp) requestCounts.clear()
    return if (
      requestCounts.getOrPut(requestInformation.clientId) { 0 } >=
        (clientRequestsPerSecond[requestInformation.clientId] ?: DEFAULT_REQUESTS_PER_SECOND)
    )
      RateLimitResult.Rejected
    else RateLimitResult.Accepted
  }
}
