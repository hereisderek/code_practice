@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._09

import Testable



// 8. String to Integer (atoi)
// https://leetcode.com/problems/string-to-integer-atoi/
// stupid ass question, don't waste your time
private interface LC_8 {
    fun myAtoi(s: String): Int
    private class M1 : LC_8 {
        override fun myAtoi(s: String): Int {
            var i = 0
            var res = 0
            var positive: Boolean? = null
            var started = false
            check@while(i < s.length) {
                when(val c = s[i]) {
                    '-' -> {
                        if (started) break@check
                        started = true
                        if (positive == null) positive = false else return 0
                    }
                    '+' -> {
                        if (started) break@check
                        started = true
                        if (positive == null) positive = true else return 0
                    }
                    in ('0' .. '9') -> {
                        started = true
                        val value : Int = c - '0'
                        if (
                            res > Int.MAX_VALUE/10
                            || (res == Int.MAX_VALUE/10 && value > if (positive == false) 8 else 7)
                        ) return if (positive==false) Int.MIN_VALUE else Int.MAX_VALUE else {
                            res = res * 10 + value
                        }
                    }
                    ' ' -> if (res != 0 || started) break@check
                    else -> break@check
                }
                i++
            }
            return if (positive != false) res else -res
        }
    }
}



// 56. Merge Intervals
// https://leetcode.com/problems/merge-intervals/
//
private interface LC_56 {
    fun merge(intervals: Array<IntArray>): Array<IntArray>

    // Runtime: 331 ms, faster than 98.10% of Kotlin online submissions for Merge Intervals.
    // Memory Usage: 44.5 MB, less than 94.92% of Kotlin online submissions for Merge Intervals.
    // https://leetcode.com/submissions/detail/794433644/
    private class M1 : LC_56 {
        override fun merge(intervals: Array<IntArray>): Array<IntArray> {
            intervals.sortBy{ it[0] }
            if (intervals.size <= 1) return intervals
            var cur = intervals[0]
            val res = ArrayList<IntArray>()
            var a = cur[0]
            var b = cur[1]

            loop@for (i in 1 until intervals.size) {
                var j = intervals[i]
                val _a = j[0]
                val _b = j[1]

                when{
                    b < _a -> {
                        res.add(intArrayOf(a, b))
                        a = _a
                        b = _b
                    }

                    b < _b -> { b = _b }

                    else -> continue@loop
                }
            }
            res.add(intArrayOf(a, b))
            return res.toTypedArray()
        }
    }
}




val _202209007 = listOf<Testable>(

)