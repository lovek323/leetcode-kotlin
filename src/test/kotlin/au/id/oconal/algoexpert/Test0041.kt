package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0041 {

  data class Data(val seats: MutableList<Int>, val output: Int)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        // Data(mutableListOf(1, 0, 1, 0, 0, 0, 1), 4),
        // Data(mutableListOf(1, 0, 0, 1), 1),
        Data(mutableListOf(1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1), 3)
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0041().bestSeat(data.seats))
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testLessSpace(data: Data) {
    assertEquals(data.output, Problem0041().bestSeatsLessSpace(data.seats))
  }
}
