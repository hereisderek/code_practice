@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._09

import Testable
import kotlin.math.pow


// 904. Fruit Into Baskets
// https://leetcode.com/problems/fruit-into-baskets/
private interface LC_904 {
    fun totalFruit(fruits: IntArray): Int

    // two pointers
    // Runtime: 809 ms, faster than 64.10% of Kotlin online submissions for Fruit Into Baskets.
    // Memory Usage: 73.9 MB, less than 58.97% of Kotlin online submissions for Fruit Into Baskets.
    // https://leetcode.com/submissions/detail/796699890/
    private class M1 : LC_904 {
        override fun totalFruit(fruits: IntArray): Int {
            if (fruits.size <= 2) return fruits.size
            var res = 0
            var l = 0
            var r = 0
            var preTypeStartIndex = 0 // the start index of previous type fruit
            val types = HashSet<Int>()
            while (r < fruits.size) {
                types.add(fruits[r])
                if (r > 0 && fruits[r] != fruits[r-1]) {
                    if (types.size > 2 ) {
                        types.clear()
                        types.add(fruits[r])
                        types.add(fruits[r-1])
                        l = preTypeStartIndex
                    }
                    preTypeStartIndex = r
                }
                r++
                res = Math.max(res, (r - l))
            }
            return res
        }
    }
}


// 73. Set Matrix Zeroes
// https://leetcode.com/problems/set-matrix-zeroes/
private interface LC_73 {
    fun setZeroes(matrix: Array<IntArray>): Unit

    // Runtime: 524 ms, faster than 40.43% of Kotlin online submissions for Set Matrix Zeroes.
    // Memory Usage: 59.4 MB, less than 53.90% of Kotlin online submissions for Set Matrix Zeroes.
    // https://leetcode.com/submissions/detail/796748427/
    // space: O(m+n), which, based on the footnote, can be improved to constant
    private class M1 : LC_73 {
        override fun setZeroes(matrix: Array<IntArray>) {
            if (matrix.isEmpty()) return
            val m = matrix[0].size
            val n = matrix.size

            val zero_x = BooleanArray(m) { false }
            val zero_y = BooleanArray(n) { false }

            for (y in 0 until n) {
                for (x in 0 until m) {
                    if (matrix[y][x] == 0) {
                        zero_x[x] = true
                        zero_y[y] = true
                    }
                }
            }

            for (y in 0 until n) for (x in 0 until m) {
                if (zero_x[x] || zero_y[y]) matrix[y][x] = 0
            }
        }
    }
    // https://leetcode.cn/problems/set-matrix-zeroes/solution/ju-zhen-zhi-ling-by-leetcode-solution-9ll7/
    private class M2 : LC_73 {
        override fun setZeroes(matrix: Array<IntArray>) {
            TODO("Not yet implemented")
        }
    }
}


// 202. Happy Number
// https://leetcode.com/problems/happy-number/
private interface LC_202 {
    fun isHappy(n: Int): Boolean

    // Runtime: 159 ms, faster than 93.44% of Kotlin online submissions for Happy Number.
    // Memory Usage: 33.2 MB, less than 98.09% of Kotlin online submissions for Happy Number.
    // https://leetcode.com/submissions/detail/796906046/
    private class M1 : LC_202 {
        val visit = HashSet<Int>()
        override fun isHappy(n: Int): Boolean {
            if (visit.contains(n)) return false
            visit.add(n)
            var r = 0
            var n = n
            while(n > 0) {
                val t = n%10
                n /= 10
                r += (t * t)
            }
            return if (r == 1) true else isHappy(r)
        }
    }
}



// 66. Plus One
// https://leetcode.com/problems/plus-one
private interface LC_66 {
    fun plusOne(digits: IntArray): IntArray
    private class M1 : LC_66 {
        // Runtime: 314 ms, faster than 22.51% of Kotlin online submissions for Plus One.
        // Memory Usage: 35.2 MB, less than 78.01% of Kotlin online submissions for Plus One.
        // https://leetcode.com/submissions/detail/796934256/
        //
        // update:
        // improved by adding line `if (carry == 0) break` (if (carry == 0) return digits)
        // Runtime: 181 ms, faster than 96.68% of Kotlin online submissions for Plus One.
        // Memory Usage: 34.6 MB, less than 94.59% of Kotlin online submissions for Plus One.
        // https://leetcode.com/submissions/detail/796936912/
        override fun plusOne(digits: IntArray): IntArray {
            var carry = 0
            for (i in digits.size - 1 downTo 0) {
                val v = if (i == digits.size - 1) {
                    1 + carry + digits[i]
                } else carry + digits[i]

                carry = v / 10
                digits[i] = v % 10
                if (carry == 0) return digits
            }

            return if (carry != 0) IntArray(digits.size+1) {
                if (it == 0) 1 else digits[it-1]
            } else digits

        }
    }

    private class S1 : LC_66 {
        override fun plusOne(digits: IntArray): IntArray {
            for (i in digits.size-1 downTo 0) {
                digits[i] = ((digits[i] + 1) % 10)
                if (digits[i] != 0) {
                    break
                }
            }
            if (digits[0] != 0) {
                return digits
            }
            return IntArray(digits.size + 1) { i ->
                if (i == 0) {
                    1
                } else {
                    digits[i-1]
                }
            }
        }
    }
}


// 50. Pow(x, n)
// https://leetcode.com/problems/powx-n/
private interface LC_50 {
    fun myPow(x: Double, n: Int): Double

    // just for the lol
    private class M1 : LC_50 {
        override fun myPow(x: Double, n: Int): Double = x.pow(n)
    }

    // Runtime: 296 ms, faster than 17.02% of Kotlin online submissions for Pow(x, n).
    // Memory Usage: 35.1 MB, less than 56.74% of Kotlin online submissions for Pow(x, n).
    // https://leetcode.com/submissions/detail/797045911/
    private class M2 : LC_50 {
        override fun myPow(x: Double, n: Int): Double {
            if (n == 0) return 1.0

            if (n == Int.MIN_VALUE) {
                return myPow(1/x, Int.MAX_VALUE) / x
            }

            val x = if (n < 0) 1/x else x
            val n = if (n < 0) -n else n

            val v = myPow(x, n/2)
            return if (n % 2 == 0) {
                v * v
            } else {
                x * v * v
            }
        }
    }
}


// 43. Multiply Strings
// https://leetcode.com/problems/multiply-strings/
private interface LC_43 {
    fun multiply(num1: String, num2: String): String

    // Runtime: 315 ms, faster than 74.29% of Kotlin online submissions for Multiply Strings.
    // Memory Usage: 35.8 MB, less than 90.71% of Kotlin online submissions for Multiply Strings.
    // https://leetcode.com/submissions/detail/797083738/
    private class M1 : LC_43 {
        override fun multiply(num1: String, num2: String): String {
            val array1 = IntArray(num1.length) {
                val index = num1.length - it - 1
                num1[index] - '0'
            }

            val array2 = IntArray(num2.length) {
                val index = num2.length - it - 1
                num2[index] - '0'
            }

            val res = IntArray(num1.length + num2.length + 2)

            for(i in 0 until array1.size) {
                for (j in 0 until array2.size) {
                    var index = i + j
                    var value = array1[i] * array2[j]
                    // println("i:$i j:$j ${array1[i]}*${array2[j]}=${value}, index:$index")
                    while(value > 0) {
                        value = res[index] + value
                        res[index] = value % 10
                        value = value / 10
                        index++
                    }
                }
            }
            val str = StringBuilder().apply{
                var started = false
                for(i in res.size-1 downTo 0) {
                    val value = res[i]
                    if (value != 0) started = true
                    if (!started && value == 0) continue
                    append(value)
                }
            }.toString()
            return if (str.length == 0) "0" else str
        }
    }
}


// 371. Sum of Two Integers
// https://leetcode.com/problems/sum-of-two-integers/
private interface LC_371 {
    fun getSum(a: Int, b: Int): Int

    // 链接：https://leetcode.cn/problems/sum-of-two-integers/solution/liang-zheng-shu-zhi-he-by-leetcode-solut-c1s3/
    private class S1 : LC_371 {
        override fun getSum(a: Int, b: Int): Int {
            var a = a
            var b = b
            while (b != 0) {
                val carry = a and b shl 1
                a = a xor b
                b = carry
            }
            return a
        }
    }
}

internal class Solution {

}





val _2022090 = listOf<Testable>(

)