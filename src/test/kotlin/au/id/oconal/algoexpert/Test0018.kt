package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0018 {

  data class Data(val n: Int, val output: Int)

  companion object {
    @JvmStatic fun dataProvider() = listOf(Data(2, 1), Data(6, 5), Data(18, 1597))
  }

  /** This test is just run so we don't get the compile time included in IntelliJ's output. */
  @Suppress("TestFunctionName") @Test fun __performance() {}

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0018().run(data.n))
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testIteratively(data: Data) {
    assertEquals(data.output, Problem0018().runIteratively(data.n))
  }
}
