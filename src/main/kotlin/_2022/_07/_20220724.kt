@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.assertEqualTo
import utils.runTimedTests
import utils.tupleOf
import java.util.*

// 739. Daily Temperatures
// https://leetcode.com/problems/daily-temperatures/
private interface Leetcode_739 {
    fun dailyTemperatures(temperatures: IntArray): IntArray
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(73,74,75,71,69,72,76,73) to intArrayOf(1,1,4,2,1,1,0,0),
                intArrayOf(30,40,50,60) to intArrayOf(1,1,1,0),
                intArrayOf(30,60,90) to intArrayOf(1,1,0),
            )
            listOf(
                S2()::dailyTemperatures,
            ).runTimedTests(tests) { i, o ->
                invoke(i).assertEqualTo(o)
            }
        }
    }

    // not working
    private class M1 : Leetcode_739 {
        override fun dailyTemperatures(temperatures: IntArray): IntArray {
            val result = IntArray(temperatures.size)
            for (i in temperatures.size - 1 downTo 0) {
                val temp = temperatures[i]
                var counter = 0
                while (i != temperatures.size && i + counter + 1 < temperatures.size && i + counter + 1 >= 0) {
                    val p = i + counter + 1
                    val pTemp = temperatures[p]
                    if (temp > pTemp) {
                        counter ++
                        break
                    } else {
                        val diff = result[p]
                        counter += if (diff == 0) 1 else diff
                    }
                }
                result[temperatures.size - i - 1] = counter
            }

            return result
        }
    }

    private class S2 : Leetcode_739 {
        override fun dailyTemperatures(temperatures: IntArray): IntArray {
            if (temperatures.size <= 1) return intArrayOf(0)
            val stack = Stack<Pair<Int, Int>>()
            val result = IntArray(temperatures.size)

            stack.push(0 to temperatures[0])
            for (i in 1 until temperatures.size) {
                val temp = temperatures[i]
                while (stack.isNotEmpty() && stack.peek().second < temp) {
                    val pop = stack.pop()
                    result[pop.first] = i - pop.first
                }
                stack.push(i to temp)
            }
            return result
        }
    }
}

// 853. Car Fleet
// https://leetcode.com/problems/car-fleet/
private interface Leetcode_853 {
    fun carFleet(target: Int, position: IntArray, speed: IntArray): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(12, intArrayOf(10,8,0,5,3), intArrayOf(2,4,1,1,3)) to 3,
                tupleOf(10, intArrayOf(3), intArrayOf(3)) to 1,
                tupleOf(100, intArrayOf(0,2,4), intArrayOf(4,2,1)) to 1,
            )
            listOf(
                S1()::carFleet,
            ).runTimedTests(tests) { a, b ->
                invoke(a.first, a.second, a.third).assertEqualTo(b)
            }
        }
    }

    // Runtime: 1109 ms, faster than 83.33% of Kotlin online submissions for Car Fleet.
    // Memory Usage: 114.7 MB, less than 71.43% of Kotlin online submissions for Car Fleet.
    // https://leetcode.com/submissions/detail/755904041/
    private class S1 : Leetcode_853 {
        override fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
            val sorted = position.zip(speed).sortedByDescending { it.first }
            val stack = Stack<Pair<Pair<Int, Int>, Double>>()

            for (pair in sorted) {
                val (p, v) = pair
                val t = (target - p) / v.toDouble()
                if (stack.isEmpty() || stack.peek().second < t) {
                    stack.push(pair to t)
                }
            }
            return stack.size
        }
    }
}

fun main() {
    // Leetcode_739.test()
    Leetcode_853.test()
}