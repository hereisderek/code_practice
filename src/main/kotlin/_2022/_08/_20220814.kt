@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.TreeNode
import utils.assertEqualTo
import utils.runTimedTests
import utils.tupleOf
import java.util.*


// 199. Binary Tree Right Side View
// https://leetcode.com/problems/binary-tree-right-side-view/
private interface Leetcode_199 {
    fun rightSideView(root: TreeNode?): List<Int>

    companion object : Testable {
        override fun test() {}
    }

    // Runtime: 292 ms, faster than 40.23% of Kotlin online submissions for Binary Tree Right Side View.
    // Memory Usage: 36.3 MB, less than 30.88% of Kotlin online submissions for Binary Tree Right Side View.
    // https://leetcode.com/submissions/detail/773318529/
    private class M1 : Leetcode_199 {
        override fun rightSideView(root: TreeNode?): List<Int> {

            if (root == null) return emptyList()

            val res = ArrayList<Int>()
            val q = LinkedList<TreeNode>()
            val level = ArrayList<TreeNode>() // Not needed, see M1_ below

            q.add(root)
            while(q.isNotEmpty()) {
                val size = q.size
                level.clear()
                for (i in 0 until size) {
                    val n = q.pop()
                    level.add(n)
                    if (n.left != null) q.add(n.left!!)
                    if (n.right != null) q.add(n.right!!)
                }
                if (level.isNotEmpty()) {
                    res.add(level.last().`val`)
                }
            }
            return res
        }
    }

    // slightly improved
    private class M1_ : Leetcode_199 {
        override fun rightSideView(root: TreeNode?): List<Int> {

            if (root == null) return emptyList()

            val res = ArrayList<Int>()
            val q = LinkedList<TreeNode>()

            q.add(root)
            while(q.isNotEmpty()) {
                val size = q.size
                var rightMost: TreeNode? = null
                for (i in 0 until size) {
                    val n = q.pop()
                    rightMost = n
                    if (n.left != null) q.add(n.left!!)
                    if (n.right != null) q.add(n.right!!)
                }
                if (rightMost != null) {
                    res.add(rightMost.`val`)
                }
            }
            return res
        }
    }
}

// 1448. Count Good Nodes in Binary Tree
// https://leetcode.com/problems/count-good-nodes-in-binary-tree/


//
//
private interface Leetcode_1448 {
    fun goodNodes(root: TreeNode?): Int


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

    // Runtime: 715 ms, faster than 21.05% of Kotlin online submissions for Count Good Nodes in Binary Tree.
    // Memory Usage: 73.2 MB, less than 50.88% of Kotlin online submissions for Count Good Nodes in Binary Tree.
    // https://leetcode.com/submissions/detail/773335970/
    private class M1 : Leetcode_1448 {
        var counter = 0
        override fun goodNodes(root: TreeNode?): Int {
            dfs(root, Int.MIN_VALUE)
            return counter
        }

        fun dfs(node: TreeNode?, max: Int) {
            if (node == null) return
            var max = max
            if (node.`val` >= max) {
                max = node.`val`
                println("added $max")
                counter++
            }
            dfs(node.left, max)
            dfs(node.right, max)
        }
    }
}

// 98. Validate Binary Search Tree
// https://leetcode.com/problems/validate-binary-search-tree/
private interface Leetcode_98 {
    fun isValidBST(root: TreeNode?): Boolean

    private class S2 : Leetcode_98 {
        var cur = Int.MIN_VALUE
        override fun isValidBST(root: TreeNode?): Boolean {
            return bfs(root)
        }
        fun bfs(node: TreeNode?) : Boolean {
            if (node == null) return true
            if (!bfs(node.left)) return false

            if (node.`val` <= cur) return false
            cur = node.`val`

            if (!bfs(node.right)) return false
            return true
        }
    }

    private class S1 : Leetcode_98 {
        var cur: Int? = null
        override fun isValidBST(root: TreeNode?): Boolean {
            return bfs(root)
        }
        fun bfs(node: TreeNode?) : Boolean {
            if (node == null) return true
            if (!bfs(node.left)) return false

            if (cur != null) {
                if (node.`val` <= cur!!) return false
            }

            cur = node.`val`

            if (!bfs(node.right)) return false
            return true
        }
    }
}

// 230. Kth Smallest Element in a BST
// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
private interface Leetcode_230 {
    fun kthSmallest(root: TreeNode?, k: Int): Int

    // Runtime: 405 ms, faster than 29.55% of Kotlin online submissions for Kth Smallest Element in a BST.
    // Memory Usage: 44.7 MB, less than 9.55% of Kotlin online submissions for Kth Smallest Element in a BST.
    // https://leetcode.com/submissions/detail/773374349/
    private class S : Leetcode_230 {
        var counter = 0
        var value: Int = 0
        override fun kthSmallest(root: TreeNode?, k: Int): Int {
            dfs(root, k)
            return value
        }

        fun dfs(root: TreeNode?, k: Int) {
            if (root == null) return
            dfs(root.left, k)

            counter++
            if (counter > k) return

            value = root.`val`
            dfs(root.right, k)
        }
    }
    private class Solution : Leetcode_230 {
        override fun kthSmallest(root: TreeNode?, k: Int): Int {
            var root = root
            var k = k
            val stack: Deque<TreeNode> = ArrayDeque()
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root)
                    root = root.left
                }
                root = stack.pop()
                --k
                if (k == 0) {
                    break
                }
                root = root.right
            }
            return root!!.`val`
        }
    }

}

// 105. Construct Binary Tree from Preorder and Inorder Traversal
private interface Leetcode_105 {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode?

    private class M : Leetcode_105 {
        override fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
            TODO("Not yet implemented")
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


// 621. Task Scheduler
// https://leetcode.com/problems/task-scheduler/
private interface Leetcode_621 {
    fun leastInterval(tasks: CharArray, n: Int): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(charArrayOf('A','A','A','B','B','B'), 2, 8),
                tupleOf(charArrayOf('A','A','A','A','A','A','B','C','D', 'E', 'F', 'G'), 2, 16),
            )
            listOf(
                M1()::leastInterval,
                S1()::leastInterval,
                // M2()::leastInterval,
            ).runTimedTests(tests) { a, b, c ->
                invoke(a, b).assertEqualTo(c)
            }
        }
    }

    private class M1 : Leetcode_621 {

        data class C(
            val c: Char,
            var count: Int,
            var nextI: Int = 0
        )

        fun useNextC(q: PriorityQueue<C>, i: Int, n: Int) : C? {
            val l = ArrayList<C>()
            var res: C? = null
            while(res == null && q.isNotEmpty()) {
                val c = q.poll()
                if (c.count == 0) continue
                if (c.nextI <= i) {
                    res = c
                    c.nextI = i + n + 1
                    c.count = (c.count - 1)
                }
                if (c.count != 0) {
                    l += c
                }
            }

            l.forEach { q.add(it) }
            return res
        }

        override fun leastInterval(tasks: CharArray, n: Int): Int {
            val map = HashMap<Char, Int>()
            val heat = PriorityQueue<C>(){ a, b ->
                if (a.count == b.count) {
                    a.nextI - b.nextI
                } else {
                    b.count - a.count
                }
            }

            var total = 0
            for (c in tasks) {
                total++
                map[c] = (map[c] ?: 0) + 1
            }

            map.forEach { t, u ->
                heat.offer(C(t, u))
            }
            var i = 0
            while (heat.isNotEmpty()) {
                val n = useNextC(heat, i, n)
                print(n?.c ?: "-")
                i++
            }


            return i
        }
    }

    private class S1 : Leetcode_621 {
        override fun leastInterval(tasks: CharArray, n: Int): Int {
            val map = HashMap<Char, Int>()
            val heat = PriorityQueue<Int>(){ a, b -> b - a }
            val q = LinkedList<Pair<Int, Int>>()

            for (c in tasks) {
                map[c] = (map[c] ?: 0) + 1
            }
            map.forEach { t, u -> heat.offer(u) }
            // a,b,0,a
            var i = 0
            while (heat.isNotEmpty() || q.isNotEmpty()) {
                i++
                if (q.isNotEmpty() && q.peek().second == i) {
                    heat.add(q.pop().first)
                }

                if (heat.isNotEmpty()) {
                    val t = heat.poll() - 1
                    if (t != 0) {
                        val pair = t to i + n + 1
                        q.add(pair)
                    }
                }
            }
            return i
        }
    }

    // not working
    private class M2 : Leetcode_621 {
        private class Item(
            var count: Int,
            var nextI: Int = 0
        )
        override fun leastInterval(tasks: CharArray, n: Int): Int {
            // Not working for test case:
            // ["A","A","A","A","A","A","B","C","D","E","F","G"]
            //
            val heat = PriorityQueue<Item>(){ a, b ->
                if (a.count != b.count) b.count - a.count else a.nextI - b.nextI
            }
            val map = HashMap<Char, Int>()
            for (c in tasks) {
                map[c] = (map[c] ?: 0) + 1
            }
            map.forEach { t, u -> heat.offer(Item(u)) }

            var i = 0
            while (heat.isNotEmpty()) {
                val t = heat.peek()
                if (t.count > 0 && t.nextI <= i) {
                    heat.poll().apply {
                        count--
                        nextI = i + n + 1
                        println("i:$i new count:$count nextI:$nextI")
                        if (count > 0) heat.offer(this)
                    }
                }
                i++
            }

            return i
        }
    }
}

// 355. Design Twitter
private interface Leetcode_355 {
    fun postTweet(userId: Int, tweetId: Int)
    fun getNewsFeed(userId: Int): List<Int>
    fun follow(followerId: Int, followeeId: Int)
    fun unfollow(followerId: Int, followeeId: Int)

    // Runtime: 301 ms, faster than 30.91% of Kotlin online submissions for Design Twitter.
    // Memory Usage: 37.2 MB, less than 38.18% of Kotlin online submissions for Design Twitter.
    // https://leetcode.com/submissions/detail/773836551/
    private class S : Leetcode_355 {
        // userId, tweetId
        val feeds = ArrayList<Pair<Int, Int>>()
        val follows = HashMap<Int, HashSet<Int>>()

        override fun postTweet(userId: Int, tweetId: Int) {
            println("postTweet $userId-$tweetId")
            feeds += userId to tweetId
        }

        override fun getNewsFeed(userId: Int): List<Int> {
            val set = follows[userId]
            var i = feeds.lastIndex
            val list = ArrayList<Int>()

            while(list.size != 10 && i >= 0) {
                val feed = feeds[i]
                println("getNewsFeed userId:$userId feed.first:${feed.first}")

                if (feed.first == userId || (set != null && feed.first in set)) {
                    list.add(feed.second)
                }
                i--
            }
            return list
        }

        override fun follow(followerId: Int, followeeId: Int) {
            val set = follows[followerId]
            if (set == null) {
                follows[followerId] = HashSet<Int>()
            }
            follows[followerId]!!.add(followeeId)
        }

        override fun unfollow(followerId: Int, followeeId: Int) {
            val set = follows[followerId] ?: return
            set -= followeeId
        }
    }
}

// 332. Reconstruct Itinerary
// https://leetcode.com/problems/reconstruct-itinerary/
private interface Leetcode_332 {
    fun findItinerary(tickets: List<List<String>>): List<String>

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                listOf(
                    // [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
                    listOf("JFK","KUL"),listOf("JFK","NRT"),listOf("NRT","JFK")
                // ["JFK","NRT","JFK","KUL"]
                ) to listOf("JFK","NRT","JFK","KUL"),
            )
            listOf(
                M1()::findItinerary
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    // Runtime: 266 ms, faster than 97.73% of Kotlin online submissions for Reconstruct Itinerary.
    // Memory Usage: 44.4 MB, less than 93.18% of Kotlin online submissions for Reconstruct Itinerary.
    // https://leetcode.com/submissions/detail/774154210/
    private class M1 : Leetcode_332 {

        override fun findItinerary(tickets: List<List<String>>): List<String> {
            val res = ArrayList<String>()
            val map = HashMap<String, ArrayList<String>>()

            for (t in tickets) {
                if (map[t[0]] == null) {
                    map[t[0]] = ArrayList<String>()
                }
                map[t[0]]!!.add(t[1])
            }

            val sorted = map.mapValues {
                ArrayList(it.value.sortedBy { it })
            }

            fun dfs(from: String) : Boolean {
                res.add(from)
                if (res.size == tickets.size + 1) return true

                val dests = sorted[from] ?: return false
                if (dests.isEmpty()) return false

                val temp = ArrayList(dests)
                for (t in temp) {
                    dests.remove(t)

                    if (dfs(t)) {
                        return true
                    } else {
                        // res.removeLast() // not available in leetcode
                        res.removeAt(res.lastIndex)
                        dests.add(t)
                    }
                }
                return false
            }

            dfs("JFK")
            return res
        }

    }
}


val _20220814 = listOf<Testable>(
    // Leetcode_621,
    Leetcode_332,
)