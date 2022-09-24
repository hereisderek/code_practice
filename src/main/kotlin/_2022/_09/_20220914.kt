@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._09

import Testable


// 213. House Robber II
// https://leetcode.com/problems/house-robber-ii/
private interface LC_213 {
    fun rob(nums: IntArray): Int

    private class M1 : LC_213 {
        override fun rob(nums: IntArray): Int {
            if (nums[0] > nums[nums.size-1]) {
                nums[nums.size-1] = 0
            } else {
                nums[0] = 0
            }

            val res = IntArray(nums.size)
            for (i in 0 until nums.size) {
                res[i] = Math.max(
                    res.getOrNull(i-1) ?: 0,
                    (res.getOrNull(i-2) ?: 0) + nums[i]
                )
            }
            return res.last()
        }
    }


    // neetcode
    // Runtime: 287 ms, faster than 14.74% of Kotlin online submissions for House Robber II.
    // Memory Usage: 36.3 MB, less than 43.16% of Kotlin online submissions for House Robber II.
    // https://leetcode.com/submissions/detail/800964913/
    private class S1 : LC_213 {
        override fun rob(nums: IntArray): Int {
            if (nums.size <= 1) return nums.getOrNull(0) ?: 0

            val res = IntArray(nums.size)

            for (i in 0 until nums.size-1) {
                res[i] = Math.max(
                    res.getOrNull(i-1) ?: 0,
                    (res.getOrNull(i-2) ?: 0) + nums[i]
                )
            }

            val res2 = IntArray(nums.size)
            for (i in 1 until nums.size) {
                res2[i] = Math.max(
                    res2.getOrNull(i-1) ?: 0,
                    (res2.getOrNull(i-2) ?: 0) + nums[i]
                )

            }
            return Math.max(
                res[nums.size-2],
                res2[nums.size-1]
            )
        }
    }
}



val _202209014 = listOf<Testable>(

)