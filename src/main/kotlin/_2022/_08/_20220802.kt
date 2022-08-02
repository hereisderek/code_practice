@file:Suppress("DuplicatedCode", "ClassName")

package _2022._08

import Testable
import utils.assertEqualTo
import utils.runTimedTests
import java.util.PriorityQueue


// 208. Implement Trie (Prefix Tree)
//
interface Leetcode_208 {
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
