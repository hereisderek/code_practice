@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.assertEqualTo
import utils.runTimedTests
import utils.tupleOf


// 846. Hand of Straights
// https://leetcode.com/problems/hand-of-straights/
private interface Leetcode_846 {
    fun isNStraightHand(hand: IntArray, groupSize: Int): Boolean

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(
                    intArrayOf(1,2,3,6,2,3,4,7,8), 3, true
                ),
                tupleOf(
                    intArrayOf(1,2,3,4,5), 4, false
                ),

            )
            listOf(
                M1()::isNStraightHand,
            ).runTimedTests(tests) { a, b, c ->
                invoke(a, b).assertEqualTo(c)
            }
        }
    }

    // Runtime: 526 ms, faster than 82.35% of Kotlin online submissions for Hand of Straights.
    // Memory Usage: 57.6 MB, less than 94.12% of Kotlin online submissions for Hand of Straights.\
    // https://leetcode.com/submissions/detail/771548657/
    private class M1 : Leetcode_846 {
        override fun isNStraightHand(hand: IntArray, groupSize: Int): Boolean {
            if (hand.size % groupSize != 0) return false
            val groupsNo = hand.size / groupSize
            hand.sort()

            var start = 0
            for (x in 0 until groupsNo) {

                val startValue = hand[start]
                val endValue = startValue + groupSize - 1
                var i = start
                var newStart = -1

                for (v in startValue .. endValue) {
                    while(i<hand.size&&hand[i] != v) {
                        if (newStart == -1 && hand[i] != -1) { newStart = i }
                        i++
                    }
                    if (i > hand.lastIndex) return false
                    // println("hand[$i](${hand[i]}) -> -1")
                    hand[i] = -1
                    i++
                }
                start = if (newStart != -1) newStart else i

            }
            return true
        }
    }
}


// 57. Insert Interval
// https://leetcode.com/problems/insert-interval/
private interface Leetcode_57 {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray>

    companion object : Testable {
        override fun test() {
            /*
            val tests = listOf(

            )
            listOf(
                M1()::,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
            */
        }
    }

    private class M1 : Leetcode_57 {
        private val res = ArrayList<IntArray>()
        override fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
            if (intervals.isEmpty()) return arrayOf(newInterval)
            val _start = newInterval[0]
            val _end = newInterval[1]

            if (intervals[0][0] > _end) return Array(intervals.size+1) {
                when(it) {
                    0 -> newInterval
                    else -> {
                        intervals[it-1]
                    }
                }
            }

            if (intervals.last()[1] < _start) return Array(intervals.size+1) {
                when(it) {
                    intervals.size -> newInterval
                    else -> {
                        intervals[it]
                    }
                }
            }


            // index
            var start = -1
            var end = -1
            fun end() {
                if (end != -1) {
                    res.add(intArrayOf(start, end))
                    start = -1
                }
                end = -1
            }

            fun setStart(v: Int) {
                end()
                if (start > v || start == -1) {
                    start = v
                }
            }

            fun setEnd(v: Int) {
                if (end == -1 || end < v) {
                    end = v
                }
            }

            for (i in intervals) {
                val _s = i[0]
                val _e = i[1]
                if (end != -1 && _start>end && _start<_e) {
                    setStart(_start)
                    end = -1
                }
                setStart(_s)
                if (_start>_s&&_start<_e) {
                    setStart(_start)
                }

                if (_end>_s&&_end<_e) {
                    setStart(_end)
                }
                setEnd(_e)
            }
            end()
            return res.toTypedArray()
        }
    }
    private class M2 : Leetcode_57 {
        private val res = ArrayList<IntArray>()
        override fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
            if (intervals.isEmpty()) return arrayOf(newInterval)
            val _start = newInterval[0]
            val _end = newInterval[1]

            if (intervals[0][0] > _end) return Array(intervals.size+1) {
                when(it) {
                    0 -> newInterval
                    else -> {
                        intervals[it-1]
                    }
                }
            }

            if (intervals.last()[1] < _start) return Array(intervals.size+1) {
                when(it) {
                    intervals.size -> newInterval
                    else -> {
                        intervals[it]
                    }
                }
            }

            var open = 0
            var p = -1 // previous
            var index = 0
            fun submit(v: Int, start: Boolean) {
                if (start) {
                    open++
                } else {
                    open--
                }
                if (open > 0) {
                    p = Math.min(p, v)
                } else {
                    res += intArrayOf(p, v)
                }
                p = v
            }

            while(index < intervals.size) {
                val temp = intervals[index]
                val _s = temp[0]
                val _e = temp[1]

                if (p != -1 &&_start>p && _start<=_s) {
                    submit(_start, true)
                }
                if (_end>p && _end<=_s) {
                    submit(_end, false)
                }

                submit(_s, true)

                if (_start>p && _start<=_e) {
                    submit(_start, true)
                }
                if (_end>p && _end<=_e) {
                    submit(_end, false)
                }

                submit(_e, false)
                index++

            }


            return res.toTypedArray()
        }
    }

    private class S1 : Leetcode_57 {
        override fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
            var newInterval : IntArray? = newInterval
            val res: MutableList<IntArray> = ArrayList()
            for (interval in intervals) {
                if (newInterval == null || interval[1] < newInterval[0]) res.add(
                    interval
                ) else if (interval[0] > newInterval[1]) {
                    res.add(newInterval)
                    res.add(interval)
                    newInterval = null
                } else {
                    newInterval[0] = Math.min(interval[0], newInterval[0])
                    newInterval[1] = Math.max(interval[1], newInterval[1])
                }
            }
            if (newInterval != null) res.add(newInterval)
            return res.toTypedArray()
        }
    }
}


val _20220812 = listOf<Testable>(
    Leetcode_846,
)