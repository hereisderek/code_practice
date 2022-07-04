@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.*


// 128. Longest Consecutive Sequence
// https://leetcode.com/problems/longest-consecutive-sequence/
private interface Leetcode_128 {
    fun longestConsecutive(nums: IntArray): Int
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(100,4,200,1,3,2) to 4,
                intArrayOf(100,4,200,1,3,2,6) to 4,
                intArrayOf(0,3,7,2,5,8,4,6,0,1) to 9,
            )
            listOf(
                M1()::longestConsecutive,
            ).runTimedTests {
                tests.forEachPair { first, second ->
                    invoke(first).assertEqualTo(second)
                }

            }
        }
    }

    // https://leetcode.com/submissions/detail/737828338/
    // Runtime: 647 ms, faster than 86.18% of Kotlin online submissions for Longest Consecutive Sequence.
    // Memory Usage: 73 MB, less than 71.43% of Kotlin online submissions for Longest Consecutive Sequence.
    private class M1 : Leetcode_128 {
        override fun longestConsecutive(nums: IntArray): Int {
            val map = HashSet<Int>()
            nums.forEach { map.add(it) }
            var max = 0
            map.forEach {
                if (!map.contains(it - 1)) {
                    var counter = 0
                    while (map.contains(counter+it)) {
                        counter++
                    }
                    max = Math.max(max, counter)
                }
            }

            return max
        }
    }
}

// 121. Best Time to Buy and Sell Stock
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
private interface Leetcode_121 {
    fun maxProfit(prices: IntArray): Int
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(7,1,5,3,6,4) to 5,
                intArrayOf(7,6,4,3,1) to 0,

            )
            listOf(
                M1()::maxProfit,
                S1()::maxProfit,
            ).runTimedTests {
                tests.forEachPair { first, second ->
                    invoke(first).assertEqualTo(second)
                }

            }
        }
    }

    // https://leetcode.com/submissions/detail/737024419/
    // Runtime: 586 ms, faster than 96.21% of Kotlin online submissions for Best Time to Buy and Sell Stock.
    // Memory Usage: 54.2 MB, less than 98.11% of Kotlin online submissions for Best Time to Buy and Sell Stock.
    private class M1 : Leetcode_121 {
        override fun maxProfit(prices: IntArray): Int {
            if (prices.size <= 1) return 0
            val maxPrices = IntArray(prices.size)
            val minPrices = IntArray(prices.size)
            var max = prices.last()

            for(i in prices.size - 1 downTo 0) {
                max = Math.max(max, prices[i])
                maxPrices[i] = max
            }

            var min = prices.first()
            for (i in prices.indices) {
                min = Math.min(min, prices[i])
                minPrices[i] = min
            }
            var res = 0
            for (i in prices.indices){
                val profit = maxPrices[i] - minPrices[i]
                res = Math.max(profit, res)
            }
            return res
        }
    }

    // Two pointers
    private class S1 : Leetcode_121 {
        override fun maxProfit(prices: IntArray): Int {
            if (prices.size <= 1) return 0
            var l = 0
            var r = 1
            var res = 0
            while (r < prices.size) {
                if (prices[r] > prices[l]) {
                    val prof = prices[r] - prices[l]
                    if (prof > res) { res = prof }
                } else {
                    l = r
                }
                r++
            }
            return res
        }
    }
}

// 424. Longest Repeating Character Replacement
// https://leetcode.com/problems/longest-repeating-character-replacement/
private interface Leetcode_424 {
    fun characterReplacement(s: String, k: Int): Int
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf("ABAB", 2, 4),
                tupleOf("AABABBA", 1, 4),
            )
            listOf(
                // M1()::characterReplacement,
                S1()::characterReplacement,
            ).runTimedTests {
                tests.forEachTuple { first, second, third ->
                    invoke(first, second).assertEqualTo(third)
                }

            }
        }
    }


    // wrong
    private class M1 : Leetcode_424 {
        private fun isValid(array: IntArray, k: Int) : Boolean {
            var freq = -1

            fun mostFreq(array: IntArray) : Int {
                var index = 0
                for (i in array.indices) {
                    if (freq < array[i]) {
                        freq = array[i]
                        index = i
                    }
                }
                return index
            }

            val mostFreq = mostFreq(array)
            var others = 0
            for (i in array.indices) {
                if (i != mostFreq) {
                    others += array[i]
                }
            }
            return others <= k
        }

        override fun characterReplacement(s: String, k: Int): Int {
            if (s.length <= 1) return s.length

            var left = 0
            var right = 0
            val array = IntArray(26){ 0 }
            var res = 0


            while (right < s.length) {
                val c = s[right]
                array[c - 'A'] += 1
                if (isValid(array, k)) {
                    right++
                    res = Math.max(res, (right - left + 1))
                } else {
                    left++
                }
            }
            return res
        }
    }

    private class S1 : Leetcode_424{
        override fun characterReplacement(s: String, k: Int): Int {
            if (s.length <= 1) return s.length
            val count = IntArray(26){ 0 }
            var res = 0
            var l = 0
            var maxF = 0
            for (r in s.indices) {
                val c = s[r]
                count[c - 'A']++
                maxF = Math.max(maxF, count[c - 'A'])

                // check if valid
                if ((r - l + 1) - maxF > k) {
                    count[s[l] - 'A']--
                    l++
                }

                res = Math.max(res, (r - l + 1))
            }
            return res
        }
    }
}

// 567. Permutation in String
// https://leetcode.com/problems/permutation-in-string/
private interface Leetcode_567 {
    fun checkInclusion(s1: String, s2: String): Boolean

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf("ab", "eidbaooo", true),
                tupleOf("ab", "eidboaoo", false),
                tupleOf("ab", "ab", true),
                tupleOf("ab", "ba", true),
                tupleOf("adc", "dcda", true),
            )
            listOf(
                M2()::checkInclusion,
            ).runTimedTests {
                tests.forEachTuple { first, second, third ->
                    invoke(first, second).assertEqualTo(third)
                }
            }
        }
    }

    // misunderstood the question, thought "permutation" was reversing order
    @Deprecated("not working", level = DeprecationLevel.ERROR)
    private class M1 : Leetcode_567 {
        override fun checkInclusion(s1: String, s2: String): Boolean {
            var p1 = s1.length - 1
            var p2 = 0
            while (p2 < s2.length) {
                while (p1 >= 0 && p2 < s2.length && s1[p1] == s2[p2]) {
                    p1--
                    p2++
                }
                if (p1 == -1) return true
                p1 = s1.length - 1
                p2++
            }
            return false
        }
    }


    // https://leetcode.com/submissions/detail/737850787/
    // Runtime: 206 ms, faster than 96.21% of Kotlin online submissions for Permutation in String.
    // Memory Usage: 35.1 MB, less than 99.53% of Kotlin online submissions for Permutation in String.
    private class M2 : Leetcode_567 {
        override fun checkInclusion(s1: String, s2: String): Boolean {
            val s1Map = IntArray(26)
            s1.forEach {
                s1Map[it - 'a'] ++
            }

            val s2Map = IntArray(26)
            var left = 0
            var right = 0
            while (right < s2.length) {
                val c = s2[right]
                s2Map[c - 'a']++

                if (s1.length == (right - left + 1)) {
                    if (s1Map.contentEquals(s2Map)) return true
                }

                right++

                if ((right - left + 1) > s1.length) {
                    s2Map[s2[left] - 'a']--
                    left++
                }
            }
            return false
        }
    }

}

fun main() {
    // Leetcode_128.test()
    // Leetcode_121.test()
    // Leetcode_424.test()
    Leetcode_567.test()
}