package au.id.oconal.algoexpert

/** Two Number Sum */
class Problem0001 {
  /**
   * Brute-Force Algorithm
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
   * Two Pointers
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
   * Hash Table
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
