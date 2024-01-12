package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0019 {

  data class Data(val array: List<*>, val output: Int)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(Data(listOf(5, 2, listOf(7, -1), 3, listOf(6, listOf(-13, 8), 4)), 12))
  }

  /** This test is just run so we don't get the compile time included in IntelliJ's output. */
  @Suppress("TestFunctionName") @Test fun __performance() {}

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0019().run(data.array))
  }
}
