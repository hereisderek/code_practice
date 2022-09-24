@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._09

import Testable
import java.util.*


// 322. Coin Change
// https://leetcode.com/problems/coin-change/
private interface LC_322 {
    fun coinChange(coins: IntArray, amount: Int): Int

    // wrong
    // [186,419,83,408] 6249 expected:20 actual:26
    private class M1 : LC_322 {
        override fun coinChange(coins: IntArray, amount: Int): Int {
            coins.sortDescending()
            var counter = 0
            var amount = amount

            fun dp(i: Int) : Boolean {
                if (i >= coins.size) return false
                if (amount == 0) return true
                if (amount < 0) {
                    return false
                }

                counter++
                for (_i in i until coins.size) {
                    amount -= coins[_i]
                    if (dp(_i)) return true
                    amount += coins[_i]
                }

                counter--
                return false
            }

            return if (dp(0)) counter else -1
        }
    }

    // improved upon M1, now brute force all the possibilities
    // however results in timeout
    private class M2 : LC_322 {
        override fun coinChange(coins: IntArray, amount: Int): Int {
            coins.sortDescending()
            var counter = 0
            var amount = amount
            var res = Int.MAX_VALUE

            fun dp(i: Int) : Boolean {
                if (i >= coins.size) return false
                if (amount == 0) {
                    res = Math.min(res, counter)
                    return true
                }
                if (amount < 0) { return false }

                counter++
                for (_i in i until coins.size) {
                    amount -= coins[_i]
                    // if (dp(_i)) return true

                    if (dp(_i)) {
                        amount += coins[_i]
                        continue
                    }
                    amount += coins[_i]
                }

                counter--
                return false
            }

            dp(0)
            return if (res == Int.MAX_VALUE) -1 else res
        }
    }

    // labuladong
    private class S1 : LC_322 {
        lateinit var memo: IntArray
        override fun coinChange(coins: IntArray, amount: Int): Int {
            memo = IntArray(amount + 1)
            // dp 数组全都初始化为特殊值
            Arrays.fill(memo, -666)
            return dp(coins, amount)
        }

        fun dp(coins: IntArray, amount: Int): Int {
            if (amount == 0) return 0
            if (amount < 0) return -1
            // 查备忘录，防止重复计算
            if (memo[amount] != -666) return memo[amount]
            var res = Int.MAX_VALUE
            for (coin in coins) {
                // 计算子问题的结果
                val subProblem = dp(coins, amount - coin)


                // 子问题无解则跳过
                if (subProblem == -1) continue
                // 在子问题中选择最优解，然后加一
                res = Math.min(res, subProblem + 1)
            }
            // 把计算结果存入备忘录
            memo[amount] = if (res == Int.MAX_VALUE) -1 else res
            return memo[amount]
        }
    }
}


val _202209016 = listOf<Testable>(

)