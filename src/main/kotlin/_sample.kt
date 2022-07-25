@file:Suppress("DuplicatedCode", "ClassName")

import Testable
import utils.assertEqualTo
import utils.runTimedTests


//
//
private interface Leetcode {

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

    private class M1 : Leetcode {

    }
}

fun main() {
    Leetcode.test()
}