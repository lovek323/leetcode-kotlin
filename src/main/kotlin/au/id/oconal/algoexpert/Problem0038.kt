@file:Suppress("DuplicatedCode", "ReplaceManualRangeWithIndicesCalls")

package au.id.oconal.algoexpert

class Problem0038 {
  /**
   * - non-empty array of integers
   * - returns array of same length
   * - each element is equal to the product of every other number in the input array
   * - solve without using division
   *
   * 1x2x3 = 3x2x1 etc.
   *
   * Brute force: O(n*(n-1))=O(n^2-2n+1)=O(n^2)
   *
   * Loop through the array forwards, keeping track of multiples Loop through the array again
   * backwards, keeping track of multiples
   */
  fun arrayOfProducts(array: List<Int>): List<Int> {
    var currentMultiple = 1
    val forwards = IntArray(array.size) { 0 }
    for (index in 0.until(array.size - 1)) {
      currentMultiple *= array[index]
      forwards[index] = currentMultiple
    }
    currentMultiple = 1
    val backwards = IntArray(array.size) { 0 }
    for (index in (array.size - 1).downTo(1)) {
      currentMultiple *= array[index]
      backwards[index] = currentMultiple
    }
    val output = IntArray(array.size) { 0 }
    for (index in 0.until(array.size)) {
      output[index] =
        (if (index == 0) 1 else forwards[index - 1]) *
          (if (index == array.size - 1) 1 else backwards[index + 1])
    }
    return output.toList()
  }
}
