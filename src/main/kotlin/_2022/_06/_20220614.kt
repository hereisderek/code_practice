@file:Suppress("DuplicatedCode", "ClassName")

package _2022._06

import utils.*

fun main() {
    // Leetcode_876.test()
    // Leetcode_92.test()
    Leetcode_206.test()
}

// 876. Middle of the Linked List
private interface Leetcode_876 {
    fun middleNode(head: ListNode?): ListNode?

    companion object {
        fun test() {
            val t1 = linkedNodesOf(1,2,3,4,5)
            val t2 = linkedNodesOf(1,2,3,4,5,6)
            val t3 = linkedNodesOf(1,2)
            val t4 = linkedNodesOf(1)
            listOf(
                S1()::middleNode
            ).runTimedTests {
                invoke(t1)!!.`val` assertEqualTo 3
                invoke(t2)!!.`val` assertEqualTo 4
                invoke(t3)!!.`val` assertEqualTo 2
                invoke(t4)!!.`val` assertEqualTo 1
            }
        }
    }

    // https://leetcode.com/submissions/detail/721947623/
    // Runtime: 131 ms, faster than 100.00% of Kotlin online submissions for Middle of the Linked List.
    // Memory Usage: 33.5 MB, less than 95.85% of Kotlin online submissions for Middle of the Linked List.
    private class S1 : Leetcode_876 {
        override fun middleNode(head: ListNode?): ListNode? {
            if (head == null || !head.hasNext) return head

            val dummy = ListNode(-1, head)
            var slow : ListNode? = dummy
            var fast : ListNode? = dummy

            while (fast != null) {
                slow = slow?.next
                fast = fast?.next?.next
            }

            return slow
        }
    }
}

//Leetcode_92
private interface Leetcode_92 {

    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode?

    companion object {
        fun test() {

            listOf(
                My1()::reverseBetween,
                My2()::reverseBetween,
            ).runTimedTests {
                invoke(
                    linkedNodesOf(*(1..5).toList().toTypedArray()),
                    2, 4
                ) assertEqualTo linkedNodesOf(1,4,3,2,5)
                invoke(linkedNodesOf(5), 1, 1) assertEqualTo linkedNodesOf(5)
                invoke(
                    linkedNodesOf(*(1..5).toList().toTypedArray()),
                    1, 1
                ) assertEqualTo linkedNodesOf(*(1..5).toList().toTypedArray())
                invoke(
                    linkedNodesOf(*(1..5).toList().toTypedArray()),
                    1, 5
                ) assertEqualTo linkedNodesOf(*(5 downTo 1).toList().toTypedArray())
            }
        }
    }

    private class My1 : Leetcode_92 {

        override fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
            if (head == null) return null
            val dummyHead : ListNode = ListNode(-1, head)
            var node : ListNode? = dummyHead
            var revertedHead : ListNode? = null
            var leftNode : ListNode? = null
            var rightNode : ListNode? = null

            for (i in 0 .. right) {
                if (node == null) return head
                val next = node.next

                if (i in left..right) {
                    if (revertedHead == null) {
                        revertedHead = node
                        rightNode = node
                    } else {
                        val temp = revertedHead
                        // revertedHead.next = node.next
                        revertedHead = node
                        node.next = temp
                    }
                } else {
                    leftNode = node
                }
                node = next
            }
            rightNode?.next = node

            leftNode?.next = revertedHead
            return dummyHead.next
        }
    }

    private class My2 : Leetcode_92 {
        override fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
            if (head == null) return null
            val dummy = ListNode(-1, head)

            var pre : ListNode = dummy
            var cur : ListNode? = null
            for (i in 0 until left - 1) {
                pre = pre.next ?: return null
            }
            cur = pre.next

            for (i in 0 until right - left) {
                val next = cur?.next ?: return null
                cur.next = next.next
                // NOT THESE!
                // next.next = cur
                // cur = next
                next.next = pre.next
                pre.next = next
            }

            return dummy.next
        }
    }

    // Sample
    // https://leetcode.cn/problems/reverse-linked-list-ii/solution/fan-zhuan-lian-biao-ii-by-leetcode-solut-teyq/

}

//206. Reverse Linked List
private interface Leetcode_206 {
    fun reverseList(head: ListNode?): ListNode?
    companion object {
        fun test() {
            listOf(
                M1()::reverseList,
                S1()::reverseList,
            ).runTimedTests {
                invoke(linkedNodesOf(1,2,3)) assertEqualTo linkedNodesOf(3,2,1)
                invoke(null) assertEqualTo null
                invoke(linkedNodesOf(1)) assertEqualTo linkedNodesOf(1)
            }
        }
    }

    // https://leetcode.com/submissions/detail/722044543/
    // Runtime: 314 ms, faster than 11.21% of Kotlin online submissions for Reverse Linked List.
    // Memory Usage: 37.9 MB, less than 5.30% of Kotlin online submissions for Reverse Linked List.
    private class M1 : Leetcode_206 {
        override fun reverseList(head: ListNode?): ListNode? {
            if (head == null) return null
            val dummy = ListNode(-1, head)
            var node : ListNode? = head
            while (node != null) {
                val next = node.next
                node.next = dummy.next
                dummy.next = node
                node = next
            }
            head.next = null

            return dummy.next
        }
    }

    private class S1 : Leetcode_206 {
        override fun reverseList(head: ListNode?): ListNode? {
            if (head?.next == null) return head
            val previous = reverseList(head.next)
            head.next?.next = head
            head.next = null
            return previous
        }
    }
}