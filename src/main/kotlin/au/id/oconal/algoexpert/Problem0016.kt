@file:Suppress("unused", "CanBePrimaryConstructorProperty")

package au.id.oconal.algoexpert

class Problem0016 {
  // This is an input class. Do not edit.
  open class LinkedList(value: Int, next: LinkedList? = null) {
    var value = value
    var next: LinkedList? = next

    fun toList(): List<Int> = listOf(value) + (next?.toList() ?: emptyList())
  }

  fun run(linkedList: LinkedList): LinkedList {
    var previous: LinkedList? = null
    var current: LinkedList? = linkedList

    while (current != null) {
      // Basically, we can just keep dropping elements from the linked list
      // and hooking up the next one as long as we have the same value
      if (previous != null) {
        if (current.value == previous.value) {
          // Skip the current one, set the previous.next to current.next
          previous.next = current.next
        }
      }

      previous = current
      current = current.next
    }

    return linkedList
  }
}
