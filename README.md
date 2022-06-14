r## Code practice [link](https://github.com/hereisderek/code_practice)

---

### Templates

| Difficulty | Marks | Name | link                     | Notes |
|:----------:|:-----:|------|--------------------------|-------|
|    Easy    |  🟠✅  |      | [Leetcode](), [Github]() |       |
|   Medium   | 🟠❗❓  |      | [Leetcode](), [Github]() |       |
|    Hard    | 🟠❗❓  |      | [Leetcode](), [Github]() |       |
|   Medium   | 🟠❗❓  |      | [Leetcode](), [Github]() |       |

#### Markers:
- ✅ For finished
- 👷 For working in progress
- ❎ For unfinished due to complicity or challenging
- ❗For marking good questions
- ❓with doubt or unfinished
- ~~Strikedthrough~~ For not bothering (e.g. too easy)

#### Difficulties: 
- 🟢(Easy)
- 🟠(Medium) 
- 🤨(Hard)
---

### Kotlin
Since I use kotlin, I have created some convenient utility functions, mostly for testing/debugging purpose. (I might just create a branch with just the utility functions, [however](https://www.youtube.com/watch?v=fLexgOxsZu0).. )

Note: To enable assertion for kotlin, pass in `-ea` as jvm launch argument, see this [link](https://stackoverflow.com/questions/50938383/how-to-set-jvm-arguments-in-intellij-idea)

_Other than those listed below, check out [_util.kt](src/main/kotlin/utils/_util.kt) for more_

 
1. Run timed tests on a list of method variable. Sample usage checkout [this](src/main/kotlin/_sample.kt)
Update: I noticed that this method may not be accurate, not sure of the cause 
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
Sample print out:
```
execution for _2022._05.Leetcode_15.MY1.threeSum finished, took 476348 Nanoseconds
execution for _2022._05.Leetcode_15.MY1.threeSum finished, took 423606 Nanoseconds
execution for _2022._05.Leetcode_15.MY2.threeSum finished, took 435733 Nanoseconds
```

2. Assertion (assertEqual)

```kotlin
test() assertEqual "expected" // for single answer
test().assertEqual("possible_output1", "possible_output2") // for multiple correct answers 
```


3. Build-in data types
   1. [Node](src/main/kotlin/utils/Node.kt) (`typealias IntNode = Node<Int>`) and some built-in functions, e.g:
      1. `fun <T> linkedNodesOf(vararg nodes: T) : Node<T>` usage:`linkedNodesOf(1, 2, 3)` to create a linked nodes like `1 -> 2 -> 3`
      2. `fun <T> Node<T>.toList() : List<T>` usage: `linkedNodesOf(1, 2, 3).toList()` gets: `[1,2,3]`
      3. `fun <T, R> Node<T>.mapValue(transform: ((T)->R)) : List<R>` usage: `linkedNodesOf(1, 2, 3).mapValue{ it * 2 }` gets `[2,4,6]`
      4. others see this file: [Node](src/main/kotlin/utils/Node.kt)
   
---

### [202205](src/main/kotlin/_2022/_05)

#### [20220528](src/main/kotlin/_2022/_05/_20220528.kt) 
[youngyangyang04/leetcode-master/0001.两数之和.md · GitHub](https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0001.%E4%B8%A4%E6%95%B0%E4%B9%8B%E5%92%8C.md)

| Marks | link                                                                                         | Notes |
|:-----:|----------------------------------------------------------------------------------------------|-------|
|  🟢✅  | [1. Two Sum](https://leetcode.cn/problems/two-sum/)                                          |       |
|  🟢✅  | [342. Valid Anagram](https://leetcode.cn/problems/valid-anagram/)                            |       |
|  🟢✅  | [349. Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/) |       |
|  🟢   | ~~[383. Ransom Note](https://leetcode.com/problems/ransom-note/)~~                           |       |
|  🟢✅  | [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/)                          |       |


---

#### [20220529, 20220601](src/main/kotlin/_2022/_05/_20220529.kt)
[youngyangyang04/leetcode-master/0005.最长回文子串.md · GitHub](https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0005.%E6%9C%80%E9%95%BF%E5%9B%9E%E6%96%87%E5%AD%90%E4%B8%B2.md)

| Marks | link                                                                                             | Notes |
|:-----:|--------------------------------------------------------------------------------------------------|-------|
|  🟠✅  | [647. Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)             |       |
| 🟠❗❓  | [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) |       |

---

#### [20220602, 20220605, 20220606](src/main/kotlin/_2022/_06/_20220602.kt)
[youngyangyang04/leetcode-master/0005.最长回文子串.md · GitHub](https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0005.%E6%9C%80%E9%95%BF%E5%9B%9E%E6%96%87%E5%AD%90%E4%B8%B2.md)

| Difficulty | Marks | link                                                                                                                                                                                                                                      | Notes        |
|:----------:|:-----:|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------|
|  _Medium_  |  ✅❗   | 5. Longest Palindromic Substring [[leetcode](https://leetcode.com/problems/longest-palindromic-substring/)] [[git](https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0015.%E4%B8%89%E6%95%B0%E4%B9%8B%E5%92%8C.md)] |              |
|  _Medium_  |   ✅   | 15. 3Sum 💃💃💃 [[leetcode](https://leetcode.com/problems/3sum/)] [[git](https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0015.%E4%B8%89%E6%95%B0%E4%B9%8B%E5%92%8C.md)]                                           | My4 not done |

---
#### [20220606](src/main/kotlin/_2022/_06/_20220606.kt)

| Difficulty |      Marks      | Name                                      | link                                                                                                                                                                                                                                                                       | Notes                                                                                                                  |
|:----------:|:---------------:|-------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|
|    Easy    |       🟠✅       |                                           |                                                                                                                                                                                                                                                                            |                                                                                                                        |
|   Medium   |       🟢✅       | 17. Letter Combinations of a Phone Number | [Leetcode](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) [Github](https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0017.%E7%94%B5%E8%AF%9D%E5%8F%B7%E7%A0%81%E7%9A%84%E5%AD%97%E6%AF%8D%E7%BB%84%E5%90%88.md)               | Didn't read the sample code since it didn't take me too much thinking to solve the question, but it might worth a read |
|   Medium   | Skipped for now | 18. 4Sum 💃💃💃💃                         | [Leetcode](https://leetcode.com/problems/4sum/) [Github](https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0018.%E5%9B%9B%E6%95%B0%E4%B9%8B%E5%92%8C.md)                                                                                             |                                                                                                                        |
|   Medium   |       🟠❓       | 19. Remove Nth Node From End of List      | [Leetcode](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) [Github](https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0019.%E5%88%A0%E9%99%A4%E9%93%BE%E8%A1%A8%E7%9A%84%E5%80%92%E6%95%B0%E7%AC%ACN%E4%B8%AA%E8%8A%82%E7%82%B9.md) |                                                                                                                        |

---
#### [20220607](src/main/kotlin/_2022/_06/_20220607.kt) [labuladong](https://labuladong.github.io/algo/2/17/16/)

| Difficulty |      Marks      | Name                                      | link                                                                                                                                                                                                                                                                       | Notes                                                                                                                  |
|:----------:|:---------------:|-------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|
|    Easy    |       🟢✅       | 21. Merge Two Sorted Lists                | [Leetcode](https://leetcode.com/problems/merge-two-sorted-lists/) [labuladong](https://labuladong.github.io/algo/2/17/16/)                                                                                                                                                 | Sample1 is interesting (recursion)                                                                                     |
|    Easy    |       🟢✅       | 141. Linked List Cycle                    | [Leetcode](https://leetcode.com/problems/linked-list-cycle/)                                                                                                                                                                                                               |
|   Medium   |        ❗        | 142. Linked List Cycle II                 | [Leetcode](https://leetcode.com/problems/linked-list-cycle-ii/) [labuladong](https://labuladong.github.io/article/?qno=142)                                                                                                                                                |

---
#### [20220608,20220617](src/main/kotlin/_2022/_06/_20220607.kt) [labuladong](https://labuladong.github.io/algo/2/17/16/)

| Difficulty | Marks | Name                                  | link                                                                                                                                    | Notes                              |
|:----------:|:-----:|---------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|------------------------------------|
|    Hard    |  🟢✅  | 23. Merge k Sorted Lists              | [Leetcode](https://leetcode.com/problems/merge-k-sorted-lists/) [labuladong](https://labuladong.github.io/article/?qno=23)              | Need to read the sample code       |
|    Easy    |   ❗   | 160. Intersection of Two Linked Lists | [Leetcode](https://leetcode.com/problems/intersection-of-two-linked-lists/) [labuladong](https://labuladong.github.io/article/?qno=160) | Sample1 is interesting (recursion) |

[//]: # (|            |       |                                       |                                                                                                                                         |)


---

| Difficulty | Marks | Name                           | link                                                                 | Notes                                                                            |
|:----------:|:-----:|--------------------------------|----------------------------------------------------------------------|----------------------------------------------------------------------------------|
|    Easy    |  🟢   | 876. Middle of the Linked List | [Leetcode](https://leetcode.com/problems/middle-of-the-linked-list/) | Faster than 100% submission 🟢🟢                                                 |
|   Medium   |   ❗   | 92. Reverse Linked List II     | [Leetcode](https://leetcode.com/problems/reverse-linked-list-ii/)    | My2                                                                              |
|    Easy    |  🟠✅  | 206. Reverse Linked List       | [Leetcode](https://leetcode.com/problems/reverse-linked-list/)       | Need to check sample and read [this](https://labuladong.github.io/algo/2/17/17/) |
|   Medium   | 🟠❗❓  |                                | [Leetcode](https://leetcode.com/problems/4sum/), [Github]()          |                                                                                  |


--- 
### References:

- [LeetCode Top 100 Problem Selection | Step-by-step Data Science](https://h1ros.github.io/posts/coding/leetcode-top-100-problem-selection/)
- [GitHub - youngyangyang04/leetcode-master: 🚀](https://github.com/youngyangyang04/leetcode-master) 《代码随想录》LeetCode 刷题攻略：200道经典题目刷题顺序
- [LABULADONG 的算法网站](https://labuladong.github.io/algo/)

---