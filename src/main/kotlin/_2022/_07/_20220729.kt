@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.TreeNode
import utils.Node
import utils.assertEqualTo
import utils.runTimedTests
import java.util.*


// 226. Invert Binary Tree
// https://leetcode.com/problems/invert-binary-tree/
interface Leetcode_226 {
    fun invertTree(root: TreeNode?): TreeNode?

    companion object : Testable {
        override fun test() {
            /*
            val tests = listOf(

            )
            listOf(
                M1()::,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
            */
        }
    }

    private class M1 : Leetcode_226 {
        override fun invertTree(root: TreeNode?): TreeNode? {
            if (root == null) return null
            val t = root.right
            root.right = root.left
            root.left = t
            if (root.right != null) invertTree(root.right)
            if (root.left != null) invertTree(root.left)
            return root
        }
    }
}


// 104. Maximum Depth of Binary Tree
// https://leetcode.com/problems/maximum-depth-of-binary-tree/
private interface Leetcode_104 {
    fun maxDepth(root: TreeNode?): Int

    companion object : Testable {
        override fun test() {
            /*
            val tests = listOf(

            )
            listOf(
                M1()::,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
            */
        }
    }

    private class S : Leetcode_104 {
        override fun maxDepth(root: TreeNode?): Int {
            return root?.run {
                1 + Math.max(
                    left?.let { maxDepth(it) } ?: 0,
                    right?.let { maxDepth(it) } ?: 0
                )
            } ?: 0
        }
    }
}

// 104. Maximum Depth of Binary Tree
// https://leetcode.com/problems/maximum-depth-of-binary-tree/
interface Leetcode_543 {
    fun diameterOfBinaryTree(root: TreeNode?): Int
    private class S : Leetcode_543 {
        private var max = 0


        override fun diameterOfBinaryTree(root: TreeNode?): Int {
            if (root == null) return 0

            return Math.max(
                diameter(root) - 1, max
            )
        }


        fun diameter(root: TreeNode?): Int {
            if (root == null) return 0
            if (root.left == null && root.right == null) return 1

            val left = if (root.left != null) (diameter(root.left)) else 0
            val right = if (root.right != null) (diameter(root.right)) else 0

            if (root.left != null && root.right != null) {
                val passRoot = left + right
                if (passRoot > max) max = passRoot
            }

            return Math.max(left, right) + 1
        }
    }
}

// 110. Balanced Binary Tree
// https://leetcode.com/problems/balanced-binary-tree/
interface Leetcode_110 {
    fun isBalanced(root: TreeNode?): Boolean
    private class S : Leetcode_110 {
        override fun isBalanced(root: TreeNode?): Boolean {
            fun getHeight(root: TreeNode?): Int {
                if (root == null) return 0
                if (root.left == null && root.right == null) return 1
                val left = root.left?.let {
                    getHeight(it)
                } ?: 0
                val right = root.right?.let {
                    getHeight(it)
                } ?: 0
                if (left == -1 || right == -1) return -1
                if (Math.abs(left - right) > 1) return -1
                val height = Math.max(left, right) + 1
                return height
            }


            if (getHeight(root) == -1) return false
            return true
        }
    }
}

// 100. Same Tree
// https://leetcode.com/problems/same-tree/

interface Leetcode_100 {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean
    private class S : Leetcode_100 {
        override fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
            if (p == q) return true

            if (p?.`val` != q?.`val`) return false

            if (
                !isSameTree(p?.left, q?.left)
                || !isSameTree(p?.right, q?.right)
            ) return false

            return true
        }
    }
}

// 572. Subtree of Another Tree
// https://leetcode.com/problems/subtree-of-another-tree/

// MARK:
// input:
// [3,4,5,1,2,null,null,null,null,0]
// [4,1,2]
// expected: [False]

interface Leetcode_572 {
    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean


    // brute force
    private class S : Leetcode_572 {
        override fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
            if (root == null && subRoot == null) return true
            if ((root == null) != (subRoot == null)) return false
            return isSubtree(root, subRoot, false)
        }


        fun isSubtree(root: TreeNode?, subRoot: TreeNode?, validate: Boolean): Boolean {
            if (root == null && subRoot == null) return true
            // what is subRoot is null and root is not, should we consider it as true?
            // if (subRoot == null) return true
            // if (root == null) return false

            if ((root == null) != (subRoot == null)) return false

            if (
                root?.`val` == subRoot?.`val`
                && isSubtree(root?.left, subRoot?.left, true)
                && isSubtree(root?.right, subRoot?.right, true)
            ) {
                return true
            }

            if (validate) return false

            if (isSubtree(root?.left, subRoot, false)) return true
            if (isSubtree(root?.right, subRoot, false)) return true

            return false
        }
    }
}

// 235. Lowest Common Ancestor of a Binary Search Tree
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
interface Leetcode_235 {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode?

    private class M1 : Leetcode_235 {
        override fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
            if (root == null || p == null || q == null) return null
            val stackP = Stack<TreeNode>()
            val stackQ = Stack<TreeNode>()
            hasDescendant(root, p, stackP)
            hasDescendant(root, p, stackQ)
            val ln1 = stackToLinkedNode(stackP)
            val ln2 = stackToLinkedNode(stackQ)

            var h1 = ln1
            var h2 = ln2
            while(h1 != null && h2 != null) {
                if (h1?.next == h2?.next) {
                    h1 = h1.next
                    h2 = h2.next
                }
            }
            return h1!!.`val`!!
        }

        private fun stackToLinkedNode(stack: Stack<TreeNode>) : Node<TreeNode?>? {
            val head = Node<TreeNode?>(null)

            while(!stack.isEmpty()) {
                val n = Node(stack.pop())
                n.next = head.next
                head.next = n
            }
            return head.next
        }

        private fun hasDescendant(
            root: TreeNode,
            p: TreeNode,
            stack: Stack<TreeNode>
        ): Boolean {

            stack.push(p)
            if (root == p) {
                return true
            }
            if (root.left != null) {
                if (hasDescendant(root.left!!, p, stack)) return true
            }

            if (root.right != null) {
                if (hasDescendant(root.right!!, p, stack)) return true
            }
            stack.pop()
            return false
        }
    }


    // Runtime: 333 ms, faster than 84.55% of Kotlin online submissions for Lowest Common Ancestor of a Binary Search Tree.
    // Memory Usage: 44.8 MB, less than 86.45% of Kotlin online submissions for Lowest Common Ancestor of a Binary Search Tree.
    // https://leetcode.com/submissions/detail/760273465/
    private class M2 : Leetcode_235 {
        override fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
            var last = root
            var h = root
            while (true) {
                last = h
                when {
                    hasDesc(h?.left, p) && hasDesc(h?.left, q) -> {
                        h = h?.left
                    }

                    hasDesc(h?.right, p) && hasDesc(h?.right, q) -> {
                        h = h?.right
                    }

                    else -> return last
                }
            }
        }

        private fun hasDesc(root: TreeNode?, n: TreeNode?): Boolean {
            if (root == null || n == null) return false
            if (root == n) return true
            return hasDesc(root.left, n) || hasDesc(root.right, n)
        }
    }

    private class M3 : Leetcode_235 {
        override fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
            if (root == null || p == null || q == null) return root

            var h = root

            while(true) {
                when{
                    p.`val` < h!!.`val` && q.`val` < h!!.`val` -> {
                        h = h.right
                    }
                    p.`val` > h!!.`val` && q.`val` > h!!.`val` -> {
                        h = h.left
                    }
                    else -> return h
                }
            }
        }
    }
}