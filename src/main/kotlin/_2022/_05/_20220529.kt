@file:Suppress("ClassName")
package _2022._05

import utils.assertEqual
import utils.runTimedTests
import java.util.Stack
import kotlin.math.min


fun main() {
    Leetcode_647.test()
    // Leetcode_5.test()
}


// https://leetcode.com/problems/longest-palindromic-substring/
interface Leetcode_647 {
    fun countSubstrings(s: String): Int

    companion object {
        fun test() {
            listOf(
                S1()::countSubstrings,
                S2()::countSubstrings,
                S3()::countSubstrings,
                S0()::countSubstrings
            ).runTimedTests() {
                invoke("abc").assertEqual(3)
                invoke("aaa").assertEqual(6)
            }
        }
    }
    // sample 128 ms submission
    private class S0 : Leetcode_647 {
        override fun countSubstrings(s: String): Int {
            var answer = 0
            for (i in 0 until s.length) {
                answer += countPalindromesAroundLetter(s, i, i)
                answer += countPalindromesAroundLetter(s, i, i + 1)
            }
            return answer
        }

        private fun countPalindromesAroundLetter(s: String, i: Int, j: Int): Int {
            var answer = 0
            var left = i
            var right = j

            while (left >= 0 && right < s.length) {
                if (s[left] != s[right]) {
                    break
                }

                answer++
                left--
                right++
            }
            return answer
        }
    }

    // https://leetcode.com/submissions/detail/708968403/
    // 130 / 130 test cases passed.
    // Status: Accepted
    // Runtime: 662 ms
    // Memory Usage: 35.4 MB
    private class S1 : Leetcode_647 {
        private fun isPalindromic(s: IntArray) : Boolean {
            for (i in 0 until s.size / 2) {
                val endIndex = s.size - i - 1
                if (s.size % 2 == 1 && endIndex - i == 1) break
                if (s[i] != s[endIndex]) return false
            }
            return true
        }

        override fun countSubstrings(s: String): Int {
            if (s.isEmpty()) return 0
            if (s.length == 1) return 1


            var counter = 0
            val chars = IntArray(s.length) { s[it] - 'a' }
            for (index in chars.indices) {
                for (length in 1 .. s.length - index) {
                    val array = IntArray(length){
                        chars[it + index]
                    }
                    val valid = isPalindromic(array)
                    // println("index:$index, length:$length, array:${array.joinToString{ ('a' + it).toString() }} valid:$valid")
                    if (valid) counter++
                }
            }

            return counter
        }
    }

    private class S2 : Leetcode_647 {
        override fun countSubstrings(s: String): Int {
            if (s.isEmpty()) return 0
            if (s.length == 1) return 1

            val array = Array<BooleanArray>(s.length){
                BooleanArray(s.length) {
                    false
                }
            }
            var counter = 0
            for (i in s.length - 1 downTo 0) {
                for (j in i until s.length) {
                    if (s[i] == s[j]) {
                        if (j - i <= 1) {
                            counter++
                            array[i][j] = true
                        } else {
                            if (s[i+1] == s[j-1]) {
                                counter++
                                array[i][j] = true
                            }
                        }
                    }
                }
            }
            return counter
        }
    }

    private class S3 : Leetcode_647 {
        override fun countSubstrings(s: String): Int {
            var counter = 0
            for (i in s.indices) {
                counter += check(s, i, i, s.length)
                counter += check(s, i, i + 1, s.length)
            }
            return counter
        }

        private fun check(s: String, i: Int, j: Int, size: Int) : Int {
            var i = i
            var j = j
            var counter = 0
            while(i >= 0 && j < size && s[i] == s[j]){
                i--
                j++
                counter++
            }
            return counter
        }
    }

}



/*
private class Leetcode_5 {
    companion object {
        fun test() = Leetcode_5().apply {
            longestPalindrome("babad").also{
                assert(it == "bab" || it == "aba")
            }

            assert(longestPalindrome("cbbd") == "bb")
        }
    }
    fun longestPalindrome(index: Int, j: Int, chars: IntArray, result: String, results: MutableSet<String>) {
        // if (i == j)
    }

    // stack
    fun longestPalindrome(s: String): String {
        if (s.length <= 1) return s

        val chars = IntArray(s.length) { s[it] - 'a' }
        val reverseChars = chars.reversedArray()
        val map = mutableMapOf<Int, Int>()

        for (i in chars.indices) {
            val stack = Stack<Int>()
            for (j in 0 until min(i, chars.size)) {

            }
        }
    }
}*/