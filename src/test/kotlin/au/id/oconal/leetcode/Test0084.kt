package au.id.oconal.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class Test0084 {

  data class Data(val heights: List<Int>, val output: Int)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(Test0084::class.java.getResource("/0084-0001.txt")!!.readText().split(",").map { it.toInt() }.toList(), 104991),
        Data(Test0084::class.java.getResource("/0084-0002.txt")!!.readText().split(",").map { it.toInt() }.toList(), 250000000),
        Data(List(30000) { 1 }, 30000),
        Data(List(35578) { 7303 }, 259826134),
        Data(listOf(0), 0),
        Data(listOf(1), 1),
        Data(listOf(2, 1, 2), 3),
        Data(listOf(2, 1, 5, 6, 2, 3), 10),
        Data(listOf(2, 4), 4),
        Data(listOf(5, 4, 1, 2), 8),
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0084().run(data.heights.toIntArray()))
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testMap(data: Data) {
    assertEquals(data.output, Problem0084().runMap(data.heights.toIntArray()))
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testStack(data: Data) {
    assertEquals(data.output, Problem0084().runStack(data.heights.toIntArray()))
  }
}
