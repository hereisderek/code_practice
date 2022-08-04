@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import java.util.*


// 46. Permutations
// https://leetcode.com/problems/permutations/
private interface Leetcode_46 {
    fun permute(nums: IntArray): List<List<Int>>

    companion object : Testable {
        override fun test() {
        }
    }

    // Runtime: 225 ms, faster than 96.25% of Kotlin online submissions for Permutations.
    // Memory Usage: 37.9 MB, less than 95.97% of Kotlin online submissions for Permutations.
    // https://leetcode.com/submissions/detail/763899810/
    private class M1 : Leetcode_46 {
        private val res = ArrayList<ArrayList<Int>>()
        private val cur = ArrayList<Int>()

        override fun permute(nums: IntArray): List<List<Int>> {
            val candidates = ArrayList<Int>()
            for (n in nums) {
                candidates.add(n)
            }
            dfs(candidates)
            return res
        }

        fun dfs(candidates: ArrayList<Int>) {
            if (candidates.isEmpty()) {
                res.add(ArrayList(cur))
                return
            }
            for (i in candidates.indices) {
                val n = candidates[i]
                candidates.removeAt(i)
                cur += n
                dfs(candidates)
                candidates.add(i, n)
                cur.removeAt(cur.lastIndex)
            }
        }
    }
}


// 90. Subsets II
// https://leetcode.com/problems/subsets-ii/
private interface Leetcode_90 {
    fun subsetsWithDup(nums: IntArray): List<List<Int>>
    companion object : Testable {
        override fun test() {
            /*
            val tests = listOf(

            )
            listOf(
                M1()::,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
            */
        }
    }

    private class M1 : Leetcode_90{
        private val res: MutableList<List<Int>> = LinkedList()
        private val track = LinkedList<Int>()

        override fun subsetsWithDup(nums: IntArray): List<List<Int>> {
            Arrays.sort(nums)
            backtrack(nums, 0)
            return res
        }
        private fun backtrack(nums: IntArray, start: Int) {
            res.add(LinkedList(track))
            for (i in start until nums.size) {
                if (i > start && nums[i] == nums[i - 1]) {
                    continue
                }
                track.addLast(nums[i])
                backtrack(nums, i + 1)
                track.removeLast()
            }
        }
    }


    private class S1 : Leetcode_90 {
        private val res: MutableList<List<Int>> = LinkedList()
        private val track = LinkedList<Int>()

        override fun subsetsWithDup(nums: IntArray): List<List<Int>> {
            // 先排序，让相同的元素靠在一起
            Arrays.sort(nums)
            backtrack(nums, 0)
            return res
        }

        fun backtrack(nums: IntArray, start: Int) {
            // 前序位置，每个节点的值都是一个子集
            res.add(LinkedList(track))
            for (i in start until nums.size) {
                // 剪枝逻辑，值相同的相邻树枝，只遍历第一条
                if (i > start && nums[i] == nums[i - 1]) {
                    continue
                }
                track.addLast(nums[i])
                backtrack(nums, i + 1)
                track.removeLast()
            }
        }
    }
}

val _20220803 = listOf<Testable>(
    Leetcode_46,
)