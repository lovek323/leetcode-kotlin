package au.id.oconal.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class Test1768 {

  data class Data(val word1: String, val word2: String, val output: String)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data("abc", "pqr", "apbqcr"),
        Data("ab", "pqrs", "apbqrs"),
        Data("abcd", "pq", "apbqcd"),
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem1768().run(data.word1, data.word2))
  }
}
