package au.id.oconal.algoexpert

class Problem0028 {

  fun run(strings: MutableList<String>): List<String> {
    val characterCounts = hashMapOf<Char, Int>()
    val commonCharacters = mutableListOf<String>()
    strings.forEachIndexed { index, string ->
      string.toSet().forEach { char ->
        val newCount = characterCounts.getOrPut(char) { 0 } + 1
        characterCounts[char] = newCount
        if (index == strings.size - 1 && newCount == strings.size) {
          commonCharacters.add(char.toString())
        }
      }
    }
    return commonCharacters
  }
}
