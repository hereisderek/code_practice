@file:Suppress("DuplicatedCode", "ClassName")

package _2022._06

import Testable
import utils.assertEqualTo
import utils.forEachPair
import utils.runTimedTests

fun main() {
    // Leetcode_344.test()
    // Leetcode_303.test()
    Leetcode_304.test()
}

// 344. Reverse String
// https://leetcode.com/problems/reverse-string/
private interface Leetcode_344 {
    fun reverseString(s: CharArray)
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                "hello",
                "Hannah"
            ).map {
                it.toCharArray().run {
                    this to this.reversedArray()
                }
            }
            listOf(
                M1()::reverseString,
            ).runTimedTests {
                tests.forEachPair { first, second ->
                    first.clone().apply {
                        invoke(this)
                    }.assertEqualTo(
                        second,
                        checker = {a, b -> a.contentEquals(b)}
                    )

                }
            }
        }
    }

    //
    // https://leetcode.com/submissions/detail/724893027/
    // Runtime: 477 ms, faster than 39.17% of Kotlin online submissions for Reverse String.
    // Memory Usage: 57.8 MB, less than 60.04% of Kotlin online submissions for Reverse String.
    private class M1 : Leetcode_344 {
        override fun reverseString(s: CharArray) {
            for (i in s.indices) {
                val end = s.size - 1 - i
                if (i >= end) break
                val temp = s[i]
                s[i] = s[end]
                s[end] = temp
            }
        }
    }
}

// 303. Range Sum Query - Immutable
// https://leetcode.com/problems/range-sum-query-immutable/
private interface Leetcode_303 {
    var nums: IntArray
    fun sumRange(left: Int, right: Int): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(-2, 0, 3, -5, 2, -1) to listOf(
                    intArrayOf(0,2) to 1,
                    intArrayOf(2,5) to -1,
                    intArrayOf(0,5) to -3,
                ),
            )
            listOf(
                M1(),
                M2(),
            ).runTimedTests {
                tests.forEachPair { nums, second ->
                    this.nums = nums
                    second.forEachPair { input, output ->
                        sumRange(input[0],input[1]).assertEqualTo(output) { expected, actual ->
                            "assertion failed for IntArray:${nums.joinToString(prefix = "[", postfix = "]")}, expected:$expected actual:$actual"
                        }
                    }
                }
            }
        }
    }

    // https://leetcode.com/submissions/detail/724940421/
    // Runtime: 464 ms, faster than 58.12% of Kotlin online submissions for Range Sum Query - Immutable.
    // Memory Usage: 58.1 MB, less than 41.03% of Kotlin online submissions for Range Sum Query - Immutable.
    private class M1(override var nums: IntArray = IntArray(0)) : Leetcode_303 {
        val map = mutableMapOf<Int, Int>()

        override fun sumRange(left: Int, right: Int): Int {
            return getSumOfFirst(right) - getSumOfFirst(left - 1)
        }
        private fun getSumOfFirst(index: Int) : Int {
            if (index < 0 ) return 0

            // existing
            if (map.containsKey(index)) return map[index]!!

            if (index == 0) {
                map[0] = nums[0]
                return nums[0]
            }

            val sum = nums[index] + getSumOfFirst(index - 1)
            map[index] = sum
            return sum
        }
    }

    // Same as M1 but use array instead
    // https://leetcode.com/submissions/detail/724998196/
    // Runtime: 548 ms, faster than 41.88% of Kotlin online submissions for Range Sum Query - Immutable.
    // Memory Usage: 57.4 MB, less than 42.74% of Kotlin online submissions for Range Sum Query - Immutable.
    private class M2(nums: IntArray = IntArray(0)) : Leetcode_303 {
        private var map = Array<Int?>(nums.size) { null }
        override var nums: IntArray = nums
            set(value) {
                if (value !== field) {
                    field = value
                    map = Array(nums.size) { null }
                }
            }


        override fun sumRange(left: Int, right: Int): Int {
            return getSumOfFirst(right) - getSumOfFirst(left - 1)
        }

        private fun getSumOfFirst(index: Int) : Int {
            if (index < 0 ) return 0
            require(index < nums.size)

            // existing
            if (map[index] != null) return map[index]!!

            if (index == 0) {
                map[0] = nums[0]
                return nums[0]
            }

            val sum = nums[index] + getSumOfFirst(index - 1)
            map[index] = sum
            return sum
        }
    }
}


// 304. Range Sum Query 2D - Immutable
// https://leetcode.com/problems/range-sum-query-2d-immutable/
private interface Leetcode_304 {
    val matrix: Array<IntArray>
    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf<Pair<Array<IntArray>, List<Pair<IntArray, Int>>>>(
/*                 arrayOf(
                    intArrayOf(3, 0, 1, 4, 2),
                    intArrayOf(5, 6, 3, 2, 1),
                    intArrayOf(1, 2, 0, 1, 5),
                    intArrayOf(4, 1, 0, 1, 7),
                    intArrayOf(1, 0, 3, 0, 5),
                ) to listOf(
                    intArrayOf(2, 1, 4, 3) to 8,
                    intArrayOf(1, 1, 2, 2) to 11,
                    intArrayOf(1, 2, 2, 4) to 12,
                ),
                arrayOf(
                    intArrayOf(-4,-5)
                ) to listOf(
                    intArrayOf(0,0,0,0) to -4,
                    intArrayOf(0,0,0,1) to -9,
                    intArrayOf(0,1,0,1) to -5,
                ), */
                arrayOf(
                    intArrayOf(8,-4,5),
                    intArrayOf(-1,4,4),
                    intArrayOf(-2,3,1),
                ) to listOf(
                    intArrayOf(0,1,0,2) to 1,
                    intArrayOf(1,1,1,2) to 8,
                    intArrayOf(0,1,0,2) to 1,
                ),
            )
            tests.flatMap {
                listOf(
                    M1(it.first) to it.second,
                    M1(it.first) to it.second,
                )
            }.forEachPair { m, tests ->
                tests.forEachPair { pos, expected ->
                    m.sumRegion(pos[0],pos[1],pos[2],pos[3]).assertEqualTo(expected)
                }
            }
            /*
            listOf(
                M1()::someMethod,
            ).runTimedTests {
                invoke().assertEqualTo(Unit)
            }
            */
        }
    }

    private class M1(override val matrix: Array<IntArray>) : Leetcode_304 {
        val sum : Array<IntArray>
        init {
            val rowCount = matrix.size
            val columnCount = matrix[0].size

            sum = Array<IntArray>(rowCount){ IntArray(columnCount) }
            
            for(r in 0 until rowCount) {
                for (c in 0 until columnCount) {
                    sum[r][c] = when {
                        r == 0 && c == 0 -> 0
                        r == 0 -> sum[0][c - 1]
                        c == 0 -> sum[r - 1][0]
                        else -> sum[r-1][c] + sum[r][c-1] - sum[r-1][c-1]
                    } + matrix[r][c]
                }
            }
        }
        override fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
            return when{
                row1 == 0 && col1 == 0 -> 0
                row1 == 0 -> 0 - sum[row2][col1 - 1]
                col1 == 0 -> 0 - sum[row1 - 1][col2]
                else -> sum[row1-1][col1-1] - sum[row2][col1 - 1] - sum[row1 - 1][col2]
            } + sum[row2][col2]
        }
    }
}