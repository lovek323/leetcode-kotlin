package au.id.oconal.mock

/*
1. Two sorted arrays
   - find the number of elements in common
   - arrays are same length
   - each array has distinct elements
   - no empty arrays
   - arrays will be sorted
   - lengths from 5 to 100
   - integer values
   - returns either (1) number of values, (2) values in array format, can be sorted or not
 */
class SortedArrayCommonElements {
  fun findCommonElements(array1: List<Int>, array2: List<Int>): List<Int> {
    // space: O(n)
    // time: O(n^2)
    return array2.filter { // O(n^2)
      array1.contains(it) // O(n)
    }
  }

  /**
   * Use two pointers, pointing to indices in array1 and array2 respectively.
   *
   * Start both at 0.
   *
   * Move forward in the arrays using the fact that the arrays are sorted to find common elements.
   *
   * Complexity:
   * Time: O(n) - where n is the number of elements in the arrays
   * Space: O(m) - where m is the number of common elements
   * Space: O(1)
   */
  fun findCommonElementsFaster(array1: List<Int>, array2: List<Int>): List<Int> {
    var pointer1 = 0
    var pointer2 = 0
    val output = mutableListOf<Int>()
    while (pointer1 < array1.size && pointer2 < array2.size) {
      val value1 = array1[pointer1]
      val value2 = array2[pointer2]
      if (value1 == value2) output.add(value1)
      if (value1 <= value2) pointer1++
      if (value2 <= value1) pointer2++
    }
    return output
  }
}
