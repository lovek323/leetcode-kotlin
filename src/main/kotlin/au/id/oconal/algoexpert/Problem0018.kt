package au.id.oconal.algoexpert

class Problem0018 {
  fun run(n: Int): Int = getNthFib(n, hashMapOf())

  private fun getNthFib(n: Int, cache: HashMap<Int, Int>): Int {
    if (n == 1) return 0
    if (n == 2) return 1
    val smaller = cache.getOrPut(n - 2) { getNthFib(n - 2, cache) }
    val larger = cache.getOrPut(n - 1) { getNthFib(n - 1, cache) }
    return smaller + larger
  }

  fun runIteratively(n: Int): Int {
    if (n == 1) return 0
    if (n == 2) return 1
    var lastButTwo = 0
    var lastButOne = 1
    var current = 0
    for (currentN in 3.rangeTo(n)) {
      current = lastButTwo + lastButOne
      lastButTwo = lastButOne
      lastButOne = current
    }
    return current
  }
}
