@file:Suppress("DuplicatedCode", "ClassName")

package _2022._06

import utils.*
import java.util.*
import kotlin.Comparator

fun main() {
    // Leetcode_23.test()
    Leetcode_160.test()
}




private interface Leetcode_23 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode?

    companion object {
        fun test() {

            listOf(
                My1()::mergeKLists,
                S01()::mergeKLists,
            ).runTimedTests {
                val t1 = arrayOf<ListNode?>(
                    linkedNodesOf(1,4,5),
                    linkedNodesOf(1,3,4),
                    linkedNodesOf(2,6),
                )
                val t2 = emptyArray<ListNode?>()
                val t3 = arrayOf<ListNode?>(null)

                invoke(t1).assertEqualTo(linkedNodesOf(1,1,2,3,4,4,5,6))
                invoke(t2) assertEqualTo null
                invoke(t3) assertEqualTo null
            }
        }
    }
    // https://leetcode.com/submissions/detail/717334868/
    // Runtime: 517 ms, faster than 32.33% of Kotlin online submissions for Merge k Sorted Lists.
    // Memory Usage: 38.9 MB, less than 85.80% of Kotlin online submissions for Merge k Sorted Lists.
    private class My1 : Leetcode_23 {

        private fun attach(previous: ListNode, list: Array<ListNode?>) {
            if (list.all { it == null }) return

            var smallestIndex = 0

            for (i in list.indices) {
                val smallest = list[smallestIndex]
                val current = list[i]
                if (smallest == null || (current != null && current.`val` < smallest.`val`)) {
                    smallestIndex = i
                }
            }
            val smallest = list[smallestIndex]
            list[smallestIndex] = smallest?.next
            previous.next = smallest
            if (smallest == null) return

            attach(smallest, list)
        }

        override fun mergeKLists(lists: Array<ListNode?>): ListNode? {
            val dummy = ListNode(-1)
            attach(dummy, lists)
            return dummy.next
        }
    }

    // https://labuladong.github.io/algo/2/21/62/
    private class S01 : Leetcode_23 {
        override fun mergeKLists(lists: Array<ListNode?>): ListNode? {
            val queue = PriorityQueue<ListNode>(
                Math.max(1, lists.size)
            ){ a, b -> a.`val`.compareTo(b.`val`) }

            lists.forEach {
                if (it != null) { queue.add(it) }
            }

            var fakeHead = ListNode(0)
            var tail = fakeHead
            var node = queue.poll()
            while (node != null) {
                val newListHead = node.next
                tail.next = node
                tail = node

                if (newListHead != null) {
                    queue.add(newListHead)
                }

                node = queue.poll()
            }

            return fakeHead.next
        }
    }

}

private interface Leetcode_160 {
    fun getIntersectionNode(headA:ListNode?, headB:ListNode?):ListNode?

    companion object {
        fun test() {
            val t1 = intersectedNodes(
                linkedNodesOf(4,1,8,4,5),
                linkedNodesOf(5,6,1,8,4,5),
                2, 3, 8
            )
            val t2 = intersectedNodes(
                linkedNodesOf(1,9,1,2,4),
                linkedNodesOf(3,2,4),
                3, 1, 2
            )
            val t3 = intersectedNodes(
                linkedNodesOf(2,6,4),
                linkedNodesOf(1,5),
                3, 2, 0
            )

            listOf(
                My1()::getIntersectionNode
            ).runTimedTests {
                t1.run {
                    invoke(first, second).apply {
                        assertEqualTo(first[2])
                        assertEqualTo(second[3])
                        this!!.`val` assertEqualTo 8
                    }
                }

                t2.run {
                    invoke(first, second).apply {
                        assertEqualTo(first[3])
                        assertEqualTo(second[1])
                        this!!.`val` assertEqualTo 2
                    }
                }

                t3.run {
                    invoke(first, second) assertEqualTo(null)
                }

            }
        }
    }

    // https://leetcode.com/submissions/detail/721942632/
    // Runtime: 351 ms, faster than 61.24% of Kotlin online submissions for Intersection of Two Linked Lists.
    // Memory Usage: 57.3 MB, less than 42.51% of Kotlin online submissions for Intersection of Two Linked Lists.
    private class My1 : Leetcode_160 {
        override fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
            var p1 = headA
            var p2 = headB

            while (p1 !== p2) { // ! much use `!==` instead of `==` for checking the reference
                p1 = if (p1 != null) p1.next else headB
                p2 = if (p2 != null) p2.next else headA
            }

            return p1
        }
    }

}