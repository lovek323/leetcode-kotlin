package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0037 {

  data class Data(val array: List<Int>, val output: Int)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(listOf(1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3), 6),
        Data(listOf(1, 3, 2), 3),
        Data(listOf(5, 4, 3, 2, 1, 2, 10, 12), 0),
        Data(listOf(1, 2, 3, 3, 2, 1), 0),
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0037().longestPeak(data.array))
  }
}
