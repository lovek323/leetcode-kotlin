package au.id.oconal.mock

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class SortedArrayCommonElementsTest {

  data class Data(val array1: List<Int>, val array2: List<Int>, val output: List<Int>)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(listOf(1, 2, 3), listOf(3, 4, 5), listOf(3)),
        Data(listOf(1, 2, 3), listOf(4, 5, 6), listOf()),
        Data(listOf(1, 2, 3), listOf(1, 2, 4), listOf(1, 2)),
        Data(listOf(2, 3, 4, 5), listOf(1, 2, 5, 6), listOf(2, 5)),
        Data(listOf(10, 12, 14, 16, 18), listOf(11, 12, 13, 14, 15), listOf(12, 14)),
        Data(listOf(21, 22, 23, 24, 25), listOf(20, 22, 24, 26, 28), listOf(22, 24)),
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    val output = SortedArrayCommonElements().findCommonElements(data.array1, data.array2)
    assertEquals(data.output.sorted(), output.sorted())
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testFaster(data: Data) {
    val output = SortedArrayCommonElements().findCommonElementsFaster(data.array1, data.array2)
    assertEquals(data.output.sorted(), output.sorted())
  }
}
