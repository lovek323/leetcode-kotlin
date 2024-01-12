package au.id.oconal.algoexpert

class Problem0019 {
  fun run(array: List<*>): Int = sum(array, 1)

  private fun sum(array: List<*>, depth: Int): Int =
    array.sumOf { if (it is Int) it else sum(it as List<*>, depth + 1) } * depth
}
