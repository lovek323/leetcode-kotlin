@file:Suppress(
  "DuplicatedCode",
  "ReplaceManualRangeWithIndicesCalls",
  "ReplaceJavaStaticMethodWithKotlinAnalog"
)

package au.id.oconal.algoexpert

class Problem0123 {
  /**
   * - array of arrays
   * - each subarray holds two integer values and represents an item
   * - the first integer is the item's value
   * - the second integer is the item's weight
   * - capacity is max capacity of knapsack
   * - goal: fit items into your knapsack without having the sum of their weights exceed capacity
   * - need to maximise their combined value
   * - only one of each item
   * - return max combined value and picked items
   *
   * Brute-force approach:
   * 1. Sort each pair by the value per weight unit
   * 2. Keep putting the items with the best value into the pack until they don't fit
   * 3. Then put the next most valuable item
   *
   * Time complexity: O(n log n) Space complexity: O(n)
   *
   * This doesn't quite solve the problem, there are some edge cases where using up the best value
   * for a given weight locks us out of better choices later.
   */
  fun knapsackProblem(items: List<List<Int>>, capacity: Int): Pair<Int, List<Int>> {
    val sortedItems =
      items.mapIndexed { index, value -> IndexedItem(index, value[0], value[1]) }.toMutableList()
    sortedItems.sortBy { -1 * it.value.toFloat() / it.weight.toFloat() }
    var capacityRemaining = capacity
    val chosenItems = mutableListOf<Int>()
    var chosenValue = 0
    while (capacityRemaining >= 0 && sortedItems.isNotEmpty()) {
      if (sortedItems[0].weight <= capacityRemaining) {
        chosenItems.add(sortedItems[0].index)
        chosenValue += sortedItems[0].value
        capacityRemaining -= sortedItems[0].weight
      }
      sortedItems.removeAt(0)
    }
    return chosenValue to chosenItems
  }

  /**
   * Build a two-dimensional array of the maximum values that knapsacks of all capacities between 0
   * and capacity.
   *
   * Start with a row of zeroes to represent the maximum value without any items.
   *
   * Next, start with a single item and build that out. This is a simple check between the capacity
   * of the napsack and the item that we have in our list.
   *
   * Next, we introduce another item, and now we have a more complex check. Is it better for us to
   * keep the item that was the best when we were at one less capacity, or should we choose the new
   * item? In order to calculate this, we work out how heavy our new item is and then work out the
   * maximum value for a napsack of the current capacity _minus_ the weight of our new item. This
   * will give us the best possible scenario with including this new item in our pack. If this is
   * greater than the value of the previous row for this capacity, we choose that other item plus
   * our new item.
   *
   * Keep going until you have considered all items.
   *
   * Now we have the maximum value that we can fit into our knapsack.
   *
   * Finally, to determine the actual list of items we've got in our knapsack, we just need to go
   * back through the array in the last capacity column and see if the number changed in that column
   * for the given row. If it did, then we added the item, otherwise we didn't.
   *
   * For the next iteration, we need to reduce the capacity index that we're checking by the weight
   * of the item we now know is in our bag, because we're checking the maximum value of a smaller
   * bag to work out what we had in that 'virtual' smaller bag.
   *
   * And so on until we have completed.
   */
  fun knapsackProblemFaster(items: List<List<Int>>, capacity: Int): Pair<Int, List<Int>> {
    val values = Array(items.size + 1) { IntArray(capacity + 1) { 0 } }
    for (itemIndex in 1.rangeTo(items.size)) {
      val item = items[itemIndex - 1]
      for (currentCapacity in 1.rangeTo(capacity)) {
        // The new item fits in our bag
        // If we go take things out until we can fit it, does it help us carry more value?
        if (item[1] <= currentCapacity)
          values[itemIndex][currentCapacity] =
            Math.max(
              values[itemIndex - 1][currentCapacity - item[1]] + item[0],
              values[itemIndex - 1][currentCapacity]
            )
        // The new item doesn't fit in our bag, just use whatever was there before
        else values[itemIndex][currentCapacity] = values[itemIndex - 1][currentCapacity]
      }
    }

    val output = mutableListOf<Int>()
    var capacityRemaining = capacity
    for (itemIndex in items.size.downTo(1)) {
      if (values[itemIndex][capacityRemaining] != values[itemIndex - 1][capacityRemaining]) {
        output.add(itemIndex - 1)
        capacityRemaining -= items[itemIndex - 1][1]
      }
    }

    return values[items.size][capacity] to output
  }

  data class IndexedItem(val index: Int, val value: Int, val weight: Int)
}
