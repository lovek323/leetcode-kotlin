@file:Suppress("KDocUnresolvedReference")

package au.id.oconal.leetcode

/**
 * You are given two integer arrays `nums1` and `nums2`, sorted in **non-decreasing order**, and two
 * integers `m` and `n`, representing the number of elements in `nums1` and `nums2` respectively.
 *
 * Merge `nums1` and `nums2` into a single array sorted in **non-decreasing order**.
 *
 * The final sorted array should not be returned by the function, but instead be _stored inside the
 * array_ `nums1`. To accommodate this, `nums1` has a length of `m + n`, where the first `m`
 * elements denote the elements that should be merged, and the last `n` elements are set to `0` and
 * should be ignored. `nums2` has a length of `n`.
 */
class Problem0088 {
  /** Using Kotlin's in-built functions. */
  fun run(
    nums1: IntArray,
    m: Int,
    nums2: IntArray,
    @Suppress("UNUSED_PARAMETER") n: Int
  ): IntArray =
    nums2.forEachIndexed { index, i -> nums1[index + m] = i }.run { nums1.sortedArray() }

  /**
   * The idea here is to start at the last non-zero element in `nums1` and `nums2`, then fill in the
   * full values of `nums2` starting at the end and working backwards.
   *
   * 1. Have three pointers: (3 operations)
   *     1. `index1 = m - 1` (1 operation)
   *     2. `index2 = n - 1` (1 operation)
   *     3. `index = m + n - 1` (1 operation)
   * 2. While `index >= 0` (1 operation + `8 * (m + n)` operations)
   *     1. If `index1 >= 0`, set `num1 = nums1[index1]` (2 operations)
   *     2. Else set `num1 = -1`
   *     3. If `index2 >= 0`, set `num2 = nums2[index2]` (2 operations)
   *     4. Else set `num2 = -1`
   *     5. If `num1 > num2`, `nums1[index--] = num1` and `index2--` (4 operations)
   *     6. Else `nums1[index--] = num2` and `index2--`
   *     7. Back to 1
   *
   * Total operation count is `4 + 8(m + n)`
   *
   * Time complexity is `O(M + N)`
   */
  fun runLinear(nums1: IntArray, m: Int, nums2: IntArray, n: Int): IntArray {
    var index1 = m - 1
    var index2 = n - 1
    var index = m + n - 1
    var num1: Int
    var num2: Int
    while (index >= 0) {
      num1 = if (index1 >= 0) nums1[index1] else Int.MIN_VALUE
      num2 = if (index2 >= 0) nums2[index2] else Int.MIN_VALUE
      nums1[index--] = if (num1 > num2) {
        index1--
        num1
      } else {
        index2--
        num2
      }
    }
    return nums1
  }
}
