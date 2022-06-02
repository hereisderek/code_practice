package utils

import kotlin.jvm.javaClass
import kotlin.reflect.KFunction
import kotlin.reflect.KCallable
import kotlin.time.ExperimentalTime
import kotlin.time.TimedValue
import kotlin.time.measureTimedValue

inline fun<reified T> List<T>.contentEquals(second: List<T>, ignoreOrder: Boolean = false): Boolean {
    if (size != second.size) {
        return false
    }
    if (!ignoreOrder) return toTypedArray() contentEquals second.toTypedArray()

    for (f in this) {
        val anyMatch = second.any { it == f }
        if (!anyMatch) return false
    }
    return true
}



@OptIn(ExperimentalTime::class)
inline fun<reified T : KCallable<*>, reified R> List<T>.runTimedTests(
    testName: String? = Thread.currentThread().stackTrace.getOrNull(1)?.className?.split("$", limit = 0)?.get(0),
    printTime: Boolean = true,
    block: T.()->R
) {
    // val testName = testName ?: Thread.currentThread().stackTrace.getOrNull(1)?.className?.split("$", limit = 0)?.get(0)
    forEachIndexed { index, it ->
        val timeValue = measureTimedValue{ it.block() }
        val prefix = if (testName.isNullOrEmpty()) "" else "${testName}."
        if (printTime) println("execution for ${prefix}${it.name} finished, took ${timeValue.duration.inWholeNanoseconds} Nanoseconds")
    }
}



inline infix fun <reified T : Any> T.assertEqual(expected: T) {
    assert(this == expected) {
        "Assertion failed, expected:$expected, actual:$this"
    }
}

inline fun <reified T : Any> T.assertEqual(vararg expected: T) {
    assert(expected.any { it == this }) {
        "Assertion failed, actual:$this expected:${expected.joinToString()}"
    }
}