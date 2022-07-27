@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.assertEqualTo
import utils.runTimedTests
import utils.tupleOf


// 84. Largest Rectangle in Histogram
// https://leetcode.com/problems/largest-rectangle-in-histogram/
private interface Leetcode_84 {
    fun largestRectangleArea(heights: IntArray)
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(2,1,5,6,2,3) to 10,
                intArrayOf(2,4) to 4
            )
            listOf(
                M1()::largestRectangleArea,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    private class M1 : Leetcode_84 {
        override fun largestRectangleArea(heights: IntArray) {
            TODO("Not yet implemented")
        }
    }
}



// 704. Binary Search
// https://leetcode.com/problems/binary-search/
private interface Leetcode_704 {
    fun search(nums: IntArray, target: Int): Int
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(intArrayOf(-1,0,3,5,9,12), 9, 4),
                tupleOf(intArrayOf(-1,0,3,5,9,12), 2, -1),
            )

            listOf(
                M1()::search,
                S1()::search,
            ).runTimedTests(tests) { a, b, c ->
                invoke(a, b).assertEqualTo(c)
            }
        }
    }

    // Runtime: 538 ms, faster than 33.51% of Kotlin online submissions for Binary Search.
    // Memory Usage: 63.1 MB, less than 82.17% of Kotlin online submissions for Binary Search.
    // https://leetcode.com/submissions/detail/756281568/
    private class M1 : Leetcode_704 {
        private fun search(nums: IntArray, target: Int, start: Int, end: Int) : Int {
            if (end - start <= 1) {
                return when (target) {
                    nums[start] -> start
                    nums[end] -> end
                    else -> -1
                }
            }
            val midIndex = (start + end) / 2
            val mid = nums[midIndex]
            if (mid == target) return midIndex

            return if (mid > target) {
                search(nums, target, start, midIndex)
            } else search(nums, target, midIndex+1, end)
        }
        override fun search(nums: IntArray, target: Int): Int {
            return search(nums, target, 0, nums.size - 1)
        }
    }

    private class S1 : Leetcode_704 {
        override fun search(nums: IntArray, target: Int): Int {
            var l = 0
            var r = nums.size

            while (l <= r) {
                val m = (l + r) / 2
                when{
                    nums[m] < target -> l = m + 1
                    nums[m] > target -> r = m - 1
                    else -> return m
                }
            }

            return -1
        }
    }
}


// 74. Search a 2D Matrix
// https://leetcode.com/problems/search-a-2d-matrix/
private interface Leetcode_74 {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(
                    arrayOf(
                        intArrayOf(1),
                    ),
                    2,
                    false
                ),
                tupleOf(
                    arrayOf(
                        intArrayOf(1,3,5,7),
                        intArrayOf(10,11,16,20),
                        intArrayOf(23,30,34,60),
                    ),
                    3,
                    true
                ),

                tupleOf(
                    arrayOf(
                        intArrayOf(1,3,5,7),
                        intArrayOf(10,11,16,20),
                        intArrayOf(23,30,34,60),
                    ),
                    13,
                    false
                ),

            )
            listOf(
                M1()::searchMatrix,
            ).runTimedTests(tests) { a, b, c ->
                invoke(a, b).assertEqualTo(c)
            }
        }
    }


    // Runtime: 301 ms, faster than 30.56% of Kotlin online submissions for Search a 2D Matrix.
    // Memory Usage: 38.1 MB, less than 25.22% of Kotlin online submissions for Search a 2D Matrix.
    // https://leetcode.com/submissions/detail/756321205/
    private class M1 : Leetcode_74 {
        override fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
            var t = 0
            var b = matrix.size - 1

            while (t < b) {
                val m = t + ((b - t) / 2)
                when{
                    matrix[m][0] > target -> b = m - 1
                    matrix[m].last() < target -> t = m + 1
                    matrix[m][0] <= target && matrix[m].last() >= target -> {
                        t = m
                        b = m
                    }
                    else -> return false
                }
            }

            var l = 0
            var r = matrix[0].size - 1
            val array = matrix[t]
            while (l <= r) {
                val m = l + (r - l) / 2
                when{
                    array[m] < target -> l = m + 1
                    array[m] > target -> r = m - 1
                    else -> return true
                }
            }
            return false
        }
    }
}


// 875. Koko Eating Bananas
// https://leetcode.com/problems/koko-eating-bananas/
private interface Leetcode_875 {
    fun minEatingSpeed(piles: IntArray, h: Int): Int
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(intArrayOf(3,6,7,11), 8, 4),
                tupleOf(intArrayOf(30,11,23,4,20), 5, 30),
                tupleOf(intArrayOf(30,11,23,4,20), 6, 23),
            )
            listOf(
                M1()::minEatingSpeed,
            ).runTimedTests(tests) { a, b, c ->
                invoke(a, b).assertEqualTo(c)
            }
        }
    }

    // 805306368,805306368,805306368
    // inspired by [link](https://labuladong.github.io/algo/2/20/31/)
    private class M1 : Leetcode_875 {
        override fun minEatingSpeed(piles: IntArray, h: Int): Int {
            var l = 1
            var r = 1000000000 + 1 // in case when h > piles.size
            while (l < r) {
                val m = l + (r - l) / 2
                if (getH(piles, m) > h) {
                    l = m + 1
                } else {
                    // if calculated h is < or == to h then
                    // continue to re-assign r to the new m
                    r = m
                }

            }
            return l
        }

        private fun getH(piles: IntArray, k: Int) : Int {
            var h = 0
            piles.forEach {
                h += it / k
                if (it % k != 0) h++
            }
            return h
        }
    }
}


// 33. Search in Rotated Sorted Array
// https://leetcode.com/problems/search-in-rotated-sorted-array/
private interface Leetcode_33 {
    fun search(nums: IntArray, target: Int): Int
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(intArrayOf(4,5,6,7,0,1,2), 0, 4),
                tupleOf(intArrayOf(4,5,6,7,0,1,2), 3, -1),
                tupleOf(intArrayOf(1), 0, -1),
            )
            listOf(
                M1()::search,
            ).runTimedTests(tests) { a, b, c ->
                invoke(a, b).assertEqualTo(c)
            }
        }
    }

    // this was to calculate the pivot point, which is not what was asked
    private class M1 : Leetcode_33 {
        override fun search(nums: IntArray, target: Int): Int {
            var l = 0
            var r = nums.size
            while (l < r) {
                val m = l + (r - l) / 2
                when{
                    nums[m] > nums[l] && nums[m] > nums[r] -> l = m + 1
                    nums[m] < nums[r] && nums[m] < nums[l] -> r = m
                    else -> {

                    }
                }
            }

            TODO("Not yet implemented")
        }
    }

    // https://www.youtube.com/watch?v=U8XENwh8Oy8
    private class S2 : Leetcode_33 {
        override fun search(nums: IntArray, target: Int): Int {

            TODO("Not yet implemented")
        }
    }
}


fun main() {
    // Leetcode_84.test()
    // Leetcode_704.test()
    // Leetcode_74.test()
    Leetcode_875.test()
}