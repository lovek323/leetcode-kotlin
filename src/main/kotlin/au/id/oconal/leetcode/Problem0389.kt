package au.id.oconal.leetcode

/**
 * You are given two strings `s` and `t`.
 *
 * String `t` is generated by randomly shuffling string `s` and then adding one more letter at a
 * random position.
 *
 * Return the letter that was added to `t`.
 */
class Problem0389 {
  fun run(s: String, t: String): Char =
    // Remove all the letters in `s` from `t` and return what is remaining
    if (s.isEmpty()) t.toCharArray().first()
    else run(s.substring(1), t.removeRange(t.indexOf(s.first()) ..< t.indexOf(s.first()) + 1))

  /**
   * Uses the sum of the ASCII values of the characters in each string to determine the added
   * character.
   *
   * Actually runs slower on my system (15 ms vs 9 ms for `run()`), but faster on the Leetcode site.
   */
  fun runSum(s: String, t: String): Char =
    (t.toCharArray().sumOf { it.code } - s.toCharArray().sumOf { it.code }).toChar()

  /**
   * The `xor` bitwise operator returns `1` if the input bits are different and `0` if they are the
   * same for each bit.
   *
   * All the letters in `s` are in `t` and one extra letter is in `t`. This means that if we compute
   * the `xor` for all codes for the letters in `s` and `t`, this will be the code for the extra
   * letter.
   *
   * On my computer, this ran in 2 ms.
   */
  fun runXor(s: String, t: String): Char =
    (s + t).map { it.code }.reduce { acc, i -> acc xor i }.toChar()
}