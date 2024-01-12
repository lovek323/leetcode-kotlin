@file:Suppress("DuplicatedCode", "ReplaceManualRangeWithIndicesCalls")

package au.id.oconal.algoexpert

class Problem0175 {
  fun run(str: String): Int {
    val palindromicity = Array(str.length) { Array<Boolean?>(str.length) { null } }

    // Okay, we're looking for palindromes surrounding the start index
    for (startIndex in (str.length - 1).downTo(0)) {
      for (endIndex in (str.length - 1).downTo(0)) {
        if (endIndex >= startIndex) {
          palindromicity[startIndex][endIndex] =
            if (endIndex == startIndex) true
            else if (startIndex == endIndex - 1) str[startIndex] == str[endIndex]
            else
              (str[startIndex] == str[endIndex] && palindromicity[startIndex + 1][endIndex - 1]!!)
        }
      }
    }

    // Now, create an array to represent the minimum number of cuts required to end up entirely with
    // palindromes for every string going from 0 to endIndex
    //
    // Our algorithm is a bit complex, but essentially:
    // 1. If the substring is a palindrome, we need 0 cuts
    // 2. If the substring is not a palindrome, we neeed a subloop
    //    1. We will be going through all the start indices now
    //    2. Starting at 1 and going until the current endIndex
    //    3. We will check the string to see if it is a palindrome
    //       (i.e., does the addition of the new letter make a palindrome with what was already
    //       there even if it doesn't with what was starting at 0)
    //    4. If there is a string starting at the new start index and ending at the end index
    //       that is a palindrome, then we just need to add a new cut before it and keep all the
    //       cuts that were required up to that point
    val minimumCutsRequired = IntArray(str.length) { 0 }
    for (endIndex in 0.until(str.length)) {
      if (palindromicity[0][endIndex] == true) {
        minimumCutsRequired[endIndex] = 0
      } else {
        for (startIndex in 1.rangeTo(endIndex)) {
          if (palindromicity[startIndex][endIndex] == true) {
            minimumCutsRequired[endIndex] = minimumCutsRequired[startIndex - 1] + 1
            break
          }
        }
      }
    }

    return minimumCutsRequired[str.length - 1]
  }
}
