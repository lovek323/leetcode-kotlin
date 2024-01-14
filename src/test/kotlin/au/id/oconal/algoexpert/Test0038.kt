package au.id.oconal.algoexpert

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0038 {

  data class Data(val array: List<Int>, val output: List<Int>)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(listOf(5, 1, 4, 2), listOf(8, 40, 10, 20)),
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output, Problem0038().arrayOfProducts(data.array))
  }
}
