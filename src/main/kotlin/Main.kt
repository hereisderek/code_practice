import _2022._07.*
import _2022._08.*

fun main(args: Array<String>) {

    val tests = listOf<List<Testable>>(
        // _20220802,
        // _20220803,
        // _20220804,
        // _20220805,
        // _20220807,
        // _20220808,
        // _20220810,
        // _20220811,
        // _20220812,
        // _20220814,
        _20220815,

    ).flatten()

    for (i in tests.indices) {
        println("Running test ${i+1}/${tests.size} ...")
        tests[i].test()
        println()
    }
}