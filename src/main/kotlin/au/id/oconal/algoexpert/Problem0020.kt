package au.id.oconal.algoexpert

class Problem0020 {
  fun run(array: List<Int>, target: Int): Int = run(array, 0, array.size - 1, target)

  private fun run(array: List<Int>, startIndex: Int, endIndex: Int, target: Int): Int {
    // Find the mid-point of the array, round down when we get halfway between two numbers
    // The range we'll be looking at will be start to mid, mid to end
    val midIndex = startIndex + (endIndex - startIndex) / 2

    // If we can't find the value
    return if (startIndex == endIndex) {
      if (array[startIndex] != target) -1 else startIndex
      // We want the right half
    } else if (array[midIndex] < target) run(array, midIndex + 1, endIndex, target)
    // We want the left half
    else run(array, startIndex, midIndex, target)
  }

  fun runIteratively(array: List<Int>, target: Int): Int {
    var startIndex = 0
    var endIndex = array.size - 1
    while (startIndex < endIndex) {
      val midIndex = startIndex + (endIndex - startIndex) / 2
      // We want the right half
      if (array[midIndex] < target) startIndex = midIndex + 1
      // We want the left half
      else endIndex = midIndex
    }

    return if (array[startIndex] == target) startIndex else -1
  }
}
