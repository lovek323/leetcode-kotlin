package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0002 {

  data class Data(val array: List<Int>, val sequence: List<Int>, val output: Boolean)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(1, 6, -1, 10), true),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(5, 1, 22, 25, 6, -1, 8, 10), true),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(5, 1, 22, 6, -1, 8, 10), true),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(22, 25, 6), true),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(1, 6, 10), true),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(5, 1, 22, 10), true),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(5, -1, 8, 10), true),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(25), true),
        Data(listOf(1, 1, 1, 1), listOf(1, 1, 1), true),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(5, 1, 22, 25, 6, -1, 8, 10, 12), false),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(4, 5, 1, 22, 25, 6, -1, 8, 10), false),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(5, 1, 22, 23, 6, -1, 8, 10), false),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(5, 1, 22, 22, 25, 6, -1, 8, 10), false),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(5, 1, 22, 22, 6, -1, 8, 10), false),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(1, 6, -1, -1), false),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(1, 6, -1, -1, 10), false),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(1, 6, -1, -2), false),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(26), false),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(5, 1, 25, 22, 6, -1, 8, 10), false),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(5, 26, 22, 8), false),
        Data(listOf(1, 1, 6, 1), listOf(1, 1, 1, 6), false),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(1, 6, -1, 10, 11, 11, 11, 11), false),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(5, 1, 22, 25, 6, -1, 8, 10, 10), false),
        Data(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(1, 6, -1, 5), false),
      )
  }

  /** This test is just run so we don't get the compile time included in IntelliJ's output. */
  @Suppress("TestFunctionName")
  @Test fun __performance() {}

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0002().run(data.array, data.sequence))
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testOneLoop(data: Data) {
    assertEquals(data.output, Problem0002().runOneLoop(data.array, data.sequence))
  }
}
