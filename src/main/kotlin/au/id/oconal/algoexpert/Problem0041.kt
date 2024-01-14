@file:Suppress(
  "DuplicatedCode",
  "ReplaceManualRangeWithIndicesCalls",
  "ReplaceJavaStaticMethodWithKotlinAnalog"
)

package au.id.oconal.algoexpert

class Problem0041 {
  /**
   * - sit anywhere within the given row
   * - sit where you get most space
   * - evenly distributed either side (preference)
   * - assume someone is always sitting in the first and last seats
   * - equally good seats, choose lower index
   * - no seats, return -1
   * - array always length 1
   *
   * Approach 1: Loop through the array forwards and backwards, computing the number of empty seats
   * to the left and right each time.
   *
   * Then, once we have this, we can eliminate seats that are full.
   *
   * We can combine these two steps by multiplying by -1 when the seat is full. This will save us an
   * iteration, but won't adjust the big O time complexity.
   *
   * Finally, we need to find the seat with the most seats to the right and left where they either
   * have the same amount of free seats to the right and left or one different.
   *
   * Time: O(n) Space: O(n)
   */
  fun bestSeat(seats: MutableList<Int>): Int {
    // Don't even have to check, we can't find a seat
    if (seats.size <= 2) return -1

    // Find out how many empty seats there are next to each seat
    val spacesToTheLeft = IntArray(seats.size) { 0 }
    val spacesToTheRight = IntArray(seats.size) { 0 }
    for (index in 1.until(seats.size)) {
      if (seats[index - 1] == 0) spacesToTheLeft[index] = spacesToTheLeft[index - 1] + 1
      if (seats[seats.size - index] == 0)
        spacesToTheRight[seats.size - index - 1] = spacesToTheRight[seats.size - index] + 1
    }

    // Exclude seats that have already got someone sitting in them
    for (index in seats.indices) {
      if (seats[index] == 1) {
        spacesToTheLeft[index] = -1
        spacesToTheRight[index] = -1
      }
    }

    // Start at Int.MIN_VALUE so that we can compute the first one, even with 0 spaces, as long
    // as there is somewhere to seat
    var largestSpacing = -1
    var largestSpacingIndex = -1
    // Find the seat with the most spaces on either side
    for (index in seats.indices) {
      if (Math.abs(spacesToTheLeft[index] - spacesToTheRight[index]) <= 1) {
        // We have a candidate
        if (spacesToTheLeft[index] + spacesToTheRight[index] > largestSpacing) {
          largestSpacing = spacesToTheLeft[index] + spacesToTheRight[index]
          largestSpacingIndex = index
        }
      }
    }

    return largestSpacingIndex
  }

  /**
   * The goal here is to move from O(n) space complexity down to O(1).
   *
   * The way to do this is to find the longest set of 0s next to each other.
   *
   * Simply loop through the array, count the zeroes and keep track of where the longest set is.
   */
  fun bestSeatsLessSpace(seats: MutableList<Int>): Int {
    var longestZeroesStartIndex = -1
    var longestZeroesEndIndex = -1
    var longestZeroesCount = 0
    var currentZeroesCount = 0
    var currentZeroesStartIndex = -1
    for (index in seats.indices) {
      if (seats[index] == 0) {
        if (currentZeroesStartIndex == -1) currentZeroesStartIndex = index
        currentZeroesCount++
      } else {
        if (currentZeroesCount > longestZeroesCount) {
          longestZeroesCount = currentZeroesCount
          longestZeroesStartIndex = currentZeroesStartIndex
          longestZeroesEndIndex = index - 1
        }
        currentZeroesCount = 0
        currentZeroesStartIndex = -1
      }
    }

    return longestZeroesStartIndex + (longestZeroesEndIndex - longestZeroesStartIndex) / 2
  }
}
