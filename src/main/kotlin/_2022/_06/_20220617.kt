@file:Suppress("DuplicatedCode", "ClassName")

package _2022._06

import utils.assertEqualTo
import utils.runTimedTests

fun main() {
    Leetcode.test()
}

private interface Leetcode {
    fun someMethod() : String

    companion object {
        fun test() = listOf(
            S1()::someMethod
        ).runTimedTests {
            invoke().assertEqualTo("SomeStr")
        }
    }

    private class S1 : Leetcode {
        override fun someMethod() : String {
            return "SomeStr"
        }
    }
}