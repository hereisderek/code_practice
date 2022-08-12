@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

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

private interface Leetcode_ {

    private class S : Leetcode_ {

    }
}

val _202208 = listOf<Testable>(
    Leetcode,
)