@file:Suppress("DuplicatedCode", "ClassName")

package _2022._05

import utils.assertEqual
import utils.assertThat
import utils.runTimedTests


fun main() {
    // Leetcode_5.test()
    Leetcode_15.test()
}



interface Leetcode_5 {
    fun longestPalindrome(s: String): String

    companion object {
        fun test() = listOf(
            S0()::longestPalindrome,
            S1()::longestPalindrome
        ).runTimedTests {
            invoke("babad").assertEqual("bab", "aba")
            invoke("cbbd") assertEqual "bb"
        }
        // fun test() {
        //     S1().longestPalindrome("babad").assertEqual("bab", "aba")
        //     S1().longestPalindrome("cbbd").assertEqual("bb")
        // }
    }

    // https://leetcode.com/submissions/detail/712753476/
    // Runtime: 352 ms, faster than 70.66% of Kotlin online submissions for Longest Palindromic Substring.
    // Memory Usage: 36.9 MB, less than 81.93% of Kotlin online submissions for Longest Palindromic
    private class S1 : Leetcode_5 {
        private fun submit(i: Int, j: Int, result: IntArray) {
            if ((j - i) > (result[1] - result[0])) {
                result[0] = i
                result[1] = j
            }
        }


        private fun check(s: String, i: Int, j: Int, result: IntArray) {
            val size = s.length
            var i = i
            var j = j

            while(i >= 0 && j < size && s[i] == s[j]){
                submit(i, j, result)
                i--
                j++
            }
        }
        override fun longestPalindrome(s: String): String {
            val results = intArrayOf(-1, -2)
            for (i in s.indices) {
                check(s, i, i, results)
                check(s, i, i + 1, results)
            }
            return s.substring(results[0], results[1] + 1)
        }
    }

    // sample 203 ms submission
    private class S0 : Leetcode_5 {
        override fun longestPalindrome(s: String): String {
            var length = s.length

            var start = 0
            var maxLength = 1
            // Iterate through each substring
            for (i in 0..s.length) {
                var right = i

                // Find all similar characters
                while (right < length && s[i] == s[right]) {
                    right += 1
                }
                // s[i] to s[right - 1] are same characters

                // Expand outwards to find longest palindrome
                var left = i - 1
                while (left >= 0 && right < length && s[left] == s[right]) {
                    left -= 1
                    right += 1
                }
                // s[left + 1] to s[right - 1] is palindrome

                // Length is difference between right/left inclusive
                val subLength = right - left - 1
                if (subLength > maxLength) {
                    maxLength = subLength
                    start = left + 1
                }
            }

            return s.substring(start, start + maxLength)
        }
    }
}


// https://leetcode.com/problems/3sum/
interface Leetcode_15 {
    fun threeSum(nums: IntArray): List<List<Int>>
    companion object {
        fun test() {
            listOf(
                S1()::threeSum
            ).runTimedTests {
                // invoke(intArrayOf(-1,0,1,2,-1,-4)).assertThat {
                //     size == 2 && all { it.size == 3 && it.sum() == 0 }
                // }
                invoke(intArrayOf(-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6)).assertThat {
                    size == 6 && all { it.size == 3 && it.sum() == 0 }
                }
                // invoke(intArrayOf(0)).assertEqual(emptyList())
            }
        }
    }

    private class S1 : Leetcode_15 {
        override fun threeSum(nums: IntArray): List<List<Int>> {
            if (nums.size <= 2) return emptyList()
            val list = mutableListOf<MutableList<Int>>()
            for (i in 0 until nums.size - 2) {
                for (j in i + 1 until nums.size - 1) {
                    for (k in j + 1 until nums.size) {
                        val a = nums[i]
                        val b = nums[j]
                        val c = nums[k]
                        if (a + b + c == 0) {
                            if (
                                !list.any { it.contains(a) && it.contains(b) }
                            ) {
                                list.add(mutableListOf(a, b, c))
                            } else {
                                println("skipping [$a $b $c]")
                            }
                        }
                    }
                }
            }

            return list
        }
    }
}