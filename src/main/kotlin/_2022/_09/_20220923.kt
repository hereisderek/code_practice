@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._09

import Testable
import utils.assertEqualTo
import utils.deepClone
import utils.runTimedTests
import utils.tupleOf
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


// 743. Network Delay Time
// https://leetcode.com/problems/network-delay-time/
private interface Leetcode_743_3 {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(
                    arrayOf(intArrayOf(1,2,1),intArrayOf(2,1,3),), 2, 2
                ) to 3,
                tupleOf(
                    arrayOf(
                        intArrayOf(2,1,1),
                        intArrayOf(2,3,1),
                        intArrayOf(3,4,1),
                    ), 4, 2
                ) to 2,
                tupleOf(
                    arrayOf(
                        intArrayOf(1,2,1),
                        intArrayOf(2,3,2),
                        intArrayOf(1,3,2),
                    ), 3, 1
                ) to 2,
                tupleOf(
                    arrayOf(
                        intArrayOf(1,2,1),
                    ), 2, 1
                ) to 1,
                tupleOf(
                    arrayOf(intArrayOf(1,2,1),), 2, 2
                ) to -1,

                )
            listOf(
                M1()::networkDelayTime,
            ).runTimedTests(tests) { a, b ->
                val array = a.first.deepClone()
                val res = invoke(array, a.second, a.third)
                res.assertEqualTo(b)
            }
        }
    }


    // Runtime: 809 ms, faster than 47.14% of Kotlin online submissions for Network Delay Time.
    // Memory Usage: 90.6 MB, less than 40.00% of Kotlin online submissions for Network Delay Time.
    // https://leetcode.com/submissions/detail/806422130/
    private class M1 : Leetcode_743_3 {
        override fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
            val map = HashMap<Int, ArrayList<IntArray>>()
            for (t in times) {
                if (map[t[0]] == null) map[t[0]] = ArrayList()
                map[t[0]]!!.add(t)
            }

            // weight, node
            val pq = PriorityQueue<Pair<Int, Int>>(){ a, b -> a.first - b.first }
            val visit = HashSet<Int>()
            visit.add(k)
            pq.offer(0 to k)

            while(pq.isNotEmpty()) {
                val (weight, node) = pq.poll()
                visit.add(node)

                map[node]?.forEach{
                    if (!visit.contains(it[1])) {
                        pq.offer(weight + it[2] to it[1])
                    }
                }
                if (visit.size == n) return weight
            }
            return -1
        }
    }
}

// 977. Squares of a Sorted Array
// https://leetcode.com/problems/squares-of-a-sorted-array/
private interface LC_977 {
    fun sortedSquares(nums: IntArray): IntArray

    // Runtime: 670 ms, faster than 55.21% of Kotlin online submissions for Squares of a Sorted Array.
    // Memory Usage: 72.6 MB, less than 42.29% of Kotlin online submissions for Squares of a Sorted Array.
    // https://leetcode.com/submissions/detail/806461198/
    private class M1 : LC_977 {
        override fun sortedSquares(nums: IntArray): IntArray {
            if (nums.isEmpty()) return nums
            var l = 0
            var r = nums.lastIndex
            var cur = r
            val res = IntArray(nums.size)

            while (l <= r) {
                val _l = nums[l] * nums[l]
                val _r = nums[r] * nums[r]

                res[cur] = if (_l > _r) {
                    l++
                    _l
                } else {
                    r--
                    _r
                }
                cur--
            }
            return res
        }
    }
}

// 209. Minimum Size Subarray Sum
// https://leetcode.com/problems/minimum-size-subarray-sum/
private interface LC_209 {
    fun minSubArrayLen(target: Int, nums: IntArray): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(7, intArrayOf(2,3,1,2,4,3), 2),
                tupleOf(4, intArrayOf(1,4,4), 1),
                tupleOf(11, intArrayOf(1,1,1,1,1,1,1,1,1,), 0),
            )

            listOf(
                M1()::minSubArrayLen,
                M2()::minSubArrayLen,
            ).runTimedTests(tests) { a, b, output ->
                invoke(a, b).assertEqualTo(output)
            }
        }
    }

    // Time Limit Exceeded
    // https://leetcode.com/submissions/detail/806811197/
    private class M1 : LC_209 {
        override fun minSubArrayLen(target: Int, nums: IntArray): Int {

            if (nums.isEmpty()) return 0
            val sum = IntArray(nums.size)

            for (i in 0 until nums.size) {
                sum[i] = if (i == 0) nums[i] else nums[i] + sum[i-1]
            }
            if (sum.last() < target) return 0


            for (i in 1 .. nums.size) { // window size
                for (j in i-1 until nums.size) {
                    val pre = if (j-i < 0) 0 else sum[j-i]
                    if (sum[j] - pre >= target) return i
                }
            }
            return 0
        }
    }

    // Runtime: 588 ms, faster than 32.30% of Kotlin online submissions for Minimum Size Subarray Sum.
    // Memory Usage: 65.3 MB, less than 57.76% of Kotlin online submissions for Minimum Size Subarray Sum.
    // https://leetcode.com/submissions/detail/807183925/
    private class M2 : LC_209 {
        override fun minSubArrayLen(target: Int, nums: IntArray): Int {
            var sum = 0
            var res = Int.MAX_VALUE
            var l = 0
            for (r in 0 until nums.size) {
                sum += nums[r]
                while(sum >= target&&l<=r) {
                    // println("l:$l r:$r new:${r-l+1} res:$res sum:$sum")
                    res = Math.min(r-l+1, res)
                    l++
                    sum -= nums[l-1]
                }
            }
            return if (res == Int.MAX_VALUE) 0 else res

        }
    }

}


val _202209023 = listOf<Testable>(
    // Leetcode_743_3,
    LC_209
)