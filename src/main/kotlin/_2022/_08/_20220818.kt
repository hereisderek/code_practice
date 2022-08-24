@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.assertEqualTo
import utils.runTimedTests
import java.util.*


// 743. Network Delay Time
// https://leetcode.com/problems/network-delay-time/
private interface Leetcode_743 {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int
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

    private class M1 : Leetcode_743 {
        lateinit var nodes: IntArray
        override fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
            TODO("Not yet implemented")
        }
    }
}


// 295. Find Median from Data Stream
// https://leetcode.com/problems/find-median-from-data-stream/
private interface Leetcode_295 {
    fun addNum(num: Int)
    fun findMedian(): Double


}


val _202208 = listOf<Testable>(

)