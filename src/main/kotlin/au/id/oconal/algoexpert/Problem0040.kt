@file:Suppress(
  "DuplicatedCode",
  "ReplaceManualRangeWithIndicesCalls",
  "ReplaceJavaStaticMethodWithKotlinAnalog"
)

package au.id.oconal.algoexpert

class Problem0040 {
  /**
   * - non-empty array
   * - arbitrary intervals
   * - interval is array of two integers, first start and second end
   * - [1, 5] & [6, 7] not overlapping
   * - [1, 6] & [6, 7] overlapping
   * - intervals are always from start to end, start <= end
   * - merge the intervals
   * - returns (order unimportant)
   *
   * [[1, 2], [3, 5], [4, 7], [6, 8], [9, 10]] [[1, 2], [3, 8], [9, 10]]
   *
   * [1, 2] & [2, 3] [1, 4] & [2, 3] -- 1 <= 2 && 3 <= 4 [1, 4] & [3, 8] -- 1 <= 3 && 4 <= 8 [3, 3]
   * & [2, 8] -- 2 <= 3 && 3 <= 8 definition of an overlapping interval in generic terms: two
   * intervals are overlapping if any part of one interval is contained in any part of the other
   *
   * two intervals are overlapping if:
   * - whichever interval starts first
   * - ends at or after the start of the other interval
   * - and ends at or before the end of the other interval
   *
   * Imagine a scenario where we have three overlapping intervals: [1, 2], [3, 4], and [2, 3] The
   * expected output is [1, 4]
   *
   * Processing [1, 2] Output list is empty, add the first interval to it output: [[1, 2]]
   *
   * Processing [3, 4] [3, 4] is not overlapping with [1, 2] Add the second interval to the output
   * list output: [[1, 2], [3, 4]]
   *
   * Processing [2, 3] [1, 2] is overlapping with [2, 3] Replace [1, 2] with [1, 3] to the output
   * list
   *
   * Now we come a cropper since we need to compare [1, 3] with everything else in the output list
   * so that we can correctly get [1, 4] as the output
   *
   * The only way I can think of solving this is with recursion
   *
   * Time complexity: O(n^2) Space complexity: O(n)
   */
  fun mergeOverlappingIntervals(intervals: List<List<Int>>): List<List<Int>> {
    val outputIntervals = mutableListOf<List<Int>>()
    var combinedAny = false
    for (interval in intervals) {
      var combined = false
      outputIntervals.forEachIndexed { index, outputInterval ->
        if (areOverlapping(interval, outputInterval)) {
          outputIntervals[index] = add(interval, outputInterval)
          combined = true
        }
      }
      if (combined) combinedAny = true else outputIntervals.add(interval)
    }
    return if (combinedAny) mergeOverlappingIntervals(outputIntervals) else outputIntervals
  }

  /**
   * Two intervals are overlapping if the start index of the smaller interval is within the larger
   * interval or the end index of the smaller interval is within the larger interval.
   */
  private fun areOverlapping(interval1: List<Int>, interval2: List<Int>): Boolean {
    val smallerInterval = if (width(interval1) < width(interval2)) interval1 else interval2
    val largerInterval = if (width(interval1) < width(interval2)) interval2 else interval1
    return contains(largerInterval, smallerInterval[0]) ||
      contains(largerInterval, smallerInterval[1])
  }

  private fun contains(interval: List<Int>, value: Int): Boolean =
    interval[0] <= value && interval[1] >= value

  private fun width(interval: List<Int>): Int = Math.abs(interval[0] - interval[1])

  private fun add(interval1: List<Int>, interval2: List<Int>): List<Int> {
    return listOf(Math.min(interval1[0], interval2[0]), Math.max(interval1[1], interval2[1]))
  }

  /**
   * The hint given is that we should first sort the intervals by their start positions. This will
   * mean that we're just extending intervals as we go.
   *
   * [[1, 2], [3, 5], [4, 7], [6, 8], [9, 10]] -> [[1, 2], [3, 8], [9, 10]]
   *
   * Time: O(n log n)
   * Space: O(n)
   */
  fun mergeOverlappingIntervalsFaster(intervals: List<List<Int>>): List<List<Int>> {
    val sorted = intervals.sortedBy { it[0] }
    var currentInterval: MutableList<Int>? = null
    val outputIntervals = mutableListOf<List<Int>>()
    for (interval in sorted) {
      // Just set the currentInterval value for the first iteration
      if (currentInterval == null) currentInterval = interval.toMutableList()
      else {
        // If the interval extends the current interval, update it
        if (interval[0] <= currentInterval[1])
          currentInterval[1] = Math.max(interval[1], currentInterval[1])
        else {
          outputIntervals.add(currentInterval)
          currentInterval = interval.toMutableList()
        }
      }
    }
    if (currentInterval != null) outputIntervals.add(currentInterval)
    return outputIntervals
  }
}
