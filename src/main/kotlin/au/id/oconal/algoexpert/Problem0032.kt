@file:Suppress("DuplicatedCode")

package au.id.oconal.algoexpert

/**
 * Three Number Sum
 *
 * **Optimal time complexity:** O(N^2)
 *
 * **Optimal space complexity:** O(N)
 */
class Problem0032 {
  /**
   * Brute-Force Approach
   *
   * **Time complexity**: O(N^3)
   *
   * **Space complexity:** O(N^3)
   */
  fun run(array: MutableList<Int>, targetSum: Int): List<List<Int>> =
    array
      .flatMap { num1 ->
        array.flatMap { num2 ->
          if (num2 != num1) {
            array.mapNotNull { num3 ->
              if (num3 != num1 && num3 != num2) {
                if (num1 + num2 + num3 == targetSum) listOf(num1, num2, num3).sorted() else null
              } else null
            }
          } else listOf()
        }
      }
      .distinct()
      .sortedWith { a, b ->
        if (a[0] == b[0]) {
          if (a[1] == b[1]) {
            if (a[2] == b[2]) {
              0
            } else {
              a[2].compareTo(b[2])
            }
          } else {
            a[1].compareTo(b[1])
          }
        } else {
          a[0].compareTo(b[0])
        }
      }

  /**
   * Using a Hash Map
   * 1. Create a list for output values
   * 2. Create a hash map of key = integer (the sum of two numbers from the input array), value =
   *    list of pairs of two integers (all pairs of two numbers from the input array that add up to
   *    the key's value)
   * 3. Loop through the list - num1
   *     1. Loop through the list again - num2
   *         1. Store num1 + num2 = [num1, num2] in a hash map (this will be a list of possible
   *            pairs and we'll add one each time to the hash map)
   * 4. Loop through the list again - num1
   *     1. Calculate the sum required to get to the target (targetSum - num1)
   *     2. Get the list of possible pairs
   *     3. Exclude any that contain num1
   *     4. Create a list of num1 and the two other numbers and sort it
   *     4. Add this to the list of output values
   * 5. Get a distinct list of output values
   * 6. Sort the list of output values
   * 7. Return the list
   */
  fun runHashMap(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
    val output = mutableListOf<List<Int>>()
    val hashMap = hashMapOf<Int, MutableList<Pair<Int, Int>>>()

    array.forEach { num1 ->
      array.forEach { num2 ->
        // Ignore if the numbers are the same
        if (num1 != num2) {
          if (!hashMap.containsKey(num1 + num2)) hashMap[num1 + num2] = mutableListOf()
          hashMap[num1 + num2]!!.add(num1 to num2)
        }
      }
    }

    array.forEach { num1 ->
      val required = targetSum - num1
      if (hashMap.containsKey(required)) {
        (hashMap[required] ?: emptyList()).forEach { values ->
          if (values.first != num1 && values.second != num1) {
            output.add(listOf(num1, values.first, values.second).sorted())
          }
        }
      }
    }

    return output.distinct().sortedWith { a, b ->
      if (a[0] == b[0]) {
        if (a[1] == b[1]) {
          if (a[2] == b[2]) {
            0
          } else {
            a[2].compareTo(b[2])
          }
        } else {
          a[1].compareTo(b[1])
        }
      } else {
        a[0].compareTo(b[0])
      }
    }
  }

  /**
   * Sorting and Using a Hash Map
   * 1. Sort the input array O(log N)
   * 2. Create a list for output values
   * 3. Create a hash map of key = integer (the sum of two numbers from the input array), value =
   *    list of pairs of two integers (all pairs of two numbers from the input array that add up to
   *    the key's value)
   * 4. Loop through the list - num1
   *     1. Loop through the list again - num2
   *         1. Store num1 + num2 = [num1, num2] in a hash map (this will be a list of possible
   *            pairs and we'll add one each time to the hash map; this list of pairs will be
   *            sorted - does that help us at all?)
   *         2. IMPROVEMENT: Because we've got the numbers sorted at step 1, we only need to loop
   *            through the following numbers in the inner loop
   * 5. Loop through the list again - num1
   *     1. Calculate the sum required to get to the target (targetSum - num1)
   *     2. Get the list of possible pairs
   *     3. Exclude any that contain num1
   *     4. Create a list of num1 and the two other numbers and sort it
   *     5. Add this to the list of output values
   * 6. Get the distinct values
   * 7. IMPROVEMENT: We don't need to sort the output values as we'll be adding them to the list in
   *    the correct order
   * 8. Return the list
   *
   * **Time complexity:** O(N^2)
   *
   * **Space complexity:** O(N)
   */
  fun runSortedHashMap(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
    /*
    targetSum: 0
    sorted input array: [-8, -6, 1, 2, 3, 5, 6, 12]
    output: []
    hashmap: []

    after 4, hashmap:
      -14 => [-8, -6]
       -7 => [-8, 1]
       -6 => [-8, 2]
       -5 => [-8, 3], [-6, 1]
       -3 => [-8, 5], [-6, 3]
       -2 => [-8, 6]
        4 => [-8, 12], [1, 3]
       -4 => [-6, 2]
       -1 => [-6, 5]
        0 => [-6, 6]
       12 => [-6, 12]
        3 => [1, 2]
        6 => [1, 5], [2, 5]
        7 => [1, 6]
       13 => [1, 12]
        5 => [2, 3]
        8 => [2, 6], [3, 5]
       14 => [2, 12]
        9 => [3, 6]
       15 => [3, 12]
       11 => [5, 6]
       17 => [5, 12]
       18 => [6, 12]

    after 5, output:
      [-8, 2, 6], [-8, 3, 5]  // 0 - (-8) = 8
      [-6, 1, 5]              // 0 - (-6) = 6
      [1, -6, 5]              // 0 - 1 = -1
      [2, -8, 6]              // 0 - 2 = -2
      [3, -8, 5], [3, -6, 3]  // 0 - 3 = -3
      [5, -8, 3], [5, -6, 1]  // 0 - 5 = -5
      [6, -8, 2]              // 0 - 6 = -6
                              // 0 - 12 = -12
     */

    array.sort()

    val output = mutableListOf<List<Int>>()
    val hashMap = hashMapOf<Int, MutableList<Pair<Int, Int>>>()

    array.forEachIndexed { index1, num1 ->
      ((index1 + 1).until(array.size)).forEach { index2 ->
        val num2 = array[index2]
        if (!hashMap.containsKey(num1 + num2)) hashMap[num1 + num2] = mutableListOf()
        hashMap[num1 + num2]!!.add(num1 to num2)
      }
    }

    array.forEach { num1 ->
      val required = targetSum - num1
      if (hashMap.containsKey(required)) {
        (hashMap[required] ?: emptyList()).forEach { values ->
          if (values.first != num1 && values.second != num1) {
            output.add(listOf(num1, values.first, values.second).sorted())
          }
        }
      }
    }

    return output.distinct()
  }

  /**
   * Two Pointers
   * 1. Sort the input array
   * 2. Loop through the values in the array
   *     1. Set a pointer to the right of the current value
   *     2. Set a pointer to the last value in the array
   *     3. If the current number, the left number, and the right number add up to `targetSum`
   *         1. Add them to the output array
   *         2. Increment left by 1
   *     4. Else, if it is larger than `targetSum`, move the right pointer to the left
   *     5. Else, move the left pointer to the right
   * 3. Return the output
   *
   * **Time complexity:** O(N^2)
   *
   * **Space complexity:** O(N)
   */
  fun runTwoPointers(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
    array.sort()

    val output = mutableListOf<List<Int>>()

    array.forEachIndexed { index, current ->
      var left = index + 1
      var right = array.size - 1
      while (left < right) {
        val sum = current + array[left] + array[right]
        if (sum == targetSum) {
          output.add(listOf(current, array[left], array[right]))
          left++
          right--
        } else if (sum > targetSum) right-- else left++
      }
    }

    return output
  }
}
