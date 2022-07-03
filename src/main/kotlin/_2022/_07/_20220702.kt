@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


// https://leetcode.com/problems/product-of-array-except-self/

private interface Leetcode_238 {
    fun productExceptSelf(nums: IntArray): IntArray
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(1,2,3,4) to intArrayOf(24,12,8,6),
                intArrayOf(-1,1,0,-3,3) to intArrayOf(0,0,9,0,0),
            )
            listOf(
                M1()::productExceptSelf,
            ).runTimedTests {
                tests.forEachPair { first, second ->
                    invoke(first).assertEqualTo(second)

                }
            }
        }
    }

    // https://leetcode.com/submissions/detail/736288566/
    // Runtime: 394 ms, faster than 85.31% of Kotlin online submissions for Product of Array Except Self.
    // Memory Usage: 46.6 MB, less than 86.44% of Kotlin online submissions for Product of Array Except Self.
    private class M1 : Leetcode_238 {
        override fun productExceptSelf(nums: IntArray): IntArray {
            val forward = IntArray(nums.size)
            val backward = IntArray(nums.size)
            forward[0] = 1
            backward[nums.size-1] = 0

            val res = IntArray(nums.size)
            var product = 1

            for (i in nums.indices) {
                product *= nums[i]
                forward[i] = product
            }
            product = 1
            for (i in nums.size - 1 downTo  0) {
                val num = nums[i]
                product *= num
                backward[i] = product
            }

            for (i in nums.indices) {
                val f = forward.getOrNull(i-1) ?: 1
                val b = backward.getOrNull(i+1) ?: 1
                res[i] = f * b
            }
            return res
        }
    }

    // TODO: use inplace calculation to achieve O(1) space complexity based off M1
    private class M2 : Leetcode_238 {
        override fun productExceptSelf(nums: IntArray): IntArray {
            val forward = IntArray(nums.size)
            val backward = IntArray(nums.size)
            forward[0] = 1
            backward[nums.size-1] = 0

            val res = IntArray(nums.size)
            var product = 1

            for (i in nums.indices) {
                product *= nums[i]
                forward[i] = product
            }
            product = 1
            for (i in nums.size - 1 downTo  0) {
                val num = nums[i]
                product *= num
                backward[i] = product
            }

            for (i in nums.indices) {
                val f = forward.getOrNull(i-1) ?: 1
                val b = backward.getOrNull(i+1) ?: 1
                res[i] = f * b
            }
            return res
        }
    }
}

//217. Contains Duplicate
// https://leetcode.com/problems/contains-duplicate/
private interface Leetcode_217 {
    fun containsDuplicate(nums: IntArray): Boolean
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(1,2,3,1) to true,
                intArrayOf(1,2,3,4) to false,
                intArrayOf(1,1,1,3,3,4,3,2,4,2) to true,
            )
            listOf(
                M1()::containsDuplicate,
            ).runTimedTests {
                tests.forEachPair { first, second ->
                    invoke(first).assertEqualTo(second)
                }

            }
        }
    }


    // https://leetcode.com/submissions/detail/736300444/
    // Runtime: 739 ms, faster than 49.96% of Kotlin online submissions for Contains Duplicate.
    // Memory Usage: 70.4 MB, less than 76.63% of Kotlin online submissions for Contains Duplicate.

    private class M1 : Leetcode_217 {
        override fun containsDuplicate(nums: IntArray): Boolean {
            val set = mutableSetOf<Int>()
            nums.forEach {
                if (set.contains(it)) return true
                set.add(it)
            }
            return false
        }
    }
}

// 347. Top K Frequent Elements
// https://leetcode.com/problems/top-k-frequent-elements/
private interface Leetcode_347 {
    fun topKFrequent(nums: IntArray, k: Int): IntArray
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(
                    intArrayOf(1,1,1,2,2,3), 2, intArrayOf(1,2)
                ),
                tupleOf(
                    intArrayOf(1), 1, intArrayOf(1)
                ),
                tupleOf(
                    intArrayOf(-1, -1), 1, intArrayOf(-1)
                ),
            )
            listOf(
                S1()::topKFrequent,
            ).runTimedTests {
                tests.forEachTuple { first, second, third ->
                    invoke(first, second).assertEqualTo(third)
                }
            }
        }
    }

    // TODO: Heatmap
    private class M1 : Leetcode_347 {
        override fun topKFrequent(nums: IntArray, k: Int): IntArray {
            val map = HashMap<Int, Int>()
            val topK = mutableListOf<Int>()
            nums.forEach {
                if (map.containsKey(it)) {
                    map[it] = map[it]!! + 1
                } else {
                    map[it] = 1
                }
            }

            TODO("Not yet implemented")
        }
    }
    private class S1 : Leetcode_347 {
        override fun topKFrequent(nums: IntArray, k: Int): IntArray {
            if (nums.isEmpty()) return intArrayOf()
            if (nums.size == 1) return intArrayOf(nums[0])

            val freq = Array<ArrayList<Int>?>(nums.size+1){ null }
            val map = HashMap<Int, Int>()
            nums.forEach {
                if (map.containsKey(it)) {
                    map[it] = map[it]!! + 1
                } else {
                    map[it] = 1
                }
            }

            map.forEach { (t, u) ->
                val list = freq.getOrNull(u) ?: ArrayList<Int>().also {
                    freq[u] = it
                }
                list.add(t)
            }
            var counter = 0
            val res = IntArray(k)
            for (i in freq.size - 1 downTo 0) {
                val list = freq[i] ?: continue
                for (l in list) {
                    res[counter] = l
                    counter ++
                }
                if (counter == k) break
            }

            return res
        }
    }
}




// 125. Valid Palindrome
// https://leetcode.com/problems/valid-palindrome/
private interface Leetcode_125 {
    fun isPalindrome(s: String): Boolean
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                "A man, a plan, a canal: Panama" to true,
                "race a car" to false,
                " " to true
            )
            listOf(
                M1()::isPalindrome,
            ).runTimedTests {
                tests.forEachPair { first, second ->
                    invoke(first).assertEqualTo(second)
                }

            }
        }
    }

    private class M1 : Leetcode_125 {
        override fun isPalindrome(s: String): Boolean {
            // val alphanumeric = (0 .. 25).map { 'a' + it } + (0 until 10).map { '0' + it }.toList()
            val alphanumeric = "abcdefghijklmnopqrstuvwxyz0123456789"
            val str = s.toLowerCase().filter { it in alphanumeric }

            var left = 0
            var right = str.length - 1
            while (left < right) {
                if (str[left] != str[right]) return false
                left++
                right--
            }

            return true
        }
    }
}

// 659 · Encode and Decode Strings
// https://www.lintcode.com/problem/659/

private interface Lintcode_659 {
    fun encode(strs: List<String>) : String
    fun decode(str: String) : List<String>
    companion object : Testable {

        override fun test() {
            val tests = listOf(
                listOf("we", "say", ":", "yes"),
                listOf(":", ";", "a:;", ";:b:;"),
            )
            listOf(
                // M1(),
                M2(),
            ).runTimedTests {
                tests.forEach {
                    val encoded = encode(it)
                    val decoded = decode(encoded)
                    it.assertEqualTo(decoded){ exp, act ->
                        "assertion failed, original: [${exp.joinToString()}] vs actual: [${act.joinToString()}], encoded:[${encoded}]"
                    }
                    println(
                        "original: [${it.joinToString()}] vs actual: [${decoded.joinToString()}], encoded:[${encoded}]"
                    )
                }

            }
        }
    }

    private class M1 : Lintcode_659 {
        override fun encode(strs: List<String>): String {
            return strs.joinToString(separator = "]:;[")
        }

        override fun decode(str: String): List<String> {
            return str.split("]:;[")
        }
    }

    private class M2 : Lintcode_659 {
        // into: 2#we3#say1#:3#yes
        override fun encode(strs: List<String>): String {
            return StringBuffer().apply {
                for (w in strs) {
                    append("${w.length}#${w}")
                }
            }.toString()
        }

        override fun decode(str: String): List<String> {
            val list = ArrayList<String>()
            var pointer = 0

            while (pointer < str.length) {
                var j = pointer
                while (str[j] != '#') { j++ }
                val length = str.substring(pointer, j).toInt()
                pointer = j + length + 1
                val word = str.substring(j+1, pointer)
                list.add(word)
            }
            return list
        }
    }
}
fun main() {
    // Leetcode_238.test()
    // Leetcode_217.test()
    // Leetcode_347.test()
    // Leetcode_125.test()
    Lintcode_659.test()
}