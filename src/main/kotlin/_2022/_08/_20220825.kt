@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.assertEqualTo
import utils.runTimedTests


// 131. Palindrome Partitioning
// https://leetcode.com/problems/palindrome-partitioning/
private interface LC_131 {
    fun partition(s: String): List<List<String>>

    // not working
    private class M1 : LC_131 {
        val res = ArrayList<ArrayList<String>>()
        val cur = ArrayList<String>()

        override fun partition(s: String): List<List<String>> {
            dfs(s, 0, 0)
            return res
        }
        fun dfs(s: String, left: Int, right: Int) {
            if (left > right) return
            if (right >= s.length) return

            if (right == s.length-1) {
                if (cur.isNotEmpty()) {
                    res.add(ArrayList(cur))
                    cur.clear()
                }
            }

            if (isPalindrome(s, left, right)) cur.add(s.substring(left, right+1))

            for (r in right+1 until s.length-1){
                dfs(s, left, r)
            }

            dfs(s, left+1, left+2)
        }
        fun isPalindrome(s: String, left: Int, right: Int) : Boolean {
            if (left < 0 || right >= s.length || left > right) return false
            if (left == right) return true

            var left = left
            var right = right

            while(left <= right) {
                if (s[left] != s[right]) return false
                left--
                right++
            }
            return true
        }
    }
}


// 17. Letter Combinations of a Phone Number
// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
private interface LC_17 {
    fun letterCombinations(digits: String): List<String>

    private class M1 : LC_17 {
        val map = arrayOf(
            charArrayOf(), // 0
            charArrayOf(), // 1
            "abc".toCharArray(),
            "def".toCharArray(),
            "ghi".toCharArray(),
            "jkl".toCharArray(),
            "mno".toCharArray(),
            "pqrs".toCharArray(),
            "tuv".toCharArray(),
            "wxyz".toCharArray()
        )

        override fun letterCombinations(digits: String): List<String> {
            var res = ArrayList<String>()
            val list = digits.map { map[it-'0'] }

            for (d in list) {
                if (res.isEmpty()) {
                    res.addAll(d.map { it.toString() })
                } else {
                    res.flatMap { s ->
                        d.map { "$s$it" }
                    }.also {
                        res = ArrayList(it)
                    }
                }
            }
            return res
        }
    }
}


val _20220825 = listOf<Testable>(

)