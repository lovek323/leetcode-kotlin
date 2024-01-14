@file:Suppress("DuplicatedCode", "ReplaceManualRangeWithIndicesCalls",
  "ReplaceJavaStaticMethodWithKotlinAnalog"
)

package au.id.oconal.algoexpert

class Problem0039 {
  /**
   * - array between 1 and n inclusive
   * - n is the length of the array
   * - first integer that appears more than once
   * - first duplicate value with minimum index
   * - you are allowed to mutate the array
   *
   * Time: O(n)
   * Space: O(n)
   */
  fun firstDuplicateValue(array: MutableList<Int>): Int {
    val hashMap = hashMapOf<Int, Boolean>()
    array.forEach { value ->
      if (hashMap.containsKey(value)) return value
      hashMap[value] = true
    }
    return -1
  }

  /**
   * 1. Loop through all the numbers
   * 2. When we encounter a number for the first time, work out its corresponding array index and
   *    multiply that value by -1 (we can do this since the numbers are from 1 to n, i.e., always
   *    positive)
   * 3. If the number there is already negative, we have encountered it for the first time so we
   *    can return it
   */
  fun firstDuplicateValueLessSpace(array: MutableList<Int>): Int {
    for (index in array.indices) {
      if (array[Math.abs(array[index]) - 1] < 0) return Math.abs(array[index])
      array[Math.abs(array[index]) -1] *= -1
    }
    return -1
  }
}
