package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0020 {

  data class Data(val array: List<Int>, val target: Int, val output: Int)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(listOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), 33, 3),
        Data(listOf(0, 1, 1, 1, 1, 1, 9, 9, 9, 9, 18, 18, 18), 9, 6)
      )
  }

  /** This test is just run so we don't get the compile time included in IntelliJ's output. */
  @Suppress("TestFunctionName") @Test fun __performance() {}

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0020().run(data.array, data.target))
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testIteratively(data: Data) {
    assertEquals(data.output, Problem0020().runIteratively(data.array, data.target))
  }
}
