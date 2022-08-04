@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.assertEqualTo
import utils.runTimedTests
import java.util.LinkedList


// 40. Combination Sum II
// https://leetcode.com/problems/combination-sum-ii/
private interface Leetcode_40 {
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>>

    companion object : Testable {
        override fun test() {}
    }

    private class M1 : Leetcode_40 {
        private val res: MutableList<List<Int>> = ArrayList()
        private val track = ArrayList<Int>()
        private var sum = 0


        override fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
            candidates.sorted()
            backtrack(candidates, target, 0)
            return res
        }
        private fun backtrack(candidates: IntArray, target: Int, start: Int) : Boolean {
            val toAdd = candidates[start]
            val s = sum + toAdd
            track.add(toAdd)

            if (s == target) {
                res.add(track)
                return false
            } else if (s > target) {
                return false
            }
            for (i in start until candidates.size) {
                val inRange = !backtrack(candidates, target, i)

                track.removeLast()
                if (!inRange) {
                    break
                }

            }

            return true
        }
    }
}



// 200. Number of Islands
// https://leetcode.com/problems/number-of-islands/
private interface Leetcode_200 {
    fun numIslands(grid: Array<CharArray>): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                arrayOf(
                    charArrayOf('1','1','1','1','0'),
                    charArrayOf('1','1','0','1','0'),
                    charArrayOf('1','1','0','0','0'),
                    charArrayOf('0','0','0','0','0'),
                ) to 1,

                arrayOf(
                    charArrayOf('1','1','0','0','0'),
                    charArrayOf('1','1','0','0','0'),
                    charArrayOf('0','0','1','0','0'),
                    charArrayOf('0','0','0','1','1'),
                ) to 3,

            )

            listOf(
                M1()::numIslands,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    private class M1 : Leetcode_200 {

        override fun numIslands(grid: Array<CharArray>): Int {

            TODO("Not yet implemented")
        }
    }
}


private interface Leetcode_ {

    private class S : Leetcode_ {

    }
}


val _20220804 = listOf<Testable>(
    Leetcode_40,
)