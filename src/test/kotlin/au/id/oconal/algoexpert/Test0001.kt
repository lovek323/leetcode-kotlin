package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class Test0001 {

  data class Data(val array: MutableList<Int>, val target: Int, val output: List<Int>)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(mutableListOf(3, 5, -4, 8, 11, 1, -1, 6), 10, listOf(11, -1)),
        Data(mutableListOf(4, 6), 10, listOf(4, 6)),
        Data(mutableListOf(4, 6, 1), 5, listOf(4, 1)),
        Data(mutableListOf(4, 6, 1, -3), 3, listOf(6, -3)),
        Data(mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9), 17, listOf(8, 9)),
        Data(mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 15), 18, listOf(3, 15)),
        Data(mutableListOf(-7, -5, -3, -1, 0, 1, 3, 5, 7), -5, listOf(-5, 0)),
        Data(mutableListOf(-21, 301, 12, 4, 65, 56, 210, 356, 9, -47), 163, listOf(210, -47)),
        Data(mutableListOf(-21, 301, 12, 4, 65, 56, 210, 356, 9, -47), 164, listOf()),
        Data(mutableListOf(3, 5, -4, 8, 11, 1, -1, 6), 15, listOf()),
        Data(mutableListOf(14), 15, listOf()),
        Data(mutableListOf(15), 15, listOf()),
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    val output = Problem0001().run(data.array, data.target)
    assertEquals(data.output.sorted(), output.sorted())
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testTwoPointers(data: Data) {
    val output = Problem0001().runTwoPointers(data.array, data.target)
    assertEquals(data.output.sorted(), output.sorted())
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testHashTable(data: Data) {
    val output = Problem0001().runHashTable(data.array, data.target)
    assertEquals(data.output.sorted(), output.sorted())
  }
}
