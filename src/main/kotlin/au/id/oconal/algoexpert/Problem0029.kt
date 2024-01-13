package au.id.oconal.algoexpert

class Problem0029 {

  /**
   * Time complexity: O(m + n + o) where n is the length of characters, m is the length of document
   * and o is the number of unique characters in document.
   */
  fun run(characters: String, document: String): Boolean {
    val availableCharacterCounts = hashMapOf<Char, Int>()
    characters.forEach {
      availableCharacterCounts[it] = availableCharacterCounts.getOrPut(it) { 0 } + 1
    }
    document.forEach {
      if ((availableCharacterCounts[it] ?: 0) == 0) return false
      availableCharacterCounts[it] = (availableCharacterCounts[it] ?: 0) - 1
    }
    return true
  }
}
