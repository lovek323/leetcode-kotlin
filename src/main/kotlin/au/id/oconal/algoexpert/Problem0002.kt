package au.id.oconal.algoexpert

import java.util.*

/** Validate Subsequence */
class Problem0002 {
  /**
   * Hash Map and Queue
   * 1. Convert `array` to a hash table with keys as the array values and the index of the value as
   *    the value.
   * 2. For each value in `sequence`, get the index from the hashmap, keep the current and last
   *    indices
   * 3. If any value in `sequence` isn't in the hashmap, return false
   * 4. If any `current` index is not greater than the `last` index return false
   * 5. Return true
   *
   * ### Problems
   *
   * This works unless there are repeated values in the array, so we can adjust the solution by
   * keeping a queue of indices for each given array and dequeuing each time we see the number
   *
   * **Time complexity:** `O(M + N)` where `M` is the length of `array` and `N` is the length of
   * `sequence`.
   *
   * **Space complexity:** `O(N)`
   */
  fun run(array: List<Int>, sequence: List<Int>): Boolean {
    val map = hashMapOf<Int, Queue<Int>>()
    array.forEachIndexed { index, i ->
      map[i] = (map[i] ?: LinkedList())
      map[i]!!.offer(index)
    }
    var lastIndex = -1
    var currentIndex: Int
    sequence.forEach {
      currentIndex =
        (map[it] ?: return false)
          .ifEmpty {
            return false
          }
          .remove()
      if (currentIndex <= lastIndex) return false
      lastIndex = currentIndex
    }
    return true
  }

  /**
   * Looping Only Once
   *
   * **Time complexity:** `O(N)` where `N` is the length of the input `array`
   *
   * **Space complexity:** `O(1)`
   */
  fun runOneLoop(array: List<Int>, sequence: List<Int>): Boolean {
    var pointer1 = 0
    var pointer2 = 0
    while (pointer1 < array.size && pointer2 < sequence.size) {
      if (array[pointer1] == sequence[pointer2]) {
        pointer1++
        pointer2++
      } else pointer1++
    }
    return pointer2 >= sequence.size
  }
}
