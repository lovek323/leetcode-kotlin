package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0028 {

  data class Data(val strings: MutableList<String>, val output: List<String>)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(mutableListOf("abc", "bcd", "cbaccd"), listOf("b", "c")),
      )
  }

  /** This test is just run so we don't get the compile time included in IntelliJ's output. */
  @Suppress("TestFunctionName") @Test fun __performance() {}

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output.sorted(), Problem0028().run(data.strings).sorted())
  }
}
