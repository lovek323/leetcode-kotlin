package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0040 {

  data class Data(val intervals: List<List<Int>>, val output: List<List<Int>>)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(
          listOf(listOf(1, 2), listOf(3, 5), listOf(4, 7), listOf(6, 8), listOf(9, 10)),
          listOf(listOf(1, 2), listOf(3, 8), listOf(9, 10))
        ),
        Data(
          listOf(
            listOf(0, 0),
            listOf(0, 0),
            listOf(0, 0),
            listOf(0, 0),
            listOf(0, 0),
            listOf(0, 0),
            listOf(0, 1)
          ),
          listOf(listOf(0, 1))
        ),
        Data(listOf(listOf(100, 105), listOf(1, 104)), listOf(listOf(1, 105)))
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0040().mergeOverlappingIntervals(data.intervals))
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testFaster(data: Data) {
    assertEquals(data.output, Problem0040().mergeOverlappingIntervalsFaster(data.intervals))
  }
}
