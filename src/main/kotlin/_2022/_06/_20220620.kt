@file:Suppress("DuplicatedCode", "ClassName")

package _2022._06

import Testable
import utils.assertEqualTo
import utils.forEachPair
import utils.runTimedTests
import utils.toMatrixStr


// 54. Spiral Matrix
// https://leetcode.com/problems/spiral-matrix/
private interface Leetcode_54 {
    fun spiralOrder(matrix: Array<IntArray>): List<Int>
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                arrayOf(
                    intArrayOf(1,2,3),
                    intArrayOf(4,5,6),
                    intArrayOf(7,8,9),
                ) to listOf(1,2,3,6,9,8,7,4,5),
                arrayOf(
                    intArrayOf(1,2,3,4),
                    intArrayOf(5,6,7,8),
                    intArrayOf(9,10,11,12),
                    intArrayOf(13,14,15,16)
                ) to listOf(1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10)
            )
            listOf(
                M2()::spiralOrder,
            ).runTimedTests {
                tests.forEachPair { first, second ->
                    invoke(first).assertEqualTo(second)
                }

            }
        }
    }

    // not working
    private class M1 : Leetcode_54 {
        private fun tryAdd(
            i: Int, k: Int, m: Int, n: Int,
            list: MutableList<Int>,
            matrix: Array<IntArray>,
            map: Array<BooleanArray>
        ) : Boolean {
            return if (i < m && k < n && i >= 0 && k >= 0 && !map[i][k]) {
                list.add(matrix[i][k])
                map[i][k] = true
                true
            } else false
        }
        override fun spiralOrder(matrix: Array<IntArray>): List<Int> {
            matrix.toMatrixStr()
            val m = matrix.size
            val n = matrix[0].size
            val map = Array<BooleanArray>(m){
                BooleanArray(n){ false }
            }
            val list = ArrayList<Int>(m * n)
            var i = 0
            var k = -1
            while (true) {
                when{
                    tryAdd(i, k+1, m, n, list, matrix, map) -> {
                        k ++
                    }
                    tryAdd( i+1, k, m, n, list, matrix, map) -> {
                        i++
                    }
                    tryAdd(i, k-1, m, n, list, matrix, map) -> {
                        k--
                    }
                    tryAdd(i-1, k, m, n, list, matrix, map) -> {
                        i--
                    }
                    // else -> break
                    else -> return list
                }
            }
            // return list
            // 1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 10, 11, 7, 6, 5
        }
    }
    private class M2 : Leetcode_54 {
        private lateinit var matrix: Array<IntArray>
        private val position = intArrayOf(0, -1)
        private lateinit var mn : Pair<Int, Int>
        private lateinit var map: Array<BooleanArray>

        private fun tryAdd(operation: Int, list: ArrayList<Int>) : Boolean {
            var i: Int = position[0]
            var k: Int = position[1]
            val m = mn.first
            val n = mn.second
            when(operation) {
                1 -> i++
                2 -> i--
                3 -> k++
                0 -> k--
                else -> throw Exception("unsupported operation:$operation")
            }

            return if (i < m && k < n && i >= 0 && k >= 0 && !map[i][k]) {
                list.add(matrix[i][k])
                map[i][k] = true
                position[0] = i
                position[1] = k
                true
            } else false
        }

        override fun spiralOrder(matrix: Array<IntArray>): List<Int> {
            matrix.toMatrixStr()
            this.matrix = matrix
            mn = matrix.size to matrix[0].size
            val size = mn.run { first * second }

            map = Array<BooleanArray>(mn.first){
                BooleanArray(mn.second){ false }
            }
            val list = ArrayList<Int>(size)
            position[0] = 0
            position[1] = -1

            var ops = 0
            for (i in 0 until size) {
                while (true) {
                    ops = ops % 4
                    if (!tryAdd(ops, list)) {
                        ops++
                    } else break
                }
            }

            return list
        }
    }


    // https://labuladong.github.io/algo/2/18/24/
}

fun main() {
    Leetcode_54.test()
}