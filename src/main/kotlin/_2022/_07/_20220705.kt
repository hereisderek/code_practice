@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.assertEqualTo
import utils.runTimedTests
import utils.tupleOf
import java.util.*
import kotlin.collections.HashMap


// 150. Evaluate Reverse Polish Notation
// https://leetcode.com/problems/evaluate-reverse-polish-notation/
private interface Leetcode_150 {
    fun evalRPN(tokens: Array<String>): Int
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                arrayOf("2","1","+","3","*") to 9,
                arrayOf("4","13","5","/","+") to 6,
                arrayOf("10","6","9","3","+","-11","*","/","*","17","+","5","+") to 22,
            )
            listOf(
                M1()::evalRPN,
                M1()::evalRPN,
                M1()::evalRPN,
            ).runTimedTests(tests = tests) { input, output ->
                invoke(input).assertEqualTo(output)
            }
        }
    }

    // https://leetcode.com/problems/evaluate-reverse-polish-notation/submissions/
    // Runtime: 315 ms, faster than 47.79% of Kotlin online submissions for Evaluate Reverse Polish Notation.
    // Memory Usage: 41.3 MB, less than 23.89% of Kotlin online submissions for Evaluate Reverse Polish Notation.
    private class M1 : Leetcode_150 {
        override fun evalRPN(tokens: Array<String>): Int {
            val operator = "+-*/"
            return Stack<String>().apply {
                tokens.forEach {
                    if (it in operator) {
                        val a = pop().toInt()
                        val b = pop().toInt()
                        val c = when(it){
                            "+" -> b + a
                            "-" -> b - a
                            "*" -> b * a
                            "/" -> b / a
                            else -> throw UnsupportedOperationException()
                        }.toString()
                        push(c)
                    } else {
                        push(it)
                    }
                }
            }.pop().toInt()
        }
    }
}

// 22. Generate Parentheses
// https://leetcode.com/problems/generate-parentheses/
private interface Leetcode_22 {
    fun generateParenthesis(n: Int): List<String>

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                3 to listOf("((()))","(()())","(())()","()(())","()()()"),
                1 to listOf("()"),
            )
            listOf(
                S1()::generateParenthesis,
            ).runTimedTests(tests) {i, o ->
                invoke(i).assertEqualTo(o)
            }
        }
    }


    private class S1 : Leetcode_22 {

        override fun generateParenthesis(n: Int): List<String> {
            val ans: MutableList<String> = ArrayList()
            backtrack(ans, StringBuilder(), 0, 0, n)
            return ans
        }

        private fun backtrack(ans: MutableList<String>, cur: StringBuilder, open: Int, close: Int, max: Int) {
            if (cur.length == max * 2) {
                ans.add(cur.toString())
                return
            }
            if (open < max) {
                cur.append("(")
                backtrack(ans, cur, open + 1, close, max)
                cur.deleteCharAt(cur.length - 1)
            }
            if (close < open) {
                cur.append(")")
                backtrack(ans, cur, open, close + 1, max)
                cur.deleteCharAt(cur.length - 1)
            }
        }
    }
}


// 496. Next Greater Element I
// https://leetcode.com/problems/next-greater-element-i/
private interface Leetcode_496 {
    fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(intArrayOf(4,1,2), intArrayOf(1,3,4,2), intArrayOf(-1,3,-1)),
                tupleOf(intArrayOf(3,1,2,4), intArrayOf(3,1,2,4), intArrayOf(4,2,4,-1)),
                tupleOf(intArrayOf(2,4), intArrayOf(1,2,3,4), intArrayOf(3,-1)),
                tupleOf(intArrayOf(3,1,5,7,9,2,6), intArrayOf(1,2,3,5,6,7,9,11), intArrayOf(5,2,6,9,11,3,7)),
                tupleOf(intArrayOf(1,3,5,2,4), intArrayOf(6,5,4,3,2,1,7), intArrayOf(7,7,7,7,7)),
            )
            listOf(
                // M1()::nextGreaterElement,
                M2()::nextGreaterElement,
            ).runTimedTests(tests) { a, b, c ->
                invoke(a, b).assertEqualTo(c)
            }
        }
    }

    // https://leetcode.com/problems/next-greater-element-i/submissions/
    // Runtime: 211 ms, faster than 96.10% of Kotlin online submissions for Next Greater Element I.
    // Memory Usage: 36.3 MB, less than 98.70% of Kotlin online submissions for Next Greater Element I.
    private class M1 : Leetcode_496 {
        override fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
            val ans = IntArray(nums1.size){ -1 }
            val finished = BooleanArray(nums1.size){ false }

            for (i in (nums2.size - 1) downTo 0) {
                val num2 = nums2[i]

                for (j in finished.indices) {
                    if (finished[j]) continue
                    val num = nums1[j]
                    if (num == num2) {
                        finished[j] = true
                        continue
                    } else if (num2 > num) {
                        ans[j] = num2
                    }
                }
            }

            return ans
        }
    }


    // this is wrong
    // [3,1,2,4]
    private class M2 : Leetcode_496 {
        override fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
            val ans = IntArray(nums1.size){ -1 }
            val map = HashMap<Int, Int>()
            nums1.forEach { map[it] = -1 }

            var left = 0
            var right = 1

            outter@ while (left < nums2.size && right < nums2.size) {
                while (nums2[left] >= nums2[right]) {
                    right++
                    if (right >= nums2.size) {
                        break@outter
                    }
                }
                for (i in left until right) {
                    val c = nums2[i]
                    map[c] = nums2[right]
                }
                left = right
                right++
            }
            nums1.forEachIndexed { index, i ->
                ans[index] = map[i] ?: -1
            }
            return ans
        }
    }



    // https://labuladong.github.io/algo/2/22/61/
    private class S1 : Leetcode_496 {
        override fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
            val ans = IntArray(nums1.size){ 0 }
            val stack = Stack<Int>()


            TODO()
        }
    }

}






fun main() {
    // Leetcode_150.test()
    // Leetcode_22.test()
    Leetcode_496.test()
}