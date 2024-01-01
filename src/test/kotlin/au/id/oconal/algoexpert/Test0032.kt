package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0032 {

  data class Data(var array: MutableList<Int>, val targetSum: Int, val output: List<List<Int>>)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(
          mutableListOf(12, 3, 1, 2, -6, 5, -8, 6),
          0,
          listOf(listOf(-8, 2, 6), listOf(-8, 3, 5), listOf(-6, 1, 5))
        ),
        Data(mutableListOf(1, 2, 3), 6, listOf(listOf(1, 2, 3))),
        Data(mutableListOf(1, 2, 3), 7, listOf()),
        Data(mutableListOf(8, 10, -2, 49, 14), 57, listOf(listOf(-2, 10, 49))),
        Data(
          mutableListOf(12, 3, 1, 2, -6, 5, 0, -8, -1),
          0,
          listOf(listOf(-8, 3, 5), listOf(-6, 1, 5), listOf(-1, 0, 1))
        ),
        Data(
          mutableListOf(12, 3, 1, 2, -6, 5, 0, 8, -1, 6),
          0,
          listOf(listOf(-6, 0, 6), listOf(-6, 1, 5), listOf(-1, 0, 1))
        ),
        Data(
          mutableListOf(12, 3, 1, 2, -6, 5, 0, -8, -1, 6, 5),
          0,
          listOf(
            listOf(-8, 2, 6),
            listOf(-8, 3, 5),
            listOf(-6, 0, 6),
            listOf(-6, 1, 5),
            listOf(-1, 0, 1)
          )
        ),
        Data(
          mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 15),
          18,
          listOf(
            listOf(1, 2, 15),
            listOf(1, 8, 9),
            listOf(2, 7, 9),
            listOf(3, 6, 9),
            listOf(3, 7, 8),
            listOf(4, 5, 9),
            listOf(4, 6, 8),
            listOf(5, 6, 7)
          )
        ),
        Data(mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 15), 32, listOf(listOf(8, 9, 15))),
        Data(mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 15), 33, listOf()),
        Data(mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 15), 5, listOf()),
      )
  }

  /** This test is just run so we don't get the compile time included in IntelliJ's output. */
  @Suppress("TestFunctionName") @Test fun __performance() {}

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0032().run(data.array, data.targetSum))
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testHashMap(data: Data) {
    assertEquals(data.output, Problem0032().runHashMap(data.array, data.targetSum))
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testSortedHashMap(data: Data) {
    assertEquals(data.output, Problem0032().runSortedHashMap(data.array, data.targetSum))
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testTwoPointers(data: Data) {
    assertEquals(data.output, Problem0032().runTwoPointers(data.array, data.targetSum))
  }
}
