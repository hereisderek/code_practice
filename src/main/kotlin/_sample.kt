@file:Suppress("DuplicatedCode", "ClassName")

import Testable
import utils.assertEqualTo
import utils.runTimedTests



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

fun main() {
    Leetcode.test()
}