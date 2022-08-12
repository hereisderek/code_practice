@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.assertEqualTo
import utils.runTimedTests


// 198. House Robber
// https://leetcode.com/problems/house-robber/
private interface Leetcode_198 {
    fun rob(nums: IntArray): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(1,2,3,1) to 4,
                intArrayOf(
                    114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240
                ) to 4173
            )
            listOf(
                M1()::rob,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    private class M1 : Leetcode_198 {

        override fun rob(nums: IntArray): Int {
            val sum = nums.sum()
            return sum - Math.min(
                dfs(nums, 0, 0),
                dfs(nums, 1, 0)
            )
        }
        fun dfs(nums: IntArray, index: Int, sum: Int) : Int {
            val s = sum + nums[index]
            if (index >= nums.size - 2) {
                return s
            }

            return Math.min(
                dfs(nums, index + 1, s),
                dfs(nums, index + 2, s)
            )
        }
    }
}

val _20220807 = listOf<Testable>(
    Leetcode_198,
)