package au.id.oconal.general.slidingwindow

import au.id.oconal.general.fixedwindow.RequestInformation
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedDeque

data class RequestInformation(val clientId: String)

class SlidingWindowRateLimiter(private val clientRequestsPerMinute: HashMap<String, Int>) {
  enum class RateLimitResult {
    Accepted,
    Rejected
  }

  companion object {
    const val DEFAULT_REQUESTS_PER_MINUTE = 100
  }

  /** A hash map of client IDs to request counts. */
  private val requestCounts = ConcurrentHashMap<String, ConcurrentLinkedDeque<Long>>()

  /**
   * Rate limit the client identified by the `clientId` in the `requestInformation` parameter. Limit
   * to the number of requests allowed in the `clientConfiguration` map or default to
   * `DEFAULT_REQUESTS_PER_SECOND`.
   */
  public fun rateLimit(requestInformation: RequestInformation): RateLimitResult {
    val timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
    val timestampWindowStart = timestamp - 60
    // Get all timestamps
    val timestamps = requestCounts.getOrPut(requestInformation.clientId) { ConcurrentLinkedDeque() }
    timestamps.push(timestamp)
    // Drop any requests that came before the current time window
    while (timestamps.peek() < timestampWindowStart) timestamps.pop()
    return if (
      timestamps.size >=
        (clientRequestsPerMinute[requestInformation.clientId] ?: DEFAULT_REQUESTS_PER_MINUTE)
    )
      RateLimitResult.Rejected
    else RateLimitResult.Accepted
  }
}
