@file:Suppress("DuplicatedCode", "ClassName")

package _2022._06

import utils.*
import java.util.LinkedList

fun main() {
    Leetcode_23.test()
    // Leetcode_160.test()
}




private interface Leetcode_23 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode?

    companion object {
        fun test() {

            listOf(
                My1()::mergeKLists
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
            println("t1:$t1, t2:$t2, t3:$t3")
        }
    }

    private class My1 : Leetcode_160 {
        override fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
            TODO("Not yet implemented")
        }
    }

}