@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.assertEqualTo
import utils.runTimedTests
import utils.tupleOf

// 134. Gas Station
// https://leetcode.com/problems/gas-station/
private interface Leetcode_134 {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(intArrayOf(2,3,4), intArrayOf(3,4,3), -1),
                tupleOf(intArrayOf(1,2,3,4,5), intArrayOf(3,4,5,1,2), 3),
            )
            listOf(
                // M1()::canCompleteCircuit,
                S1()::canCompleteCircuit,
            ).runTimedTests(tests) { a, b, c ->
                invoke(a, b).assertEqualTo(c)
            }
        }
    }

    private class M1 : Leetcode_134 {
        override fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
            if (gas.isEmpty()) return 0

            val sums = IntArray(gas.size){
                gas[it] - cost[it]
            }

            var l = 0
            var r = 0
            var sum = 0
            var counter = 0

            while(counter<=sums.size) {
                sum += sums[r]
                r = (r+1)%sums.size
                if (sum < 0) {
                    l = r
                    sum = 0
                    counter++
                } else {
                    if (l == r) return r
                }
            }
            return -1
        }
    }

    private class S1 : Leetcode_134 {
        override fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
            if (gas.sum() < cost.sum()) return -1

            var total = 0
            var start = 0
            for (i in gas.indices) {
                total += (gas[i] - cost[i])
                if (total < 0) {
                    total = 0
                    start = i + 1
                }
            }
            return start
        }
    }
}


val _20220811 = listOf<Testable>(
    Leetcode_134,
)