@file:Suppress("DuplicatedCode", "ClassName")

import utils.assertEqualTo
import utils.runTimedTests

fun main() {
    Leetcode.test()
}

interface Leetcode {
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