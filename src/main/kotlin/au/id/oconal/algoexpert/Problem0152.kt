package au.id.oconal.algoexpert

class Problem0152 {

  fun quickSort(array: MutableList<Int>): List<Int> {
    quickSort(array, 0, array.size - 1)
    return array
  }

  private fun quickSort(array: MutableList<Int>, start: Int, end: Int) {
    if (end == start) return

    val pivot = array[start]
    var left = start + 1
    var right = end
    while (left <= right) {
      val leftValue = array[left]
      val rightValue = array[right]
      if (leftValue <= pivot) left++
      if (rightValue >= pivot) right--
      @Suppress("ConvertTwoComparisonsToRangeCheck") // Simplicity
      if (leftValue > pivot && rightValue < pivot) swap(array, left, right)
    }
    swap(array, start, right)
    // Now iterate through the smaller arrays first based on where the pivot is now located
    val leftSize = right - start
    val rightSize = end - right

    // We only have a left array, so sort that
    if (rightSize == 0) quickSort(array, start, right - 1)
    // We only have a right array, so sort that
    else if (leftSize == 0) quickSort(array, right + 1, end)
    // Sort the left array before the right array
    else if (leftSize < rightSize) {
      quickSort(array, start, right - 1)
      quickSort(array, right + 1, end)
    }
    // Sort the right array before the left array
    else {
      quickSort(array, right + 1, end)
      quickSort(array, start, right - 1)
    }
  }

  private fun swap(array: MutableList<Int>, index1: Int, index2: Int) {
    if (index1 == index2) return
    array[index1] = array[index1] xor array[index2]
    array[index2] = array[index1] xor array[index2]
    array[index1] = array[index1] xor array[index2]
  }
}
