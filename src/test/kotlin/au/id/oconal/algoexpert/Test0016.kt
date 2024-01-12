package au.id.oconal.algoexpert

import au.id.oconal.algoexpert.Problem0016.LinkedList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0016 {

  data class Data(val linkedList: LinkedList, val output: LinkedList)

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(LinkedList(1, LinkedList(1)), LinkedList(1)),
        Data(
          LinkedList(1, LinkedList(1, LinkedList(3, LinkedList(4, LinkedList(4))))),
          LinkedList(1, LinkedList(3, LinkedList(4)))
        ),
      )
  }

  /** This test is just run so we don't get the compile time included in IntelliJ's output. */
  @Suppress("TestFunctionName") @Test fun __performance() {}

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(data.output.toList(), Problem0016().run(data.linkedList).toList())
  }
}
