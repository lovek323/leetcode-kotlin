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


  fun semordnilap(words: List<String>): List<List<String>> {
    // First, for each word, get all the other possible words based on string length
    val wordGroups = hashMapOf<Int, MutableList<String>>()
    words.forEach {
      wordGroups.getOrPut(it.length) { mutableListOf() }.add(it)
    }
    wordGroups.values.filter { it.size > 1 }.forEach {
      println(it)
    }
    return emptyList()
  }
}
