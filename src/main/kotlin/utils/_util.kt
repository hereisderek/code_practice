@file:Suppress("DuplicatedCode")

package utils

import kotlin.reflect.KCallable
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import kotlin.jvm.internal.CallableReference
import kotlin.jvm.internal.ClassReference
import kotlin.time.Duration


/**
 * For checking if the contents of a list is equal to another list, with optional #[ignoreOrder] flag for ignoring the order
 * This is meant for validation of results, not to be used as part of the solution as it is not efficient
 */

inline fun<reified T : Comparable<T>> Iterable<T>.contentEquals(second: Iterable<T>, ignoreOrder: Boolean = false): Boolean {
    if (this == second) return true
    if (this is Collection && second is Collection) {
        if (size != second.size) return false
    }

    if (!ignoreOrder) {
        val i = iterator()
        val k = second.iterator()
        while (i.hasNext() || k.hasNext()){
            if (i.hasNext() != k.hasNext()) return false
            if (i.next() != k.next()) return false
        }
        return true
    }
    // return this.containsAll(second) && second.containsAll(this) // doesn't handle cases as ["a", "a", "b"] vs ["a", "b", "b"]
    // return this.sortedBy { it } == second.sortedBy { it }
    return this.sorted() == second.sorted()
}

inline fun<reified T : Comparable<T>> Array<T>.contentEquals(second: Array<T>, ignoreOrder: Boolean = false): Boolean {
    if (this === second) return true
    if (size != second.size) return false

    if (!ignoreOrder) {
        for (i in indices) {
            if (get(i) != second[i]) return false
        }
        return true
    }
    // return this.containsAll(second) && second.containsAll(this) // doesn't handle cases as ["a", "a", "b"] vs ["a", "b", "b"]
    return this.sorted() == second.sorted()
}

inline fun IntArray?.contentEquals(second: IntArray?, ignoreOrder: Boolean = false): Boolean {
    if (this === second) return true
    if (this == null || second == null) return false

    if (size != second.size) return false

    if (!ignoreOrder) {
        for (i in indices) {
            if (get(i) != second[i]) return false
        }
        return true
    }

    return this.sorted() == second.sorted()
}

inline fun <A, B> Collection<Pair<A, B>>.forEachPair(block: ((first: A, second: B)->Unit)) {
    forEach {
        block.invoke(it.first, it.second)
    }
}



@OptIn(ExperimentalTime::class)
inline fun<reified T : KCallable<*>, reified R> List<T>.runTimedTests(
    printTime: Boolean = true,
    block: T.()->R
) {
    // warming up, as I found that first print is always taking drastic longer time especially inside a measureTime block, no clue why
    firstOrNull()?.also {
        measureTime { it.block() }
        print("")
    }
    // val testName: String? = Thread.currentThread().stackTrace.getOrNull(1)?.className?.split("$", limit = 0)?.get(0),
    forEachIndexed { index, it ->
        (it as CallableReference).owner

        val testName = try { (it.owner as ClassReference).qualifiedName } catch (e: Exception) { null }
        val duration = measureTime{ it.block() }
        val prefix = if (testName.isNullOrEmpty()) "" else "${testName}."
        if (printTime) println("execution for ${prefix}${it.name} finished, took ${duration.inWholeNanoseconds} Nanoseconds")
    }
}




@OptIn(ExperimentalTime::class)
@JvmName("runTimedTestsOn")
inline fun<reified T, reified R> List<T>.runTimedTests(
    printTime: Boolean = true,
    testName: String? = null,
    block: T.()->R
) {
    try {
        firstOrNull()?.also {
            measureTime { it.block() }
            print("")
        }
    } catch (e: Exception) {
        // Ignored
    }

    forEachIndexed { index, it ->
        var duration : Duration? = null
        try {
            duration = measureTime { it.block() }
        } catch (e: Exception) {
            throw e
        } finally {
            val prefix = try {
                if (testName.isNullOrEmpty()) {
                    (Thread.currentThread().stackTrace.getOrNull(1) as StackTraceElement).className.split("$")[0]
                } else null
            } catch (e: Exception) {
                null
            } ?: testName
            if (printTime) println("execution for ${prefix}@$index finished, took ${duration?.inWholeNanoseconds} Nanoseconds")
        }
    }
}


/// assert
inline infix fun <reified T> T.assertEqualTo(expected: T) = assertEqualTo(expected, checker = null)


inline fun  <T> T.assertEqualTo(
    expected: T,
    noinline checker: ((expected: T, actual: T) -> Boolean)? = null,
    crossinline toStr: (T.() -> String) = {
        "[" + when (this) {
            is Iterable<*> -> joinToString()
            is IntArray -> joinToString()
            is CharArray -> joinToString()
            is FloatArray -> joinToString()
            is BooleanArray -> joinToString()
            is DoubleArray -> joinToString()
            is Array<*> -> joinToString()
            else -> toString()
        } + "]"
    },
    noinline lazyMsg: ((expected: T, actual: T) -> String)? = null
) {
    val actual = this
    val msg = {
        lazyMsg?.invoke(expected, actual) ?: "Assertion failed, expected:${expected.toStr()} actual:${actual.toStr()}"
    }
    assert(
        when{
            checker != null -> checker.invoke(expected, actual)
            expected is IntArray && actual is IntArray -> expected.contentEquals(actual)
            expected is CharArray && actual is CharArray -> expected.contentEquals(actual)
            expected is FloatArray && actual is FloatArray -> expected.contentEquals(actual)
            expected is BooleanArray && actual is BooleanArray -> expected.contentEquals(actual)
            expected is DoubleArray && actual is DoubleArray -> expected.contentEquals(actual)
            expected is Array<*> && actual is Array<*> -> expected.contentEquals(actual)
            else -> expected == actual
        },
        msg
    )
}

/*inline */fun </*reified*/ T> T.assertThat(lazyMsg: ((actual: T)->String)? = null, block: T.() -> Boolean) {
    assert(this.block()) {
        lazyMsg?.invoke(this) ?: "Assertion failed, actual:$this"
    }
}


/* inline fun <reified T> T.assertEqualTo(
    expected: T,
    noinline lazyMsg:  ((expected: T, actual: T) -> String)
) {
    assert(this == expected) {
        lazyMsg.invoke(expected, this)
    }
} */

/*
inline fun <reified T> T.assertEqualTo(
    expected: T,
    toStr: (T.()->String) = { this.toString() },
    noinline lazyMsg:  ((expected: T, actual: T) -> Pair<String, String>)? = null
) {
    assert(this == expected) {
        if (lazyMsg == null) {
            "Assertion failed, expected:${expected.toStr()}, actual:${this.toStr()}"
        } else {
            val strPair = lazyMsg.invoke(expected, this)
            "Assertion failed, expected:${strPair.first}, actual:${strPair.second}"
        }
    }
}
*/

inline fun <reified T> T.assertEqualToAny(vararg expected: T) {
    assert(expected.any { it == this }) {
        "Assertion failed, actual:$this expected:${expected.joinToString()}"
    }
}