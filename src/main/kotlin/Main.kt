import _2022._07.*


fun main(args: Array<String>) {

    val tests = listOf<List<Testable>>(
        _2022._08._20220802,
        _2022._08._20220803,
        // _2022._08.tests,

    ).flatten()

    for (i in tests.indices) {
        println("Running test ${i+1}/${tests.size} ...")
        tests[i].test()
        println()
    }
}