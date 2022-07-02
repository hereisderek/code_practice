@file:Suppress("DuplicatedCode", "ClassName")

package _2022._06

import Testable
import utils.*

// https://leetcode.com/problems/spiral-matrix-ii/
private interface Leetcode_59 {
    fun generateMatrix(n: Int): Array<IntArray>
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                3 to arrayOf(
                    intArrayOf(1,2,3),
                    intArrayOf(8,9,4),
                    intArrayOf(7,6,5),
                ),
                // [[1,2,3,4],[12,13,14,5],[11,16,15,6],[10,9,8,7]]
                4 to arrayOf(
                    intArrayOf(1,2,3,4),
                    intArrayOf(12,13,14,5),
                    intArrayOf(11,16,15,6),
                    intArrayOf(10,9,8,7),
                ),
            )
            listOf(
                M1()::generateMatrix,
            ).runTimedTests {
                tests.forEachPair { first, second ->
                    invoke(first).assertEqualTo(
                        second,
                        checker = {a, b -> a.matrixEquals(b) },
                        toStr = { toMatrixStr() }
                    )
                }
            }
        }
    }

    private class M1 : Leetcode_59 {

        override fun generateMatrix(n: Int): Array<IntArray> {
            val result = Array(n) {
                IntArray(n)
            }
            var side = n
            var counter = 2
            var direction = 0

            // coord[m][n]
            var x: Int = 0
            var y: Int = 0
            result[0][0] = 1

            while (counter < n * n) {
                for (sizeCounter in 0 until 3) {
                    direction %= 4

                    for (s in (side - 1) downTo  1 ) {
                        when(direction) {
                            0 -> y++
                            1 -> x++
                            2 -> y--
                            else -> x--
                        }

                        result[x][y] = counter
                        counter++
                        if (counter > n * n) break
                    }

                    if (side == 0) return result

                    if (counter > n * n) break
                    direction++
                }
                side--
            }
            return result
        }
    }
}
// 3. Longest Substring Without Repeating Characters
// https://leetcode.com/problems/longest-substring-without-repeating-characters/
private interface Leetcode_3 {
    fun lengthOfLongestSubstring(s: String): Int
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                "au" to 2,
                "abcabcbb" to 3,
                "bbbbb" to 1,
                "pwwkew" to 3
            )
            listOf(
                M1()::lengthOfLongestSubstring,
                S1()::lengthOfLongestSubstring,
            ).runTimedTests {
                tests.forEachPair { first, second ->
                    invoke(first).assertEqualTo(second) { exp, act ->
                        "assertion failed for input:$first, exp:$exp, act:$act"
                    }
                }

            }
        }
    }

    private class M1 : Leetcode_3 {
        /**
         * @param end: exclusive
         */
        private fun hasChar(s: String, first: Int, end: Int, check: Int) : Int {
            val c = s.getOrNull(check) ?: return -1
            for (i in first until end) {
                if (s[i] == c) return i
            }
            return -1
        }

        // https://leetcode.com/submissions/detail/730847914/
        // Runtime: 700 ms, faster than 11.84% of Kotlin online submissions for Longest Substring Without Repeating Characters.
        // Memory Usage: 39.7 MB, less than 63.52% of Kotlin online submissions for Longest Substring Without Repeating Characters.
        override fun lengthOfLongestSubstring(s: String): Int {
            if (s.length <= 1) return s.length

            var leftIndex = 0
            var rightIndex = 1
            var longest = 1
            while (rightIndex != s.length) {
                when(val existIndex = hasChar(s, leftIndex, rightIndex, rightIndex)){
                    -1 -> {
                        val length = rightIndex - leftIndex + 1
                        if (length > longest) {
                            longest = length
                        }
                        rightIndex++

                    }
                    else -> {
                        leftIndex = existIndex + 1
                        rightIndex = leftIndex + 1
                    }
                }
            }
            return longest
        }
    }

    private class S1 : Leetcode_3 {
        override fun lengthOfLongestSubstring(s: String): Int {
            var left = 0
            var right = 0
            val map = HashMap<Char, Int>()
            var max = 0
            while (right < s.length) {
                val c = s[right]
                right++
                map[c] = (map[c] ?: 0) + 1
                while (map[c]!! > 1) {
                    val d = s[left]
                    left++
                    map[d] = map[d]!! - 1
                }
                max = Math.max(max, (right - left))
            }
            return max
        }
    }


}

fun main() {
    // Leetcode_59.test()
    Leetcode_3.test()
}

