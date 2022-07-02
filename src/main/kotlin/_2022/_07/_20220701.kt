@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.assertEqualTo
import utils.forEachPair
import utils.runTimedTests

// 42. Trapping Rain Water
// https://leetcode.com/problems/trapping-rain-water/
private interface Leetcode_42 {
    fun trap(height: IntArray): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1) to 6,
                intArrayOf(4,2,0,3,2,5) to 9,
            )
            listOf(
                M1()::trap,
            ).runTimedTests {
                tests.forEachPair { first, second ->
                    invoke(first).assertEqualTo(second, lazyMsg = { a, b ->
                        "assertion failed for input:${first.joinToString()}, exp:$a vs actual:$b"
                    })
                }
            }
        }
    }

    // Two pointers
    private class M1 : Leetcode_42 {
        override fun trap(height: IntArray): Int {
            if (height.size <= 1) return 0

            var left = 0
            var right = 1
            var sum = 0
            while (left < height.size - 1) {
                while (left < right) {
                    right++
                }
                right--
                for (i in left .. right) {
                    val v = height[i]

                }
                left = right
                right++
            }


            TODO("Not yet implemented")
        }
    }
}

fun main() {
    Leetcode_42.test()
}