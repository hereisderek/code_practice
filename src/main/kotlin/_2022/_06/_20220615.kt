@file:Suppress("DuplicatedCode", "ClassName")

package _2022._06

import utils.*

fun main() {
    Leetcode_25.test()
}

//25. Reverse Nodes in k-Group
private interface Leetcode_25 {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode?
    companion object {
        fun test() {
            listOf(
                M1()::reverseKGroup
            ).runTimedTests {

                val t1 = ImmutableTuple(
                    linkedNodesOf(1,2,3,4,5),
                    2,
                    linkedNodesOf(2,1,4,3,5)
                )
                val t2 = ImmutableTuple(
                    linkedNodesOf(1,2,3,4,5),
                    3,
                    linkedNodesOf(3,2,1,4,5)
                )

                fun assert(case: Tuple<ListNode, Int, ListNode>) = case.apply {
                    invoke(first, second).assertEqualTo(third)
                }

                assert(t1)
                assert(t2)
            }
        }
    }

    // https://leetcode.com/submissions/detail/723455025/
    // Runtime: 268 ms, faster than 63.53% of Kotlin online submissions for Reverse Nodes in k-Group.
    // Memory Usage: 35.7 MB, less than 100.00% of Kotlin online submissions for Reverse Nodes in k-Group.
    private class M1 : Leetcode_25 {
        /**
         * revert the next k nodes after the given head, and return the last node after reversion
         * @param head
         */
        private fun revert(head: ListNode?, k: Int): ListNode? {
            if (head == null) return null

            // check if it has at least k children
            var temp : ListNode? = head
            for (i in 0 until k) {
                temp = temp?.next ?: return null
            }

            // first element
            val start = head.next ?: return null

            // element that's in front after each step
            var front: ListNode = start


            for (i in 0 until k - 1) {
                val next = start.next
                start.next = next!!.next
                next.next = front
                front = next
                head.next = front
            }

            return start
        }
        override fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
            if (head?.next == null) return head
            val dummy = ListNode(-1, head)
            dummy.next = head
            var left: ListNode? = dummy

            while(left != null) {
                left = revert(left, k)
            }
            return dummy.next
        }
    }
}
