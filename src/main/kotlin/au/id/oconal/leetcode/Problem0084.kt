package au.id.oconal.leetcode

import java.util.*
import kotlin.math.max

/**
 * Given an array of integers `heights` representing the histogram's bar height where the width of
 * each bar is `1`, return _the area of the largest rectangle in the histogram_.
 */
class Problem0084 {
  /*
   * Brute-force algorithm:
   * 1. Starting with the height at the given index, count itself and the number of subsequent and
   *    antecedent columns that have at least the same height
   * 2. Compute the area
   * 3. If it is larger than the current max, set max to the new area
   * 4. Repeat
   *
   * Fails on large test case (takes ~8 seconds).
   */
  fun run(heights: IntArray): Int =
    heights
      .mapIndexed { currentHeightIndex, maxHeight ->
        val countAntecedent =
          heights.slice(0 ..< currentHeightIndex).reversed().takeWhile { it >= maxHeight }.count()
        val countSubsequent =
          heights.slice(currentHeightIndex ..< heights.size).takeWhile { it >= maxHeight }.count()
        maxHeight * (countAntecedent + countSubsequent)
      }
      .max()

  /**
   * Keep track of the max rectangle size and the list of current rectangles.
   *
   * For each column in the histogram:
   * 1. Drop any rectangles that are higher than the current height (before dropping, compute the
   *    area and update the max if necessary).
   * 2. Check the list of remaining rectangles, if this continues any of the current rectangles,
   *    increase their width.
   * 3. Add any new rectangles, for each between the max of the current rectangles and the current
   *    height.
   *
   * Finally, compute the max.
   *
   * PERFORMANCE IMPROVEMENT
   * -----------------------
   * When adding the new rectangles (3), only add one rectangle instead of one for each possible new
   * height. This can stand in for every rectangle up to this height. This means that we need to
   * make a few adjustments.
   * 1. If the current height is 0, remove all rectangles.
   * 2. Otherwise, instead of removing previous rectangles, clamp them down to the current height
   *    (still compute the max for the rectangle as though it was removed). This will allow us to
   *    have 'virtual' smaller rectangles without needing to go back and determine their full width.
   * 3. Only add a single rectangle in point (3) and only add one if it's needed.
   *
   * PERFORMANCE IMPROVEMENT
   * -----------------------
   * Don't use a data class here, use a simple array of integers.
   *
   * PERFORMANCE IMPROVEMENT
   * -----------------------
   * Compute the max when we update each rectangle as we go along. We'll also need to compute the
   * max when we add a new rectangle in case that's the last rectangle that we add.
   *
   * PERFORMANCE IMPROVEMENT
   * -----------------------
   * Use a map, not a list so that we can only keep a single entry for each height when we clamp
   * down.
   *
   * This wouldn't work within the required time for one of the larger inputs.
   */
  fun runMap(heights: IntArray): Int {
    var max = 0
    val rectangles = mutableMapOf<Int, Int>()

    heights.forEach { height ->
      // If the height is 0, remove all rectangles
      if (height == 0) rectangles.clear()
      else {
        // Clamp down any rectangles that cannot be continued and increase the width of all
        // rectangles
        var maxWidthForHeight = 1
        rectangles.forEach { (thisHeight, width) ->
          if (thisHeight >= height) maxWidthForHeight = max(maxWidthForHeight, width + 1)
          if (thisHeight <= height) {
            rectangles[thisHeight] = width + 1
            max = max(max, thisHeight * (width + 1))
          }
        }
        rectangles.entries.removeIf { (thisHeight, _) -> thisHeight > height }

        // Add a new rectangle if necessary
        rectangles[height] = maxWidthForHeight
        max = max(max, height * maxWidthForHeight)
      }
    }

    return max
  }

  /**
   * In this solution, we'll use a stack. The stack will keep track of all the active rectangles. A
   * rectangle is active as long as the current height we're looking at is greater than or equal to
   * its own height.
   *
   * For the first column, there's nothing on the stack, so we just add it to the stack with its
   * column index (HEIGHT_1, 0).
   *
   * For the second and subsequent columns, we will determine which rectangles are no longer active
   * and compute their areas, keeping track of the maximum area that we've seen so far. We'll pop
   * off any inactive rectangles and keep track of the column index of the now-inactive rectangle,
   * which will allow us to keep the first column that had our current height. Then, we'll add a new
   * rectangle with the current height and the first column index we've kept track of.
   *
   * Finally, after we've looped through all columns, we'll see if any of the remaining active
   * rectangles on the stack have a larger area than the maximum area we've been keeping track of
   * and we'll return that maximum.
   */
  fun runStack(heights: IntArray): Int {
    data class Rectangle(val column: Int, val height: Int)
    val stack = Stack<Rectangle>()
    var max = 0

    for (column in heights.indices) {
      val height = heights[column]
      var firstColumn = column
      // Deal with any rectangles on the stack with heights greater than our current height, they
      // will continue to make a rectangle with our current column, but it'll be a less tall one
      // than what already exists.
      while (stack.isNotEmpty() && height < stack.peek().height) {
        firstColumn = stack.peek().column
        max = max(max, (column - stack.peek().column) * stack.peek().height)
        stack.pop()
      }
      stack.push(Rectangle(firstColumn, height))
    }

    return max(max, stack.maxOf { (heights.size - it.column) * it.height })
  }
}
