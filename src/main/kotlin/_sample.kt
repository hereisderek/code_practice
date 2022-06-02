@file:Suppress("DuplicatedCode", "ClassName")

import utils.assertEqual
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
            invoke().assertEqual("SomeStr")
        }
    }

    private class S1 : Leetcode {
        override fun someMethod() : String {
            return "SomeStr"
        }
    }
}