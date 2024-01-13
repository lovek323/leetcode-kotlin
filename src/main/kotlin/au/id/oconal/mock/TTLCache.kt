package au.id.oconal.mock

import kotlinx.coroutines.*
import java.lang.System.currentTimeMillis
import java.util.concurrent.ConcurrentHashMap

/**
 * We are designing a simple in-memory cache system. Keys are strings. Values are anything. It
 * supports expiring keys based on a TTL and must be threadsafe.
 *
 * TTLs specified in milliseconds.
 */
class TTLCache(private val defaultTTL: Long, private val scope: CoroutineScope) {
  private val data = ConcurrentHashMap<String, CacheValue>()
  private val cleanupJob: Job

  init {
    cleanupJob = scope.launch {
      while (isActive) {
        delay(defaultTTL)
        cleanup()
      }
    }
  }

  private fun cleanup() {
    val currentTime = currentTimeMillis()
    data.entries.removeIf { currentTime > it.value.expiryTime }
  }

  fun put(key: String, value: Any, ttl: Long = defaultTTL) {
    data[key] = CacheValue(value, ttl)
  }

  fun get(key: String): Any? = data[key]?.value

  fun shutdown() = cleanupJob.cancel()

  private data class CacheValue(val value: Any, val expiryTime: Long)
}
