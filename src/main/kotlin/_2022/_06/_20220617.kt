@file:Suppress("DuplicatedCode", "ClassName")

package _2022._06

import Testable
import utils.immutableTupleOf
import utils.runTimedTests
import utils.contentEquals
import utils.*

fun main() {
    // Leetcode_167.test()
    Leetcode_283.test()
}

// 167. Two Sum II - Input Array Is Sorted
// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
private interface Leetcode_167 {
    fun twoSum(numbers: IntArray, target: Int): IntArray
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                immutableTupleOf(intArrayOf(2,7,11,15), 9, intArrayOf(1,2)),
                immutableTupleOf(intArrayOf(2,3,4), 6, intArrayOf(1,3)),
                immutableTupleOf(intArrayOf(-1,0), -1, intArrayOf(1,2)),
            )
            listOf(
                M1()::twoSum,
            ).runTimedTests {
                tests.forEach {
                    it.run {
                        invoke(first.clone(), second)contentEquals(third)
                    }
                }
            }
        }
    }
    // https://leetcode.com/submissions/detail/724422848/
    // Runtime: 520 ms, faster than 19.83% of Kotlin online submissions for Two Sum II - Input Array Is Sorted.
    // Memory Usage: 47 MB, less than 63.22% of Kotlin online submissions for Two Sum II - Input Array Is Sorted.
    private class M1 : Leetcode_167 {
        override fun twoSum(numbers: IntArray, target: Int): IntArray {
            val map = mutableMapOf<Int, Int>()
            for (i in numbers.indices){
                if (map.containsKey(numbers[i])) return intArrayOf(map[numbers[i]]!!+1, i+1)
                val diff = target - numbers[i]
                map[diff] = i
            }
            throw Exception("No solution found for target:$target in array:${numbers.joinToString()}")
        }
    }
}

//283. Move Zeroes
// https://leetcode.com/problems/move-zeroes/
private interface Leetcode_283 {
    fun moveZeroes(nums: IntArray): Unit
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(0,1,0,3,12) to intArrayOf(1,3,12,0,0),
                intArrayOf(0) to intArrayOf(0),
                intArrayOf(1) to intArrayOf(1),
                intArrayOf(0,0) to intArrayOf(0,0),
            )
            listOf(
                M1()::moveZeroes,
            ).runTimedTests {
                tests.forEachPair { first, second ->
                    first.clone().apply {
                        invoke(this)
                    }.assertEqualTo(
                        second,
                        checker = {a,b -> a.contentEquals(b, false) },
                        toStr = { joinToString() }
                    )
                }
            }
        }
    }


    // https://leetcode.com/submissions/detail/724874018/
    // Runtime: 595 ms, faster than 41.31% of Kotlin online submissions for Move Zeroes.
    // Memory Usage: 62.8 MB, less than 76.39% of Kotlin online submissions for Move Zeroes.

    private class M1 : Leetcode_283 {
        override fun moveZeroes(nums: IntArray): Unit {
            var index = 0
            nums.forEachIndexed { i, num ->
                if (num != 0) {
                    nums[i] = 0
                    nums[index] = num
                    index++
                }
            }
        }
    }
}