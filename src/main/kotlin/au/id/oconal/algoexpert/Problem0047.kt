@file:Suppress("CanBePrimaryConstructorProperty")

package au.id.oconal.algoexpert

class Problem0047 {

  open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null

    fun insert(value: Int): BST {
      if (value < this.value) {
        if (left == null) left = BST(value) else left!!.insert(value)
      } else if (value >= this.value) {
        if (right == null) right = BST(value) else right!!.insert(value)
      }

      return this
    }

    fun contains(value: Int): Boolean {
      if (this.value != value && isLeaf()) return false
      if (value < this.value) return left?.contains(value) ?: false
      if (value > this.value) return right?.contains(value) ?: false

      return true
    }

    fun remove(value: Int): BST? {
      // If we've found the value to delete
      if (value == this.value) {
        // If this is a leaf node, return null
        if (isLeaf()) return null
        // If this node only has one child, copy over that child to this node
        if (left == null) {
          this.value = right!!.value
          left = right!!.left
          right = right!!.right
        } else if (right == null) {
          this.value = left!!.value
          right = left!!.right
          left = left!!.left
        } else {
          // Otherwise, if this node has two children, find the lowest value in the
          // right subtree and swap it with this value, then delete the leaf node from
          // the right subtree
          val minNode = right!!.minNode()
          this.value = minNode.value
          minNode.value = value
          right = right!!.remove(value)
        }
      }

      // If the value is less than this, we're trying to remove from the left tree
      if (value < this.value && left != null) left = left!!.remove(value)

      // If the value is greater than this, we're trying to remove from the right tree
      if (value > this.value && right != null) right = right!!.remove(value)

      return this
    }

    private fun isLeaf() = left == null && right == null

    private fun allNodes(): List<BST> =
      (listOf(this) + (left?.allNodes() ?: emptyList()) + (right?.allNodes() ?: emptyList()))

    private fun minNode() = allNodes().minBy { it.value }

    override fun toString(): String {
      return "BST(value=$value, left=$left, right=$right)"
    }
  }
}
