@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._09

import Testable
import utils.assertEqualTo
import utils.runTimedTests
import java.util.*

// 435. Non-overlapping Intervals
// https://leetcode.com/problems/non-overlapping-intervals/
private interface LC_435 {
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int
    private class M1 : LC_435 {
        override fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
            TODO("Not yet implemented")
        }
    }
}


// Lintcode 920 Meeting Rooms
// https://leetcode.com/problems/meeting-rooms/
// https://www.lintcode.com/problem/920/

private interface LC_920 {

    data class Interval(var start: Int = 0, var end: Int = 0)

    fun canAttendMeetings(intervals: List<Interval>): Boolean

    private class M1 : LC_920 {
        override fun canAttendMeetings(intervals: List<Interval>): Boolean {
            if (intervals.size <= 1) return true
            val intervals = intervals.sortedBy{ it.start }
            var t = Int.MIN_VALUE
            for (i in intervals) {
                if (t > i.start) return false
                t = i.end
            }
            return true
        }
    }
}

// Lintcode 919 · Meeting Rooms II
// https://leetcode.com/problems/meeting-rooms-ii/
private interface LC_919 {
    data class Interval(var start: Int = 0, var end: Int = 0)

    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    fun minMeetingRooms(intervals: List<Interval>): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                listOf(Interval(0,30),Interval(5,10),Interval(15,20),) to 2,
                listOf(Interval(4,16),Interval(5,17),Interval(4,17),Interval(12,17),) to 4,
                listOf(Interval(2,7)) to 1,
            )

            listOf(
                M2()::minMeetingRooms
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    // not working
    private class M2 : LC_919 {
        override fun minMeetingRooms(intervals: List<Interval>): Int {
            if (intervals.size <= 1) return intervals.size

            val sorted = intervals.sortedWith{ a, b ->
                if (a.start == b.start) a.end - b.end else a.start - b.start
            }

            val queue = PriorityQueue<Int> { a, b -> a - b }
            var i = 0
            var res = 0

            while (i < sorted.size) {
                val start = sorted[i].start
                // val nextIndex = sorted.firstOrNull { it.start > start } ?: sorted.size
                var nextIndex = sorted.size
                for (j in i until sorted.size) {
                    val item = sorted[j]
                    if (item.start > start) {
                        nextIndex = j
                        if (j == sorted.lastIndex) queue.offer(item.end)
                    } else {
                        queue.offer(item.end)
                    }

                }
                while(queue.isNotEmpty() && queue.peek() <= start) {
                    queue.poll()
                }
                res = Math.max(res, queue.size)
                i = nextIndex
            }

            return res
        }
    }
}

val _20220908 = listOf<Testable>(
    LC_919
)