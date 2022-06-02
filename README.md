## Code practice [link](https://github.com/hereisderek/code_practice)

---

### Templates

| Marks | link | Notes |
|:-----:|------|-------|
|  😕✅  |      |       |
| 😕❗❓  |      |       |

#### Markers:
- ✅ For finished
- 👷 For working in progress
- ❎ For unfinished due to complicity or challenging
- ❗For marking good questions
- ❓with doubt or unfinished
- ~~Strikedthrough~~ For not bothering (e.g. too easy)

#### Difficulties: 
- 😏(Easy)
- 😕(Medium) 
- 🤨(Hard)
---

### Kotlin
Since I use kotlin, I have created some convenient utility functions, mostly for testing/debugging purpose. (I might just create a branch with just the utility functions, [however](https://www.youtube.com/watch?v=fLexgOxsZu0).. )

Note: To enable assertion for kotlin, pass in `-ea` as jvm launch argument, see this [link](https://stackoverflow.com/questions/50938383/how-to-set-jvm-arguments-in-intellij-idea)

_Other than those listed below, check out [_util.kt](src/main/kotlin/utils/_util.kt) for more_

 
1. Run timed tests on a list of method variable. Sample usage checkout [this](src/main/kotlin/_sample.kt)
```kotlin
@OptIn(ExperimentalTime::class)
inline fun<reified T : KCallable<*>, reified R> List<T>.runTimedTests(
    testName: String? = Thread.currentThread().stackTrace.getOrNull(1)?.className?.split("$", limit = 0)?.get(0),
    printTime: Boolean = true,
    block: T.()->R
) {
    forEachIndexed { index, it ->
        val timeValue = measureTimedValue{ it.block() }
        val prefix = if (testName.isNullOrEmpty()) "" else "${testName}."
        if (printTime) println("execution for ${prefix}${it.name} finished, took ${timeValue.duration.inWholeNanoseconds} Nanoseconds")
    }
}
```
Sample output:
```
/usr/local/Cellar/openjdk/18.0.1/libexec/openjdk.jdk/Contents/Home/bin/java -ea -javaagent:/Users/derek/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/221.5591.52/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=51964:/Users/derek/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/221.5591.52/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/derek/Workspace/mine/code_practice/build/classes/kotlin/main:/Users/derek/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk8/1.6.21/eeb4d60d75e9ea9c11200d52974e522793b14fba/kotlin-stdlib-jdk8-1.6.21.jar:/Users/derek/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk7/1.6.21/568c1b78a8e17a4f35b31f0a74e2916095ed74c2/kotlin-stdlib-jdk7-1.6.21.jar:/Users/derek/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/1.6.21/11ef67f1900634fd951bad28c53ec957fabbe5b8/kotlin-stdlib-1.6.21.jar:/Users/derek/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-common/1.6.21/5e5b55c26dbc80372a920aef60eb774b714559b8/kotlin-stdlib-common-1.6.21.jar:/Users/derek/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar _2022._05._20220602Kt
execution for _2022._05.Leetcode_5.longestPalindrome finished, took 4293279 Nanoseconds
execution for _2022._05.Leetcode_5.longestPalindrome finished, took 34576 Nanoseconds
```

2. Assertion (assertEqual)

```kotlin
test() assertEqual "expected" // for single answer
test().assertEqual("possible_output1", "possible_output2") // for multiple correct answers 
```




---

### [202205](src/main/kotlin/_2022/_05)

#### [20220528](src/main/kotlin/_2022/_05/_20220528.kt) 
[youngyangyang04/leetcode-master/0001.两数之和.md · GitHub](https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0001.%E4%B8%A4%E6%95%B0%E4%B9%8B%E5%92%8C.md)

| Marks | link                                                                                         | Notes |
|:-----:|----------------------------------------------------------------------------------------------|-------|
|  😏✅  | [1. Two Sum](https://leetcode.cn/problems/two-sum/)                                          |       |
|  😏✅  | [342. Valid Anagram](https://leetcode.cn/problems/valid-anagram/)                            |       |
|  😏✅  | [349. Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/) |       |
|  😏   | ~~[383. Ransom Note](https://leetcode.com/problems/ransom-note/)~~                           |       |
|  😏✅  | [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/)                          |       |


---

#### [20220529-20220601](src/main/kotlin/_2022/_05/_20220529.kt)
[youngyangyang04/leetcode-master/0005.最长回文子串.md · GitHub](https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0005.%E6%9C%80%E9%95%BF%E5%9B%9E%E6%96%87%E5%AD%90%E4%B8%B2.md)

| Marks | link                                                                                             | Notes |
|:-----:|--------------------------------------------------------------------------------------------------|-------|
|  😕✅  | [647. Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)             |       |
| 😕❗❓  | [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) |       |

---

#### [20220602](src/main/kotlin/_2022/_05/_20220602.kt)
[youngyangyang04/leetcode-master/0005.最长回文子串.md · GitHub](https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0005.%E6%9C%80%E9%95%BF%E5%9B%9E%E6%96%87%E5%AD%90%E4%B8%B2.md)

| Marks  | link                                                                                                                                                                                     | Notes |
|:------:|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------|
|  😕✅❗  | [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)                                                                                         | S0 ❓  |
|   👷   | 15. 3Sum [[leetcode](https://leetcode.com/problems/3sum/)] [[git](https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0015.%E4%B8%89%E6%95%B0%E4%B9%8B%E5%92%8C.md)] |       |


--- 
### References:

- [LeetCode Top 100 Problem Selection | Step-by-step Data Science](https://h1ros.github.io/posts/coding/leetcode-top-100-problem-selection/)

- [GitHub - youngyangyang04/leetcode-master: 🚀](https://github.com/youngyangyang04/leetcode-master) 《代码随想录》LeetCode 刷题攻略：200道经典题目刷题顺序

---