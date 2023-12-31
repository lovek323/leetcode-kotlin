package au.id.oconal.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class Test0088 {

  data class Data(
    val nums1: List<Int>,
    val m: Int,
    val nums2: List<Int>,
    val n: Int,
    val output: List<Int>
  )

  companion object {
    @JvmStatic
    fun dataProvider() =
      listOf(
        Data(listOf(1, 2, 3, 0, 0, 0), 3, listOf(2, 5, 6), 3, listOf(1, 2, 2, 3, 5, 6)),
        Data(listOf(1), 1, listOf(), 0, listOf(1)),
        Data(listOf(0), 0, listOf(1), 1, listOf(1)),
        Data(
          listOf(
            -10,
            -10,
            -9,
            -9,
            -9,
            -8,
            -8,
            -7,
            -7,
            -7,
            -6,
            -6,
            -6,
            -6,
            -6,
            -6,
            -6,
            -5,
            -5,
            -5,
            -4,
            -4,
            -4,
            -3,
            -3,
            -2,
            -2,
            -1,
            -1,
            0,
            1,
            1,
            1,
            2,
            2,
            2,
            3,
            3,
            3,
            4,
            5,
            5,
            6,
            6,
            6,
            6,
            7,
            7,
            7,
            7,
            8,
            9,
            9,
            9,
            9,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
          ),
          55,
          listOf(
            -10,
            -10,
            -9,
            -9,
            -9,
            -9,
            -8,
            -8,
            -8,
            -8,
            -8,
            -7,
            -7,
            -7,
            -7,
            -7,
            -7,
            -7,
            -7,
            -6,
            -6,
            -6,
            -6,
            -5,
            -5,
            -5,
            -5,
            -5,
            -4,
            -4,
            -4,
            -4,
            -4,
            -3,
            -3,
            -3,
            -2,
            -2,
            -2,
            -2,
            -2,
            -2,
            -2,
            -1,
            -1,
            -1,
            0,
            0,
            0,
            0,
            0,
            1,
            1,
            1,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            3,
            3,
            3,
            3,
            4,
            4,
            4,
            4,
            4,
            4,
            4,
            5,
            5,
            5,
            5,
            5,
            5,
            6,
            6,
            6,
            6,
            6,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
            8,
            8,
            8,
            8,
            9,
            9,
            9,
            9
          ),
          99,
          listOf(
            -10,
            -10,
            -10,
            -10,
            -9,
            -9,
            -9,
            -9,
            -9,
            -9,
            -9,
            -8,
            -8,
            -8,
            -8,
            -8,
            -8,
            -8,
            -7,
            -7,
            -7,
            -7,
            -7,
            -7,
            -7,
            -7,
            -7,
            -7,
            -7,
            -6,
            -6,
            -6,
            -6,
            -6,
            -6,
            -6,
            -6,
            -6,
            -6,
            -6,
            -5,
            -5,
            -5,
            -5,
            -5,
            -5,
            -5,
            -5,
            -4,
            -4,
            -4,
            -4,
            -4,
            -4,
            -4,
            -4,
            -3,
            -3,
            -3,
            -3,
            -3,
            -2,
            -2,
            -2,
            -2,
            -2,
            -2,
            -2,
            -2,
            -2,
            -1,
            -1,
            -1,
            -1,
            -1,
            0,
            0,
            0,
            0,
            0,
            0,
            1,
            1,
            1,
            1,
            1,
            1,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            4,
            4,
            4,
            4,
            4,
            4,
            4,
            4,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            6,
            6,
            6,
            6,
            6,
            6,
            6,
            6,
            6,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
            7,
            8,
            8,
            8,
            8,
            8,
            9,
            9,
            9,
            9,
            9,
            9,
            9,
            9
          )
        )
      )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun test(data: Data) {
    assertEquals(
      data.output,
      Problem0088().run(data.nums1.toIntArray(), data.m, data.nums2.toIntArray(), data.n).toList()
    )
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  fun testLinear(data: Data) {
    assertEquals(
      data.output,
      Problem0088()
        .runLinear(data.nums1.toIntArray(), data.m, data.nums2.toIntArray(), data.n)
        .toList()
    )
  }
}
