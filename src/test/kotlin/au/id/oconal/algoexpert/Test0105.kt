package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0105 {

  data class Data(var array: MutableList<Int>, val targetSum: Int, val output: List<List<Int>>)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(mutableListOf(7, 6, 4, -1, 1, 2), 16, listOf(listOf(7, 6, 4, -1), listOf(7, 6, 1, 2))),
        Data(mutableListOf(1, 2, 3, 4, 5, 6, 7), 10, listOf(listOf(3, 4, 2, 1))),
        Data(
          mutableListOf(5, -5, -2, 2, 3, -3),
          0,
          listOf(listOf(-2, 2, -5, 5), listOf(3, -3, -5, 5), listOf(3, -3, 2, -2))
        ),
        Data(
          mutableListOf(-2, -1, 1, 2, 3, 4, 5, 6, 7, 8, 9),
          4,
          listOf(
            listOf(1, 6, -1, -2),
            listOf(2, 3, 1, -2),
            listOf(2, 5, -1, -2),
            listOf(3, 4, -1, -2)
          )
        ),
        Data(
          mutableListOf(-1, 22, 18, 4, 7, 11, 2, -5, -3),
          30,
          listOf(
            listOf(7, 2, 22, -1),
            listOf(7, -3, 4, 22),
            listOf(11, 2, 18, -1),
            listOf(11, -3, 4, 18),
            listOf(2, -5, 11, 22)
          )
        ),
        Data(
          mutableListOf(-10, -3, -5, 2, 15, -7, 28, -6, 12, 8, 11, 5),
          20,
          listOf(
            listOf(15, 8, 2, -5),
            listOf(-7, 28, 2, -3),
            listOf(28, 5, -3, -10),
            listOf(-6, 8, 28, -10),
            listOf(-6, 5, 28, -7),
            listOf(12, 11, 2, -5),
            listOf(8, 5, 12, -5)
          )
        ),
        Data(mutableListOf(1, 2, 3, 4, 5), 100, listOf()),
        Data(
          mutableListOf(1, 2, 3, 4, 5, -5, 6, -6),
          5,
          listOf(
            listOf(5, -5, 3, 2),
            listOf(5, -5, 4, 1),
            listOf(5, -6, 4, 2),
            listOf(-5, 6, 3, 1),
            listOf(6, -6, 3, 2),
            listOf(6, -6, 4, 1)
          )
        ),
      )
  }

  /** This test is just run so we don't get the compile time included in IntelliJ's output. */
  @Suppress("TestFunctionName") @Test fun __performance() {}

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    val actual = Problem0105().run(data.array, data.targetSum)
    assertEquals(data.output.size, actual.size) { "Expected: ${data.output}, Actual: $actual" }
    data.output.forEach { expected ->
      assertTrue(actual.any { expected.containsAll(it) && it.containsAll(expected) })
    }
  }
}
