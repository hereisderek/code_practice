@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.assertEqualTo
import utils.runTimedTests


//
// https://leetcode.com/problems/valid-sudoku/
private interface Leetcode_36 {
    fun isValidSudoku(board: Array<CharArray>): Boolean
    companion object : Testable {
        override fun test() {}
    }

    private class M1 : Leetcode_36 {
        private val row = Array<MutableSet<Char>>(9){ HashSet() }
        private val col = Array<MutableSet<Char>>(9){ HashSet() }
        private val box = Array<MutableSet<Char>>(9){ HashSet() }

        override fun isValidSudoku(board: Array<CharArray>): Boolean {
            for (y in 0 until 9) {
                for (x in 0 until 9) {
                    if (!validate(x, y, board[y][x])) {
                        return false
                    }
                }
            }
            return true
        }

        fun validate(x: Int, y: Int, value: Char) : Boolean {
            val boxIndex = (x/3) + (y/3) * 3
            println("x:$x y:$y boxIndex:$boxIndex")
            return validateCell(value, row[x])
                    && validateCell(value, col[y])
                    && validateCell(value, box[boxIndex])
        }
        fun validateCell(value: Char, into: MutableSet<Char>) : Boolean {
            if (into.contains(value)) return false
            into.add(value)
            return true
        }
    }
}


// 53. Maximum Subarray
// https://leetcode.com/problems/maximum-subarray/
private interface Leetcode_53 {
    fun maxSubArray(nums: IntArray): Int
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(-2,1,-3,4,-1,2,1,-5,4) to 6,
                intArrayOf(-2,-1,-3,-4,-1,-2,-1,-5,-4) to -1,
            )
            listOf(
                M1()::maxSubArray,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    private class M1 : Leetcode_53 {
        override fun maxSubArray(nums: IntArray): Int {
            var s_max = nums[0]
            var sum = Int.MIN_VALUE
            var temp = 0

            for (n in nums) {
                s_max = Math.max(s_max, n)
                if (temp == 0 && n < 0) {
                    continue
                }
                temp += n
                if (sum < temp) {
                    sum = temp
                }
                if (temp < 0){
                    temp = 0
                }
            }
            return Math.max(sum, s_max)
        }
    }
}


// 55. Jump Game
// https://leetcode.com/problems/jump-game/
private interface Leetcode_55 {
    fun canJump(nums: IntArray): Boolean
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(2,3,1,1,4) to true,
                intArrayOf(3,2,1,0,4) to false,
                intArrayOf(2,3,1,1,4) to true,
                intArrayOf(5,9,3,2,1,0,2,3,3,1,0,0) to true,

            )
            listOf(
                M1_()::canJump,
                // M2()::canJump,
                M3()::canJump,
                M4()::canJump,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    // https://leetcode.com/problems/jump-game/submissions/
    // Time Limit Exceeded
    private class M1 : Leetcode_55 {
        override fun canJump(nums: IntArray): Boolean {
            return dfs(nums, 0)
        }

        fun dfs(nums: IntArray, cur: Int) : Boolean {
            if (cur >= nums.lastIndex) return true
            val steps = nums[cur]
            if (steps == 0) return false
            for (i in 1..steps) {
                if (dfs(nums, cur+i)) return true
            }
            return false
        }
    }

    // based on M1 but with cache
    private class M1_ : Leetcode_55 {
        private val cache = HashSet<Int>()

        override fun canJump(nums: IntArray): Boolean {
            return dfs(nums, 0)
        }

        fun dfs(nums: IntArray, cur: Int) : Boolean {
            if (cur >= nums.lastIndex) return true
            val steps = nums[cur]
            if (steps == 0) return false
            for (i in 1..steps) {
                if (dfs(nums, cur+i)) {
                    return true
                } else {
                    cache.add(cur+i)
                }

            }
            return false
        }
    }

    // wrong
    private class M2 : Leetcode_55 {
        override fun canJump(nums: IntArray): Boolean {
            return dfs(nums, 0, 0)
        }

        fun dfs(nums: IntArray, cur: Int, from: Int) : Boolean {
            if (cur > nums.lastIndex) return true
            val value = nums[cur]
            if (value == 0) return false
            var steps = 0
            for (i in 1..value) {
                if (i+cur > nums.lastIndex) return true
                steps = Math.max(steps, nums[i+cur]+i)
            }
            println("steps:$steps")

            for (i in (steps+cur) downTo (cur+value)+1) {
                if (dfs(nums, i, cur+value)) return true
            }
            return false

        }
    }

    private class M3 : Leetcode_55 {
        override fun canJump(nums: IntArray): Boolean {
            return dfs(nums, 0)
        }

        fun dfs(nums: IntArray, from: Int, upTo: Int = from) : Boolean {
            if (upTo >= nums.lastIndex) return true
            if (from > upTo) return false

            var max = -1
            for (i in from..upTo) {
                val value = nums[i]
                val index = i + value
                max = Math.max(max, index)
            }
            return dfs(nums, upTo+1, max)
        }
    }

    // leetcode solution
    private class M4 : Leetcode_55 {
        override fun canJump(nums: IntArray): Boolean {
            var goal = nums.lastIndex

            for (i in goal downTo 0){
                if (i + nums[i] >= goal) goal = i
            }
            return goal == 0
        }
    }
}


// 45. Jump Game II
// https://leetcode.com/problems/jump-game-ii/
private interface Leetcode_45 {
    fun jump(nums: IntArray): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(2,3,1,1,4) to 2,
                intArrayOf(2,3,0,1,4) to 2,
                intArrayOf(2,1) to 1,

            )
            listOf(
                M1()::jump,
                S1()::jump,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    private class M1 : Leetcode_45 {
        override fun jump(nums: IntArray): Int {
            // var count = 0
            val count = IntArray(nums.size) { 0 }
            for (i in nums.size - 2 downTo 0) {
                val v = nums[i]
                var smallest = count[i+1]
                for (j in 1..v) {
                    smallest = if (j+i>=count.lastIndex) {
                        0
                    } else {
                        Math.min(smallest, count[j+i])
                    }
                }
                count[i] = smallest+1
                println("i:$i count:${count[i]}")
            }
            return count[0]
        }
    }

    // neetcode
    private class S1 : Leetcode_45 {
        override fun jump(nums: IntArray): Int {
            var res = 0
            var r = 0
            var l = 0
            var fur = 0
            while (r < nums.size - 1) {
                fur = 0
                for (i in l..r) fur = Math.max(fur, i + nums[i])
                l = r + 1
                r = fur
                res++
            }
            return res
        }
    }
}


val _20220810 = listOf<Testable>(
    // Leetcode_36,
    // Leetcode_53,
    // Leetcode_55,
    Leetcode_45,
)