@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.assertEqualTo
import utils.runTimedTests



private interface Leetcode {

    companion object : Testable {
        override fun test() {
            /*
            listOf(
                M1()::,
            ).runTimedTests {
                invoke().assertEqualTo(Unit)
            }
            */
        }
    }

    private class M1 : Leetcode {

    }
}

fun main() {
    Leetcode.test()
}