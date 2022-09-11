@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._09

import Testable


// 6
// https://leetcode.com/problems/zigzag-conversion/submissions/
private interface LC_6 {
    fun convert(s: String, numRows: Int): String

    // Runtime: 269 ms, faster than 92.05% of Kotlin online submissions for Zigzag Conversion.
    // Memory Usage: 36.4 MB, less than 97.73% of Kotlin online submissions for Zigzag Conversion.
    // https://leetcode.com/submissions/detail/792078864/
    private class M1 : LC_6 {
        override fun convert(s: String, numRows: Int): String {
            val cycle = Math.max(numRows * 2 - 2, 1) // for
            val builder = StringBuilder()

            for (i in (numRows-1) downTo 0) {
                for (j in (numRows-1) until s.length + cycle step cycle) {
                    if ((j-i) < s.length) builder.append(s[j-i])
                    if (i != 0 && i != (numRows-1)) {
                        if ((j+i) < s.length) builder.append(s[j+i])
                    }
                }
            }
            return builder.toString()
        }
    }

    // leetcode.cn
    private class S2 : LC_6{
        override fun convert(s: String, numRows: Int): String {
            if (numRows < 2) return s
            val rows: MutableList<StringBuilder> = ArrayList()
            for (i in 0 until numRows) rows.add(StringBuilder())
            var i = 0
            var flag = -1
            for (c in s.toCharArray()) {
                rows[i].append(c)
                if (i == 0 || i == numRows - 1) flag = -flag
                i += flag
            }
            val res = StringBuilder()
            for (row in rows) res.append(row)
            return res.toString()
        }
    }

}


// 7. Reverse Integer
// https://leetcode.com/problems/reverse-integer/
private interface LC_7 {
    fun reverse(x: Int): Int

    // input: 1534236469 output: 1056389759 expected: 0
    // not working if overflow
    private class M1 : LC_7  {
        override fun reverse(x: Int): Int {
            val negative = x < 0
            var res = 0
            var x = Math.abs(x)
            while (x > 0) {
                val r = x % 10
                x /= 10
                res = res * 10 + r
            }
            return if (negative) -res else res
        }
    }

    // https://leetcode.cn/problems/reverse-integer/solution/zheng-shu-fan-zhuan-by-leetcode-solution-bccn/
    private class S1 : LC_7   {
        override fun reverse(x: Int): Int {
            var x = x
            var rev = 0
            while (x != 0) {
                val pop = x % 10
                x /= 10
                if (rev > Int.MAX_VALUE / 10 || (rev == Int.MAX_VALUE / 10 && pop > 7)) return 0
                if (rev < Int.MIN_VALUE / 10 || (rev == Int.MIN_VALUE / 10 && pop < -8)) return 0
                rev = rev * 10 + pop
            }
            return rev
        }
    }
}



val _20220906 = listOf<Testable>(

)