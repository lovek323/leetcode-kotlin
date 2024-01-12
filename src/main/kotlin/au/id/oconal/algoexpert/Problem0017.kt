@file:Suppress("unused", "CanBePrimaryConstructorProperty", "MemberVisibilityCanBePrivate")

package au.id.oconal.algoexpert

class Problem0017 {
  open class LinkedList(value: Int, next: LinkedList? = null) {
    var value = value
    var next: LinkedList? = next

    fun toList(): List<Int> = listOf(value) + (next?.toList() ?: emptyList())
  }

  fun run(linkedList: LinkedList): LinkedList {
    var currentSlow: LinkedList? = linkedList
    var currentFast: LinkedList? = linkedList.next
    while (currentFast != null) {
      currentFast = currentFast.next?.next
      currentSlow = currentSlow?.next
    }
    return currentSlow!!
  }
}
