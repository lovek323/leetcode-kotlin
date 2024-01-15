@file:Suppress("DuplicatedCode", "ReplaceManualRangeWithIndicesCalls")

package au.id.oconal.algoexpert

import java.util.*

class Problem0186LinkedHashMap(private val maxSize: Int) {

  private val cache = linkedMapOf<String, Int>()

  fun insertKeyValuePair(key: String, value: Int): Int {
    cache[key] = value
    if (cache.size > maxSize) cache -= cache.keys.first()
    return cache.size
  }

  fun getValueFromKey(key: String): Int? {
    // Update the position as it's been recently accessed
    val value = cache[key]
    if (value != null) {
      cache -= key
      cache[key] = value
    }
    return value
  }

  fun getMostRecentKey(): String? = cache.keys.lastOrNull()
}

class Problem0186HashTableAndLinkedDeque(private val maxSize: Int) {

  private val cache = HashMap<String, InternalLinkedList.Node>()
  private val order = InternalLinkedList()

  private class InternalLinkedList {

    class Node(val key: String, val value: Int, var next: Node?, var prev: Node?)

    private var head: Node? = null
    private var tail: Node? = null

    private var size: Int = 0

    fun addToEnd(key: String, value: Int): Node {
      val oldTail = tail
      val newTail = Node(key, value, null, oldTail)

      if (oldTail != null) oldTail.next = newTail

      tail = newTail

      if (head == null) head = newTail

      size++

      return newTail
    }

    fun addToEnd(node: Node) {
      if (size == 0) {
        // The list is empty
        head = node
        tail = node
      } else {
        val oldTail = tail!!
        oldTail.next = node
        node.prev = oldTail
        node.next = null

        tail = node
      }

      size++
    }

    fun getSize() = size

    fun getTail() = tail

    fun remove(node: Node) {
      // Assuming the node is part of this list
      val next = node.next
      val prev = node.prev

      // If this node was the head of the list
      if (prev == null) head = next
      else {
        prev.next = next
        node.prev = null
      }

      // If this node was the tail of the list
      if (next == null) tail = prev
      else {
        next.prev = prev
        node.next = null
      }

      size--
    }

    /**
     * Returns the removed node.
     */
    fun removeFromStart(): Node? {
      val headToRemove = head ?: return null
      head = headToRemove.next
      headToRemove.next = null
      if (head != null) head!!.prev = null
      size--
      return headToRemove
    }
  }

  fun insertKeyValuePair(key: String, value: Int): Int {
    if (cache.containsKey(key)) order.remove(cache[key]!!)
    cache[key] = order.addToEnd(key, value)
    if (order.getSize() > maxSize) {
      val removed = order.removeFromStart()
      if (removed != null) cache -= removed.key
    }
    return order.getSize()
  }

  fun getValueFromKey(key: String): Int? {
    // Update the position as it's been recently accessed
    val node = cache[key] ?: return null
    order.remove(node)
    order.addToEnd(node)
    return node.value
  }

  fun getMostRecentKey(): String? = order.getTail()?.key
}
