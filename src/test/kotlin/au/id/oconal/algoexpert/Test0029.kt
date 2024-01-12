package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0029 {

  data class Data(val characters: String, val document: String, val output: Boolean)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        // Data("Bste!hetsi ogEAxpelrt x ", "AlgoExpert is the Best!", true),
        Data(" ", "hello", false),
      )
  }

  /** This test is just run so we don't get the compile time included in IntelliJ's output. */
  @Suppress("TestFunctionName") @Test fun __performance() {}

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0029().run(data.characters, data.document))
  }
}
