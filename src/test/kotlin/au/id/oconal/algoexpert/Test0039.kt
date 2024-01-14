package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0039 {

  data class Data(val array: MutableList<Int>, val output: Int)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(mutableListOf(2, 1, 5, 2, 3, 3, 4), 2),
        Data(mutableListOf(7, 6, 5, 3, 6, 4, 3, 5, 2), 6),
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0039().firstDuplicateValue(data.array))
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testLessSpace(data: Data) {
    assertEquals(data.output, Problem0039().firstDuplicateValueLessSpace(data.array))
  }
}
