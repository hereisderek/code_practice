@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.TreeNode
import java.util.*


// 102. Binary Tree Level Order Traversal
// https://leetcode.com/problems/binary-tree-level-order-traversal/

private interface Leetcode_102 {
    fun levelOrder(root: TreeNode?): List<List<Int>>
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

    // Runtime: 342 ms, faster than 37.53% of Kotlin online submissions for Binary Tree Level Order Traversal.
    // Memory Usage: 38.2 MB, less than 8.11% of Kotlin online submissions for Binary Tree Level Order Traversal.
    // https://leetcode.com/submissions/detail/773022919/
    private class M1 : Leetcode_102 {
        val map = HashMap<Int, ArrayList<Int>>()

        override fun levelOrder(root: TreeNode?): List<List<Int>> {
            if (root == null) return emptyList()
            dfs(root, 0)
            val res = ArrayList<ArrayList<Int>>()
            for (i in 0 until map.size) {
                res.add(map[i]!!)
            }
            return res
        }
        fun dfs(parent: TreeNode?, level: Int){
            if (parent == null) return
            val list = map.getOrPut(level){
                ArrayList<Int>()
            }
            list.add(parent.`val`)
            dfs(parent.left, level+1)
            dfs(parent.right, level+1)
        }
    }

    // BFS (Not really), i don't think the q here is really needed

    private class M2 : Leetcode_102 {
        override fun levelOrder(root: TreeNode?): List<List<Int>> {
            if (root == null) return emptyList()
            val q = LinkedList<TreeNode>()
            val res = ArrayList<List<Int>>()
            q.add(root)
            while(q.isNotEmpty()) {
                val list = ArrayList<TreeNode>()
                val _res = ArrayList<Int>()
                res.add(_res)
                while(q.isNotEmpty()) {
                    list.add(q.pop())
                }
                for (i in list) {
                    _res.add(i.`val`)
                    if (i.left != null) q.add(i.left!!)
                    if (i.right != null) q.add(i.right!!)
                }
            }
            return res
        }
    }

    private class S1 : Leetcode_102 {
        override fun levelOrder(root: TreeNode?): List<List<Int>> {
            if (root == null) return emptyList()
            val q = LinkedList<TreeNode>()
            val res = ArrayList<List<Int>>()
            q.add(root)
            while(q.isNotEmpty()) {
                val size = q.size
                val level = ArrayList<Int>()
                for (i in 0 until size) {
                    val n = q.pop()
                    level.add(n.`val`)
                    if (n.left != null) q.add(n.left!!)
                    if (n.right != null) q.add(n.right!!)
                }
                res.add(level)
            }
            return res
        }
    }
}


val _20220813 = listOf<Testable>(
    Leetcode_102
)