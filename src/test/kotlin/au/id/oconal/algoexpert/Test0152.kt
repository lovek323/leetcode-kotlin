package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0152 {

  data class Data(val array: MutableList<Int>, val output: List<Int>)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        // Data(mutableListOf(8, 5, 2, 9, 5, 6, 3), listOf(2, 3, 5, 5, 6, 8, 9)),
        Data(mutableListOf(1, 3, 2), listOf(1, 2, 3))
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0152().quickSort(data.array))
  }
}
