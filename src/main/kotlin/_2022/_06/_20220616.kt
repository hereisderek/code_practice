@file:Suppress("DuplicatedCode", "ClassName")

package _2022._06

import Testable
import utils.*
import java.util.*
import java.util.Arrays.sort

fun main() {
    // Leetcode_234.test()
    // Leetcode_26.test()
    // Leetcode_27.test()
    Leetcode_83.test()
}

private interface Leetcode_234 {
    fun isPalindrome(head: ListNode?): Boolean

    companion object {
        fun test() {
            val tests = listOf(
                // linkedNodesOf(1,2,3) to false,
                // linkedNodesOf(1,2,3,4) to false,
                linkedNodesOf(1,2,2,1) to true,
                linkedNodesOf(1,2,1) to true,
                linkedNodesOf(1,2) to false,
                linkedNodesOf(1,0,0) to false,
            )
            listOf(
                M3()::isPalindrome,
            ).runTimedTests {
                tests.forEach {
                    invoke(it.first).assertEqualTo(it.second) { exp, act ->
                        "assertion failed for ${it.first.toUnsafeString()}, expected:$exp, actual:$act"
                    }
                }
            }
        }
    }

    // doesn't work for cases like [1,2,1]
    private class M1 : Leetcode_234 {
        override fun isPalindrome(head: ListNode?): Boolean {
            if (head?.next == null) return true
            val stack = Stack<ListNode>()
            var pointer : ListNode? = head
            while (pointer != null) {
                val checker = stack.run {
                    if (isEmpty()) null else peek()
                }
                if (checker?.`val` == pointer.`val`) {
                    stack.pop()
                } else {
                    stack.add(pointer)
                }
                pointer = pointer.next
            }
            return stack.isEmpty()
        }
    }


    private class M3 : Leetcode_234 {
        override fun isPalindrome(head: ListNode?): Boolean {
            if (head?.next == null) return true
            var slow : ListNode? = head
            var fast : ListNode? = head
            val stack = Stack<ListNode>()

            stack.add(slow)
            while(fast?.next != null) {
                fast = fast?.next?.next
                slow = slow?.next
                stack.add(slow)
            }

            if (fast?.next != null) {
                slow = slow?.next
            }

            // mid
            // for cases like [1,2]
            // if (slow !== stack.peek() && slow?.next != null) {
            //     stack.pop()
            //     slow = slow?.next
            // }


            while (slow != null) {
                if (stack.isEmpty()) return false
                val item = stack.pop()
                if (item.`val` != slow?.`val`) {
                    return false
                }
            }

            return stack.isEmpty()
        }
    }
}


// https://leetcode.com/problems/remove-duplicates-from-sorted-array/
private interface Leetcode_26 {
    fun removeDuplicates(nums: IntArray): Int

    companion object : Testable {
        /**
         * Note: test cases can be wrong, the output array should ignore order, see test() for #Leetcode_27
         */
        override fun test() {
            val t1 = mutableTupleOf(
                intArrayOf(1,1,2),
                2,
                intArrayOf(1,2)
            )
            val t2 = mutableTupleOf(
                intArrayOf(0,0,1,1,1,2,2,3,3,4),
                5,
                intArrayOf(0,1,2,3,4)
            )
            val test_cases = listOf(t1, t2)
            listOf(
                M1()::removeDuplicates,
            ).runTimedTests {
                test_cases.forEach {
                    it.apply {
                        val input = first.clone()
                        invoke(input) assertEqualTo second
                        for (i in 0 until second) {
                            assert(input[i] == third[i]) {
                                "assertion failed, expected:${third.joinToString()}, actual:${input.joinToString(limit = second)}"
                            }
                        }
                    }
                }
            }
        }
    }
    // https://leetcode.com/submissions/detail/723620489/
    // Runtime: 369 ms, faster than 71.50% of Kotlin online submissions for Remove Duplicates from Sorted Array.
    // Memory Usage: 49.2 MB, less than 12.75% of Kotlin online submissions for Remove Duplicates from Sorted Array.

    private class M1 : Leetcode_26 {
        override fun removeDuplicates(nums: IntArray): Int {
            var index = -1
            for (i in nums.indices) {
                val value = nums[i]
                if (index == -1 || value > nums[index]) {
                    index ++
                    nums[index] = value
                }
            }
            return index + 1
        }
    }
}

// 27. Remove Element
// https://leetcode.com/problems/remove-element/

private interface Leetcode_27 {
    fun removeElement(nums: IntArray, `val`: Int): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                (intArrayOf(3,2,2,3) to 3) to (intArrayOf(2,2) to 2),
                (intArrayOf(0,1,2,2,3,0,4,2) to 2) to (intArrayOf(0,1,4,0,3) to 5),
            )

            listOf(
                M1()::removeElement,
            ).runTimedTests {
                tests.forEach {
                    it.apply {
                        val inputArray = first.first.clone()
                        val k = second.second
                        invoke(inputArray, first.second) assertEqualTo k
                        val expectedArray = second.first.clone()
                        Arrays.sort(inputArray, 0, k)
                        Arrays.sort(expectedArray, 0, k)
                        for (i in 0 until k) {
                            assert(inputArray[i] == expectedArray[i]) {
                                "assertion failed, expected:${expectedArray.joinToString()}, actual:${inputArray.joinToString(limit = second.second)}"
                            }
                        }
                    }
                }
            }
        }
    }

    // https://leetcode.com/submissions/detail/723633679/
    // Runtime: 291 ms, faster than 22.77% of Kotlin online submissions for Remove Element.
    // Memory Usage: 35.7 MB, less than 20.99% of Kotlin online submissions for Remove Element.
    private class M1 : Leetcode_27 {
        override fun removeElement(nums: IntArray, `val`: Int): Int {
            var index = -1
            for (i in nums.indices) {
                val value = nums[i]

                if (value != `val`) {
                    index ++
                    nums[index] = value
                }

            }
            return index + 1
        }
    }
}



private interface Leetcode_83 {
    fun deleteDuplicates(head: ListNode?): ListNode?
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                linkedNodesOf(1,1,2) to linkedNodesOf(1,2),
                linkedNodesOf(1,1,2,3,3) to linkedNodesOf(1,2,3),
            )
            listOf(
                M1()::deleteDuplicates,
            ).runTimedTests {
                tests.forEach {
                    val input = it.first.clone()
                    invoke(input).assertEqualTo(it.second) { e, a ->
                        "assertion failed for ${it.first.toUnsafeString()}, expected:${e?.toUnsafeString()} actual:${a?.toUnsafeString()}"
                    }
                }
            }
        }
    }

    private class M1 : Leetcode_83 {
        override fun deleteDuplicates(head: ListNode?): ListNode? {
            if (head?.next == null) return head
            var pre : ListNode? = head
            var cur : ListNode? = head
            while (cur != null) {
                val next = cur.next
                // it actually checks for next
                if (next?.`val` == pre?.`val`) {
                    cur.next = next?.next
                } else {
                    pre = cur
                }
                cur = next
            }
            return head
        }
    }
}
