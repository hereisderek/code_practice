@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.TreeNode
import utils.assertEqualTo
import utils.runTimedTests
import utils.tupleOf
import java.util.*


// 199. Binary Tree Right Side View
// https://leetcode.com/problems/binary-tree-right-side-view/
private interface Leetcode_199 {
    fun rightSideView(root: TreeNode?): List<Int>

    companion object : Testable {
        override fun test() {}
    }

    // Runtime: 292 ms, faster than 40.23% of Kotlin online submissions for Binary Tree Right Side View.
    // Memory Usage: 36.3 MB, less than 30.88% of Kotlin online submissions for Binary Tree Right Side View.
    // https://leetcode.com/submissions/detail/773318529/
    private class M1 : Leetcode_199 {
        override fun rightSideView(root: TreeNode?): List<Int> {

            if (root == null) return emptyList()

            val res = ArrayList<Int>()
            val q = LinkedList<TreeNode>()
            val level = ArrayList<TreeNode>() // Not needed, see M1_ below

            q.add(root)
            while(q.isNotEmpty()) {
                val size = q.size
                level.clear()
                for (i in 0 until size) {
                    val n = q.pop()
                    level.add(n)
                    if (n.left != null) q.add(n.left!!)
                    if (n.right != null) q.add(n.right!!)
                }
                if (level.isNotEmpty()) {
                    res.add(level.last().`val`)
                }
            }
            return res
        }
    }

    // slightly improved
    private class M1_ : Leetcode_199 {
        override fun rightSideView(root: TreeNode?): List<Int> {

            if (root == null) return emptyList()

            val res = ArrayList<Int>()
            val q = LinkedList<TreeNode>()

            q.add(root)
            while(q.isNotEmpty()) {
                val size = q.size
                var rightMost: TreeNode? = null
                for (i in 0 until size) {
                    val n = q.pop()
                    rightMost = n
                    if (n.left != null) q.add(n.left!!)
                    if (n.right != null) q.add(n.right!!)
                }
                if (rightMost != null) {
                    res.add(rightMost.`val`)
                }
            }
            return res
        }
    }
}

// 1448. Count Good Nodes in Binary Tree
// https://leetcode.com/problems/count-good-nodes-in-binary-tree/


//
//
private interface Leetcode_1448 {
    fun goodNodes(root: TreeNode?): Int


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

    // Runtime: 715 ms, faster than 21.05% of Kotlin online submissions for Count Good Nodes in Binary Tree.
    // Memory Usage: 73.2 MB, less than 50.88% of Kotlin online submissions for Count Good Nodes in Binary Tree.
    // https://leetcode.com/submissions/detail/773335970/
    private class M1 : Leetcode_1448 {
        var counter = 0
        override fun goodNodes(root: TreeNode?): Int {
            dfs(root, Int.MIN_VALUE)
            return counter
        }

        fun dfs(node: TreeNode?, max: Int) {
            if (node == null) return
            var max = max
            if (node.`val` >= max) {
                max = node.`val`
                println("added $max")
                counter++
            }
            dfs(node.left, max)
            dfs(node.right, max)
        }
    }
}

// 98. Validate Binary Search Tree
// https://leetcode.com/problems/validate-binary-search-tree/
private interface Leetcode_98 {
    fun isValidBST(root: TreeNode?): Boolean

    private class S2 : Leetcode_98 {
        var cur = Int.MIN_VALUE
        override fun isValidBST(root: TreeNode?): Boolean {
            return bfs(root)
        }
        fun bfs(node: TreeNode?) : Boolean {
            if (node == null) return true
            if (!bfs(node.left)) return false

            if (node.`val` <= cur) return false
            cur = node.`val`

            if (!bfs(node.right)) return false
            return true
        }
    }

    private class S1 : Leetcode_98 {
        var cur: Int? = null
        override fun isValidBST(root: TreeNode?): Boolean {
            return bfs(root)
        }
        fun bfs(node: TreeNode?) : Boolean {
            if (node == null) return true
            if (!bfs(node.left)) return false

            if (cur != null) {
                if (node.`val` <= cur!!) return false
            }

            cur = node.`val`

            if (!bfs(node.right)) return false
            return true
        }
    }
}

// 230. Kth Smallest Element in a BST
// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
private interface Leetcode_230 {
    fun kthSmallest(root: TreeNode?, k: Int): Int

    // Runtime: 405 ms, faster than 29.55% of Kotlin online submissions for Kth Smallest Element in a BST.
    // Memory Usage: 44.7 MB, less than 9.55% of Kotlin online submissions for Kth Smallest Element in a BST.
    // https://leetcode.com/submissions/detail/773374349/
    private class S : Leetcode_230 {
        var counter = 0
        var value: Int = 0
        override fun kthSmallest(root: TreeNode?, k: Int): Int {
            dfs(root, k)
            return value
        }

        fun dfs(root: TreeNode?, k: Int) {
            if (root == null) return
            dfs(root.left, k)

            counter++
            if (counter > k) return

            value = root.`val`
            dfs(root.right, k)
        }
    }
    private class Solution : Leetcode_230 {
        override fun kthSmallest(root: TreeNode?, k: Int): Int {
            var root = root
            var k = k
            val stack: Deque<TreeNode> = ArrayDeque()
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root)
                    root = root.left
                }
                root = stack.pop()
                --k
                if (k == 0) {
                    break
                }
                root = root.right
            }
            return root!!.`val`
        }
    }

}

// 105. Construct Binary Tree from Preorder and Inorder Traversal
private interface Leetcode_105 {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode?

    private class M : Leetcode_105 {
        override fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
            TODO("Not yet implemented")
        }
    }
}


// 211. Design Add and Search Words Data Structure
// https://leetcode.com/problems/design-add-and-search-words-data-structure/
private interface Leetcode_211 {
    fun addWord(word: String)
    fun search(word: String): Boolean

    companion object : Testable {
        override fun test() {
            listOf(
                M1::class.java,
            ).runTimedTests {
                getDeclaredConstructor().newInstance().apply {
                    addWord("bad")
                    addWord("dad")
                    addWord("mad")
                    search("pad").assertEqualTo(false)
                    search("bad").assertEqualTo(true)
                    search(".ad").assertEqualTo(true)
                    search("b..").assertEqualTo(true)
                }
            }
        }
    }

    private class M1 : Leetcode_211 {


        override fun addWord(word: String) {
            TODO("Not yet implemented")
        }

        override fun search(word: String): Boolean {
            TODO("Not yet implemented")
        }
    }
}


// 621. Task Scheduler
// https://leetcode.com/problems/task-scheduler/
private interface Leetcode_621 {
    fun leastInterval(tasks: CharArray, n: Int): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(charArrayOf('A','A','A','B','B','B'), 2, 8),
            )
            listOf(
                // M1()::leastInterval,
                S1()::leastInterval,
            ).runTimedTests(tests) { a, b, c ->
                invoke(a, b).assertEqualTo(c)
            }
        }
    }

    private class M1 : Leetcode_621 {

        data class C(
            val c: Char,
            var count: Int,
            var nextI: Int = 0
        )

        fun useNextC(q: PriorityQueue<C>, i: Int, n: Int) : C? {
            val l = ArrayList<C>()
            var res: C? = null
            while(res == null && q.isNotEmpty()) {
                val c = q.poll()
                if (c.count == 0) continue
                if (c.nextI <= i) {
                    res = c
                    c.nextI = i + n + 1
                    c.count = (c.count - 1)
                }
                if (c.count != 0) {
                    l += c
                }
            }

            l.forEach { q.add(it) }
            return res
        }

        override fun leastInterval(tasks: CharArray, n: Int): Int {
            val map = HashMap<Char, Int>()
            val heat = PriorityQueue<C>(){ a, b ->
                if (a.count == b.count) {
                    a.nextI - b.nextI
                } else {
                    b.count - a.count
                }
            }

            var total = 0
            for (c in tasks) {
                total++
                map[c] = (map[c] ?: 0) + 1
            }

            map.forEach { t, u ->
                heat.offer(C(t, u))
            }
            var i = 0
            while (heat.isNotEmpty()) {
                val n = useNextC(heat, i, n)
                print(n?.c ?: "-")
                i++
            }


            return i
        }
    }

    private class S1 : Leetcode_621 {
        override fun leastInterval(tasks: CharArray, n: Int): Int {
            val map = HashMap<Char, Int>()
            val heat = PriorityQueue<Int>(){ a, b -> b - a }
            val q = LinkedList<Pair<Int, Int>>()

            for (c in tasks) {
                map[c] = (map[c] ?: 0) + 1
            }
            map.forEach { t, u -> heat.offer(u) }
            // a,b,0,a
            var i = 0
            while (heat.isNotEmpty() || q.isNotEmpty()) {
                i++
                if (q.isNotEmpty() && q.peek().second == i) {
                    heat.add(q.pop().first)
                }

                if (heat.isNotEmpty()) {
                    val t = heat.poll() - 1
                    if (t != 0) {
                        val pair = t to i + n + 1
                        q.add(pair)
                    }
                }


            }
            return i
        }
    }
}

val _20220814 = listOf<Testable>(
    Leetcode_621
)