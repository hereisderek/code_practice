@file:Suppress("DuplicatedCode", "ClassName")

package _2022._06

import Testable
import utils.assertEqualTo
import utils.runTimedTests

fun main() {
    Leetcode.test()
}

private interface Leetcode {

    companion object : Testable {
        override fun test() {
            /*
            listOf(
                M1()::someMethod,
            ).runTimedTests {
                invoke().assertEqualTo(Unit)
            }
            */
        }
    }

    private class M1 : Leetcode {

    }
}
