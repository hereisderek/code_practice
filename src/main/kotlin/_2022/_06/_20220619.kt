@file:Suppress("DuplicatedCode", "ClassName")

package _2022._06

import Testable
import utils.*



// Leetcode 370: Range Addition
// https://leetcode.ca/all/370.html
// https://baihuqian.github.io/2018-08-16-range-addition/
/**
 * Assume you have an array of length n initialized with all 0’s and are given k update operations.
 *
 * Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex … endIndex] (startIndex and endIndex inclusive) with inc.
 *
 * Return the modified array after all k operations were executed.
 *
 * Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * Output: [-2,0,3,5,3]
 *
 * Initial state:
 * [ 0, 0, 0, 0, 0 ]
 * After applying operation [1, 3, 2]:
 * [ 0, 2, 2, 2, 0 ]
 * After applying operation [2, 4, 3]:
 * [ 0, 2, 5, 5, 3 ]
 * After applying operation [0, 2, -2]:
 * [-2, 0, 3, 5, 3 ]
 *
 *
 * */

private interface Leetcode_370 {
    fun getModifiedArray(length: Int, updates: Array<IntArray>) : IntArray

    companion object : Testable {
        override fun test() {

            listOf(
                tupleOf(
                    arrayOf(
                        intArrayOf(1,  3,  2),
                        intArrayOf(2,  4,  3),
                        intArrayOf(0,  2, -2),
                    ),
                    5,
                    intArrayOf(-2, 0, 3, 5, 3)
                )
            ).runTimedTests {

            }


            val tests = listOf(
                tupleOf(
                    arrayOf(
                        intArrayOf(1,  3,  2),
                        intArrayOf(2,  4,  3),
                        intArrayOf(0,  2, -2),
                    ),
                    5,
                    intArrayOf(-2, 0, 3, 5, 3)
                )
            )

            listOf(
                M1()::getModifiedArray,
            ).runTimedTests {
                tests.forEachTuple { first, second, third ->
                    val array = first.clone()
                    invoke(second, array).assertEqualTo(third)
                }

            }
        }
    }

    private class M1 : Leetcode_370 {
        override fun getModifiedArray(length: Int, updates: Array<IntArray>): IntArray {
            TODO("Not yet implemented")
        }
    }
}



private interface Leetcode_1094 {
    fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(
                    arrayOf(intArrayOf(2,1,5), intArrayOf(3,3,7),),
                    4,
                    false
                ),
                tupleOf(
                    arrayOf(intArrayOf(2,1,5), intArrayOf(3,3,7),),
                    5,
                    true
                ),
            )
            listOf(
                M1()::carPooling,
            ).runTimedTests {
                tests.forEachTuple { first, second, third ->
                    invoke(first, second).assertEqualTo(third)
                }

            }
        }
    }

    private class M1 : Leetcode_1094 {
        override fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
            val tripDiff = IntArray(1001)
            for (trip in trips) {
                tripDiff[trip[1]] += trip[0]
                tripDiff[trip[2]] -= trip[0]
            }

            val result = IntArray(tripDiff.size)
            var counter = 0
            for (i in tripDiff.indices) {
                counter += tripDiff[i]
                result[i] = counter
                if (counter > capacity) return false
            }
            return true
        }
    }
}


// 1109. Corporate Flight Bookings
// https://leetcode.com/problems/corporate-flight-bookings/

// 48. Rotate Image
// https://leetcode.com/problems/rotate-image/


private interface Leetcode_48 {
    fun rotate(matrix: Array<IntArray>): Unit
    companion object : Testable {
        override fun test() {
            val tests = listOf<Pair<Matrix, Matrix>>(
                arrayOf(
                    intArrayOf(1,2,3),
                    intArrayOf(4,5,6),
                    intArrayOf(7,8,9),
                ) to arrayOf(
                    intArrayOf(7,4,1),
                    intArrayOf(8,5,2),
                    intArrayOf(9,6,3),
                ),
                arrayOf(
                    intArrayOf(5,1,9,11),
                    intArrayOf(2,4,8,10),
                    intArrayOf(13,3,6,7),
                    intArrayOf(15,14,12,16),
                ) to arrayOf(
                    intArrayOf(15,13,2,5),
                    intArrayOf(14,3,4,1),
                    intArrayOf(12,6,8,9),
                    intArrayOf(16,7,10,11),
                ),
            )
            with(M1()) {
                tests.forEachPair { first, second ->
                    rotate(first)
                }
            }

            listOf(
                M1()::rotate,
            ).runTimedTests {
                tests.forEachPair { first, second ->
                    first.deepClone().also {
                        invoke(it)
                    }.assertEqualTo(second, toStr = { toMatrixStr(false) })
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

    private class M1 : Leetcode_48 {
        private val position = IntArray(2)
        private fun newPos(y: Int, x: Int, side: Int) : IntArray {
            if (side % 2 == 0) {

            } else {

            }
            return position
        }
        override fun rotate(matrix: Array<IntArray>) {
            matrix.toMatrixStr()
            val size = matrix.size



            // TODO("Not yet implemented")
        }
    }
}

fun main() {
    // Leetcode_370.test()
    // Leetcode_1094.test()
    Leetcode_48.test()
}