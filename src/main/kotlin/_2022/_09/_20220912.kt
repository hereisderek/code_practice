@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._09

import Testable


// 338. Counting Bits
// https://leetcode.com/problems/counting-bits/
private interface LC_338 {
    fun countBits(n: Int): IntArray

    // Runtime: 203 ms, faster than 92.67% of Kotlin online submissions for Counting Bits.
    // Memory Usage: 39.1 MB, less than 88.67% of Kotlin online submissions for Counting Bits.
    // https://leetcode.com/submissions/detail/797786948/

    private class M1 : LC_338 {
        val cache = HashMap<Int, Int>()
        override fun countBits(n: Int): IntArray {
            if (n == 0) return intArrayOf(0)
            val res = IntArray(n+1){ 0 }
            var i = 1
            while(true) {
                for (j in 0 until i) {
                    res[i+j] = 1 + res[j]
                    if (i+j>=n) return res
                }
                i *= 2
            }
        }
    }
}


// 91. Decode Ways
// https://leetcode.com/problems/decode-ways/
private interface LC_91 {
    fun numDecodings(s: String): Int
    // private class M1 : LC_91 {
    //
    // }
}



val _202209012 = listOf<Testable>(

)