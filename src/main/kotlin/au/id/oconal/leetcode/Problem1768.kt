package au.id.oconal.leetcode

import kotlin.math.max

/**
 * You are given two strings `word1` and `word2`. Merge the strings by adding letters in alternating
 * order, starting with `word1`. If a string is longer than the other, append the additional letters
 * onto the end of the merged string.
 *
 * Return the merged string.
 */
class Problem1768 {
  fun run(word1: String, word2: String) =
    IntRange(0, max(word1.length, word2.length) - 1).joinToString("") {
      (word1.getOrNull(it)?.toString() ?: "") + (word2.getOrNull(it)?.toString() ?: "")
    }
}
