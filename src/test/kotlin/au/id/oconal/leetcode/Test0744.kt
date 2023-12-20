@file:Suppress("ArrayInDataClass")

package au.id.oconal.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class Test0744 {

  data class Data(val letters: CharArray, val target: Char, val output: Char)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data("cfj".toCharArray(), 'a', 'c'),
        Data("cfj".toCharArray(), 'c', 'f'),
        Data("xxyy".toCharArray(), 'z', 'x')
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0744().run(data.letters, data.target))
  }
}
