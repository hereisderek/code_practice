@file:OptIn(ExperimentalTime::class)
@file:Suppress("DuplicatedCode")

package utils

import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime


inline fun </* reified */ T> toStr(t: T) : String = t.run {
    "[" + when (this) {
        is Iterable<*> -> joinToString()
        is IntArray -> joinToString()
        is CharArray -> joinToString()
        is FloatArray -> joinToString()
        is BooleanArray -> joinToString()
        is DoubleArray -> joinToString()
        is Array<*> -> joinToString()

        // is Matrix -> toMatrixStr(false)
        else -> toString()
    } + "]"
}

@OptIn(ExperimentalTime::class)
inline fun <reified T, reified R, reified A, reified B, reified Test : Pair<A, B>> List<T>.runTimedTests(
    tests: List<Test>,
    printTime: Boolean = true,
    showDetail: Boolean = true,
    testName: String? = null,
    block: T.(input: A, output: B) -> R
) {
    // mock run
    try {
        firstOrNull()?.also {
            measureTime { it.block(tests.first().first, tests.first().second) }
            print("")
        }
    } catch (_: Exception){}

    forEachIndexed { runIndex, it ->
        tests.forEachIndexed { testIndex, test ->
            val input = test.first
            val output = test.second
            var duration : Duration? = null
            try {
                duration = measureTime { it.block(input, output) }
            } catch (e: Exception) {
                throw e
            } finally {
                if (printTime) {
                    val prefix = try {
                        if (testName.isNullOrEmpty()) {
                            (Thread.currentThread().stackTrace.getOrNull(1) as StackTraceElement).className.split("$")[0]
                        } else null
                    } catch (e: Exception) {
                        null
                    } ?: testName
                    val testId = "${prefix} receiver:$runIndex case:$testIndex"
                    if (duration != null) {
                        if (showDetail) {
                            println("$testId, took ${duration?.inWholeNanoseconds} Nanoseconds, input:${toStr(input)} output:${toStr(output)}")
                        } else {
                            println("$testId, took ${duration?.inWholeNanoseconds} Nanoseconds")
                        }
                    }
                }

            }
        }
    }
}


@OptIn(ExperimentalTime::class)
inline fun <reified T, reified R, reified A, reified B, reified C, reified Test : Tuple<A, B, C>> List<T>.runTimedTests(
    tests: List<Test>,
    printTime: Boolean = true,
    showDetail: Boolean = true,
    testName: String? = null,
    block: T.(a: A, b: B, output: C) -> R
) {
    // mock run
    try {
        firstOrNull()?.also {
            measureTime { it.block(tests.first().first, tests.first().second, tests.first().third) }
            print("")
        }
    } catch (_: Exception){}

    forEachIndexed { runIndex, it ->
        tests.forEachIndexed { testIndex, test ->
            val a = test.first
            val b = test.second
            val output = test.third

            var duration : Duration? = null
            try {
                duration = measureTime { it.block(a, b, output) }
            } catch (e: Exception) {
                throw e
            } finally {
                if (printTime) {
                    val prefix = try {
                        if (testName.isNullOrEmpty()) {
                            (Thread.currentThread().stackTrace.getOrNull(1) as StackTraceElement).className.split("$")[0]
                        } else null
                    } catch (e: Exception) {
                        null
                    } ?: testName
                    val testId = "${prefix} solution:${runIndex + 1} test:$testIndex"
                    if (duration != null) {
                        if (showDetail) {
                            println("[PASS]$testId, took ${duration.inWholeNanoseconds} Nanoseconds, input:[${toStr(a)}, ${toStr(b)}] output:${toStr(output)}")
                        } else {
                            println("[PASS]$testId, took ${duration.inWholeNanoseconds} Nanoseconds")
                        }
                    }

                }

            }
        }
    }
}

