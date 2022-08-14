@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.assertEqualTo
import utils.runTimedTests
import utils.toStr


// 133. Clone Graph
// https://leetcode.com/problems/clone-graph/
private interface Leetcode_133 {
    data class Node(
        var `val`: Int,
        var neighbors: ArrayList<Node?> = ArrayList()
    )
    fun cloneGraph(node: Node?): Node?
    companion object : Testable {
        override fun test() {
        }
    }

    // not working! (likely)
    private class M1 : Leetcode_133 {
        private val map = HashMap<Node, Node>()

        override fun cloneGraph(node: Node?): Node? {
            if (node == null) return null
            val shallow = shallow(node)
            deep(shallow, node)
            return shallow
        }

        fun shallow(node: Node) : Node {
            if (map.contains(node)) return map[node]!!
            val n = Node(node.`val`)
            map[node] = n
            node.neighbors.forEach{
                shallow(it!!)
            }
            return n
        }

        fun deep(n: Node, old: Node) : Node {
            if (n.neighbors.size == old.neighbors.size) return n

            val old_neighbors = old.neighbors
            val neighbors = ArrayList<Node?>()
            n.neighbors = neighbors

            if (old_neighbors != null && old_neighbors.isNotEmpty()) {
                for (t in old_neighbors) {
                    neighbors.add(
                        map[t]
                    )
                }
            }

            return n
        }
    }

    // Runtime: 358 ms, faster than 40.10% of Kotlin online submissions for Clone Graph.
    // Memory Usage: 37.5 MB, less than 27.92% of Kotlin online submissions for Clone Graph.
    // https://leetcode.com/submissions/detail/765648368/
    private class M2 : Leetcode_133 {
        private val map = HashMap<Int, Node>()
        override fun cloneGraph(node: Node?): Node? {
            if (node == null) return null
            val res = dfs(node)
            attachNeighbors(node)
            return res
        }

        fun dfs(node: Node) : Node {
            if (map.contains(node.`val`)) return map[node.`val`]!!

            val out = shallowCopy(node)
            node.neighbors.forEach{
                if (it != null) dfs(it)
            }
            return out
        }

        fun attachNeighbors(node: Node) {
            val copy = map[node.`val`]!!
            if (node.neighbors.size != copy.neighbors?.size) {
                println("attachNeighbors:${node.`val`}")
                val neighbors = ArrayList<Node?>()
                copy.neighbors = neighbors
                node.neighbors.forEach{
                    neighbors.add(map[it?.`val`])
                }
                node.neighbors.forEach{
                    attachNeighbors(it!!)
                }
            }
        }

        fun shallowCopy(node: Node) : Node {
            return map.getOrPut(node.`val`) {
                println("shallowCopy:${node.`val`}")
                Node(node.`val`)
            }
        }
    }

    // dfs but in one go
    // Runtime: 435 ms, faster than 6.60% of Kotlin online submissions for Clone Graph.
    // Memory Usage: 37.2 MB, less than 64.97% of Kotlin online submissions for Clone Graph.
    // https://leetcode.com/submissions/detail/765811034/
    private class M3 : Leetcode_133 {
        // old to new
        private val map = HashMap<Node, Node>()
        override fun cloneGraph(node: Node?): Node? {
            if (node == null) return node
            return clone(node)
        }
        private fun clone(node: Node) : Node  {
            if (map.contains(node)) return map[node]!!
            val n = Node(node.`val`, ArrayList<Node?>())

            node.neighbors.forEach {
                if (it != null) n.neighbors.add(clone(it))

            }

            return n
        }

    }
}


// 695. Max Area of Island
// https://leetcode.com/problems/max-area-of-island/
private interface Leetcode_695 {
    fun maxAreaOfIsland(grid: Array<IntArray>): Int
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                arrayOf(
                    intArrayOf(0,0,1,0,0,0,0,1,0,0,0,0,0),
                    intArrayOf(0,0,0,0,0,0,0,1,1,1,0,0,0),
                    intArrayOf(0,1,1,0,1,0,0,0,0,0,0,0,0),
                    intArrayOf(0,1,0,0,1,1,0,0,1,0,1,0,0),
                    intArrayOf(0,1,0,0,1,1,0,0,1,1,1,0,0),
                    intArrayOf(0,0,0,0,0,0,0,0,0,0,1,0,0),
                    intArrayOf(0,0,0,0,0,0,0,1,1,1,0,0,0),
                    intArrayOf(0,0,0,0,0,0,0,1,1,0,0,0,0),
                ) to 6,
                arrayOf(
                    intArrayOf(0,0,0,0,0,0,0,0)
                ) to 0,
            )
            listOf(
                M1()::maxAreaOfIsland,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    private class M1 : Leetcode_695 {
        private var max = 0
        override fun maxAreaOfIsland(grid: Array<IntArray>): Int {
            val n = grid.size
            val m = grid[0].size

            for (y in 0 until n) {
                for (x in 0 until m) {
                    if (grid[y][x] == 1) {
                        max = Math.max(max, flood(grid, x, y))
                    }
                }
            }
            return max
        }

        fun flood(grid: Array<IntArray>, x: Int, y: Int, area: Int = 0) : Int {
            var area = area
            if (isLand(grid, x, y)) {
                area += 1
                grid[y][x] = 0
            }
            // up
            if (isLand(grid, x, y-1)) area += flood(grid, x, y-1)
            // left
            if (isLand(grid, x-1, y)) area += flood(grid, x-1, y)
            // bottom
            if (isLand(grid, x, y+1)) area += flood(grid, x, y+1)
            // right
            if (isLand(grid, x+1, y)) area += flood(grid, x+1, y)
            return area
        }

        fun isLand(grid: Array<IntArray>, x: Int, y: Int) : Boolean {
            val n = grid.size
            val m = grid[0].size
            if (x < 0 || x >= m || y < 0 || y >= n) return false
            return grid[y][x] == 1
        }
    }
}


// 417. Pacific Atlantic Water Flow
// https://leetcode.com/problems/pacific-atlantic-water-flow/
private interface Leetcode_417 {
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>>
    companion object : Testable {
        override fun test() {
            val tests = listOf(
                arrayOf(
                    intArrayOf(1,2,2,3,5),
                    intArrayOf(3,2,3,4,4),
                    intArrayOf(2,4,5,3,1),
                    intArrayOf(6,7,1,4,5),
                    intArrayOf(5,1,1,2,4),
                ) to listOf(
                    listOf(0,4),
                    listOf(1,3),
                    listOf(1,4),
                    listOf(2,2),
                    listOf(3,0),
                    listOf(3,1),
                    listOf(4,0),
                ),
                arrayOf(
                    intArrayOf(2,1),
                    intArrayOf(1,2),
                ) to listOf(
                    listOf(0,0),
                    listOf(0,1),
                    listOf(1,0),
                    listOf(1,1),
                ),
            )
            listOf(
                M1()::pacificAtlantic,
            ).runTimedTests(tests) { a, b ->
                invoke(a).assertEqualTo(b)
            }
        }
    }

    private class M1 : Leetcode_417 {
        override fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
            val p_set = HashSet<Pair<Int, Int>>()
            val a_set = HashSet<Pair<Int, Int>>()

            val n = heights.size
            val m = heights[0].size


            for (r in 0 until n) {
                for (c in 0 until m) {
                    if (c == 0 || r == 0) {
                        dfs(heights, r, c, p_set)
                    }
                    if (r == n-1 || c == m-1) {
                        dfs(heights, r, c, a_set)
                    }
                }
            }

            val res = ArrayList<List<Int>>()
            for (p in p_set) {
                if (p in a_set) {
                    res += listOf(p.first, p.second)
                }
            }
            return res

        }

        fun dfs(heights: Array<IntArray>, r: Int, c: Int, set: HashSet<Pair<Int, Int>>) {
            // if (!inBound(heights, r, c)) return
            val pair = r to c
            if (pair in set) return
            set.add(pair)
            val height = heights[r][c]

            fun checker(r: Int, c: Int) {
                if (inBound(heights, r, c)) {
                    val h = heights[r][c]
                    if (h >= height) dfs(heights, r, c, set)
                }
            }

            // top
            checker(r-1, c)

            // left
            checker(r, c-1)

            // bottom
            checker(r+1,c)

            // right
            checker(r, c+1)
        }

        fun inBound(heights: Array<IntArray>, r: Int, c: Int) : Boolean {
            val n = heights.size
            val m = heights[0].size
            return !(
                    r < 0 || r >= n || c < 0 || c >= m
                    )
        }
    }
}


// 130. Surrounded Regions
// https://leetcode.com/problems/surrounded-regions/
private interface Leetcode_130 {

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

    private class M1 : Leetcode_130 {
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
}

val _20220805 = listOf<Testable>(
    // Leetcode_133,
    // Leetcode_417,
    Leetcode_130
)