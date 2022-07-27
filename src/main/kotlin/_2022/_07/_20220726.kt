@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.ListNode
import utils.assertEqualTo
import utils.linkedNodesOf
import utils.runTimedTests


// 143. Reorder List
// https://leetcode.com/problems/reorder-list/
private interface Leetcode_143 {
    fun reorderList(head: ListNode?)
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                linkedNodesOf(1,2,3,4) to linkedNodesOf(1,4,2,3),
                linkedNodesOf(1,2,3,4,5) to linkedNodesOf(1,5,2,4,3),
            )
            listOf(
                M1()::reorderList,
            ).runTimedTests(tests) { a, b ->
                val input = a.clone()
                invoke(input)
                input.assertEqualTo(b, toStr = {this.toUnsafeString()})
            }
        }
    }

    private class M1 : Leetcode_143 {
        override fun reorderList(head: ListNode?) {
            if (head == null) return


            var fast: ListNode? = head
            var slow: ListNode? = head

            while (fast != null) {
                fast = fast?.next?.next
                slow = slow?.next
            }

            // cut off
            var h2 = slow!!.next
            slow.next = null

            val d = ListNode(-1)
            var p: ListNode? = h2
            // reverse the rest
            while (p != null) {
                val t = d.next
                d.next = p
                p = p.next
                p?.next = t
            }

            var h1 = head


            while (h1 != null || h2 != null) {
                val n1 = h1?.next
                val n2 = h2?.next
                h1?.next = h2
                h2?.next = n1
                n1?.next = n2

                h1 = n1
                h2 = n2
            }

        }
    }
}

fun main() {
    Leetcode_143.test()
}