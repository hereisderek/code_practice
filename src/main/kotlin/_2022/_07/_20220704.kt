@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.*
import java.util.EmptyStackException
import java.util.LinkedList
import java.util.Stack


// 239. Sliding Window Maximum
// https://leetcode.com/problems/sliding-window-maximum/
private interface Leetcode_239 {
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(intArrayOf(1,3,-1,-3,5,3,6,7), 3, intArrayOf(3,3,5,5,6,7)),
                tupleOf(intArrayOf(1), 1, intArrayOf(1)),
            )
            listOf(
                M1()::maxSlidingWindow,
            ).runTimedTests {
                tests.forEachTuple { first, second, third ->
                    invoke(first, second).assertEqualTo(third)
                }
            }
        }
    }

    private class M1 : Leetcode_239 {
        override fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
            val res = IntArray(nums.size - k + 1)
            // the index of the max of the current cell and (k-1) previous cells
            val maxes = IntArray(nums.size)

            var l = 0
            var r = k - 1

            for (i in nums.indices) {
                val c = nums[i]
                if (i == 0) {
                    maxes[i] = c
                } else {
                    val preMax = maxes[i-1]
                    if (c > preMax) {
                        maxes[i] = c
                    } else {

                    }
                }
            }


            TODO("Not yet implemented")
        }
    }
}

// 20. Valid Parentheses
// https://leetcode.com/problems/valid-parentheses/
private interface Leetcode_20 {
    fun isValid(s: String): Boolean
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                "()" to true,
                "()[]{}" to true,
                "()[{}" to false,
                "()][{}" to false,
            )
            listOf(
                M1()::isValid,
            ).runTimedTests {
                tests.forEachPair { first, second ->
                    invoke(first).assertEqualTo(second)
                }

            }
        }
    }

    // https://leetcode.com/submissions/detail/738135480/
    // Runtime: 149 ms, faster than 96.79% of Kotlin online submissions for Valid Parentheses.
    // Memory Usage: 33.7 MB, less than 94.25% of Kotlin online submissions for Valid Parentheses.
    private class M1 : Leetcode_20 {
        override fun isValid(s: String): Boolean {
            return Stack<Int>().apply {
                s.forEach {
                    when(it) {
                        '(' -> add(0)
                        '[' -> add(1)
                        '{' -> add(2)
                        else -> {
                            if (isEmpty()) return false
                            if (when (it) {
                                    ')' -> 0
                                    ']' -> 1
                                    '}' -> 2
                                    else -> return false
                                } != pop()
                            ) return false
                        }

                    }
                }
            }.isEmpty()
        }
    }
}

// 155. Min Stack
// https://leetcode.com/problems/min-stack/
private interface Leetcode_155 {
    fun push(`val`: Int)
    fun pop()
    fun top(): Int
    fun getMin(): Int

    companion object : Testable {
        override fun test() {
            listOf(
                S1(), S2(),
            ).runTimedTests {
                push(-2)
                top().assertEqualTo(-2)

                push(0)
                top().assertEqualTo(0)

                push(-3)
                top().assertEqualTo(-3)

                getMin().assertEqualTo(-3)

                pop()

                top().assertEqualTo(0)

                getMin().assertEqualTo(-2)

            }


            listOf(
                S1(), S2(),
            ).runTimedTests {
                push(-2)
                top().assertEqualTo(-2)

                push(0)
                top().assertEqualTo(0)

                push(-1)
                top().assertEqualTo(-1)

                getMin().assertEqualTo(-2)

                top().assertEqualTo(-1)

                pop()

                getMin().assertEqualTo(-2)

            }
        }
    }

    // Not working
    private class M1 : Leetcode_155 {
        private data class Node<T>(
            val `val`: T,
            var pre: Node<T>? = null,
            var preMin: Node<T>? = null
        )

        private var min: Node<Int>? = null
        private var head: Node<Int>? = null

        // TODO: Not done,
        // this keep preMin sorted
        override fun push(`val`: Int) {
            val node = Node(`val`, pre = head)

            if (min == null) {
                min = node
            } else {
                var p: Node<Int>? = min!!
                var m = p
                while (node.`val` < p!!.`val`) {
                    if (p.preMin == null) break
                    m = p
                    p = p.preMin
                }
                node.preMin = p
                m?.preMin = node
            }

            head = node
        }

        override fun pop() {
            val node = head ?: throw EmptyStackException()
            head = node.pre
            min = node.preMin
        }

        override fun top(): Int {
            return head?.`val` ?: throw EmptyStackException()
        }


        override fun getMin(): Int {
            return min?.`val` ?: throw EmptyStackException()
        }
    }

    // MARK: minStack
    private class S1 : Leetcode_155 {
        private val stack = Stack<Int>()
        private val minStack = Stack<Int>()
        override fun push(`val`: Int) {
            stack.push(`val`)
            if (minStack.isEmpty() || minStack.peek() > `val`) {
                minStack.push(`val`)
            } else {
                // !!!
                minStack.push(minStack.peek())
            }
        }

        override fun pop() {
            stack.pop()
            minStack.pop()
        }

        override fun top(): Int {
            return stack.peek()
        }

        override fun getMin(): Int {
            return minStack.peek()
        }
    }

    private class S2 : Leetcode_155 {
        private val stack = Stack<Int>()
        private val minStack = Stack<Int>()
        override fun push(`val`: Int) {
            stack.push(`val`)
            if (minStack.isEmpty() || minStack.peek() >= `val`) {
                minStack.push(`val`)
            }
        }

        override fun pop() {
            if (minStack.peek() == stack.peek()) {
                minStack.pop()
            }
            stack.pop()
        }

        override fun top(): Int {
            return stack.peek()
        }

        override fun getMin(): Int {
            return minStack.peek()
        }
    }

}
fun main() {
    // Leetcode_239.test()
    // Leetcode_20.test()
    Leetcode_155.test()
}