package au.id.oconal.algoexpert

class Problem0027 {

  fun run(string: String): String {
    val array = string.toCharArray()
    val output = StringBuilder()
    var currentChar: Char? = null
    var currentCount = 0
    for (char in array) {
      if (char != currentChar || currentCount == 9) {
        if (currentChar != null) output.append("$currentCount$currentChar")
        currentChar = char
        currentCount = 1
      } else currentCount++
    }
    if (currentChar != null) output.append("$currentCount$currentChar")
    return output.toString()
  }
}
