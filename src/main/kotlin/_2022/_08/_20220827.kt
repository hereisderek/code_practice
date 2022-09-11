@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.assertEqualTo
import utils.deepClone
import utils.runTimedTests
import utils.tupleOf
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


// 684. Redundant Connection
// https://leetcode.com/problems/redundant-connection/
private interface LC_684 {
    fun findRedundantConnection(edges: Array<IntArray>): IntArray

    // Runtime: 196 ms, faster than 100.00% of Kotlin online submissions for Redundant Connection.
    // Memory Usage: 40.5 MB, less than 46.51% of Kotlin online submissions for Redundant Connection.
    // https://leetcode.com/submissions/detail/784365115/
    private class M1 : LC_684 {

        private class UnionFind(count: Int) {
            private val parent = IntArray(count) { it }

            var count: Int = count; private set

            fun union(p: Int, q: Int) {
                val rootP = find(p)
                val rootQ = find(q)
                if (rootP == rootQ) return

                parent[rootQ] = rootP
                count--
            }

            fun connected(p: Int, q: Int): Boolean {
                return find(p) == find(q)
            }

            private fun find(x: Int): Int {
                if (parent[x] != x) {
                    parent[x] = find(parent[x])
                }
                return parent[x]
            }
        }

        override fun findRedundantConnection(edges: Array<IntArray>): IntArray {
            val uf = UnionFind(edges.size+1) // as the input starts from 1
            for (i in edges) {
                if (uf.connected(i[0], i[1])) return i
                uf.union(i[0], i[1])
            }
            throw Exception("no answer")
        }

    }
}

// https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
// https://www.lintcode.com/vip/guide/?fromUrl=https%253A%252F%252Fwww.lintcode.com%252Fproblem%252F431%252F


// https://leetcode.com/problems/graph-valid-tree/


// 743. Network Delay Time
// https://leetcode.com/problems/network-delay-time/
private interface Leetcode_743_2 {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(
                    arrayOf(intArrayOf(1,2,1),intArrayOf(2,1,3),), 2, 2
                ) to 3,
                tupleOf(
                    arrayOf(
                        intArrayOf(2,1,1),
                        intArrayOf(2,3,1),
                        intArrayOf(3,4,1),
                    ), 4, 2
                ) to 2,
                tupleOf(
                    arrayOf(
                        intArrayOf(1,2,1),
                        intArrayOf(2,3,2),
                        intArrayOf(1,3,2),
                    ), 3, 1
                ) to 2,
                tupleOf(
                    arrayOf(
                        intArrayOf(1,2,1),
                    ), 2, 1
                ) to 1,
                tupleOf(
                    arrayOf(intArrayOf(1,2,1),), 2, 2
                ) to -1,

            )
            listOf(
                M1()::networkDelayTime,
            ).runTimedTests(tests) { a, b ->
                val array = a.first.deepClone()
                val res = invoke(array, a.second, a.third)
                res.assertEqualTo(b)
            }
        }
    }


    private class M1 : Leetcode_743_2 {
        override fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
            val map = HashMap<Int, ArrayList<IntArray>>()
            for (t in times) {
                if (map[t[0]] == null) map[t[0]] = ArrayList()
                map[t[0]]!!.add(t)
            }

            val queue = PriorityQueue<IntArray> { a, b -> a[2] - b[2] }
            if (map[k].isNullOrEmpty()) {
                return -1
            }
            queue.addAll(map[k]!!)

            var sum = 0
            val visited = HashSet<Int>()
            visited.add(k)

            while (queue.isNotEmpty()) {
                val min = queue.peek()[2]
                sum += min
                val cur = ArrayList<IntArray>()

                while (queue.isNotEmpty()) {
                    val node = queue.poll()
                    node[2] -= min
                    if (node[2] == 0) {
                        val nextNodes = map[node[1]]
                        if (!nextNodes.isNullOrEmpty()) {
                            for (_n in nextNodes) {
                                if (!visited.contains(_n[1])) {
                                    cur.add(_n)
                                }
                                visited.add(_n[1])
                            }
                        }
                    } else {
                        cur.add(node)
                    }
                    visited.add(node[1])
                }
                cur.forEach { queue.add(it) }
            }

            return if (visited.size == n) sum else -1
        }
    }
}


val _20220827 = listOf<Testable>(
    Leetcode_743_2
)