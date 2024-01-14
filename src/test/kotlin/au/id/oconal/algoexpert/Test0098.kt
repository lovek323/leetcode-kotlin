package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0098 {

  data class Data(val string: String, val output: String)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        // Data("abaxyzzyxf", "xyzzyx"),
        // Data("a", "a"),
        // Data("it's highnoon", "noon"),
        Data("aa", "aa"),
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0098().longestPalindromicSubstring(data.string))
  }
}
