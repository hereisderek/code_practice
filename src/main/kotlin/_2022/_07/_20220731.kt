@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.assertEqualTo
import utils.runTimedTests


// 136. Single Number
// https://leetcode.com/problems/single-number/


private interface Leetcode_136 {
    fun singleNumber(nums: IntArray): Int
    private class S : Leetcode_136 {
        override fun singleNumber(nums: IntArray): Int {
            var r = 0
            nums.forEach{
                r = r xor it
            }
            return r
        }
    }
}

// 191. Number of 1 Bits
// https://leetcode.com/problems/number-of-1-bits/
private interface Leetcode_191 {
    fun hammingWeight(n:Int):Int

    companion object : Testable {
        override fun test() {
            val tests = listOf<Pair<Int, Int>>(
                // 0b00000000000000000000000000001011 to 3,
                // 0b00000000000000000000000010000000 to 1,
                // 0b11111111111111111111111111111101 to 31,
                -3 to 31
            )
            listOf(
                S0()::hammingWeight,
                S1()::hammingWeight,
                S2()::hammingWeight,
                S3()::hammingWeight,
                M()::hammingWeight,

                ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    // WRONG, not working for test case 3
    // see #S3
    class M : Leetcode_191 {
        // you need treat n as an unsigned value
        private var counter = 0
        private val mask = 1

        override fun hammingWeight(n:Int):Int {
            var n = n
            var counter = 0

            while(n != 0) {
                if ((n and mask) == mask) {
                    counter++
                }
                n = n ushr 1
            }
            return counter
        }
    }

    // neetcode solution
    // Java, Bit Magic, flip the rightmost 1
    class S0 : Leetcode_191 {
        override fun hammingWeight(n: Int): Int {
            var n = n
            var count = 0
            while (n != 0) {
                n = n and n - 1
                count++
            }
            return count
        }
    }

    // Java, moving mask, loop over
    class S1 : Leetcode_191 {
        override fun hammingWeight(n: Int): Int {
            var bits = 0
            var mask = 1
            for (i in 0..31) {
                if (n and mask !== 0) {
                    bits++
                }
                mask = mask shl 1
            }
            return bits
        }
    }

    // Java, right-shift of n, loop over
    class S2 : Leetcode_191 {
        override fun hammingWeight(n: Int): Int {
            var n = n
            var bits = 0
            val mask = 1
            for (i in 0..31) {
                if (n and mask !== 0) {
                    bits++
                }
                n = n shr 1
            }
            return bits
        }
    }

    // Java, right-shift of n, until all zeros
    class S3 : Leetcode_191 {
        override fun hammingWeight(n: Int): Int {
            var n = n
            var ones = 0
            while (n != 0) {
                ones = ones + (n and 1)
                n = n ushr 1
            }
            return ones
        }
    }
}


// 338. Counting Bits
// https://leetcode.com/problems/counting-bits/
private interface Leetcode_338 {
    fun countBits(n: Int): IntArray

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                2 to intArrayOf(0,1,1),
                5 to intArrayOf(0,1,1,2,1,2)
            )
            listOf(
                M1()::countBits,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    private class M1 : Leetcode_338 {
        override fun countBits(n: Int): IntArray {
            TODO("Not yet implemented")
        }
    }
}


// 190. Reverse Bits
// https://leetcode.com/problems/reverse-bits/
private interface Leetcode_190 {
    // you need treat n as an unsigned value
    fun reverseBits(n:Int): Int

    companion object : Testable {
        override fun test() {
            val u_tests = listOf<Pair<UInt, UInt>>(
                /* 43261596U */ 0b00000010100101000001111010011100U to 964176192U, // 0b00111001011110000010100101000000
                /* 4294967293 */ 0b11111111111111111111111111111101U to 3221225471U, // 0b10111111111111111111111111111111
            )

            val tests = listOf<Pair<Int, Int>>(
                43261596 /* 0b00000010100101000001111010011100U */  to 964176192, // 0b00111001011110000010100101000000
                // 4294967293U.toInt() /* 0b11111111111111111111111111111101U */ to 3221225471, // 0b10111111111111111111111111111111
                -3 to -1073741825
            )

            
            listOf(
                M1()::reverseBits,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    private class M1 : Leetcode_190 {
        override fun reverseBits(n: Int): Int {
            var n = n
            var r = 0
            for (i in 0 until 32) {
                val t = n % 2
                n = n ushr 1
                r = r shl 1
                r = t or r
            }
            return r
        }
    }
}


// 268. Missing Number
// https://leetcode.com/problems/missing-number/
private interface Leetcode_268 {
    fun missingNumber(nums: IntArray): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(0,1) to 2,
                intArrayOf(9,6,4,2,3,5,7,0,1) to 8
            )
            listOf(
                M1()::missingNumber,
                M2()::missingNumber,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    private class M1 : Leetcode_268 {
        override fun missingNumber(nums: IntArray): Int {
            return (0 .. nums.size).sum() - nums.sum()
        }
    }
    private class M2 : Leetcode_268 {
        override fun missingNumber(nums: IntArray): Int {
            var sum = 0
            for (i in nums.indices) {
                sum += i
                sum -= nums[i]
            }
            sum += nums.size
            return sum
        }
    }
}

