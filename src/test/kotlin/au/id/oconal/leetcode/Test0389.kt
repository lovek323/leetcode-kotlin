package au.id.oconal.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class Test0389 {

  data class Data(val s: String, val t: String, val output: Char)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data("abcd", "abcde", 'e'),
        Data("", "y", 'y'),
        Data("dabc", "edabc", 'e'),
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0389().run(data.s, data.t))
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testSum(data: Data) {
    assertEquals(data.output, Problem0389().runSum(data.s, data.t))
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testXor(data: Data) {
    assertEquals(data.output, Problem0389().runXor(data.s, data.t))
  }
}
