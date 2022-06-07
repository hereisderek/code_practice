@file:Suppress("DuplicatedCode", "ClassName")

package _2022._06

import utils.*

fun main() {
    // Leetcode_21.test()
    // Leetcode_141.test()
    Leetcode_142.test()
}

// https://leetcode.com/problems/merge-two-sorted-lists/
private interface Leetcode_21 {

    companion object {

        // execution for _2022._06.Leetcode_21.My1.mergeTwoLists finished, took 151088 Nanoseconds
        // execution for _2022._06.Leetcode_21.Sample1.mergeTwoLists finished, took 445839 Nanoseconds
        fun test() = listOf(
            My1()::mergeTwoLists,
            Sample1()::mergeTwoLists,
        ).runTimedTests {

            invoke(
                linkedNodesOf(1,2,4),
                linkedNodesOf(1,3,4)
            ).assertEqualTo(
                linkedNodesOf(1,1,2,3,4,4)
            )
            invoke(
                linkedNodesOf(1),
                linkedNodesOf(2)
            ).assertEqualTo(
                linkedNodesOf(1,2)
            )
        }
    }
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode?


    // Runtime: 246 ms, faster than 59.29% of Kotlin online submissions for Merge Two Sorted Lists.
    // Memory Usage: 36.3 MB, less than 23.59% of Kotlin online submissions for Merge Two Sorted Lists.
    // https://leetcode.com/submissions/detail/716383804/
    private class My1 : Leetcode_21 {
        override fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
            if (list1 == null) return list2
            if (list2 == null) return list1
            var p1: ListNode? = list1
            var p2: ListNode? = list2
            val dummy = ListNode(-1)
            var head: ListNode? = dummy

            while (true) {
                val v1 = p1?.`val`
                val v2 = p2?.`val`

                when {
                    v1 == null && v2 == null -> break
                    v1 == null -> {
                        head!!.next = p2
                        p2 = p2!!.next
                    }
                    v2 == null -> {
                        head!!.next = p1
                        p1 = p1!!.next
                    }
                    v1 < v2 -> {
                        head!!.next = p1
                        p1 = p1!!.next
                    }
                    else -> {
                        head!!.next = p2
                        p2 = p2!!.next
                    }

                }
                head = head!!.next
            }
            return dummy.next
        }
    }

    // Same as My1 but this runs on leetcode, because it doesn't use `break` in a while loop which the version of kotlin used
    // in leetcode does not support
    private class My1_ : Leetcode_21 {
        override fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
            if (list1 == null) return list2
            if (list2 == null) return list1
            var p1: ListNode? = list1
            var p2: ListNode? = list2
            val dummy = ListNode(-1)
            var head: ListNode? = dummy

            while (p1?.`val` != null || p2?.`val` != null) {
                val v1 = p1?.`val`
                val v2 = p2?.`val`

                when {
                    v1 == null -> {
                        head!!.next = p2
                        p2 = p2!!.next
                    }
                    v2 == null -> {
                        head!!.next = p1
                        p1 = p1!!.next
                    }
                    v1 < v2 -> {
                        head!!.next = p1
                        p1 = p1!!.next
                    }
                    else -> {
                        head!!.next = p2
                        p2 = p2!!.next
                    }

                }
                head = head!!.next
            }
            return dummy.next
        }
    }


    // sample 140 ms submission
    private class Sample1 : Leetcode_21 {
        override fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
            if (list1 == null) return list2
            if (list2 == null) return list1
            if (list1.`val` < list2.`val`) {
                list1.next = mergeTwoLists(list1.next, list2)
                return list1
            } else {
                list2.next = mergeTwoLists(list1, list2.next)
                return list2
            }
        }
    }
}

private interface Leetcode_141 {
    fun hasCycle(head: ListNode?): Boolean

    companion object {

        fun test() {
            val test1 = cycledNodesOf(intArrayOf(3,2,0,-4), 1)
            val test2 = cycledNodesOf(intArrayOf(1,2), 0)
            val test3 = cycledNodesOf(intArrayOf(1), -1)

            listOf(
                My1()::hasCycle,
                Sample1()::hasCycle,
            ).runTimedTests {
                assert(invoke(test1))
                assert(invoke(test2))
                assert(!invoke(test3))
            }
        }
    }

    // Runtime: 310 ms, faster than 55.06% of Kotlin online submissions for Linked List Cycle.
    // Memory Usage: 44.3 MB, less than 16.67% of Kotlin online submissions for Linked List Cycle.
    // https://leetcode.com/problems/linked-list-cycle/submissions/
    private class My1 : Leetcode_141 {
        override fun hasCycle(head: ListNode?): Boolean {
            var slow: ListNode? = head
            var fast: ListNode? = head

            while (slow?.next != null) {
                slow = slow.next
                fast = fast?.next?.next ?: return false
                if (slow == fast) return true
            }
            return false
        }
    }

    // sample 160 ms submission
    private class Sample1 : Leetcode_141 {
        override fun hasCycle(head: ListNode?): Boolean {
            var fast = head
            var slow = head
            while (fast != null) {
                fast = fast?.next?.next
                slow = slow?.next
                if (fast != null && fast === slow) return true
            }
            return false
        }
    }
}

private interface Leetcode_142 {
    fun detectCycle(head: ListNode?): ListNode?

    companion object {

        fun test() {
            val test1 = cycledNodesOf(intArrayOf(3,2,0,-4), 1)
            val test2 = cycledNodesOf(intArrayOf(1,2), 0)
            val test3 = cycledNodesOf(intArrayOf(1), -1)

            listOf(
                My1()::detectCycle,
            ).runTimedTests {
                invoke(test1).assertThat {
                    this == test1.get(1)
                    // this?.`val` == 2
                }
                invoke(test2).assertThat {
                    this?.`val` == 1
                }
                invoke(test3).assertThat {
                    this == null
                }

            }
        }
    }

    private class My1 : Leetcode_142 {
        override fun detectCycle(head: ListNode?): ListNode? {
            TODO("Not yet implemented")
        }
    }
}

