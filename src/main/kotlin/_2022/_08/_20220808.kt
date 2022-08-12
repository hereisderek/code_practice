@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.assertEqualTo
import utils.runTimedTests
import utils.tupleOf


// 91. Decode Ways
// https://leetcode.com/problems/decode-ways/
private interface Leetcode_91 {
    fun numDecodings(s: String): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                "12" to 2,
                "226" to 3,
                "06" to 0,
                "111111111111111111111111111111111111111111111" to 1836311903
            )
            listOf(
                M1()::numDecodings,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    private class M1 : Leetcode_91 {
        var counter = 0

        override fun numDecodings(s: String): Int {
            counter = 0
            dfs(s.toCharArray(), 0)
            return counter
        }
        fun dfs(s: CharArray, index: Int) {
            if (index >= s.size) {
                counter++
                return
            }

            if (index <= s.size - 1) {
                val c1 = s[index]
                if (c1 == '0') return

                if (c1 >= '0' && c1 <= '9') {
                    dfs(s, index+1)
                }

                if (index < s.size - 1) {
                    val c2 = s[index+1]
                    if (c1 == '1' && c2 >= '0' && c2 <= '9') {
                        dfs(s, index+2)
                    }
                    if (c1 == '2' && c2 >= '0' && c2 <= '6') {
                        dfs(s, index+2)
                    }
                }
            }
        }
    }

    private class S1 : Leetcode_91 {

        override fun numDecodings(s: String): Int {
            TODO("Not yet implemented")
        }
    }
}

// 322. Coin Change
// https://leetcode.com/problems/coin-change/
private interface Leetcode_322 {
    fun coinChange(coins: IntArray, amount: Int): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(
                    intArrayOf(1,2,5), 11, 3
                ),
                tupleOf(
                    intArrayOf(2), 3, -1
                ),
                tupleOf(
                    intArrayOf(1), 0, 0
                ),
            )
            listOf(
                M1()::coinChange,
            ).runTimedTests(tests) { a, b, c ->
                invoke(a,b).assertEqualTo(c)
            }
        }
    }

    private class M1 : Leetcode_322 {
        var count = 0
        override fun coinChange(coins: IntArray, amount: Int): Int {
            coins.sortDescending()
            return if (dfs(coins, amount, 0)) count else -1
        }

        fun dfs(coins: IntArray, amount: Int, index: Int) : Boolean {
            if (amount < 0 || index >= coins.size) return false
            if (amount == 0) return true
            val deno = coins[index]
            return if (amount < deno) {
                var i = index
                while(
                    i < coins.size && !dfs(coins, amount, i)
                ){ i++ }
                i != coins.size
            } else {
                count++
                dfs(coins, amount-deno, index)
            }
        }
    }
}


val _20220808 = listOf<Testable>(
    // Leetcode_91,
    Leetcode_322,
)