@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.assertEqualTo
import utils.forEachPair
import utils.runTimedTests


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

private interface Leetcode {

    companion object : Testable {
        override fun test() {
            /*
            listOf(
                M1()::someMethod,
            ).runTimedTests {
                invoke().assertEqualTo(Unit)
            }
            */
        }
    }

    private class M1 : Leetcode {

    }
}



fun main() {
    // Leetcode_238.test()
    Leetcode_217.test()
}