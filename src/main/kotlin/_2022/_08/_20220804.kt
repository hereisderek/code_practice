@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.UnionFind
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
                    charArrayOf('1'),
                    charArrayOf('1'),
                ) to 1,
                arrayOf(
                    charArrayOf('1','0','0','0','0'),
                ) to 1,
                arrayOf(
                    charArrayOf('1','0','1','0','0'),
                ) to 2,
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
                M2()::numIslands,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }
    // UnionFind
    private class M1 : Leetcode_200 {
        private lateinit var uf : UnionFind
        override fun numIslands(grid: Array<CharArray>): Int {
            val n = grid.size
            val m = grid[0].size
            uf = UnionFind(m * n)

            for (i in 0 until n) {
                for (j in 0 until m) {
                    val index = i * m + j
                    val c = grid[i][j]
                    if (c != '1') continue


                    // top
                    if (i > 0) {
                        if (grid[i - 1][j] == '1') {
                            uf.union(index, (i - 1) * m + j)
                        }
                    }

                    // left
                    if (j > 0) {
                        if (grid[i][j-1] == '1') {
                            uf.union(index, index - 1)
                        }
                    }
                }
            }

            val island = HashSet<Int>()
            var counter = 0
            for (i in 0 until n) {
                for (j in 0 until m) {
                    val index = i * m + j
                    val c = grid[i][j]
                    if (c != '1') continue

                    if (!island.any { it != index && uf.connected(it, index) }) {
                        counter++
                        island.add(index)
                    }
                }
            }
            return counter
        }
    }

    private class M2 : Leetcode_200 {
        override fun numIslands(grid: Array<CharArray>): Int {
            val n = grid.size
            val m = grid[0].size
            var counter = 0
            for (y in 0 until n) {
                for (x in 0 until m) {
                    if (isLand(grid, x, y)) {
                        counter++
                        dfs(grid, x, y)
                    }
                }
            }
            return counter
        }

        fun dfs(grid: Array<CharArray>, x: Int, y: Int) {
            val n = grid.size
            val m = grid[0].size
            // out of bound
            if (x < 0 || x >= m || y < 0 || y >= n) return
            grid[y][x] = '0'

            if (isLand(grid, x-1, y)) dfs(grid, x-1, y)
            if (isLand(grid, x+1, y)) dfs(grid, x+1, y)
            if (isLand(grid, x, y-1)) dfs(grid, x, y-1)
            if (isLand(grid, x, y+1)) dfs(grid, x, y+1)
        }

        fun isLand(grid: Array<CharArray>, x: Int, y: Int) : Boolean {
            val n = grid.size
            val m = grid[0].size
            // out of bound
            if (x < 0 || x >= m || y < 0 || y >= n) return false

            return grid[y][x] == '1'
        }
    }
}


val _20220804 = listOf<Testable>(
    // Leetcode_40,
    Leetcode_200,
)