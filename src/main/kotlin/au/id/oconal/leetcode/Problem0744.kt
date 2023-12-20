package au.id.oconal.leetcode

/**
 * You are given an array of characters letters that is sorted in non-decreasing order, and a
 * character target. There are at least two different characters in letters.
 *
 * Return the smallest character in letters that is lexicographically greater than target. If such a
 * character does not exist, return the first character in letters.
 *
 * https://leetcode.com/problems/find-smallest-letter-greater-than-target/submissions/1124123890/?submissionId=1124126918
 *
 * Computational complexity: linear, O(N)
 *
 * Space complexity: constant, O(1)
 */
class Problem0744 {
  fun run(letters: CharArray, target: Char) =
    // Letters in non-decreasing order: a b c
    // Smallest (first) character in letters that is > target
    letters.firstOrNull { it > target } ?: letters.first()
}
