package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0186 {

  @Test
  fun testLinkedHashMap() {
    val cache = Problem0186LinkedHashMap(3)
    cache.insertKeyValuePair("b", 2)
    cache.insertKeyValuePair("a", 1)
    cache.insertKeyValuePair("c", 3)
    assertEquals("c", cache.getMostRecentKey())
    assertEquals(1, cache.getValueFromKey("a"))
    assertEquals("a", cache.getMostRecentKey())

    // The cache had three entries, the least recently used one is evicted
    cache.insertKeyValuePair("d", 4)
    // "b" was evicted in the previous operation
    assertNull(cache.getValueFromKey("b"))

    // "a" already exists in the cache so its value just gets replaced
    cache.insertKeyValuePair("a", 5)
    assertEquals(5, cache.getValueFromKey("a"))
  }

  @Test
  fun testHashTableAndLinkedList1() {
    val cache = Problem0186HashTableAndLinkedDeque(3)
    cache.insertKeyValuePair("b", 2)
    cache.insertKeyValuePair("a", 1)
    cache.insertKeyValuePair("c", 3)
    assertEquals("c", cache.getMostRecentKey())
    assertEquals(1, cache.getValueFromKey("a"))
    assertEquals("a", cache.getMostRecentKey())

    // The cache had three entries, the least recently used one is evicted
    cache.insertKeyValuePair("d", 4)
    // "b" was evicted in the previous operation
    assertNull(cache.getValueFromKey("b"))

    // "a" already exists in the cache so its value just gets replaced
    cache.insertKeyValuePair("a", 5)
    assertEquals(5, cache.getValueFromKey("a"))
  }

  @Test
  fun testHashTableAndLinkedList2() {
    val cache = Problem0186HashTableAndLinkedDeque(3)
    assertNull(cache.getValueFromKey("a"))
    cache.insertKeyValuePair("a", 1)
    assertEquals(1, cache.getValueFromKey("a"))
    cache.insertKeyValuePair("a", 9001)
    assertEquals(9001, cache.getValueFromKey("a"))
    cache.insertKeyValuePair("b", 2)
    assertEquals(9001, cache.getValueFromKey("a"))
    assertEquals(2, cache.getValueFromKey("b"))
    cache.insertKeyValuePair("c", 3)
    assertEquals(9001, cache.getValueFromKey("a"))
    assertEquals(2, cache.getValueFromKey("b"))
    assertEquals(3, cache.getValueFromKey("c"))
  }
}
