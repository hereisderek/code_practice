@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.*
import java.util.*


// 663 · Walls and Gates
// https://www.lintcode.com/problem/663/
// https://leetcode.com/problems/walls-and-gates/
private interface LintCode_663 {
    /**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    fun wallsAndGates(rooms: Array<IntArray>)

    companion object : Testable {
        override fun test() {
            // [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
            // Output:
            // [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
            val tests = listOf(
                arrayOf(
                    intArrayOf(2147483647,-1,0,2147483647),
                    intArrayOf(2147483647,2147483647,2147483647,-1),
                    intArrayOf(2147483647,-1,2147483647,-1),
                    intArrayOf(0,-1,2147483647,2147483647),
                ) to arrayOf(
                    intArrayOf(3,-1,0,1),
                    intArrayOf(2,2,1,-1),
                    intArrayOf(1,-1,2,-1),
                    intArrayOf(0,-1,3,4),
                )
            )
            listOf(
                M1()::wallsAndGates,
                M2()::wallsAndGates,
                S1()::wallsAndGates,
                S2()::wallsAndGates,
            ).runTimedTests(tests) { a, b ->
                val input = a.deepClone()
                invoke(input)
                input.matrixEquals(b)
            }
        }
    }


    // dfs
    private class M1 : LintCode_663 {
        val INF = Int.MAX_VALUE
        val adjcents = arrayOf(
            arrayOf(-1,0), arrayOf(1,0), arrayOf(0,-1), arrayOf(0,1),
        )

        /**
         * @param rooms: m x n 2D grid
         * @return: nothing
         */
        override fun wallsAndGates(rooms: Array<IntArray>): Unit {
            val n = rooms.size
            val m = rooms[0].size
            for (y in 0 until n) for (x in 0 until m) {
                val value = rooms[y][x]
                if (value == 0) {
                    dfs(rooms, x, y, 0)
                }
            }

            for (y in 0 until n) for (x in 0 until m) {
                if (rooms[y][x] == INF) {
                    rooms[y][x] = -1
                }
            }
        }

        fun inBound(rooms: Array<IntArray>, x: Int, y: Int) : Boolean {
            val n = rooms.size
            val m = rooms[0].size
            return x>=0 && y>=0 && x<m && y<n
        }

        fun dfs(rooms: Array<IntArray>, x: Int, y: Int, distance: Int) {
            if (!inBound(rooms, x, y)) return
            val value = rooms[y][x]
            if (value == -1) return
            if (value != 0 && distance > value) return

            if (value != 0) rooms[y][x] = distance
            for (i in adjcents) {
                dfs(rooms, x+i[0], y+i[1], distance+1)
            }
        }
    }

    // bfs
    private class M2 : LintCode_663 {
        val adjcents = arrayOf(
            arrayOf(-1,0), arrayOf(1,0), arrayOf(0,-1), arrayOf(0,1),
        )

        override fun wallsAndGates(rooms: Array<IntArray>) {
            val n = rooms.size
            val m = rooms[0].size
            val queue = LinkedList<Pair<Int, Int>>()

            for (y in 0 until n) for (x in 0 until m) {
                val value = rooms[y][x]
                if (value == 0) {
                    queue.add(x to y)
                }
            }
            var counter = 0
            while (queue.isNotEmpty()) {
                val size = queue.size
                counter++
                for (i in 0 until size) {
                    val n = queue.poll()
                    for (adj in adjcents) {
                        val x = n.first + adj[0]
                        val y = n.second + adj[1]
                        if (!inBound(rooms, x, y)) continue
                        if (rooms[y][x] > counter) {
                            rooms[y][x] = counter
                            queue.add(x to y)
                        }
                    }
                }
            }
        }

        fun inBound(rooms: Array<IntArray>, x: Int, y: Int) : Boolean {
            val n = rooms.size
            val m = rooms[0].size
            return x>=0 && y>=0 && x<m && y<n
        }
    }

    // bfs
    private class S1 : LintCode_663 {
        override fun wallsAndGates(rooms: Array<IntArray>) {
            val m = rooms.size
            if (m == 0) return
            val n = rooms[0].size
            val q: Queue<IntArray> = LinkedList()
            for (row in 0 until m) {
                for (col in 0 until n) {
                    if (rooms[row][col] == GATE) {
                        q.add(intArrayOf(row, col))
                    }
                }
            }
            while (!q.isEmpty()) {
                val point: IntArray = q.poll()
                val row = point[0]
                val col = point[1]
                for (direction in DIRECTIONS) {
                    val r = row + direction[0]
                    val c = col + direction[1]
                    if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
                        continue
                    }
                    rooms[r][c] = rooms[row][col] + 1
                    q.add(intArrayOf(r, c))
                }
            }
        }

        companion object {
            private const val EMPTY = Int.MAX_VALUE
            private const val GATE = 0
            private val DIRECTIONS: List<IntArray> =
                listOf(intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1))
        }
    }

    // dfs
    private class S2 : LintCode_663 {
        /**
         * @param rooms: m x n 2D grid
         * @return: nothing
         */
        override fun wallsAndGates(rooms: Array<IntArray>) {
            // write your code here
            if (rooms.isEmpty() || rooms[0].isEmpty()) {
                return
            }
            val m = rooms.size
            val n = rooms[0].size
            for (i in 0 until m) {
                for (j in 0 until n) {
                    if (rooms[i][j] == 0) {
                        dfs(rooms, i, j, 0)
                    }
                }
            }
        }

        private fun dfs(rooms: Array<IntArray>, x: Int, y: Int, distance: Int) {
            if (!inBound(rooms, x, y) || rooms[x][y] == -1) {
                return
            }
            if (rooms[x][y] > distance || distance == 0) {
                rooms[x][y] = distance
                dfs(rooms, x + 1, y, distance + 1)
                dfs(rooms, x, y + 1, distance + 1)
                dfs(rooms, x - 1, y, distance + 1)
                dfs(rooms, x, y - 1, distance + 1)
            }
        }

        private fun inBound(rooms: Array<IntArray>, x: Int, y: Int) : Boolean {
            val n = rooms.size
            val m = rooms[0].size
            return x>=0 && y>=0 && x<m && y<n
        }
    }
}



// 207. Course Schedule
// https://leetcode.com/problems/course-schedule/submissions/
private interface LC_207 {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean

    companion object : Testable {
        override fun test() {
            val tests = listOf(
                tupleOf(
                    2, arrayOf(intArrayOf(1,0)), true
                ),
                tupleOf(
                    2, arrayOf(intArrayOf(1,0),intArrayOf(0,1)), false
                ),
                // tupleOf(
                //     100,
                //     arrayOf(
                //         intArrayOf(1,0),intArrayOf(2,0),intArrayOf(2,1),intArrayOf(3,1),intArrayOf(3,2),intArrayOf(4,2),intArrayOf(4,3),intArrayOf(5,3),intArrayOf(5,4),intArrayOf(6,4),intArrayOf(6,5),intArrayOf(7,5),intArrayOf(7,6),intArrayOf(8,6),intArrayOf(8,7),intArrayOf(9,7),intArrayOf(9,8),intArrayOf(10,8),intArrayOf(10,9),intArrayOf(11,9),intArrayOf(11,10),intArrayOf(12,10),intArrayOf(12,11),intArrayOf(13,11),intArrayOf(13,12),intArrayOf(14,12),intArrayOf(14,13),intArrayOf(15,13),intArrayOf(15,14),intArrayOf(16,14),intArrayOf(16,15),intArrayOf(17,15),intArrayOf(17,16),intArrayOf(18,16),intArrayOf(18,17),intArrayOf(19,17),intArrayOf(19,18),intArrayOf(20,18),intArrayOf(20,19),intArrayOf(21,19),intArrayOf(21,20),intArrayOf(22,20),intArrayOf(22,21),intArrayOf(23,21),intArrayOf(23,22),intArrayOf(24,22),intArrayOf(24,23),intArrayOf(25,23),intArrayOf(25,24),intArrayOf(26,24),intArrayOf(26,25),intArrayOf(27,25),intArrayOf(27,26),intArrayOf(28,26),intArrayOf(28,27),intArrayOf(29,27),intArrayOf(29,28),intArrayOf(30,28),intArrayOf(30,29),intArrayOf(31,29),intArrayOf(31,30),intArrayOf(32,30),intArrayOf(32,31),intArrayOf(33,31),intArrayOf(33,32),intArrayOf(34,32),intArrayOf(34,33),intArrayOf(35,33),intArrayOf(35,34),intArrayOf(36,34),intArrayOf(36,35),intArrayOf(37,35),intArrayOf(37,36),intArrayOf(38,36),intArrayOf(38,37),intArrayOf(39,37),intArrayOf(39,38),intArrayOf(40,38),intArrayOf(40,39),intArrayOf(41,39),intArrayOf(41,40),intArrayOf(42,40),intArrayOf(42,41),intArrayOf(43,41),intArrayOf(43,42),intArrayOf(44,42),intArrayOf(44,43),intArrayOf(45,43),intArrayOf(45,44),intArrayOf(46,44),intArrayOf(46,45),intArrayOf(47,45),intArrayOf(47,46),intArrayOf(48,46),intArrayOf(48,47),intArrayOf(49,47),intArrayOf(49,48),intArrayOf(50,48),intArrayOf(50,49),intArrayOf(51,49),intArrayOf(51,50),intArrayOf(52,50),intArrayOf(52,51),intArrayOf(53,51),intArrayOf(53,52),intArrayOf(54,52),intArrayOf(54,53),intArrayOf(55,53),intArrayOf(55,54),intArrayOf(56,54),intArrayOf(56,55),intArrayOf(57,55),intArrayOf(57,56),intArrayOf(58,56),intArrayOf(58,57),intArrayOf(59,57),intArrayOf(59,58),intArrayOf(60,58),intArrayOf(60,59),intArrayOf(61,59),intArrayOf(61,60),intArrayOf(62,60),intArrayOf(62,61),intArrayOf(63,61),intArrayOf(63,62),intArrayOf(64,62),intArrayOf(64,63),intArrayOf(65,63),intArrayOf(65,64),intArrayOf(66,64),intArrayOf(66,65),intArrayOf(67,65),intArrayOf(67,66),intArrayOf(68,66),intArrayOf(68,67),intArrayOf(69,67),intArrayOf(69,68),intArrayOf(70,68),intArrayOf(70,69),intArrayOf(71,69),intArrayOf(71,70),intArrayOf(72,70),intArrayOf(72,71),intArrayOf(73,71),intArrayOf(73,72),intArrayOf(74,72),intArrayOf(74,73),intArrayOf(75,73),intArrayOf(75,74),intArrayOf(76,74),intArrayOf(76,75),intArrayOf(77,75),intArrayOf(77,76),intArrayOf(78,76),intArrayOf(78,77),intArrayOf(79,77),intArrayOf(79,78),intArrayOf(80,78),intArrayOf(80,79),intArrayOf(81,79),intArrayOf(81,80),intArrayOf(82,80),intArrayOf(82,81),intArrayOf(83,81),intArrayOf(83,82),intArrayOf(84,82),intArrayOf(84,83),intArrayOf(85,83),intArrayOf(85,84),intArrayOf(86,84),intArrayOf(86,85),intArrayOf(87,85),intArrayOf(87,86),intArrayOf(88,86),intArrayOf(88,87),intArrayOf(89,87),intArrayOf(89,88),intArrayOf(90,88),intArrayOf(90,89),intArrayOf(91,89),intArrayOf(91,90),intArrayOf(92,90),intArrayOf(92,91),intArrayOf(93,91),intArrayOf(93,92),intArrayOf(94,92),intArrayOf(94,93),intArrayOf(95,93),intArrayOf(95,94),intArrayOf(96,94),intArrayOf(96,95),intArrayOf(97,95),intArrayOf(97,96),intArrayOf(98,96),intArrayOf(98,97),intArrayOf(99,97)
                //     ),
                //     true
                // ),
            )
            listOf(
                M1()::canFinish,
                S1()::canFinish,
            ).runTimedTests(tests) { a, b, c ->
                invoke(a,b).assertEqualTo(c)
            }

        }
    }

    // Runtime: 235 ms, faster than 95.00% of Kotlin online submissions for Course Schedule.
    // Memory Usage: 45.1 MB, less than 87.92% of Kotlin online submissions for Course Schedule.
    // https://leetcode.com/submissions/detail/783901460/
    private class M1 : LC_207 {
        val map = HashMap<Int, ArrayList<Int>>()
        lateinit var satisfied: BooleanArray
        var set = HashSet<Int>()

        override fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
            for (pre in prerequisites) {
                if (map[pre[0]] == null) map[pre[0]] = ArrayList()
                map[pre[0]]!!.add(pre[1])
            }

            satisfied = BooleanArray(numCourses){ false }
            for (c in 0 until numCourses) {
                if (!checkCourse(c)) return false
                set.clear()
            }
            return true
        }

        fun checkCourse(course: Int) : Boolean {
            val pre = map[course] ?: return true
            if (pre.isEmpty()) return true
            if (satisfied[course]) return true
            if (set.contains(course)) return false

            set.add(course)
            for (p in pre) {
                if (!checkCourse(p)) return false
            }
            set.remove(course)
            satisfied[course] = true
            return true
        }
    }

    // leetcode.cn
    // Runtime: 415 ms, faster than 39.17% of Kotlin online submissions for Course Schedule.
    // Memory Usage: 48.4 MB, less than 73.75% of Kotlin online submissions for Course Schedule.
    // https://leetcode.com/submissions/detail/783936711/
    private class S1 : LC_207 {
        // dependency <- dependent
        val map = HashMap<Int, ArrayList<Int>>()

        override fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
            val count = IntArray(numCourses)

            for (pre in prerequisites) {
                if (map[pre[1]] == null) map[pre[1]] = ArrayList()
                map[pre[1]]!!.add(pre[0])
                count[pre[0]]++
            }

            val q = LinkedList<Int>()
            count.forEachIndexed { index, i ->
                if (i == 0) q.add(index)
            }
            if (q.isEmpty()) return false

            while (q.isNotEmpty()){
                val n = q.poll()
                map[n]?.forEach{
                    count[it]--
                    if (count[it] == 0) q.add(it)
                }
            }

            return !count.any { it != 0 }
        }
    }
}


val _20220826 = listOf<Testable>(
    // LintCode_663,
    LC_207
)