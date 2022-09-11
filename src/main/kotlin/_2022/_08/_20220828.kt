@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.TreeNode
import java.util.*




// 2385. Amount of Time for Binary Tree to Be Infected
// https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/
private interface LC_2385 {
    fun amountOfTime(root: TreeNode?, start: Int): Int

    private class M1 : LC_2385 {
        override fun amountOfTime(root: TreeNode?, start: Int): Int {
            if (root == null) return 0
            val map = HashMap<Int, IntArray>()

            // populate nodes into a map using dfs
            fun dfs(node: TreeNode?, parent: TreeNode?) {
                if (node == null) return
                map[node.`val`] = intArrayOf(
                    parent?.`val` ?: 0,
                    node.left?.`val` ?: 0,
                    node.right?.`val` ?: 0
                )

                if (node.left != null) { dfs(node.left!!, node) }
                if (node.right != null) { dfs(node.right!!, node) }
            }

            dfs(root, null)

            // bfs
            val visit = HashSet<Int>()
            val q = LinkedList<Int>()
            var counter = 0
            q.add(start)
            visit.add(0)
            visit.add(start)
            while(q.isNotEmpty()) {
                counter++
                val size = q.size
                for (i in 0 until size) {
                    val node = q.pop()
                    val array = map[node]
                    if (array == null || array.isEmpty()) continue
                    visit.add(node)

                    for(j in array) {
                        if (j != 0 && !visit.contains(j)) q.add(j)
                    }
                }
            }
            return counter-1
        }
    }

    // leetcode.cn
    // https://leetcode.cn/problems/amount-of-time-for-binary-tree-to-be-infected/solution/java-dfs-by-backtraxe-5ov9/
    private class S1 : LC_2385 {
        var ans = 0 // 最短用时
        var depth = -1 // 起始节点的高度
        override fun amountOfTime(root: TreeNode?, start: Int): Int {
            dfs(root, 0, start)
            return ans
        }

        fun dfs(root: TreeNode?, level: Int, start: Int): Int {
            if (root == null) return 0
            if (root.`val` === start) depth = level // 当前节点即起始节点
            val l = dfs(root.left, level + 1, start) // 左子树的树高
            val inLeft = depth != -1 // 起始节点是否在左子树上
            val r = dfs(root.right, level + 1, start) // 右子树的树高
            if (root.`val` === start) ans = Math.max(ans, Math.max(l, r)) // 情况1：感染以 start 为根结点的树所需时间
            ans = if (inLeft) Math.max(ans, depth - level + r) // 情况2：感染以 root 为根结点的树所需时间
            else Math.max(ans, depth - level + l)
            return Math.max(l, r) + 1 // 返回树高
        }
    }
}


// Runtime: 1350 ms, faster than 69.05% of Kotlin online submissions for Amount of Time for Binary Tree to Be Infected.
// Memory Usage: 156.1 MB, less than 61.90% of Kotlin online submissions for Amount of Time for Binary Tree to Be Infected.

val _20220828 = listOf<Testable>(

)