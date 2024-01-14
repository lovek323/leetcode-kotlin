package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class Test0123 {

  data class Data(val items: List<List<Int>>, val capacity: Int, val output: Pair<Int, List<Int>>)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(listOf(listOf(1, 2), listOf(4, 3), listOf(5, 6), listOf(6, 7)), 10, 10 to listOf(1, 3))
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  @Disabled("Broken")
  fun test(data: Data) {
    assertEquals(data.output, Problem0123().knapsackProblem(data.items, data.capacity))
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testFaster(data: Data) {
    val output = Problem0123().knapsackProblemFaster(data.items, data.capacity)
    assertEquals(data.output.first, output.first)
    assertEquals(data.output.second.sorted(), output.second.sorted())
  }
}
