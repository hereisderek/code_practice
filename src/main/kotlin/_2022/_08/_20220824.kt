@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable


// 79. Word Search
//
private interface LC_79 {
    fun exist(board: Array<CharArray>, word: String): Boolean

    // https://leetcode.com/submissions/detail/782112284/
    // Runtime: 569 ms, faster than 56.18% of Kotlin online submissions for Word Search.
    // Memory Usage: 36.8 MB, less than 67.01% of Kotlin online submissions for Word Search.
    private class M1 : LC_79 {
        val visited = HashSet<Int>()
        // c -> Index
        val map = HashMap<Char, ArrayList<Int>>()

        val adjcents = arrayOf(
            intArrayOf(1,0), intArrayOf(-1,0), intArrayOf(0,1), intArrayOf(0,-1)
        )

        override fun exist(board: Array<CharArray>, word: String): Boolean {
            val n = board.size
            val m = board[0].size

            for (y in 0 until n) for (x in 0 until m) {
                val c = board[y][x]
                if (map[c] == null) map[c] = ArrayList()
                val index = board.getIndex(x, y)
                map[c]!!.add(index)
            }
            return dfs(board, word, 0)
        }

        fun dfs(
            board: Array<CharArray>,
            word: String,
            word_index: Int,
            curPos: Int = -1
        ) : Boolean {
            val n = board.size
            val m = board[0].size

            // println("dfs word_index:$word_index curPos:$curPos visited:${visited.joinToString()}")

            if (word_index >= word.length) return false
            val c = word[word_index]

            // get the position for the first char
            if (curPos == -1) {
                return map[c]?.any{ i ->
                    dfs(board, word, 0, i)
                } ?: false
            }

            visited.add(curPos)
            val x = curPos % m
            val y = curPos / m
            val curC = board[y][x]
            if (curC != c) return false

            // that this is the last char to check
            if (word_index == word.length-1) return true

            // check remaining char using recursion
            for (i in adjcents) {
                val _x = x + i[0]
                val _y = y + i[1]
                val _i = _x + _y * m
                if (board.inBound(_x, _y) && !visited.contains(_i)) {
                    if (dfs(board, word, word_index+1, _i)) {
                        return true
                    } else {
                        visited.remove(_i)
                    }
                }
            }
            visited.remove(curPos)
            return false
        }

        fun Array<CharArray>.getIndex(x: Int, y: Int) : Int {
            val n = size
            val m = get(0).size
            return x + y * m
        }
        fun Array<CharArray>.inBound(x: Int, y: Int) : Boolean {
            val n = size
            val m = get(0).size
            return x >= 0 && x < m && y >= 0 && y < n
        }
    }

    // neetcode
    // https://www.youtube.com/watch?v=pfiQ_PS1g8E
    class S1 : LC_79 {
        val adjcents = arrayOf(intArrayOf(1,0), intArrayOf(-1,0), intArrayOf(0,1), intArrayOf(0,-1))

        override fun exist(board: Array<CharArray>, word: String): Boolean {
            val n = board.size
            val m = board[0].size
            val visited = HashSet<Pair<Int, Int>>()

            // i: current index of the word
            fun dfs(r: Int, c: Int, i: Int) : Boolean {
                if (i == word.length) return true
                if (r<0||r>=n||c<0||c>=m) return false
                if (visited.contains(r to c)) return false
                if (board[r][c] != word[i]) return false
                visited.add(r to c)
                for (p in adjcents) {
                    val _c = c + p[0]
                    val _r = r + p[1]
                    if (dfs(_r, _c, i+1)) return true
                }
                visited.remove(r to c)
                return false
            }

            for (y in 0 until n) for (x in 0 until m) {
                if (dfs(y, x, 0)) return true
            }
            return false
        }

    }
}


val _20220823 = listOf<Testable>(

)