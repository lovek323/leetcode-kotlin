package au.id.oconal.algoexpert

/**
 * # Two Number Sum
 *
 * Write a function that takes in a non-empty array of distinct integers and an integer representing
 * a target sum. If any two numbers in the input array sum up to the target sum, the function should
 * return them in an array, in any order. If no two numbers sum up to the target sum, the function
 * should return an empty array.
 *
 * Note that the target sum has to be obtained by summing two different integers in the array; you
 * can't add a single integer to itself in order to obtain the target sum.
 *
 * You can assume that there will be at most one pair of numbers summing up to the target sum.
 *
 * ## Optimal Space & Time Complexity
 *
 * `O(N)` time | `O(N)` space where `N` is the length of the input array.
 */
class Problem0001 {
  /**
   * ## Brute-Force Algorithm
   *
   * **Hint 1:** Try using two for loops to sum all possible pairs of numbers in the input array.
   * What are the time and space implications of this approach?
   *
   * **Time complexity:** O(N^2)
   *
   * **Space complexity:** O(N)
   */
  fun run(array: MutableList<Int>, targetSum: Int): List<Int> =
    array
      .flatMapIndexed { index1, num1 ->
        array
          .mapIndexed { index2, num2 -> if (index1 == index2) null else listOf(num1, num2) }
          .filterNotNull()
      }
      .firstOrNull { it[0] + it[1] == targetSum } ?: emptyList()

  /**
   * 1. Sort the array - O(N log N)
   * 2. Initialise a `left` pointer (left) set to the first element of the array
   * 3. Initialise a `right` pointer (right) set to the last element of the array
   * 4. We can then sum up the values at `left` and `right` and call this `sum`
   * 5. We can compare this sum S to the targetSum
   * 6. If `sum < targetSum`
   *         1. If we move `right` to the left, then we'll only end up with a smaller number (as
   *            moving to the left goes to smaller numbers)
   *         2. So we know we need to move the `left` pointer to the right
   * 7. If `sum > targetSum`
   *     1. If we move `left to the right, we'll only end up with a larger number (as moving to the
   *        right goes to larger numbers)
   *     2. So we know that we need to move the `right` pointer to the left
   * 8. If `sum == targetSum`, we return the values at `left` and `right`
   *
   * **Time complexity:** `O(N)` time
   *
   * **Space complexity:** `O(1)` space
   */
  fun runTwoPointers(array: MutableList<Int>, targetSum: Int): List<Int> {
    array.sort()
    var left = 0
    var right = array.size - 1
    while (left < right) {
      val sum = array[left] + array[right]
      if (sum < targetSum) left++
      else if (sum > targetSum) right-- else return listOf(array[left], array[right])
    }
    return emptyList()
  }

  /**
   * ## Hash Table
   *
   * **Hint 2:** Realise that for every number `X` in the input array, you are essentially trying to
   * find a corresponding number `Y` such that `X + Y = targetSum`. With two variables in this
   * equation known to you, it shouldn't be hard to solve for `Y`.
   *
   * **Hint 3:** Try storing every number in a hash table, solving the equation mentioned in hint 2
   * for every number, and checking if the `Y` that you find is stored in the hash table. What are
   * the time and space implications of this approach?
   *
   * Using this information, we can improve to `O(N)`:
   * 1. Loop through every integer (`num1`) in the input array
   *     1. Compute the value (`num2`) required to reach the target
   *     2. Store `num1` in a hash table
   *     3. If `num2` is in the hash table, return it
   *
   * **Time complexity:** O(N)
   *
   * **Space complexity:** O(N)
   */
  fun runHashTable(array: MutableList<Int>, targetSum: Int): List<Int> {
    val hashTable = hashMapOf<Int, Any?>()
    array.forEach {
      val requiredValue = targetSum - it
      if (hashTable.containsKey(requiredValue)) return listOf(it, requiredValue)
      hashTable[it] = null
    }
    return emptyList()
  }
}
