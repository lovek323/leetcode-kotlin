package au.id.oconal.mock

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TTLTTLCacheTest {

  @Test
  fun testExpiry() = runTest {
    val cache = TTLCache(100, this)
    cache.put("key", "value", 100)
    assertEquals("value", cache.get("key"))
    advanceTimeBy(200)
    assertNull(cache.get("key"))
    cache.shutdown()
  }

  @Test
  fun testNotExpiring() = runTest {
    val cache = TTLCache(100, this)
    cache.put("key", "value", 500)
    assertEquals("value", cache.get("key"))
    advanceTimeBy(100)
    assertNotNull(cache.get("key"))
    cache.shutdown()
  }
}
