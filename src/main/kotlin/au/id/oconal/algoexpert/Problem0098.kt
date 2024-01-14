@file:Suppress(
  "DuplicatedCode",
  "ReplaceManualRangeWithIndicesCalls",
  "ReplaceJavaStaticMethodWithKotlinAnalog"
)

package au.id.oconal.algoexpert

class Problem0098 {
  fun longestPalindromicSubstring(string: String): String {
    var longestPalindromeIndexStart = -1
    var longestPalindromeIndexEnd = -1
    for (index in 0.until(string.length)) {
      // Let's say we have the string ABBA
      // And we're at index 1
      // We need to compare ABB (indices 0 to 2) - not a palindrome (checking for an odd palindrome)
      // But we also want to compare BB (indices 1 to 2) (checking for an even palindrome)

      // First do the odd palindromes
      var palindromeIndexStart = index
      var palindromeIndexEnd = index
      for (offset in 1.rangeTo(Math.min(index, string.length - index - 1))) {
        if (string[index - offset] != string[index + offset]) break
        palindromeIndexStart = index - offset
        palindromeIndexEnd = index + offset
      }
      if (
        palindromeIndexEnd - palindromeIndexStart + 1 >
          longestPalindromeIndexEnd - longestPalindromeIndexStart
      ) {
        longestPalindromeIndexStart = palindromeIndexStart
        longestPalindromeIndexEnd = palindromeIndexEnd
      }

      // Then do the even palindromes (look forwards)
      if (index < string.length - 1 && string[index] == string[index + 1]) {
        palindromeIndexStart = index
        palindromeIndexEnd = index + 1
        for (offset in 1.rangeTo(Math.min(index, string.length - index - 2))) {
          if (string[index - offset] != string[index + offset + 1]) break
          palindromeIndexStart = index - offset
          palindromeIndexEnd = index + offset + 1
        }
        println(
          "found palindrome: ${string.substring(palindromeIndexStart, palindromeIndexEnd + 1)}"
        )
        if (
          palindromeIndexEnd - palindromeIndexStart + 1 >
            longestPalindromeIndexEnd - longestPalindromeIndexStart
        ) {
          longestPalindromeIndexStart = palindromeIndexStart
          longestPalindromeIndexEnd = palindromeIndexEnd
        }
      }
    }

    return string.substring(longestPalindromeIndexStart, longestPalindromeIndexEnd + 1)
  }
}
