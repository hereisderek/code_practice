@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08


import Testable
import utils.assertEqualTo
import utils.runTimedTests
import utils.tupleOf
import java.util.*


// 208. Implement Trie (Prefix Tree)
// https://leetcode.com/problems/implement-trie-prefix-tree/
private interface Leetcode_208 {
    fun insert(word: String)
    fun search(word: String): Boolean
    fun startsWith(prefix: String): Boolean

    companion object : Testable {
        override fun test() {
            listOf(
                M1::class.java,
                M2::class.java,
                M3::class.java,
            ).runTimedTests() {
                this.getDeclaredConstructor().newInstance().apply {
                    insert("apple")
                    search("apple").assertEqualTo(true)
                    search("app").assertEqualTo(false)
                    startsWith("app").assertEqualTo(true)
                    insert("app")
                    search("app").assertEqualTo(true)
                    search("appi").assertEqualTo(false)
                }

                // ["Trie","insert","insert","insert","insert","insert","insert","search","search","search","search","search","search","search","search","search","startsWith","startsWith","startsWith","startsWith","startsWith","startsWith","startsWith","startsWith","startsWith"]
                // [[],["app"],["apple"],["beer"],["add"],["jam"],["rental"],["apps"],["app"],["ad"],["applepie"],["rest"],["jan"],["rent"],["beer"],["jam"],["apps"],["app"],["ad"],["applepie"],["rest"],["jan"],["rent"],["beer"],["jam"]]
                //[null,null,null,null,null,null,null,false,true,false,false,false,false,false,true,true,false,true,true,false,false,false,true,true,true]
                this.getDeclaredConstructor().newInstance().apply {
                    insert("app")
                    insert("apple")
                    insert("beer")
                    insert("rental")
                    search("apps").assertEqualTo(false)
                    search("app").assertEqualTo(true)

                }



            }
        }
    }

    // kotlin build in
    private class M1 : Leetcode_208 {
        private val pq = PriorityQueue<String>()

        override fun insert(word: String) {
            pq.offer(word)
        }

        override fun search(word: String): Boolean {
            return pq.contains(word)
        }

        override fun startsWith(prefix: String): Boolean {
            return pq.any { it.startsWith(prefix) }
        }
    }

    // sorted list
    private class M2 : Leetcode_208 {
        private val list = ArrayList<String>()

        private fun findIndexOf(word: String) : Int {
            if (list.isEmpty()) return 0
            var l = 0
            var r = list.lastIndex
            while (l <= r) {
                val m = l + (r - l) / 2
                val mid = list[m]
                val diff = mid.compareTo(word)

                if (diff == 0) {
                    l = m
                    r = m
                    break
                } else if (diff > 0) {
                    r = m - 1
                } else {
                    l = m + 1
                }
            }
            return l
        }

        override fun insert(word: String) {
            val i = findIndexOf(word)
            list.add(i, word)
        }

        override fun search(word: String): Boolean {
            val index = findIndexOf(word)
            return list.getOrNull(index) == word
        }

        override fun startsWith(prefix: String): Boolean {
            val index = findIndexOf(prefix)
            return list.getOrNull(index)?.startsWith(prefix) ?: false

        }
    }

    // using neetcode.io method
    // linked nodes
    // Runtime: 715 ms, faster than 59.80% of Kotlin online submissions for Implement Trie (Prefix Tree).
    // Memory Usage: 93.8 MB, less than 29.65% of Kotlin online submissions for Implement Trie (Prefix Tree).
    // https://leetcode.com/submissions/detail/762799057/
    private class M3 : Leetcode_208 {
        private class Node {
            // or this could've been a hashmap too
            val next = Array<Node?>(26) { null }
            var isEnd: Boolean = false
        }
        private val head = Node()

        override fun insert(word: String) {
            var h = head
            word.forEach{
                h = if (h.next[it - 'a'] == null) {
                    val n = Node()
                    h.next[it - 'a'] = n
                    n
                } else h.next[it - 'a']!!
            }
            h.isEnd = true
        }

        override fun search(word: String): Boolean {
            var h = head
            for (w in word) {
                h = h.next.getOrNull(w - 'a') ?: return false
            }
            return h.isEnd
        }

        override fun startsWith(prefix: String): Boolean {
            var h = head
            for (w in prefix) {
                h = h.next.getOrNull(w - 'a') ?: return false
            }
            return true
        }
    }
}


// 211. Design Add and Search Words Data Structure
// https://leetcode.com/problems/design-add-and-search-words-data-structure/
private interface Leetcode_211 {
    fun addWord(word: String)
    fun search(word: String): Boolean

    companion object : Testable {
        override fun test() {
            listOf(
                M1::class.java,
            ).runTimedTests {
                getDeclaredConstructor().newInstance().apply {
                    addWord("bad")
                    addWord("dad")
                    addWord("mad")
                    search("pad").assertEqualTo(false)
                    search("bad").assertEqualTo(true)
                    search(".ad").assertEqualTo(true)
                    search("b..").assertEqualTo(true)
                }
            }
        }
    }

    private class M1 : Leetcode_211 {


        override fun addWord(word: String) {
            TODO("Not yet implemented")
        }

        override fun search(word: String): Boolean {
            TODO("Not yet implemented")
        }
    }
}


// 981. Time Based Key-Value Store
// https://leetcode.com/problems/time-based-key-value-store/
private interface Leetcode_981 {
    fun set(key: String, value: String, timestamp: Int)
    fun get(key: String, timestamp: Int): String

    companion object : Testable {
        override fun test() {

            listOf(
                M1::class.java,
            ).runTimedTests {

                getDeclaredConstructor().newInstance().apply {
                    val k = "love"
                    set(k, "h", 10)
                    set(k, "l", 20)
                    get(k, 5).assertEqualTo("")
                    get(k, 10).assertEqualTo("h")
                    get(k, 15).assertEqualTo("h")
                    get(k, 20).assertEqualTo("l")
                    get(k, 25).assertEqualTo("l")
                }
            }
        }
    }

    private class M1 : Leetcode_981 {
        //                          key           timestamp to value
        private val map = HashMap<String, ArrayList<Pair<Int, String>>>()

        override fun set(key: String, value: String, timestamp: Int) {
            map.getOrPut(key){
                ArrayList()
            }.add(timestamp to value)
        }

        override fun get(key: String, timestamp: Int): String {
            val list = map[key] ?: return ""
            if (list.isEmpty()) return ""
            if (list[0].first > timestamp) return ""
            if (list.last().first <= timestamp) return list.last().second

            var l = 0
            var r = list.lastIndex
            while(l <= r) {
                val m = l + (r - l) / 2
                val (t, v) = list[m]
                when {
                    timestamp == t -> return v
                    (l == r - 1) -> break
                    timestamp < t -> {
                        r = m - 1
                    }
                    else -> {
                        l = m + 1
                    }
                }
            }
            return list[l].second
        }
    }
}

// 973. K Closest Points to Origin
// https://leetcode.com/problems/k-closest-points-to-origin/
private interface Leetcode_973 {
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray>
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(
                    arrayOf(intArrayOf(1,3), intArrayOf(-2,2)),
                    1,
                    arrayOf(intArrayOf(-2,2))
                ),
                tupleOf(
                    arrayOf(intArrayOf(3,3), intArrayOf(5,-1), intArrayOf(-2,4)),
                    2,
                    arrayOf(intArrayOf(3,3), intArrayOf(-2,4))
                ),
            )
            listOf(
                M1()::kClosest,
            ).runTimedTests(tests) { a, b, c ->
                invoke(a, b).assertEqualTo(
                    c,
                    toStr = { joinToString(prefix = "[", postfix = "]"){ it.joinToString(prefix = "[", postfix = "]") } },
                    // not ignoring orders
                    // checker = { exp, act ->
                    //     exp.contentDeepEquals(act)
                    // }
                )
            }
        }
    }

    // Runtime: 1241 ms, faster than 82.06% of Kotlin online submissions for K Closest Points to Origin.
    // Memory Usage: 102.7 MB, less than 61.44% of Kotlin online submissions for K Closest Points to Origin.
    // https://leetcode.com/submissions/detail/763038195/
    private class M1 : Leetcode_973 {
        private data class N(
            val coords: IntArray
        ) {
            val distance = coords.run { (get(0) * get(0)) + (get(1) * get(1)) }
        }

        private lateinit var pq : PriorityQueue<N>
        override fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
            pq = PriorityQueue<N>(k) { o1, o2 -> o2.distance - o1.distance }
            for (p in points) {
                pq.offer(N(p))
                if (pq.size > k){
                    pq.poll()
                }
            }
            return Array<IntArray>(k){ pq.poll().coords }
        }
    }
}


// 215. Kth Largest Element in an Array
// https://leetcode.com/problems/kth-largest-element-in-an-array/
private interface Leetcode_215 {
    fun findKthLargest(nums: IntArray, k: Int): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(
                    intArrayOf(3,2,1,5,6,4), 2, 5
                ),
                tupleOf(
                    intArrayOf(3,2,3,1,2,4,5,5,6), 4, 4
                )
            )
            listOf(
                M1()::findKthLargest,
                M2()::findKthLargest,
                S1()::findKthLargest,
            ).runTimedTests(tests) { a, b, c ->
                invoke(a, b).assertEqualTo(c)
            }
        }
    }

    // Runtime: 800 ms, faster than 6.69% of Kotlin online submissions for Kth Largest Element in an Array.
    // Memory Usage: 70.7 MB, less than 8.41% of Kotlin online submissions for Kth Largest Element in an Array.
    // https://leetcode.com/submissions/detail/763069863/
    private class M1 : Leetcode_215 {
        override fun findKthLargest(nums: IntArray, k: Int): Int {
            val pq = PriorityQueue<Int>() // { a, b -> b - a }
            nums.forEach {
                pq.offer(it)
                if (pq.size > k) pq.poll()
            }
            return pq.poll()
        }
    }

    private class M2 : Leetcode_215 {
        override fun findKthLargest(nums: IntArray, k: Int): Int {
            val queue = PriorityQueue<Int>()

            for(i in 0 until nums.size){
                if(queue.size < k){
                    queue.add(nums[i])
                }else{

                    if(queue.peek() < nums[i]){
                        queue.poll()
                        queue.add(nums[i])
                    }
                }
            }
            return queue.peek()
        }
    }

    // sample 181 ms submission
    private class S1 : Leetcode_215 {
        override fun findKthLargest(nums: IntArray, k: Int): Int {
            val heap: MyPriorityQueue = MyPriorityQueueImpl()
            heap.buildMaxHeap(nums)
            var i = 1
            while (i < k) {
                heap.poll()
                i++
            }
            return heap.peek()!!
        }


        interface MyPriorityQueue {
            fun buildMaxHeap(array: IntArray)
            fun peek(): Int?
            fun poll(): Int?
            fun insert(item: Int)
            fun increasePriority(index: Int, priority: Int)
        }

        class MyPriorityQueueImpl : MyPriorityQueue {
            private var capacity = 20000
            private var heapSize = 0
            private var A = IntArray(capacity) { Int.MIN_VALUE }

            override fun buildMaxHeap(array: IntArray) {
                heapSize = array.size
                for (i in 0 until array.size) {
                    A[i] = array[i]
                }
                for (i in array.size / 2 downTo 0) {
                    heapifyDown(i)
                }
            }

            override fun peek(): Int? = A[0]

            override fun poll(): Int? {
                val peek = peek()
                A[0] = A[heapSize - 1]
                heapSize--
                heapifyDown(0)
                return peek
            }

            override fun insert(item: Int) {
                ensureCapacity()
                A[heapSize] = item
                heapSize++
                heapifyUp(heapSize - 1)
            }

            override fun increasePriority(index: Int, priority: Int) {
                if (A[index] < priority) {
                    A[index] = priority
                    heapifyUp(index)
                }
            }

            private fun leftIndex(index: Int) = index * 2 + 1
            private fun rightIndex(index: Int) = index * 2 + 2
            private fun parentIndex(index: Int) = (index - 1) / 2

            private fun hasLeft(index: Int) = leftIndex(index) in (0 until heapSize)
            private fun hasRight(index: Int) = rightIndex(index) in (0 until heapSize)
            private fun hasParent(index: Int) = parentIndex(index) in (0 until heapSize)

            private fun leftChild(index: Int) = if (hasLeft(index)) A[leftIndex(index)] else null
            private fun rightChild(index: Int) = if (hasRight(index)) A[rightIndex(index)] else null
            private fun parent(index: Int) = if (hasParent(index)) A[parentIndex(index)] else null

            private fun heapifyDown(index: Int) {
                val leftIndex = index * 2 + 1
                val rightIndex = index * 2 + 2
                var maxIndex = index
                if (hasLeft(index) && A[leftIndex] > A[maxIndex]) {
                    maxIndex = leftIndex
                }
                if (hasLeft(index) && A[rightIndex] > A[maxIndex]) {
                    maxIndex = rightIndex
                }
                if (maxIndex != index) {
                    swap(maxIndex, index)
                    heapifyDown(maxIndex)
                }
            }

            private fun heapifyUp(index: Int) {
                var i = index
                while (hasParent(i) && A[parentIndex(i)] < A[i]) {
                    swap(parentIndex(i), i)
                    i = parentIndex(i)
                }
            }

            private fun swap(i: Int, j: Int) {
                val temp = A[i]
                A[i] = A[j]
                A[j] = temp
            }

            private fun ensureCapacity() {
                if (heapSize == capacity) {
                    capacity *= 2
                    A = Arrays.copyOf(A, capacity)
                }
            }
        }
    }

}


// 78. Subsets
// https://leetcode.com/problems/subsets/
private interface Leetcode_78 {
    fun subsets(nums: IntArray): List<List<Int>>

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(1,2,3)
            )
            listOf(
                M1()::subsets,
            ).runTimedTests {
                tests.forEach {
                    val r = invoke(it)
                    println("r size:${r.size}, content:${r.joinToString()}")
                }
            }
        }
    }

    // Runtime: 326 ms, faster than 41.07% of Kotlin online submissions for Subsets.
    // Memory Usage: 36.1 MB, less than 98.57% of Kotlin online submissions for Subsets.
    // https://leetcode.com/submissions/detail/763136249/
    private class M1 : Leetcode_78 {
        val res = ArrayList<List<Int>>()
        private fun helper(nums: IntArray, index: Int, list: ArrayList<Int>) {
            if (index == nums.size) {
                res.add(list)
                return
            }
            helper(nums, index + 1, list)

            val l = ArrayList<Int>(list)
            val i = nums[index]
            l.add(i)
            helper(nums, index + 1, l)
        }
        override fun subsets(nums: IntArray): List<List<Int>> {
            helper(nums, 0, ArrayList())
            return res
        }
    }
}


// 39. Combination Sum
// https://leetcode.com/problems/combination-sum/
private interface Leetcode_39 {
    companion object : Testable {
        override fun test() {

        }
    }
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>>

    // Runtime: 278 ms, faster than 91.02% of Kotlin online submissions for Combination Sum.
    // Memory Usage: 40.7 MB, less than 89.39% of Kotlin online submissions for Combination Sum.
    // https://leetcode.com/submissions/detail/763196038/
    private class M1 : Leetcode_39 {
        private val res = ArrayList<List<Int>>()
        private val l = ArrayList<Int>()
        override fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
            dfs(0, 0, candidates, target)
            return res
        }

        private fun dfs(sum: Int, index: Int, candidates: IntArray, target: Int) {

            if (sum == target) {
                res.add(ArrayList(l))
            }
            if (sum < target){
                for (i in index until candidates.size) {
                    val c = candidates[i]
                    val s = sum + c
                    if (s <= target) {
                        l += c
                        dfs(s, i, candidates, target)
                        l.removeAt(l.size - 1)
                    }
                }
            }
        }
    }


    private class S1 : Leetcode_39 {
        override fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
            val ans: MutableList<List<Int>> = ArrayList()
            val cur: MutableList<Int> = ArrayList()
            backtrack(candidates, target, ans, cur, 0)
            return ans
        }

        fun backtrack(
            candidates: IntArray,
            target: Int,
            ans: MutableList<List<Int>>,
            cur: MutableList<Int>,
            index: Int
        ) {
            if (target == 0) {
                ans.add(ArrayList<Int>(cur))
            } else if (target < 0 || index >= candidates.size) {
                return
            } else {
                cur.add(candidates[index])
                backtrack(candidates, target - candidates[index], ans, cur, index)
                cur.remove(cur[cur.size - 1])
                backtrack(candidates, target, ans, cur, index + 1)
            }
        }
    }

}


val _20220802 = listOf<Testable>(
    // Leetcode_208,
    // Leetcode_211,
    // Leetcode_981,
    // Leetcode_973,
    // Leetcode_215,
    Leetcode_78,
    Leetcode_39
)