@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.ListNode
import utils.assertEqualTo
import utils.runTimedTests
import java.util.SortedSet


// 143. Reorder List
// https://leetcode.com/problems/reorder-list/
private interface Leetcode_143 {
    fun reorderList(head: ListNode?): Unit

    private class M1 : Leetcode_143 {
        override fun reorderList(head: ListNode?): Unit {
            if (head == null) return

            var slow: ListNode? = head
            var fast: ListNode? = head
            var tail: ListNode? = null
            while(fast != null) {
                fast = fast.next?.next
                tail = slow
                slow = slow!!.next
            }
            tail!!.next = null

            // reverse slow
            val dummy = ListNode(-1)
            while(slow != null) {
                val old = dummy.next
                val nextS = slow.next
                dummy.next = slow
                slow.next = old
                slow = nextS
            }

            slow = dummy.next
            fast = head
            while(fast != null && slow != null) {
                val nextF = fast.next
                val nextS = slow?.next
                fast.next = slow
                slow.next = nextF

                fast = nextF
                slow = nextS
            }
        }
    }
}

// 146. LRU Cache
// https://leetcode.com/problems/lru-cache/
private interface Leetcode_146 {
    fun get(key: Int): Int
    fun put(key: Int, value: Int)

    companion object : Testable{
        override fun test() {
            TODO("Not yet implemented")
        }
    }

    // Runtime: 3028 ms, faster than 30.59% of Kotlin online submissions for LRU Cache.
    // Memory Usage: 203.7 MB, less than 59.71% of Kotlin online submissions for LRU Cache.
    // https://leetcode.com/submissions/detail/775624052/
    private class M1(val capacity: Int) {
        val data = HashMap<Int, Node>(capacity)
        var head: Node? = null
        var tail: Node? = null
        var count = 0

        data class Node(
            val key: Int,
            val value: Int,
            var pre: Node? = null,
            var next: Node? = null
        )

        private fun pop(key: Int) : Node? {
            val n = data[key] ?: return null
            n.pre?.next = n.next
            n.next?.pre = n.pre

            if (head == n) {
                head = n.next
            }

            if (tail == n) {
                tail = n.pre
            }
            count --

            data.remove(key)
            return n
        }

        fun get(key: Int): Int {
            val n = pop(key) ?: return -1
            val v = n.value
            put(key, v)
            return v
        }

        fun put(key: Int, value: Int) {
            if (data.contains(key)) {
                pop(key)
            }
            val node = Node(key, value)
            data[key] = node

            head?.pre = node
            node.next = head
            head = node
            if (tail == null) {
                tail = node
            }

            count++
            if (count > capacity) {
                pop(tail!!.key)
            }
        }
    }
}


// 23. Merge k Sorted Lists
// https://leetcode.com/problems/merge-k-sorted-lists/
private interface Leetcode_23 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode?

    private class M1 : Leetcode_23 {
        override fun mergeKLists(lists: Array<ListNode?>): ListNode? {
            if (lists.isEmpty()) return null
            val dummy = ListNode(-1)
            var cur = dummy

            while(true) {
                var minIndex = 0
                for (i in lists.indices) {
                    val n = lists[i] ?: continue
                    if (lists[minIndex] == null || n.`val` < lists[minIndex]!!.`val`) {
                        minIndex = i
                    }
                }

                val next = lists[minIndex] ?: break
                lists[minIndex] = next.next
                cur.next = next
                print("${next.`val`},")
                cur = next
            }
            return dummy.next
        }
    }
}

// 25. Reverse Nodes in k-Group
// https://leetcode.com/problems/reverse-nodes-in-k-group/

private interface Leetcode_25 {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode?

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

    private class M1 : Leetcode_25 {
        override fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
            if (head == null) return null

            val dummy = ListNode(-1)
            dummy.next = head
            var cur:ListNode? = dummy
            while(cur != null) {
                cur = reverse(cur, k)
                // cur = sort(cur, k, reverse)
                // reverse = !reverse
            }
            return dummy.next
        }

        fun reverse(preHead: ListNode?, k: Int) : ListNode? {
            if (preHead == null) return null
            val cur = preHead.next ?: return null

            // check has next k nodes
            var temp : ListNode? = preHead?.next ?: return null
            for (i in 0 until k-1) {
                temp = temp?.next ?: return null
            }

            var counter = 1
            while(cur.next != null && counter < k) {
                val next = cur.next!!
                cur.next = next.next
                next.next = preHead.next
                preHead.next = next
                counter++
            }
            return cur
        }

        // I understood the question wrong at first
        // fun skip(preHead: ListNode?, k: Int) : ListNode? {
        //     if (preHead == null) return null
        //     var cur = preHead.next
        //     for (i in 0 until k) {
        //         cur = cur?.next
        //         if (cur == null) break
        //     }
        //     return cur
        // }
        //
        // fun sort(preHead: ListNode?, k: Int, reverse: Boolean)
        //     = if (reverse) reverse(preHead, k) else skip(preHead, k)
    }

    private class Solution {
        fun reverseKGroup(head: ListNode, k: Int): ListNode {
            var head = head
            val hair = ListNode(0)
            hair.next = head
            var pre = hair
            while (head != null) {
                var tail = pre
                // 查看剩余部分长度是否大于等于 k
                for (i in 0 until k) {
                    tail = tail.next!!
                    if (tail == null) {
                        return hair.next!!
                    }
                }
                val nex = tail.next!!
                val reverse = myReverse(head, tail)
                head = reverse[0]
                tail = reverse[1]
                // 把子链表重新接回原链表
                pre.next = head
                tail.next = nex
                pre = tail
                head = tail.next!!
            }
            return hair.next!!
        }

        fun myReverse(head: ListNode, tail: ListNode): Array<ListNode> {
            var prev = tail.next!!
            var p = head
            while (prev !== tail) {
                val nex = p.next!!
                p.next = prev
                prev = p
                p = nex
            }
            return arrayOf(tail, head)
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

    // Runtime: 2740 ms, faster than 61.00% of Kotlin online submissions for Design Add and Search Words Data Structure.
    // Memory Usage: 268.9 MB, less than 62.00% of Kotlin online submissions for Design Add and Search Words Data Structure.
    // https://leetcode.com/submissions/detail/775782299/
    private class M1 : Leetcode_211 {
        data class C(
            var isEnd: Boolean = false,
            val next: Array<C?> = Array<C?>(26) { null }
        )

        val root = C()

        override fun addWord(word: String) {
            var r = root
            for (i in word.indices) {
                val c = word[i] - 'a'
                val isEnd = i == word.lastIndex
                if (r.next[c] == null) {
                    r.next[c] = C()
                }
                r = r.next[c]!!
                r.isEnd = r.isEnd || isEnd
            }
        }

        override fun search(word: String): Boolean {
            return dfs(root, word, 0)
        }

        fun dfs(root: C, word: String, index: Int) : Boolean {
            if (index >= word.length) {
                println("dfs index:${index} >= word.length:${word.length} ")
                return false
            }
            val char = word[index]

            if (char == '.') {
                return if (index == word.lastIndex) {
                    root.next.any{ it != null && it.isEnd }
                } else root.next.any{ it != null && dfs(it, word, index+1) }
            } else {
                val i = char - 'a'
                val next = root.next[i] ?: return false
                return if (index == word.lastIndex) {
                    next.isEnd
                } else {
                    dfs(next, word, index+1)
                }
            }

        }

    }
}


// 4. Median of Two Sorted Arrays
// https://leetcode.com/problems/median-of-two-sorted-arrays/
private interface Leetcode_4 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double

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

    private class M1 : Leetcode_4 {
        override fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
            TODO("Not yet implemented")
        }
    }
}

// 130. Surrounded Regions

// 130. Surrounded Regions
// https://leetcode.com/problems/surrounded-regions/
private interface Leetcode_130_2 {

    fun solve(board: Array<CharArray>): Unit

    companion object : Testable {
        fun print(board: Array<CharArray>) {
            println("------------")
            println(
                board.joinToString(separator = "\n") {
                    it.joinToString(separator = " ")
                }
            )
            println("------------")
        }
        override fun test() {
            val tests = listOf(
                arrayOf(
                    charArrayOf('O','O','O'),
                    charArrayOf('O','O','O'),
                    charArrayOf('O','O','O'),
                ) to arrayOf(
                    charArrayOf('O','O','O'),
                    charArrayOf('O','O','O'),
                    charArrayOf('O','O','O'),
                ),
                arrayOf(
                    charArrayOf('X','X','X','X'),
                    charArrayOf('X','O','O','X'),
                    charArrayOf('X','X','O','X'),
                    charArrayOf('X','O','X','X'),
                ) to arrayOf(
                    charArrayOf('X','X','X','X'),
                    charArrayOf('X','X','X','X'),
                    charArrayOf('X','X','X','X'),
                    charArrayOf('X','O','X','X'),
                )
            )
            listOf(
                M1()::solve,
            ).runTimedTests(tests) { a, b ->
                println("expected:")
                print(b)
                a.clone().also {
                    invoke(it)
                }.assertEqualTo(b)

            }
        }
    }

    // Not working
    private class M1 : Leetcode_130_2 {
        private val pending = ArrayList<Pair<Int, Int>>()

        override fun solve(board: Array<CharArray>): Unit {
            val n = board.size
            val m = board[0].size

            print(board)

            for (r in 1 until n-1) {
                for (c in 1 until m-1) {
                    if (board[r][c] == 'X') continue
                    if (dfs(board, r, c)) {
                        pending.forEach{ (r, c) ->
                            board[r][c] = 'X'
                        }
                    } else {
                        pending.clear()
                    }
                }
            }
            print(board)
        }

        fun dfs(board: Array<CharArray>, r: Int, c: Int) : Boolean {
            if (!inBound(board, r, c)) return false
            val pair = r to c
            if (pending.contains(pair)) return true
            pending += r to c

            fun checker(r: Int, c: Int) : Boolean {
                if (!inBound(board, r, c)) return false
                if (board[r][c] != 'X') return true
                return dfs(board, r, c)

                // if (board[r][c] == 'O') return dfs(board, r, c)
                // return true
            }

            return checker(r-1, c) && checker(r,c-1)
                    && checker(r+1,c) && checker(r,c+1)

        }

        fun inBound(board: Array<CharArray>, r: Int, c: Int) : Boolean {
            val n = board.size
            val m = board[0].size
            return !(r < 0 || r >= n || c < 0 || c >= m)
            // return !(r < 1 || r >= n-1 || c < 1 || c >= m-1)
        }
    }

    // Runtime: 356 ms, faster than 72.97% of Kotlin online submissions for Surrounded Regions.
    // Memory Usage: 48.5 MB, less than 75.68% of Kotlin online submissions for Surrounded Regions.
    // https://leetcode.com/submissions/detail/776042031/
    private class M2 : Leetcode_130_2 {
        override fun solve(board: Array<CharArray>): Unit {
            val n = board.size
            val m = board[0].size

            for (y in 0 until n) {
                for (x in 0 until m) {
                    if (x==0||x==m-1||y==0||y==n-1){
                        if (board[y][x] == 'O') {
                            markI(board, y, x)
                        }
                    }
                }
            }

            for (y in 0 until n) {
                for (x in 0 until m) {
                    board[y][x] = when(board[y][x]){
                        'O' -> 'X'
                        'I' -> 'O'
                        else -> board[y][x]
                    }
                }
            }
        }
        fun inBound(board: Array<CharArray>, r: Int, c: Int) : Boolean {
            val n = board.size
            val m = board[0].size
            return !(r < 0 || r >= n || c < 0 || c >= m)
            // return !(r < 1 || r >= n-1 || c < 1 || c >= m-1)
        }
        fun markI(board: Array<CharArray>, y: Int, x: Int) {
            if (!inBound(board, y, x)) return
            if (board[y][x] != 'O') return
            board[y][x] = 'I'
            markI(board, y+1, x)
            markI(board, y-1, x)
            markI(board, y, x+1)
            markI(board, y, x-1)
        }
    }
}




val _20220817 = listOf<Testable>(

)