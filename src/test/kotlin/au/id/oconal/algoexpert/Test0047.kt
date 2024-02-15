package au.id.oconal.algoexpert

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@TestMethodOrder(MethodOrderer.MethodName::class)
class Test0047 {

  @Test
  fun test1() {
    val tree = Problem0047.BST(10)

    tree.insert(5)
    tree.insert(15)
    tree.insert(2)
    tree.insert(5)
    tree.insert(13)
    tree.insert(22)
    tree.insert(1)
    tree.insert(14)
    tree.insert(12)
    tree.remove(5)
    tree.remove(5)
    tree.remove(12)
    tree.remove(13)
    tree.remove(14)
    tree.remove(22)
    tree.remove(2)
    tree.remove(1)
    tree.contains(15)
    println(tree)
  }
}
