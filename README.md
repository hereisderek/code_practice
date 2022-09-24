## Code practice [link](https://github.com/hereisderek/code_practice)

---

### [Templates]()

| Difficulty | Marks | Name | link         | Notes |
|:----------:|:-----:|------|--------------|-------|
|     🟢     | ✅❗❓❌  |      | [Leetcode]() |       |
|     🟠     | ✅❗❓❌  |      | [Leetcode]() |       |
|     🔴     | ✅❗❓❌  |      | [Leetcode]() |       |

#### Markers:
- ✅/✔️ For finished
- 👷 For working in progress
- ❌ For unfinished due to complicity or challenging
- ❗For marking good questions
- ❓with doubt or unfinished
- ~~Strikedthrough~~ For not bothering (e.g. too easy)
- `MARK` in code for paying extra attention

#### Difficulties: 
- 🟢(Easy)
- 🟠(Medium) 
- 🔴(Hard) 🤨
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
#### [20220608,20220614](src/main/kotlin/_2022/_06/_20220607.kt) [labuladong](https://labuladong.github.io/algo/2/17/16/)

| Difficulty | Marks | Name                                  | link                                                                                                                                    | Notes                              |
|:----------:|:-----:|---------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|------------------------------------|
|    Hard    |  🟢✅  | 23. Merge k Sorted Lists              | [Leetcode](https://leetcode.com/problems/merge-k-sorted-lists/) [labuladong](https://labuladong.github.io/article/?qno=23)              | Need to read the sample code       |
|    Easy    |   ❗   | 160. Intersection of Two Linked Lists | [Leetcode](https://leetcode.com/problems/intersection-of-two-linked-lists/) [labuladong](https://labuladong.github.io/article/?qno=160) | Sample1 is interesting (recursion) |

---
#### [20220614,20220615](src/main/kotlin/_2022/_06/_20220614.kt)

[labuladong](https://labuladong.github.io/algo/2/17/17/)

| Difficulty | Marks | Name                           | link                                                                 | Notes                                                                                        |
|:----------:|:-----:|--------------------------------|----------------------------------------------------------------------|----------------------------------------------------------------------------------------------|
|    Easy    |  🟢   | 876. Middle of the Linked List | [Leetcode](https://leetcode.com/problems/middle-of-the-linked-list/) | Faster than 100% submission 🟢🟢                                                             |
|   Medium   |   ❗   | 92. Reverse Linked List II     | [Leetcode](https://leetcode.com/problems/reverse-linked-list-ii/)    | My2                                                                                          |
|    Easy    |  🟠✅  | 206. Reverse Linked List       | [Leetcode](https://leetcode.com/problems/reverse-linked-list/)       | Need to check sample and read [this](https://labuladong.github.io/algo/2/17/17/) and read S1 |


---
#### [20220615,20220615](src/main/kotlin/_2022/_06/_20220615.kt)


| Difficulty | Marks | Name                         | link                                                                | Notes |
|:----------:|:-----:|------------------------------|---------------------------------------------------------------------|-------|
|    Hard    | 🔴❗❗❓ | 25. Reverse Nodes in k-Group | [Leetcode](https://leetcode.com/problems/reverse-nodes-in-k-group/) |       |


**TODO:**
 * https://labuladong.github.io/algo/2/17/18/

---

### [20220616](src/main/kotlin/_2022/_06/_20220616.kt)

| Difficulty | Marks | Name                                    | link                                                                           | Notes |
|:----------:|:-----:|-----------------------------------------|--------------------------------------------------------------------------------|-------|
|     🟢     |   ❌   | 234. Palindrome Linked List             | [Leetcode](https://leetcode.com/problems/palindrome-linked-list/)              |       |
|     🟢     |   ✅   | 26. Remove Duplicates from Sorted Array | [Leetcode](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) |       |
|     🟢     |   ✅   | 27. Remove Element                      | [Leetcode](https://leetcode.com/problems/remove-element/)                      |       |
|     🟢     |   ✅   | 83. Remove Duplicates from Sorted List  | [Leetcode](https://leetcode.com/problems/remove-duplicates-from-sorted-list/)  |       |


TODO: Leetcode_710

---

### [20220617](src/main/kotlin/_2022/_06/_20220617.kt)

| Difficulty | Marks | Name                                    | link                                                                        | Notes |
|:----------:|:-----:|-----------------------------------------|-----------------------------------------------------------------------------|-------|
|     🟠     |  ✅🟢  | 167. Two Sum II - Input Array Is Sorted | [Leetcode](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) |       |
|     🟢     |   ✅   | 283. Move Zeroes                        | [Leetcode](https://leetcode.com/problems/move-zeroes/)                      |       |


--- 

### [20220618](src/main/kotlin/_2022/_06/_20220618.kt)

| Difficulty | Marks | Name                                | link                                                                    | Notes |
|:----------:|:-----:|-------------------------------------|-------------------------------------------------------------------------|-------|
|     🟢     |   ✅   | 344. Reverse String                 | [Leetcode](https://leetcode.com/problems/reverse-string/)               |       |
|     🟢     |   ✅   | 303. Range Sum Query - Immutable    | [Leetcode](https://leetcode.com/problems/range-sum-query-immutable/)    |       |
|     🟠     |   ✅   | 304. Range Sum Query 2D - Immutable | [Leetcode](https://leetcode.com/problems/range-sum-query-2d-immutable/) |       |


Leetcode 370: Range Addition

---

### [20220619](src/main/kotlin/_2022/_06/_20220619.kt)

| Difficulty |     | Marks |     | Name                                | link                                                                 | Notes           |
|:----------:|:----|:-----:|:----|-------------------------------------|----------------------------------------------------------------------|-----------------|
|     🟠     |     |   ❓   |     | ~~370: Range Addition~~             | [Leetcode](https://baihuqian.github.io/2018-08-16-range-addition/)   | Put off for now |
|     🟠     |     |  ✅🟢  |     | 1094. Car Pooling                   | [Leetcode](https://leetcode.com/problems/car-pooling/)               |                 |
|     🟠     |     |   ❓   |     | ~~1109. Corporate Flight Bookings~~ | [Leetcode](https://leetcode.com/problems/corporate-flight-bookings/) |                 |
|     🟠     |     |   ❓   |     | 48. Rotate Image                    | [Leetcode](https://leetcode.com/problems/rotate-image/)              |                 |

---

### [20220620](src/main/kotlin/_2022/_06/_20220620.kt)

| Difficulty | Marks | Name              | link                                                     | Notes |
|:----------:|:-----:|-------------------|----------------------------------------------------------|-------|
|     🟠     |   ✅   | 54. Spiral Matrix | [Leetcode](https://leetcode.com/problems/spiral-matrix/) |       |

---

### [20220621](src/main/kotlin/_2022/_06/_20220621.kt)

| Difficulty | Marks | Name                                              | link                                                                                      | Notes                  |
|:----------:|:-----:|---------------------------------------------------|-------------------------------------------------------------------------------------------|------------------------|
|     🟠     |   ❓   | 59. Spiral Matrix II                              | [Leetcode](https://leetcode.com/problems/spiral-matrix-ii/)                               |                        |
|     🟠     |  ✅❓   | 3. Longest Substring Without Repeating Characters | [Leetcode](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | TODO: read explanation |


---

### [20220627,20200630](src/main/kotlin/_2022/_06/_20220627.kt)

| Difficulty | Marks | Name                                                        | link                                                                                               | Notes |
|:----------:|:-----:|-------------------------------------------------------------|----------------------------------------------------------------------------------------------------|-------|
|     🔴     |       | 76. Minimum Window Substring                                | [Leetcode]( https://leetcode.com/problems/minimum-window-substring/)                               |       |
|     🟠     |       | 34. Find First and Last Position of Element in Sorted Array | [Leetcode](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) |       |
|     🟠     |       | 1109. Corporate Flight Bookings                             | [Leetcode]()                                                                                       |       |

---

### [20220701](src/main/kotlin/_2022/_07/_20220701.kt)

| Difficulty | Marks | Name                          | link                                                                 | Notes |
|:----------:|:-----:|-------------------------------|----------------------------------------------------------------------|-------|
|     🟠     |   ✅   | 11. Container With Most Water | [Leetcode](https://leetcode.com/problems/container-with-most-water/) |       |
|     🔴     | ✅❗❓❌  | 42. Trapping Rain Water       | [Leetcode](https://leetcode.com/problems/trapping-rain-water/)       |       |
---

### [20220702](src/main/kotlin/_2022/_07/_20220702.kt)

| Difficulty | Marks | Name                              | link                                                                    | Notes                           |
|:----------:|:-----:|-----------------------------------|-------------------------------------------------------------------------|---------------------------------|
|     🟠     |   ✅   | 238. Product of Array Except Self | [Leetcode](https://leetcode.com/problems/product-of-array-except-self/) |                                 |
|     🟢     |   ✅   | 217. Contains Duplicate           | [Leetcode]()                                                            |                                 |
|     🟠     |   ✅   | 347. Top K Frequent Elements      | [Leetcode](https://leetcode.com/problems/top-k-frequent-elements/)      | 1. Heatmap, 2. bucket list/sort |
|     🟢     |   ✅   | 125. Valid Palindrome             | [Leetcode](https://leetcode.com/problems/valid-palindrome/)             |                                 |
|     🟠     |   ✅   | 271. Encode and Decode Strings    | [Lintcode_659](https://www.lintcode.com/problem/659/)                   |                                 |

---

### [20220703](src/main/kotlin/_2022/_07/_20220703.kt)

| Difficulty | Marks | Name                                         | link                                                                               | Notes |
|:----------:|:-----:|----------------------------------------------|------------------------------------------------------------------------------------|-------|
|     🟠     |   ✅   | 128. Longest Consecutive Sequence            | [Leetcode](https://leetcode.com/problems/longest-consecutive-sequence/)            |       |
|     🟢     |   ✅   | 121. Best Time to Buy and Sell Stock         | [Leetcode](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)         |       |
|     🟠     |  ✅❓   | 424. Longest Repeating Character Replacement | [Leetcode](https://leetcode.com/problems/longest-repeating-character-replacement/) |       |
|     🟠     |   ✅   | 567. Permutation in String                   | [Leetcode](https://leetcode.com/problems/permutation-in-string/)                   |       |

---

### [20220704](src/main/kotlin/_2022/_07/_20220704.kt)

| Difficulty | Marks | Name                        | link                                                              | Notes                             |
|:----------:|:-----:|-----------------------------|-------------------------------------------------------------------|-----------------------------------|
|     🔴     | ✅❗❓❌  | 239. Sliding Window Maximum | [Leetcode](https://leetcode.com/problems/sliding-window-maximum/) |                                   |
|     🟢     |   ✅   | 20. Valid Parentheses       | [Leetcode](https://leetcode.com/problems/valid-parentheses/)      |                                   |
|     🟠     |  ✅❗   | 155. Min Stack              | [Leetcode](https://leetcode.com/problems/min-stack/)              | The use of miniStack in S1 and S2 |

---

### [20220705](src/main/kotlin/_2022/_07/_20220705.kt)

| Difficulty | Marks | Name                                  | link                                                                        | Notes |
|:----------:|:-----:|---------------------------------------|-----------------------------------------------------------------------------|-------|
|     🟠     |   ✅   | 150. Evaluate Reverse Polish Notation | [Leetcode](https://leetcode.com/problems/evaluate-reverse-polish-notation/) |       |
|     🟠     |  ✅❓   | 22. Generate Parentheses              | [Leetcode](https://leetcode.com/problems/generate-parentheses/)             |       |
|     🟢     | ✅❗❓❌  | 496. Next Greater Element I           | [Leetcode](https://leetcode.com/problems/next-greater-element-i/)           |       |
---

### [20220724](src/main/kotlin/_2022/_07/_20220724.kt)

| Difficulty | Marks | Name                    | link                                                          | Notes |
|:----------:|:-----:|-------------------------|---------------------------------------------------------------|-------|
|     🟠     |   ✅   | 739. Daily Temperatures | [Leetcode](https://leetcode.com/problems/daily-temperatures/) |       |
|     🟠     |   ✅   | 853. Car Fleet          | [Leetcode](https://leetcode.com/problems/car-fleet/)          |       |
                                            |            |

---

### [20220725](src/main/kotlin/_2022/_07/_20220725.kt)

| Difficulty | Marks | Name                               | link                                                                      | Notes |
|:----------:|:-----:|------------------------------------|---------------------------------------------------------------------------|-------|
|     🔴     |   ❌   | 84. Largest Rectangle in Histogram | [Leetcode](https://leetcode.com/problems/largest-rectangle-in-histogram/) |       |
|     🟢     |   ✅   | 704. Binary Search                 | [Leetcode](https://leetcode.com/problems/binary-search/)                  |       |
|     🟠     |   ✅   | 74. Search a 2D Matrix             | [Leetcode](https://leetcode.com/problems/search-a-2d-matrix/)             |       |
|     🟠     |  ✅❗   | 875. Koko Eating Bananas           | [Leetcode](https://leetcode.com/problems/koko-eating-bananas/)            |       |
|     🟠     |   ❌   | 33. Search in Rotated Sorted Array | [Leetcode](https://leetcode.com/problems/search-in-rotated-sorted-array/) |       |
|            |       |                                    |                                                                           |       |
|     🟢     | ✅❗❓❌  |                                    | [Leetcode]()                                                              |       |
|     🟠     | ✅❗❓❌  |                                    | [Leetcode]()                                                              |       |
|     🔴     | ✅❗❓❌  |                                    | [Leetcode]()                                                              |       |


---

### [20220726](src/main/kotlin/_2022/_07/_20220726.kt)

| Difficulty | Marks | Name              | link                                                    | Notes |
|:----------:|:-----:|-------------------|---------------------------------------------------------|-------|
|     🟠     | ✅❗❓❌  | 143. Reorder List | [Leetcode](https://leetcode.com/problems/reorder-list/) |       |
|     🟢     | ✅❗❓❌  |                   | [Leetcode]()                                            |       |
|     🟠     | ✅❗❓❌  |                   | [Leetcode]()                                            |       |
|     🔴     | ✅❗❓❌  |                   | [Leetcode]()                                            |       |


---

### [20220727](src/main/kotlin/_2022/_07/_20220727.kt)

| Difficulty | Marks | Name                               | link                                                                     | Notes |
|:----------:|:-----:|------------------------------------|--------------------------------------------------------------------------|-------|
|     🟠     |   ✅   | 138. Copy List with Random Pointer | [Leetcode](https://leetcode.com/problems/copy-list-with-random-pointer/) |       |
---

### [2022078](src/main/kotlin/_2022/_07/_20220728.kt)

| Difficulty | Marks | Name                           | link                                                                 | Notes |
|:----------:|:-----:|--------------------------------|----------------------------------------------------------------------|-------|
|     🟠     |   ✅   | 2. Add Two Numbers             | [Leetcode](https://leetcode.com/problems/add-two-numbers/)           |       |
|     🟠     |   ❌   | 287. Find the Duplicate Number | [Leetcode](https://leetcode.com/problems/find-the-duplicate-number/) |       |
|     🟠     |   ❌   | 146. LRU Cache                 | [Leetcode](https://leetcode.com/problems/lru-cache/)                 |       |
---

### [20220729](src/main/kotlin/_2022/_07/_20220729.kt)

| Difficulty | Marks | Name                              | link                                                                    | Notes |
|:----------:|:-----:|-----------------------------------|-------------------------------------------------------------------------|-------|
|     🟢     |   ✅   | 226. Invert Binary Tree           | [Leetcode](https://leetcode.com/problems/invert-binary-tree/)           |       |
|     🟢     |  ✅❗   | 104. Maximum Depth of Binary Tree | [Leetcode](https://leetcode.com/problems/maximum-depth-of-binary-tree/) |       |
|     🟢     |   ✅   | 110. Balanced Binary Tree         | [Leetcode](https://leetcode.com/problems/balanced-binary-tree/)         |       |
|     🟢     |   ✅   | 100. Same Tree                    | [Leetcode](https://leetcode.com/problems/same-tree/)                    |       |
|     🟢     |  ✅❗   | 572. Subtree of Another Tree      | [Leetcode](https://leetcode.com/problems/subtree-of-another-tree/)      |       |


 * 572: can use a utility method `isSameTree` to check or need to be careful (see solution)

---

### [20220730](src/main/kotlin/_2022/_07/_20220730.kt)

| Difficulty | Marks | Name                                                | link                                                                                      | Notes |
|:----------:|:-----:|-----------------------------------------------------|-------------------------------------------------------------------------------------------|-------|
|     🟢     |   ✅   | 235. Lowest Common Ancestor of a Binary Search Tree | [Leetcode](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) |       |
|     🟢     | ✅❗❓❌  | 703. Kth Largest Element in a Stream                | [Leetcode](https://leetcode.com/problems/kth-largest-element-in-a-stream/)                |       |
|     🟢     |   ✅   | 1046. Last Stone Weight                             | [Leetcode](https://leetcode.com/problems/last-stone-weight/)                              |       |
|     🟢     |   ✅   | 70. Climbing Stairs                                 | [Leetcode](https://leetcode.com/problems/climbing-stairs/)                                |       |
|     🟢     |   ✅   | 746. Min Cost Climbing Stairs                       | [Leetcode](https://leetcode.com/problems/min-cost-climbing-stairs/)                       |       |
|     🟢     | ✅❗❓❌  |                                                     | [Leetcode]()                                                                              |       |
|     🟠     | ✅❗❓❌  |                                                     | [Leetcode]()                                                                              |       |
|     🔴     | ✅❗❓❌  |                                                     | [Leetcode]()                                                                              |       |


* 235: `Binary search tree`: left <= `val` <= right
* 746: Build an array dp where dp[i] is the minimum cost to climb to the top starting from the ith staircase.


---

### [20220731](src/main/kotlin/_2022/_07/_20220726.kt)

| Difficulty | Marks | Name                  | link                                                        | Notes |
|:----------:|:-----:|-----------------------|-------------------------------------------------------------|-------|
|     🟢     |   ✅   | 136. Single Number    | [Leetcode](https://leetcode.com/problems/single-number/)    |       |
|     🟢     |  ✅❗   | 191. Number of 1 Bits | [Leetcode](https://leetcode.com/problems/number-of-1-bits/) |       |
|     🟢     |   ❌   | 338. Counting Bits    | [Leetcode](https://leetcode.com/problems/counting-bits/)    |       |
|     🟢     |   ✅   | 190. Reverse Bits     | [Leetcode](https://leetcode.com/problems/reverse-bits/)     |       |
|     🟢     | ✅❗❓❌  | 268. Missing Number   | [Leetcode](https://leetcode.com/problems/missing-number/)   |       |

* 191: use mod n = n % 2

---

### [20220802](src/main/kotlin/_2022/_08/_20220802.kt)

| Difficulty | Marks | Name                                            | link                                                                                  | Notes                                        |
|:----------:|:-----:|-------------------------------------------------|---------------------------------------------------------------------------------------|----------------------------------------------|
|     🟠     |   ✅   | 208. Implement Trie (Prefix Tree)               | [Leetcode](https://leetcode.com/problems/implement-trie-prefix-tree/)                 |                                              |
|     🟠     |   ❓   | 211. Design Add and Search Words Data Structure | [Leetcode](https://leetcode.com/problems/design-add-and-search-words-data-structure/) |                                              |
|     🟠     |   ✅   | 981. Time Based Key-Value Store                 | [Leetcode](https://leetcode.com/problems/time-based-key-value-store/)                 |                                              |
|     🟠     |   ✅   | 973. K Closest Points to Origin                 | [Leetcode](https://leetcode.com/problems/k-closest-points-to-origin/)                 |                                              |
|     🟠     |   ✅   | 215. Kth Largest Element in an Array            | [Leetcode](https://leetcode.com/problems/kth-largest-element-in-an-array/)            |                                              |
|     🟠     |   ✅   | 78. Subsets                                     | [Leetcode](https://leetcode.com/problems/subsets/)                                    | try to solve with single res: ArrayList<Int> |
|     🟠     | ✅❗❓❌  | 39. Combination Sum                             | [Leetcode](https://leetcode.com/problems/combination-sum/)                            | S1                                           |

---

### [20220803](src/main/kotlin/_2022/_08/_20220803.kt)

| Difficulty | Marks | Name             | link                                                    | Notes |
|:----------:|:-----:|------------------|---------------------------------------------------------|-------|
|     🟠     |   ✅   | 46. Permutations | [Leetcode](https://leetcode.com/problems/permutations/) |       |
|     🟠     | ✅❗❓❌  | 90. Subsets II   | [Leetcode](https://leetcode.com/problems/subsets-ii/)   |       |


---

### [20220804](src/main/kotlin/_2022/_08/_20220804.kt)

| Difficulty | Marks | Name                   | link                                                          | Notes |
|:----------:|:-----:|------------------------|---------------------------------------------------------------|-------|
|     🟠     |   ❓   | 40. Combination Sum II | [Leetcode](https://leetcode.com/problems/combination-sum-ii/) |       |
|     🟠     |  ✅❗   | 200. Number of Islands | [Leetcode](https://leetcode.com/problems/number-of-islands/)  |       |

---

### [20220805](src/main/kotlin/_2022/_08/_20220805.kt)

| Difficulty | Marks | Name                    | link                                                          | Notes |
|:----------:|:-----:|-------------------------|---------------------------------------------------------------|-------|
|     🟠     | ✅❗❓❌  | 695. Max Area of Island | [Leetcode](https://leetcode.com/problems/max-area-of-island/) |       |
|     🟠     | ✅❗❓❌  | 133. Clone Graph        | [Leetcode](https://leetcode.com/problems/clone-graph/)        |       |


---

### [20220806](src/main/kotlin/_2022/_08/_20220806.kt)

| Difficulty | Marks | Name                             | link                                                                   | Notes                 |
|:----------:|:-----:|----------------------------------|------------------------------------------------------------------------|-----------------------|
|     🟠     |   ✅   | 417. Pacific Atlantic Water Flow | [Leetcode](https://leetcode.com/problems/pacific-atlantic-water-flow/) | revert: ocean to land |
|     🟠     | ✅❗❓❌  | 130. Surrounded Regions          | [Leetcode](https://leetcode.com/problems/surrounded-regions/)          |                       |



Note:
- 130: reverse thinking, thanks all non-surrounded region to T and change all O to X then change Ts back to O

---

### [20220807](src/main/kotlin/_2022/_08/_2022087.kt)

| Difficulty | Marks | Name              | link                                                    | Notes |
|:----------:|:-----:|-------------------|---------------------------------------------------------|-------|
|     🟠     |  ✅❗   | 198. House Robber | [Leetcode](https://leetcode.com/problems/house-robber/) |       |


---

### [20220808](src/main/kotlin/_2022/_08/_20220808.kt)

| Difficulty | Marks | Name             | link                                                   | Notes |
|:----------:|:-----:|------------------|--------------------------------------------------------|-------|
|     🟠     | ✅❗❓❌  | 322. Coin Change | [Leetcode](https://leetcode.com/problems/coin-change/) |       |
|     🟠     | ✅❗❓❌  | 91. Decode Ways  | [Leetcode](https://leetcode.com/problems/decode-ways/) |       |


---

### [20220810](src/main/kotlin/_2022/_08/_20220810.kt)

| Difficulty | Marks | Name                 | link                                                        | Notes |
|:----------:|:-----:|----------------------|-------------------------------------------------------------|-------|
|     🟠     |   ✅   | 36. Valid Sudoku     | [Leetcode](https://leetcode.com/problems/valid-sudoku/)     |       |
|     🟠     | ✅❗❓❌  | 53. Maximum Subarray | [Leetcode](https://leetcode.com/problems/maximum-subarray/) |       |


---

### [20220811](src/main/kotlin/_2022/_08/_20220811.kt)

| Difficulty | Marks | Name             | link                                                    | Notes |
|:----------:|:-----:|------------------|---------------------------------------------------------|-------|
|     🟠     |  ✅❗   | 134. Gas Station | [Leetcode](https://leetcode.com/problems/gas-station/)  |       |
|     🟠     |  ✅❓❓  | 55. Jump Game    | [Leetcode](https://leetcode.com/problems/jump-game/)    |       |
|     🟠     |  ✅❓   | 45. Jump Game II | [Leetcode](https://leetcode.com/problems/jump-game-ii/) |       |

Notes:
- 55: M4
- 134: S1

---

### [20220812](src/main/kotlin/_2022/_08/_20220812.kt)

| Difficulty | Marks | Name                   | link                                                         | Notes |
|:----------:|:-----:|------------------------|--------------------------------------------------------------|-------|
|     🟠     |   ✅   | 846. Hand of Straights | [Leetcode](https://leetcode.com/problems/hand-of-straights/) |       |
|     🟠     |  ✅❓   | 57. Insert Interval    | [Leetcode](https://leetcode.com/problems/insert-interval/)   |       |


---

### [20220813](src/main/kotlin/_2022/_08/_20220813.kt)

| Difficulty | Marks | Name                                   | link                                                                         | Notes |
|:----------:|:-----:|----------------------------------------|------------------------------------------------------------------------------|-------|
|     🟠     | ✅❗❓❌  | 102. Binary Tree Level Order Traversal | [Leetcode](https://leetcode.com/problems/binary-tree-level-order-traversal/) |       |


---

### [20220814](src/main/kotlin/_2022/_08/_20220814.kt)

| Difficulty | Marks | Name                                                           | link                                                                                  | Notes |
|:----------:|:-----:|----------------------------------------------------------------|---------------------------------------------------------------------------------------|-------|
|     🟠     |   ✅   | 199. Binary Tree Right Side View                               | [Leetcode](https://leetcode.com/problems/binary-tree-right-side-view/)                |       |
|     🟠     |   ✅   | 1448. Count Good Nodes in Binary Tree                          | [Leetcode](https://leetcode.com/problems/count-good-nodes-in-binary-tree/)            |       |
|     🟠     |   ✅   | 98. Validate Binary Search Tree                                | [Leetcode](https://leetcode.com/problems/validate-binary-search-tree/)                |       |
|     🟠     |  ✅❗   | 230. Kth Smallest Element in a BST                             | [Leetcode](https://leetcode.com/problems/kth-smallest-element-in-a-bst/)              |       |
|     🟠     | ✅❗❗❗  | 105. Construct Binary Tree from Preorder and Inorder Traversal | [Leetcode]()                                                                          |       |
|     🟠     | ✅❗❓❌  | 211. Design Add and Search Words Data Structure                | [Leetcode](https://leetcode.com/problems/design-add-and-search-words-data-structure/) |       |
|     🟠     |  ✅❓   | 621. Task Scheduler                                            | [Leetcode](https://leetcode.com/problems/task-scheduler/)                             |       |

Notes:
- bfs - order level traversal
- 98: binary search tree - sorted order
- 230: [solution](https://leetcode.cn/problems/kth-smallest-element-in-a-bst/solution/er-cha-sou-suo-shu-zhong-di-kxiao-de-yua-8o07/)


---

### [20220815](src/main/kotlin/_2022/_08/_20220815.kt)

| Difficulty | Marks | Name                                 | link                                                                      | Notes |
|:----------:|:-----:|--------------------------------------|---------------------------------------------------------------------------|-------|
|     🟢     | ✅❗❓❌  |                                      | [Leetcode]()                                                              |       |
|     🟠     |  ✅❓   | 355. Design Twitter                  | [Leetcode](https://leetcode.com/problems/design-twitter/)                 |       |
|     🟠     |   ✅   | 332. Reconstruct Itinerary           | [Leetcode](https://leetcode.com/problems/reconstruct-itinerary/)          |       |
|     🟠     |  ✅❓   | 1584. Min Cost to Connect All Points | [Leetcode](https://leetcode.com/problems/min-cost-to-connect-all-points/) |       |

Notes:
- 1584: Read S2 and [link](https://leetcode.cn/problems/min-cost-to-connect-all-points/solution/prim-and-kruskal-by-yexiso-c500/)

---

### [2022081](src/main/kotlin/_2022/_08/_20220817.kt)

| Difficulty | Marks | Name                                            | link                                                                                  | Notes |
|:----------:|:-----:|-------------------------------------------------|---------------------------------------------------------------------------------------|-------|
|     🟢     | ✅❗❓❌  |                                                 | [Leetcode]()                                                                          |       |
|     🟠     |   ✅   | 143. Reorder List                               | [Leetcode](https://leetcode.com/problems/reorder-list/)                               |       |
|     🟠     |   ✅   | 146. LRU Cache                                  | [Leetcode](https://leetcode.com/problems/lru-cache/)                                  |       |
|     🟠     |   ✅   | 23. Merge k Sorted Lists                        | [Leetcode](https://leetcode.com/problems/merge-k-sorted-lists/)                       |       |
|     🔴     |   ✅   | 25. Reverse Nodes in k-Group                    | [Leetcode](https://leetcode.com/problems/reverse-nodes-in-k-group/)                   |       |
|     🟠     |       | 211. Design Add and Search Words Data Structure | [Leetcode](https://leetcode.com/problems/design-add-and-search-words-data-structure/) |
|     🟠     |  ❗❓❌  | 4. Median of Two Sorted Arrays                  | [Leetcode]()                                                                          |       |
|     🟠     |   ✅   | 130. Surrounded Regions                         | [Leetcode](https://leetcode.com/problems/surrounded-regions/)                         |       |


Notes:!!
- 143: slow -> 0 and fast -> 1, fast jumps two steps while slow jumps one, when fast became null slow is at the end of the first half

---

### [202208](src/main/kotlin/_2022/_08/_202208.kt)

| Difficulty | Marks | Name                              | link                                                                    |
|:----------:|:-----:|-----------------------------------|-------------------------------------------------------------------------|
|     🟠     | ✅❗❓❌  | 743. Network Delay Time           | [Leetcode](https://leetcode.com/problems/network-delay-time/)           |       |
|     🟠     | ✅❗❓❌  | 295. Find Median from Data Stream | [Leetcode](https://leetcode.com/problems/find-median-from-data-stream/) |

---

### [20220824](src/main/kotlin/_2022/_08/_202208.kt)

| Difficulty | Marks | Name            | link                                                   |
|:----------:|:-----:|-----------------|--------------------------------------------------------|
|     🟠     |   ✅   | 79. Word Search | [Leetcode](https://leetcode.com/problems/word-search/) |

---

### [20220825](src/main/kotlin/_2022/_08/_20220825.kt)

| Difficulty | Marks | Name                                      | link                                                               |
|:----------:|:-----:|-------------------------------------------|--------------------------------------------------------------------|
|     🟠     |   ❌   | 131. Palindrome Partitioning              | [Leetcode](https://leetcode.com/problems/palindrome-partitioning/) |
|     🟠     | ✅❗❓❌  | 17. Letter Combinations of a Phone Number | [Leetcode]()                                                       |


---

### [20220826](src/main/kotlin/_2022/_08/_20220826.kt)

| Difficulty | Marks | Name                  | link                                                                   |
|:----------:|:-----:|-----------------------|------------------------------------------------------------------------|
|     🟠     |   ✅   | 663 · Walls and Gates | [Leetcode](https://www.lintcode.com/problem/663/)                      |
|     🟠     |   ✅   | 207. Course Schedule  | [Leetcode](https://leetcode.com/problems/course-schedule/submissions/) |


- 207: S1

---
### [20220827](src/main/kotlin/_2022/_08/_20220827.kt)

| Difficulty | Marks | Name                      | link                                                               |
|:----------:|:-----:|---------------------------|--------------------------------------------------------------------|
|     🟠     | ✅❗❓❌  | 684. Redundant Connection | [Leetcode](// https://leetcode.com/problems/redundant-connection/) |


---
### [20220828](src/main/kotlin/_2022/_08/_20220828.kt)

| Difficulty | Marks | Name                                                | link                                                                                     |
|:----------:|:-----:|-----------------------------------------------------|------------------------------------------------------------------------------------------|
|     🟠     |   ✅   | 2385. Amount of Time for Binary Tree to Be Infected | [Leetcode](https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/) |

- 2385: S1

---
### [20220829](src/main/kotlin/_2022/_08/_20220829.kt)

| Difficulty | Marks | Name                           | link         |
|:----------:|:-----:|--------------------------------|--------------|
|     🔴     | ✅❗❓❌  | 4. Median of Two Sorted Arrays | [Leetcode]() |

---
### [20220907](src/main/kotlin/_2022/_08/_202208.kt)

| Difficulty | Marks | Name                        | link                                                              |
|:----------:|:-----:|-----------------------------|-------------------------------------------------------------------|
|     🟠     | ✅❗❓❌  | 8. String to Integer (atoi) | [Leetcode](https://leetcode.com/problems/string-to-integer-atoi/) |
|     🟠     | ✅❗❓❌  | 56. Merge Intervals         | [Leetcode](https://leetcode.com/problems/merge-intervals/)        |


- 8: stupid ass question, not worth looking at

---
### [20220908](src/main/kotlin/_2022/_08/_20220908.kt)

| Difficulty | Marks | Name                            | link                                                                                                               |
|:----------:|:-----:|---------------------------------|--------------------------------------------------------------------------------------------------------------------|
|     🟠     |   ❌   | 435. Non-overlapping Intervals  | [Leetcode](https://leetcode.com/problems/non-overlapping-intervals/)                                               |
|     🟠     | ✅❗❓❌  | Lintcode 919 · Meeting Rooms II | [Leetcode](https://leetcode.com/problems/meeting-rooms-ii/) [youtube](https://www.youtube.com/watch?v=FdzJmTCVyJU) |

---
### [20220911](src/main/kotlin/_2022/_08/_20220911.kt)

| Difficulty | Marks | Name                     | link                                                           |
|:----------:|:-----:|--------------------------|----------------------------------------------------------------|
|     🟠     |   ✅   |                          | [Leetcode](https://leetcode.com/problems/fruit-into-baskets/)  |
|     🟠     |   ✅   | 73. Set Matrix Zeroes    | [Leetcode](https://leetcode.com/problems/set-matrix-zeroes/)   |
|     🟠     |   ✅   | 50. Pow(x, n)            | [Leetcode](https://leetcode.com/problems/powx-n/)              |
|     🟠     |   ❌   | 371. Sum of Two Integers | [Leetcode](https://leetcode.com/problems/sum-of-two-integers/) |
|     🟢     |   ✅   | 202. Happy Number        | [Leetcode](https://leetcode.com/problems/happy-number/)        |
|     🟢     |   ✅   | 66. Plus One             | [Leetcode](https://leetcode.com/problems/plus-one)             |


---
### [20220912](src/main/kotlin/_2022/_08/_20220912.kt)

| Difficulty | Marks | Name               | link                                                     |
|:----------:|:-----:|--------------------|----------------------------------------------------------|
|     🟠     |   ✅   | 338. Counting Bits | [Leetcode](https://leetcode.com/problems/counting-bits/) |
|     🟠     | ✅❗❓❌  | 91. Decode Ways    | [Leetcode](https://leetcode.com/problems/decode-ways/)   |

---
### [20220914](src/main/kotlin/_2022/_08/_20220914.kt)

| Difficulty | Marks | Name                 | link         |
|:----------:|:-----:|----------------------|--------------|
|     🟠     | ✅❗❓❌  | 213. House Robber II | [Leetcode]() |

* 213: run house robber 1 two times with either first house included or the last

---
### [20220916](src/main/kotlin/_2022/_08/_20220916.kt)

| Difficulty | Marks |       Name       | link                                                   |
|:----------:|:-----:|:----------------:|--------------------------------------------------------|
|     🟠     | ✅❗❓❌  | 322. Coin Change | [Leetcode](https://leetcode.com/problems/coin-change/) |

---
### [20220923](src/main/kotlin/_2022/_08/_20220923.kt)

| Diff | Marks |              Name              | link                                                                 |
|:----:|:-----:|:------------------------------:|----------------------------------------------------------------------|
|  🟠  |  ✅❗   |    743. Network Delay Time     | [Leetcode](https://leetcode.com/problems/network-delay-time/)        |       |
|  🟠  |   ✅   |   209. Minimum Size Subarray Sum                             | [Leetcode](https://leetcode.com/problems/minimum-size-subarray-sum/)                                                         |
|  🟠  | ✅❗❓❌  |                                | [Leetcode]()                                                         |
|  🟠  | ✅❗❓❌  |                                | [Leetcode]()                                                         |
|  🟠  | ✅❗❓❌  |                                | [Leetcode]()                                                         |
|  🟢  | ✅❗❓❌  |                                | [Leetcode]()                                                         |
|  🟢  | ✅❗❓❌  | 977. Squares of a Sorted Array | [Leetcode](https://leetcode.com/problems/squares-of-a-sorted-array/) |
|  🔴  | ✅❗❓❌  |                                | [Leetcode]()                                                         |

* 209: sliding window (left/right pointers)
---

### [202209](src/main/kotlin/_2022/_08/_202209.kt)

| Diff | Marks | Name | link         |
|:----:|:-----:|:----:|--------------|
|  🟠  | ✅❗❓❌  |      | [Leetcode]() |
|  🟠  | ✅❗❓❌  |      | [Leetcode]() |
|  🟠  | ✅❗❓❌  |      | [Leetcode]() |
|  🟠  | ✅❗❓❌  |      | [Leetcode]() |
|  🟢  | ✅❗❓❌  |      | [Leetcode]() |
|  🔴  | ✅❗❓❌  |      | [Leetcode]() |

---

### Read List
- [Union-Find/Disjoint Set](https://labuladong.github.io/algo/2/22/53/)

### TODO:
- Quick sort
- Quick select
- depth first search


### Binary search tree
- when there are multiple same value (not distinct), how to get the left most index of the given value (or right)
- `l < r` vs `l <= r` 

### backtrack / dfs
- you can go either way 
  - starting from position 0, and for each layer down the line you can either add or not add the next element (Leetcode_46), or 
  - starting form all the element including, you can have any many children as the element, and for each child, consider the corresponding element removed (Leetcode_90)
 
### References:

- [LeetCode Top 100 Problem Selection | Step-by-step Data Science](https://h1ros.github.io/posts/coding/leetcode-top-100-problem-selection/)
- [GitHub - youngyangyang04/leetcode-master: 🚀](https://github.com/youngyangyang04/leetcode-master) 《代码随想录》LeetCode 刷题攻略：200道经典题目刷题顺序
- [LABULADONG 的算法网站](https://labuladong.github.io/algo/)

---


### To-Do list
- https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
- https://leetcode.com/problems/graph-valid-tree/
- https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
- https://leetcode.com/problems/palindrome-partitioning/submissions/
- https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/