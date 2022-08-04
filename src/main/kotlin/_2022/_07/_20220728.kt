@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.*
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet


// 2. Add Two Numbers
// https://leetcode.com/problems/add-two-numbers/
private interface Leetcode_2 {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode?
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(
                    linkedNodesOf(2,4,3),
                    linkedNodesOf(5,6,4),
                    linkedNodesOf(7,0,8)
                )
            )
            listOf(
                M1()::addTwoNumbers,
            ).runTimedTests(tests) { a, b, c ->
                invoke(a, b).assertEqualTo(c)
            }
        }
    }

    // Runtime: 602 ms, faster than 5.41% of Kotlin online submissions for Add Two Numbers.
    // Memory Usage: 52.5 MB, less than 17.71% of Kotlin online submissions for Add Two Numbers.
    // https://leetcode.com/submissions/detail/758652077/
    private class M1 : Leetcode_2 {
        override fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
            var a = l1
            var b = l2
            if (a == null || b == null) return a ?: b

            val dummy = ListNode(-1)
            var r: ListNode? = null

            var carry = 0

            while (a != null || b != null || carry != 0) {
                var c = (a?.`val` ?: 0) + (b?.`val` ?: 0) + carry
                carry = 0

                if (c >= 10) {
                    c -= 10
                    carry = 1
                }

                val n = ListNode(c)
                (r ?: dummy).next = n
                r = n
                a = a?.next
                b = b?.next
            }

            return dummy.next
        }
    }
}


// 287. Find the Duplicate Number
// https://leetcode.com/problems/find-the-duplicate-number/
private interface Leetcode_287 {
    fun findDuplicate(nums: IntArray): Int
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(1,3,4,2,2) to 2,
                intArrayOf(3,1,3,4,2) to 3,
                intArrayOf(2,2,2,2,2) to 2,

            )
            listOf(
                M3()::findDuplicate,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    // WRONG!
    // intArrayOf(2,2,2,2,2) to 2
    private class M1 : Leetcode_287 {
        override fun findDuplicate(nums: IntArray): Int {
            return nums.sum() - (((nums.size - 1) * nums.size) / 2)
        }
    }

    // not allowed as set is not constant space
    private class M2 : Leetcode_287 {
        override fun findDuplicate(nums: IntArray): Int {
            val set = HashSet<Int>()
            for (i in nums) {
                if (set.contains(i)) {
                    return i
                } else {
                    set.add(i)
                }
            }
            return -1
        }
    }

    private class M3 : Leetcode_287 {

        override fun findDuplicate(nums: IntArray): Int {
            var slow = 0
            var fast = 0

            while(true) {
                slow = nums[slow]
                fast = nums[nums[fast]]
                if (slow == fast) break
            }

            slow = 0
            while(true) {
                slow = nums[slow]
                fast = nums[fast]
                if (slow == fast) break
            }

            return slow
        }
    }
}


// 146. LRU Cache
// https://leetcode.com/problems/lru-cache/
// https://www.youtube.com/watch?v=7ABFKPK2hD4
private interface Leetcode_146 {
    fun get(key: Int): Int
    fun put(key: Int, value: Int)


    companion object : Testable {
        private fun run(
            lru: (capacity: Int) -> Leetcode_146,
            test: Tuple<List<String>, List<IntArray>, List<Int?>>
        ) : List<Int?> {
            val results = ArrayList<Int?>(test.first.size)
            var instance: Leetcode_146? = null
            for (i in test.first.indices) {
                val result = when(val instruction = test.first[i]) {
                    "LRUCache" -> {
                        instance = lru.invoke(test.second[i][0])
                        null
                    }
                    "put" -> {
                        checkNotNull(instance).put(test.second[i][0], test.second[i][1])
                        null
                    }
                    "get" -> {
                        checkNotNull(instance).get(test.second[i][0])

                    }
                    else -> throw IllegalArgumentException("unknown instruction:$instruction")
                }
                results.add(result)
            }
            return results
        }

        override fun test() {
            val tests = listOf(
                tupleOf(
                    listOf("LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"),
                    listOf(
                        intArrayOf(2), intArrayOf(1, 1), intArrayOf(2, 2), intArrayOf(1), intArrayOf(3, 3), intArrayOf(2), intArrayOf(4, 4), intArrayOf(1), intArrayOf(3), intArrayOf(4)
                    ),
                    listOf<Int?>(null, null, null, 1, null, -1, null, -1, 3, 4)
                )
            )
            
            listOf<(capacity: Int) -> Leetcode_146>(
                {M1(it)},
                // {M1(it)},
            ).runTimedTests {
                tests.forEach {
                    val actual = run(lru = {it -> M1(it)}, it)
                    actual.assertEqualTo(it.third)
                }
            }
        }
    }

    private class M1(val capacity: Int) : Leetcode_146 {
        // key: parent listNode for the given key
        private val map = HashMap<Int, ListNode>()
        private val head = ListNode(-1)
        private var tail: ListNode? = null

        private fun removeNode(key: Int) : ListNode? {
            return if (map.containsKey(key)) {
                val p = map[key]!!
                val n = p.next!!
                p.next = n.next
                map.remove(key)
                n
            } else null
        }

        override fun get(key: Int): Int {
            return if (map.containsKey(key)) {
                val previous = map[key]
                val node = previous!!.next!!
                val r = node.`val`

                1
            } else return -1
        }

        override fun put(key: Int, value: Int) {

            TODO("Not yet implemented")
        }
    }
}


fun main() {
    // Leetcode_2.test()
    Leetcode_287.test()
}