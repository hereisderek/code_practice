@file:Suppress("DuplicatedCode", "ClassName")

package _2022._07

import Testable
import utils.TreeNode
import utils.Node
import utils.assertEqualTo
import utils.runTimedTests
import java.util.*


// 226. Invert Binary Tree
// https://leetcode.com/problems/invert-binary-tree/
private interface Leetcode_226 {
    fun invertTree(root: TreeNode?): TreeNode?

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

    private class M1 : Leetcode_226 {
        override fun invertTree(root: TreeNode?): TreeNode? {
            if (root == null) return null
            val t = root.right
            root.right = root.left
            root.left = t
            if (root.right != null) invertTree(root.right)
            if (root.left != null) invertTree(root.left)
            return root
        }
    }
}


// 104. Maximum Depth of Binary Tree
// https://leetcode.com/problems/maximum-depth-of-binary-tree/
private interface Leetcode_104 {
    fun maxDepth(root: TreeNode?): Int

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

    private class S : Leetcode_104 {
        override fun maxDepth(root: TreeNode?): Int {
            return root?.run {
                1 + Math.max(
                    left?.let { maxDepth(it) } ?: 0,
                    right?.let { maxDepth(it) } ?: 0
                )
            } ?: 0
        }
    }
}

// 104. Maximum Depth of Binary Tree
// https://leetcode.com/problems/maximum-depth-of-binary-tree/
private interface Leetcode_543 {
    fun diameterOfBinaryTree(root: TreeNode?): Int
    private class S : Leetcode_543 {
        private var max = 0


        override fun diameterOfBinaryTree(root: TreeNode?): Int {
            if (root == null) return 0

            return Math.max(
                diameter(root) - 1, max
            )
        }


        fun diameter(root: TreeNode?): Int {
            if (root == null) return 0
            if (root.left == null && root.right == null) return 1

            val left = if (root.left != null) (diameter(root.left)) else 0
            val right = if (root.right != null) (diameter(root.right)) else 0

            if (root.left != null && root.right != null) {
                val passRoot = left + right
                if (passRoot > max) max = passRoot
            }

            return Math.max(left, right) + 1
        }
    }
}

// 110. Balanced Binary Tree
// https://leetcode.com/problems/balanced-binary-tree/
private interface Leetcode_110 {
    fun isBalanced(root: TreeNode?): Boolean
    private class S : Leetcode_110 {
        override fun isBalanced(root: TreeNode?): Boolean {
            fun getHeight(root: TreeNode?): Int {
                if (root == null) return 0
                if (root.left == null && root.right == null) return 1
                val left = root.left?.let {
                    getHeight(it)
                } ?: 0
                val right = root.right?.let {
                    getHeight(it)
                } ?: 0
                if (left == -1 || right == -1) return -1
                if (Math.abs(left - right) > 1) return -1
                val height = Math.max(left, right) + 1
                return height
            }


            if (getHeight(root) == -1) return false
            return true
        }
    }
}

// 100. Same Tree
// https://leetcode.com/problems/same-tree/

private interface Leetcode_100 {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean
    private class S : Leetcode_100 {
        override fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
            if (p == q) return true

            if (p?.`val` != q?.`val`) return false

            if (
                !isSameTree(p?.left, q?.left)
                || !isSameTree(p?.right, q?.right)
            ) return false

            return true
        }
    }
}

// 572. Subtree of Another Tree
// https://leetcode.com/problems/subtree-of-another-tree/

// MARK:
// input:
// [3,4,5,1,2,null,null,null,null,0]
// [4,1,2]
// expected: [False]

private interface Leetcode_572 {
    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean


    // brute force
    private class S : Leetcode_572 {
        override fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
            if (root == null && subRoot == null) return true
            if ((root == null) != (subRoot == null)) return false
            return isSubtree(root, subRoot, false)
        }


        fun isSubtree(root: TreeNode?, subRoot: TreeNode?, validate: Boolean): Boolean {
            if (root == null && subRoot == null) return true
            // what is subRoot is null and root is not, should we consider it as true?
            // if (subRoot == null) return true
            // if (root == null) return false

            if ((root == null) != (subRoot == null)) return false

            if (
                root?.`val` == subRoot?.`val`
                && isSubtree(root?.left, subRoot?.left, true)
                && isSubtree(root?.right, subRoot?.right, true)
            ) {
                return true
            }

            if (validate) return false

            if (isSubtree(root?.left, subRoot, false)) return true
            if (isSubtree(root?.right, subRoot, false)) return true

            return false
        }
    }
}

// 235. Lowest Common Ancestor of a Binary Search Tree
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
private interface Leetcode_235 {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode?

    private class M1 : Leetcode_235 {
        override fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
            if (root == null || p == null || q == null) return null
            val stackP = Stack<TreeNode>()
            val stackQ = Stack<TreeNode>()
            hasDescendant(root, p, stackP)
            hasDescendant(root, p, stackQ)
            val ln1 = stackToLinkedNode(stackP)
            val ln2 = stackToLinkedNode(stackQ)

            var h1 = ln1
            var h2 = ln2
            while(h1 != null && h2 != null) {
                if (h1?.next == h2?.next) {
                    h1 = h1.next
                    h2 = h2.next
                }
            }
            return h1!!.`val`!!
        }

        private fun stackToLinkedNode(stack: Stack<TreeNode>) : Node<TreeNode?>? {
            val head = Node<TreeNode?>(null)

            while(!stack.isEmpty()) {
                val n = Node(stack.pop())
                n.next = head.next
                head.next = n
            }
            return head.next
        }

        private fun hasDescendant(
            root: TreeNode,
            p: TreeNode,
            stack: Stack<TreeNode>
        ): Boolean {

            stack.push(p)
            if (root == p) {
                return true
            }
            if (root.left != null) {
                if (hasDescendant(root.left!!, p, stack)) return true
            }

            if (root.right != null) {
                if (hasDescendant(root.right!!, p, stack)) return true
            }
            stack.pop()
            return false
        }
    }


    // Runtime: 333 ms, faster than 84.55% of Kotlin online submissions for Lowest Common Ancestor of a Binary Search Tree.
    // Memory Usage: 44.8 MB, less than 86.45% of Kotlin online submissions for Lowest Common Ancestor of a Binary Search Tree.
    // https://leetcode.com/submissions/detail/760273465/
    private class M2 : Leetcode_235 {
        override fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
            var last = root
            var h = root
            while (true) {
                last = h
                when {
                    hasDesc(h?.left, p) && hasDesc(h?.left, q) -> {
                        h = h?.left
                    }

                    hasDesc(h?.right, p) && hasDesc(h?.right, q) -> {
                        h = h?.right
                    }

                    else -> return last
                }
            }
        }

        private fun hasDesc(root: TreeNode?, n: TreeNode?): Boolean {
            if (root == null || n == null) return false
            if (root == n) return true
            return hasDesc(root.left, n) || hasDesc(root.right, n)
        }
    }

    private class M3 : Leetcode_235 {
        override fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
            if (root == null || p == null || q == null) return root

            var h = root

            while(true) {
                when{
                    p.`val` < h!!.`val` && q.`val` < h!!.`val` -> {
                        h = h.right
                    }
                    p.`val` > h!!.`val` && q.`val` > h!!.`val` -> {
                        h = h.left
                    }
                    else -> return h
                }
            }
        }
    }
}

// 703. Kth Largest Element in a Stream
// https://leetcode.com/problems/kth-largest-element-in-a-stream/
private interface Leetcode_703 {
    fun add(`val`: Int): Int

    // Runtime: 606 ms, faster than 39.45% of Kotlin online submissions for Kth Largest Element in a Stream.
    // Memory Usage: 61 MB, less than 58.72% of Kotlin online submissions for Kth Largest Element in a Stream.
    // https://leetcode.com/submissions/detail/760367680/
    private class S(val k: Int, nums: IntArray) : Leetcode_703 {
        private val pq = PriorityQueue<Int>()
        init {
            nums.forEach {
                pq.offer(it)
                if (pq.size > k) {
                    pq.poll()
                }
            }
        }
        override fun add(`val`: Int): Int {
            pq.offer(`val`)
            if (pq.size > k) {
                pq.poll()
            }
            return pq.peek()
        }
    }
}

// 1046. Last Stone Weight
// https://leetcode.com/problems/last-stone-weight/
private interface Leetcode_1046 {
    fun lastStoneWeight(stones: IntArray): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                intArrayOf(2,7,4,1,8,1) to 1
            )

            listOf(
                S()::lastStoneWeight
            ).runTimedTests(tests) {a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    private class S : Leetcode_1046 {
        override fun lastStoneWeight(stones: IntArray): Int {
            val q = PriorityQueue<Int>()
            stones.forEach{
                q.offer(-it)
            }

            while(q.size > 1) {
                val a = q.poll()
                val b = q.poll()
                val r = Math.abs(a-b)
                if (r != 0) {
                    q.offer(-r)
                }
            }
            return if (q.isEmpty()) 0 else (-1 * q.poll())
        }
    }
}

// 70. Climbing Stairs
// https://leetcode.com/problems/climbing-stairs/
private interface Leetcode_70 {
    fun climbStairs(n: Int): Int

    // Runtime: 228 ms, faster than 26.86% of Kotlin online submissions for Climbing Stairs.
    // Memory Usage: 33.6 MB, less than 38.59% of Kotlin online submissions for Climbing Stairs.
    // https://leetcode.com/submissions/detail/761040530/
    private class M : Leetcode_70 {
        private val map = HashMap<Int, Int>()
        override fun climbStairs(n: Int): Int {
            return when(n) {
                0 -> 0
                1 -> 1
                2 -> 2
                else -> map.getOrPut(n) {
                    climbStairs(n - 1) + climbStairs(n - 2)
                }
            }
        }
    }

    // sample 115 ms submission
    private class S : Leetcode_70 {
        override fun climbStairs(n: Int) : Int {
            val memo = IntArray(n+1) { -1 }
            return helper(n, memo)
        }

        fun helper(n: Int, memo: IntArray) : Int {
            if (n == 0) return 1
            if (n < 0) return 0

            if (memo[n] == -1) {
                memo[n] = helper(n-1, memo) + helper(n-2, memo)
            }

            return memo[n]
        }
    }
}

// 746. Min Cost Climbing Stairs
// https://leetcode.com/problems/min-cost-climbing-stairs/
private interface Leetcode_746 {
    fun minCostClimbingStairs(cost: IntArray): Int

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                // intArrayOf(10,15,20) to 15,
                // intArrayOf(1,100,1,1,1,100,1,1,100,1) to 6,
                intArrayOf(
                    841,462,566,398,243,248,238,650,989,576,361,126,334,729,446,897,953,38,195,679,65,707,196,705,569,275,259,872,630,965,978,109,56,523,851,887,91,544,598,963,305,481,959,560,454,883,50,216,732,572,511,156,177,831,122,667,548,978,771,880,922,777,990,498,525,317,469,151,874,202,519,139,670,341,514,469,858,913,94,849,839,813,664,163,3,802,21,634,944,901,446,186,843,742,330,610,932,614,625,169,833,4,81,55,124,294,71,24,929,534,621,543,417,534,427,327,179,90,341,949,368,692,646,290,488,145,273,617,596,82,538,751,80,616,763,826,932,184,630,478,163,925,259,237,839,602,60,786,603,413,816,278,4,35,243,64,631,405,23,638,618,829,481,877,756,482,999,973,718,157,262,752,931,882,741,40,77,535,542,879,607,879,321,46,210,116,244,830,591,285,382,925,48,497,913,203,239,696,162,623,291,525,950,27,546,293,108,577,672,354,256,3,671,998,22,989,557,424,251,923,542,243,46,488,80,374,372,334,190,817,150,742,362,196,75,193,162,645,859,758,433,903,199,289,175,303,475,818,213,576,181,668,243,297,572,549,840,161,292,719,226,338,981,345,203,655,210,65,111,746,76,935,406,646,976,567,32,726,638,674,727,861,426,297,349,464,973,341,452,826,223,805,940,458,468,967,107,345,987,553,407,916,103,324,367,864,74,946,712,596,105,194,79,634,855,703,70,170,543,208,739,632,663,880,857,824,258,743,488,659,647,470,958,492,211,927,356,488,744,570,143,674,502,589,270,80,6,463,506,556,495,713,407,229,689,280,162,454,757,565,267,575,417,948,607,269,852,938,560,24,222,580,604,800,628,487,485,615,796,384,555,226,412,445,503,810,949,966,28,768,83,213,883,963,831,390,951,378,497,440,780,209,734,290,96,398,146,56,445,880,910,858,671,164,552,686,748,738,837,556,710,787,343,137,298,685,909,828,499,816,538,604,652,7,272,729,529,343,443,593,992,434,588,936,261,873,64,177,827,172,712,628,609,328,672,376,628,441,9,92,525,222,654,699,134,506,934,178,270,770,994,158,653,199,833,802,553,399,366,818,523,447,420,957,669,267,118,535,971,180,469,768,184,321,712,167,867,12,660,283,813,498,192,740,696,421,504,795,894,724,562,234,110,88,100,408,104,864,473,59,474,922,759,720,69,490,540,962,461,324,453,91,173,870,470,292,394,771,161,777,287,560,532,339,301,90,411,387,59,67,828,775,882,677,9,393,128,910,630,396,77,321,642,568,817,222,902,680,596,359,639,189,436,648,825,46,699,967,202,954,680,251,455,420,599,20,894,224,47,266,644,943,808,653,563,351,709,116,849,38,870,852,333,829,306,881,203,660,266,540,510,748,840,821,199,250,253,279,672,472,707,921,582,713,900,137,70,912,51,250,188,967,14,608,30,541,424,813,343,297,346,27,774,549,931,141,81,120,342,288,332,967,768,178,230,378,800,408,272,596,560,942,612,910,743,461,425,878,254,929,780,641,657,279,160,184,585,651,204,353,454,536,185,550,428,125,889,436,906,99,942,355,666,746,964,936,661,515,978,492,836,468,867,422,879,92,438,802,276,805,832,649,572,638,43,971,974,804,66,100,792,878,469,585,254,630,309,172,361,906,628,219,534,617,95,190,541,93,477,933,328,984,117,678,746,296,232,240,532,643,901,982,342,918,884,62,68,835,173,493,252,382,862,672,803,803,873,24,431,580,257,457,519,388,218,970,691,287,486,274,942,184,817,405,575,369,591,713,158,264,826,870,561,450,419,606,925,710,758,151,533,405,946,285,86,346,685,153,834,625,745,925,281,805,99,891,122,102,874,491,64,277,277,840,657,443,492,880,925,65,880,393,504,736,340,64,330,318,703,949,950,887,956,39,595,764,176,371,215,601,435,249,86,761,793,201,54,189,451,179,849,760,689,539,453,450,404,852,709,313,529,666,545,399,808,290,848,129,352,846,2,266,777,286,22,898,81,299,786,949,435,434,695,298,402,532,177,399,458,528,672,882,90,547,690,935,424,516,390,346,702,781,644,794,420,116,24,919,467,543,58,938,217,502,169,457,723,122,158,188,109,868,311,708,8,893,853,376,359,223,654,895,877,709,940,195,323,64,51,807,510,170,508,155,724,784,603,67,316,217,148,972,19,658,5,762,618,744,534,956,703,434,302,541,997,214,429,961,648,774,244,684,218,49,729,990,521,948,317,847,76,566,415,874,399,613,816,613,467,191
                ) to 10
            )
            listOf(
                S()::minCostClimbingStairs,
                // S()::minCostClimbingStairs,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    // Time Limit Exceeded
    private class m : Leetcode_746 {
        private var min = -1
        override fun minCostClimbingStairs(cost: IntArray): Int {
            min = -1
            step(cost, 0, -1)
            step(cost, 0, -2)
            return min
        }

        fun step(cost: IntArray, sum: Int, step: Int) {
            if (min != -1 && sum > min) return
            if (step >= (cost.size - 2)) {
                if (min == -1) {
                    min = sum
                } else min = Math.min(min, sum)
            } else {
                if (step+1>=0) step(cost, sum + cost[step+1], step+1)
                if (step+2>=0) step(cost, sum + cost[step+2], step+2)
            }
        }
    }
    private class S : Leetcode_746 {
        private lateinit var dp: IntArray
        override fun minCostClimbingStairs(cost: IntArray): Int {
            dp = IntArray(cost.size){
                when (it) {
                    cost.size - 1 -> cost[it]
                    cost.size - 2 -> cost[it]
                    else -> -1
                }
            }

            return Math.min(
                minCostToTop(cost, 0),
                minCostToTop(cost, 1)
            )
        }
        fun minCostToTop(cost: IntArray, i: Int) : Int {
            if (dp[i] != -1) return dp[i]

            val r = Math.min(
                minCostToTop(cost, i+1),
                minCostToTop(cost, i+2)
            ) + cost[i]
            dp[i] = r
            return r
        }
    }
}