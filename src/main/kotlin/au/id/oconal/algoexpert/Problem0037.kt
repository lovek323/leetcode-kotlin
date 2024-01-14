@file:Suppress("DuplicatedCode")

package au.id.oconal.algoexpert

class Problem0037 {
  /**
   * Time complexity: O(n)
   * Space complexity: O(1)
   */
  fun longestPeak(array: List<Int>): Int {
    var longest = 0
    for (index in 1.until(array.size - 1)) {
      // First, ensure that this is a peak
      if (array[index - 1] >= array[index] || array[index + 1] >= array[index]) continue
      var peakSize = 1
      // Second, compute the left peak size
      for (leftIndex in (index - 1).downTo(0)) {
        if (array[leftIndex] < array[leftIndex + 1]) peakSize++
        else break
      }
      // Third, compute the right peak size
      for (rightIndex in (index + 1).until(array.size)) {
        if (array[rightIndex] < array[rightIndex - 1]) peakSize++
        else break
      }
      if (peakSize > longest) longest = peakSize
    }
    return if (longest >= 3) longest else 0
  }
}
