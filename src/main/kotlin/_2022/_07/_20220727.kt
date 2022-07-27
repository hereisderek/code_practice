@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.assertEqualTo
import utils.runTimedTests


// 138. Copy List with Random Pointer
// https://leetcode.com/problems/copy-list-with-random-pointer/
private data class Node(
    var `val`: Int,
    var next: Node? = null,
    var random: Node? = null
) {
    override fun toString(): String {
        return "$`val`(${random?.`val` ?: "-"})"
    }

    override fun hashCode(): Int {
        return (`val` shl 31 + (next?.`val` ?: 0)) shl 31 + (random?.`val` ?: 0)
    }
    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is Node) return false
        var h1 : Node? = this
        var h2 : Node? = other
        while (h1 != null || h2 != null) {
            if ((h1 == null) != (h1 == null)) return false
            if (h1 == null || h2 == null) return true

            // if (h1 != null && h2 == null) return false
            // if (h1 == null && h2 != null) return false

            if (h1.`val` != h2.`val`) return false
            if (h1.random?.`val` != h2.random?.`val`) return false

            h1 = h1.next
            h2 = h2.next
        }
        return true
    }

    companion object {
        /**
         * @param nodes: list of pairs,
         *  @first: Int value,
         *  @second: Int? index of linked random node
         */
        fun fromList(vararg nodes: Pair<Int, Int?>) : Node {
            val nodeList = nodes.map { Node(it.first) }
            for (i in nodeList.indices) {
                nodeList[i].apply {
                    next = nodeList.getOrNull(i + 1)
                    random = nodes.getOrNull(i)?.second?.let {
                        nodeList.getOrNull(it)
                    }

                }
            }
            return nodeList[0]
        }
    }
}

private interface Leetcode_138 {
    fun copyRandomList(node: Node?): Node?
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                Node.fromList(
                    7 to null, 13 to 0, 11 to 4, 10 to 2, 1 to 0
                ),
            )
            listOf(
                M1()::copyRandomList,
            ).runTimedTests() {
                tests.forEach {
                    val output = invoke(it)
                    assert(it !== output)
                    assert(it == output)
                }
            }
        }
    }

    // this one requires rewrite of hashCode method as the key for the hashmap,
    // therefore it feels like cheating
    // however, it does pass leetcode tests
    // Runtime: 364 ms, faster than 28.57% of Kotlin online submissions for Copy List with Random Pointer.
    // Memory Usage: 38.9 MB, less than 74.29% of Kotlin online submissions for Copy List with Random Pointer.
    // https://leetcode.com/submissions/detail/758144087/
    private class M1 : Leetcode_138 {
        override fun copyRandomList(node: Node?): Node? {
            val map = HashMap<Node, Node>()
            var h: Node? = node
            var p: Node? = null
            while (h != null) {
                val n = Node(h.`val`)
                map.put(h, n)
                p?.next = n
                p = n
                h = h.next
            }

            map.forEach { t, u ->
                val r = t.random
                if (r != null) {
                    u.random = map[r]
                }
            }

            return map[node]
        }
    }
}

fun main() {
    Leetcode_138.test()
}