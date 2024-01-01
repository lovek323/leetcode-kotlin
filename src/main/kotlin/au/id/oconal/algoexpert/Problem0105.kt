@file:Suppress("DuplicatedCode")

package au.id.oconal.algoexpert

/**
 * Four Number Sum
 *
 * **Average complexity:** O(N^2) time, O(N^2) space
 *
 * **Worst complexity:** O(N^3) time, O(N^2) space
 */
class Problem0105 {
  /**
   * Using my three-number sum algorithm.
   * 1. Create an output list
   * 2. Create a hash table
   * 3. Loop through every value in the input array, index: `index1`, value: `num1`
   *     1. Loop through every value in the input array starting at `index1 + 1` until the end,
   *        index: `index2`, value: `num2`
   *         1. Calculate the sum of `num1` and `num2`, `sum`
   *         2. Calculate the difference between `totalSum` and `sum`: `difference`
   *         3. If `difference` is in the hash table, we have a possible quadruple, add these to the
   *            output
   *     2. Now, loop through every value in the input array starting at `0` until `index1`, index:
   *        `index2`, value: `num2`
   *         1. Calculate the sum of `num1` and `num2`
   *         2. Add this to the hashtable along with `num1` and `num2`
   * 4. Return the output list
   *
   * See inside the function for an example.
   */
  fun run(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
    // array: [7, 6, 4, -1, 1, 2]
    // targetSum: 16
    //
    // OUTER LOOP INDEX = 0
    //   Nothing happens in this loop
    //
    // OUTER LOOP: INDEX = 1, VAL = 6
    //   Nothing happens in first inner loop
    //   INNER LOOP 2: INDEX = 0, VAL = 7, SUM = 13, HASHMAP += 13:[[7,6]]
    //
    // OUTER LOOP: INDEX = 2, VAL = 4
    //   INNER LOOP 1: INDEX = 3, VAL = -1, SUM = 3, DIFF = 13, OUTPUT += [7,6,4,-1]
    //
    val hashMap = hashMapOf<Int, MutableList<Pair<Int, Int>>>()
    val output = mutableListOf<List<Int>>()
    for (index1 in 1.until(array.size)) {
      for (index2 in (index1 + 1).until(array.size)) {
        val sum = array[index1] + array[index2]
        val difference = targetSum - sum
        hashMap[difference]?.forEach { (num3, num4) ->
          output.add(listOf(array[index1], array[index2], num3, num4))
        }
      }
      for (index3 in 0.until(index1)) {
        val sum = array[index1] + array[index3]
        hashMap.getOrPut(sum) { mutableListOf() }.add(array[index1] to array[index3])
      }
    }
    return output
  }
}
