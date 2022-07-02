@file:Suppress("DuplicatedCode", "ClassName")

package _2022._06

import Testable
import utils.*
import java.lang.Integer.min
import kotlin.collections.contentEquals


// 76. Minimum Window Substring
// https://leetcode.com/problems/minimum-window-substring/
private interface Leetcode_76 {
    fun minWindow(s: String, t: String): String
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(
                    "ADOBECODEBANC", "ABC", "BANC"
                ),
                tupleOf(
                    "a", "a", "a"
                ),
                tupleOf(
                    "a", "aa", ""
                ),
                tupleOf(
                    "aaflslflsldkalskaaa", "aaa", "aaa"
                )
            )
            listOf(
                // M1()::minWindow,
                // M2()::minWindow,
                M3()::minWindow,
            ).runTimedTests {
                tests.forEachTuple { first, second, third ->
                    invoke(first, second).assertEqualTo(third)
                }
            }
        }
    }

    // Does not work for test case "a", "aa" -> ""
    private class M1 : Leetcode_76 {
        override fun minWindow(s: String, t: String): String {
            // val tMap = IntArray(t.length) { -1 }
            val tMap = HashMap<Char, Int>(t.length)
            var min = 0
            var max = s.length
            s.forEachIndexed { index, c ->
                tMap[c] = index

                if (tMap.keys.containsAll(t.toList())) {

                    var _min = -1
                    var _max = -1
                    for (tc in t) {
                        val value = tMap.get(tc) ?: break
                        if (_min == -1 || value < _min) _min = value
                        if (_max == -1 || value > _max) _max = value
                    }
                    if (_min != -1 && _max != -1) {
                        if ((_max - _min) < (max - min)) {
                            max = _max
                            min = _min
                        }
                    }
                }
            }
            return if (max != s.length) s.substring(min, max+1) else ""
        }
    }

    // Nope, not working
    private class M2 : Leetcode_76 {
        override fun minWindow(s: String, t: String): String {
            val tMap = IntArray(t.length) { -1 }
            var min = 0
            var max = s.length

            s.forEachIndexed { index, c ->
                var tIndex = -1
                for (ti in t.indices) {
                    if (t[ti] == c) {
                        if (tMap[ti] == -1) {
                            tIndex = ti
                        }
                    }
                }

                if (tIndex == -1) {
                    for (ti in t.indices) {
                        if (t[ti] == c) {
                            if (tIndex == -1 || t[ti] > t[tIndex]) {
                                tIndex = ti
                            }
                        }

                    }

                }

                if (tIndex != -1) {
                    tMap[tIndex] = index
                    if (tMap.all { it != -1 }) {
                        var _min = -1
                        var _max = -1
                        tMap.forEach {
                            if (_min == -1 || it < _min) _min = it
                            if (_max == -1 || it > _max) _max = it
                        }
                        if (_min != -1 && _max != -1) {
                            if ((_max - _min) < (max - min)) {
                                max = _max
                                min = _min
                            }
                        }
                    }
                }
            }

            return if (max != s.length) s.substring(min, max+1) else ""
        }
    }

    private class M3 : Leetcode_76 {

        override fun minWindow(s: String, t: String): String {

            val tMap = HashMap<Char, Int>().apply {
                t.forEach {
                    set(it, (get(it) ?: 0) + 1)
                }
            }
            val tCount = IntArray(t.length)

            fun valid() : Boolean {
                for (i in t.indices) {
                    val c = t[i]

                    if (tCount[i] < checkNotNull(tMap[c])) {
                        return false
                    }
                }
                return true
            }

            var left = 0
            var right = 0
            var resultLeft = -1
            var resultRight = -1
            // [left, right)

            while (right < s.length) {
                val rC = s[right]
                tCount[right] ++

                if (valid()) {

                    while (valid()) {
                        left++
                        tCount[left]--
                    }
                    tCount[left]++
                    left--

                    if (resultLeft == -1 || (right - left) < (resultRight - resultLeft)) {
                        resultLeft = left
                        resultRight = right
                    }
                }
                right++
            }

            return if (resultLeft != -1) t.substring(resultLeft, resultRight) else ""
        }
    }
}



// 34. Find First and Last Position of Element in Sorted Array
// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

private interface Leetcode_34 {
    fun searchRange(nums: IntArray, target: Int): IntArray
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(intArrayOf(5,7,7,8,8,10), 8, intArrayOf(3,4)),
                tupleOf(intArrayOf(5,7,7,8,8,10), 6, intArrayOf(-1,-1)),
                tupleOf(intArrayOf(), 8, intArrayOf(-1,-1)),
                tupleOf(intArrayOf(1), 8, intArrayOf(-1,-1)),
                tupleOf(intArrayOf(2,2), 2, intArrayOf(0,1)),
                tupleOf(intArrayOf(2,2), 1, intArrayOf(-1,-1)),

            )
            listOf(
                M1()::searchRange,
                M2()::searchRange,
            ).runTimedTests {
                tests.forEachTuple { first, second, third ->
                    invoke(first, second).assertEqualTo(third, toStr = {
                        joinToString()
                    }, checker = { exp, actual ->
                        exp.contentEquals(actual)
                    }, lazyMsg = { exp, actual ->
                        "assertion failed for input:${first.joinToString()}, exp:${exp.joinToString()} vs actual:${actual.joinToString()}"
                    })
                }

            }
        }
    }

    // https://leetcode.com/submissions/detail/734756643/
    // Runtime: 334 ms, faster than 55.04% of Kotlin online submissions for Find First and Last Position of Element in Sorted Array.
    // Memory Usage: 45.9 MB, less than 6.92% of Kotlin online submissions for Find First and Last Position of Element in Sorted Array.

    private class M1 : Leetcode_34 {
        override fun searchRange(nums: IntArray, target: Int): IntArray {
            if (nums.isEmpty()) return intArrayOf(-1, -1)
            if (nums.size == 1) return if (
                nums.first() == target
            ) intArrayOf(0,0) else intArrayOf(-1, -1)
            var a = 0
            var b = nums.size - 1
            var i = b / 2

            loop@ while (true) {
                val value = nums[i]
                when{
                    value == target -> break@loop
                    a + 1 == b -> {
                        return when{
                            nums[a] == target && nums[b] == target -> intArrayOf(a,b)
                            nums[a] == target || nums[b] == target -> {
                                if (nums[a] == target) intArrayOf(a,a) else intArrayOf(b,b)
                            }
                            else -> intArrayOf(-1,-1)
                        }
                    }
                    value > target -> {
                        b = i
                        i = ((a + i + 0.5) / 2f).toInt()
                    }
                    else -> {
                        a = i
                        i = ((b + i + 0.5) / 2f).toInt()
                    }
                }
            }
            a = i
            b = i
            while (a >= 0 && nums[a] == target) {
                a--
            }
            a++

            while (b < nums.size && nums[b] == target) {
                b++
            }
            if (b > nums.size) {
                b = -1
            } else b--
            return intArrayOf(a++, b)
        }
    }
    // 0 1 2 3 4 5
    // 5,7,7,8,8,10
    private class M2 : Leetcode_34 {

        private fun search(nums: IntArray, target: Int, leftIndex: Int, rightIndex: Int, isLeft: Boolean) : Int {
            if (leftIndex == -1 || rightIndex == -1) return -1
            if (leftIndex == rightIndex) return if (nums[leftIndex] == target) leftIndex else -1
            if (isLeft) {
                if (nums[leftIndex] == target) return leftIndex
            } else {
                if (nums[rightIndex] == target) return rightIndex
            }

            val mid = (leftIndex + rightIndex) / 2
            val midValue = nums[mid]

            return when{
                midValue == target -> {
                    if (isLeft) {
                        search(nums, target, leftIndex, mid - 1, isLeft)
                    } else {
                        search(nums, target, mid + 1, rightIndex, isLeft)
                    }.let {
                        if (it == -1) mid else it
                    }
                }
                midValue < target -> {
                    search(nums, target, mid + 1, rightIndex, isLeft)
                }
                else -> {
                    search(nums, target, leftIndex, mid - 1, isLeft)
                }
            }

        }

        override fun searchRange(nums: IntArray, target: Int): IntArray {
            if (nums.isEmpty()) return intArrayOf(-1, -1)
            if (nums.size == 1) return if (nums.first() == target) intArrayOf(0,0) else intArrayOf(-1, -1)

            val left = search(nums, target, 0, nums.size - 1, true)
            val right = search(nums, target, left, nums.size - 1, false)
            return intArrayOf(left, right)
        }
    }
}


// 11. Container With Most Water
// https://leetcode.com/problems/container-with-most-water/

private interface Leetcode_11 {
    fun maxArea(height: IntArray): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(1,8,6,2,5,4,8,3,7) to 49,
            )
            listOf(
                M1()::maxArea,
                S3()::maxArea,
                S4()::maxArea,
            ).runTimedTests {
                tests.forEachPair { first, second ->
                    invoke(first).assertEqualTo(second, lazyMsg = { a, b ->
                        "assertion failed for input:${first.joinToString()}, exp:$a vs actual:$b"
                    })
                }
            }
        }
    }

    // timeout
    private class M1 : Leetcode_11 {
        override fun maxArea(height: IntArray): Int {
            var max = 0
            for (i in 0 until height.size - 1) {
                val left = height[i]
                for(j in i + 1 until height.size) {
                    val right = height[j]
                    val volume = Math.min(left, right) * (j - i)
                    if (volume > max) {
                        max = volume
                    }
                }
            }
            return max
        }
    }

    // https://leetcode.com/submissions/detail/735453613/
    // Runtime: 459 ms, faster than 94.08% of Kotlin online submissions for Container With Most Water.
    // Memory Usage: 49.4 MB, less than 98.52% of Kotlin online submissions for Container With Most Water.
    private class S3 : Leetcode_11 {
        override fun maxArea(height: IntArray): Int {
            var max = 0
            var left = 0
            var right = height.size - 1
            while(right > left) {
                val v = (right - left) * Math.min(height[left], height[right])
                if (v > max) {
                    max = v
                }
                if (height[left] > height[right]) {
                    right--
                } else left++
            }

            return max
        }
    }

    // slightly improved on S3
    private class S4 : Leetcode_11 {
        override fun maxArea(height: IntArray): Int {
            var max = 0
            var left = 0
            var right = height.size - 1
            var maxH = 0 // track the current max height
            while(right > left) {
                val h = Math.min(height[left], height[right])

                if (h <= maxH) {
                    if (height[left] > height[right]) {
                        right--
                    } else left++
                    continue
                }

                maxH = h
                val v = (right - left) * h
                if (v > max) {
                    max = v
                }
                if (height[left] > height[right]) {
                    right--
                } else left++
            }

            return max
        }
    }
}


fun main() {
    // Leetcode_76.test()
    // Leetcode_34.test()
    Leetcode_11.test()
}