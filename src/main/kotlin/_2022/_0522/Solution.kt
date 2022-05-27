@file:Suppress("ClassName")

package _2022._0522

import utils.contentEquals
import utils.runTimedTests


// https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0001.%E4%B8%A4%E6%95%B0%E4%B9%8B%E5%92%8C.md
// https://leetcode.cn/problems/two-sum/


class Solution_1 {
    // 57 / 57 test cases passed.
    // Status: Accepted
    // Runtime: 342 ms
    // Memory Usage: 42.2 MB
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (map.containsKey(complement)) {
                return intArrayOf(map[complement]!!, i)
            }
            map[nums[i]] = i
        }
        return intArrayOf()
    }

    companion object {
        fun test() = Solution_1().apply {
            twoSum(intArrayOf(2, 7, 11, 15), 9).run {
                assert(this.contentEquals(intArrayOf(0, 1)))
            }

            twoSum(intArrayOf(3, 3), 6).run {
                assert(this.contentEquals(intArrayOf(0, 1)))
            }
            twoSum(intArrayOf(3, 2, 4), 6).run {
                assert(this.contentEquals(intArrayOf(1, 2)))
            }
        }
    }
}

// https://leetcode.cn/problems/valid-anagram/
class Solution_242{
    companion object {
        fun test() = Solution_242().run {
            val methods = arrayOf(/*this::isAnagram2,*/ this::isAnagram)
            methods.forEach {
                assert(it.invoke("anagram", "nagaram"))
                assert(!it.invoke("anagram", "nagarama"))
                assert(!it.invoke("anagramb", "nagarama"))
            }
        }
    }

    @Deprecated("fails", level = DeprecationLevel.ERROR)
    fun isAnagram2(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val map = HashMap<Char, Int>()
        for (c1 in s) {
            map[c1] = 0
        }
        for (c2 in t) {
            if (map.containsKey(c2)) {
                map.remove(c2)
            } else {
                map.put(c2, 1)
            }
        }
        return map.size == 0
    }

    // https://leetcode.com/submissions/detail/708280934/
    // 36 / 36 test cases passed.
    // Status: Accepted
    // Runtime: 287 ms
    // Memory Usage: 38.5 MB
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val chars = IntArray(26)
        for (c in s) {
            val code = c.code - 'a'.code
            chars[code] = chars[code]+1
        }
        for (c in t) {
            val code = c.code - 'a'.code
            chars[code] = chars[code]-1
        }
        return !chars.any { it != 0 }
    }
    // sample 164 ms submission
    fun isAnagram_0(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val sCount = IntArray(26) { 0 }
        val tCount = IntArray(26) { 0 }

        for (c in s) {
            ++sCount[c.toInt() - 97]
        }

        for (c in t) {
            ++tCount[c.toInt() - 97]
        }

        for (i in 0 until 26) {
            if (sCount[i] != tCount[i]) return false
        }

        return true
    }
}



// https://leetcode.com/problems/intersection-of-two-arrays/
class Solution_349 {
    companion object {
        fun test() = Solution_349().run {
            listOf(this::intersection, this::_intersection).runTimedTests("Solution_349") {
                invoke(intArrayOf(1,2,2,1), intArrayOf(2,2)).contentEquals(intArrayOf(2))
                invoke(intArrayOf(4,9,5), intArrayOf(9,4,9,8,4)).contentEquals(intArrayOf(4,9))
            }
        }
    }
    // https://leetcode.com/submissions/detail/708288985/
    // 55 / 55 test cases passed.
    // Status: Accepted
    // Runtime: 313 ms
    // Memory Usage: 38 MB
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val map = HashMap<Int, Boolean>()
        for (i in nums1) {
            map[i] = false
        }
        for (i in nums2) {
            if (map.containsKey(i)) {
                map[i] = true
            }
        }
        return map.filter { it.value }.keys.toIntArray()
    }

    // sample 188 ms submission
    fun _intersection(nums1: IntArray, nums2: IntArray): IntArray {
        // Using an ordered HashSet (binary insert)
        val set = mutableSetOf<Int>()
        val list = mutableListOf<Int>()

        for (num in nums1) {
            set.add(num)
        }

        for (num in nums2) {
            if (set.contains(num)) {
                // Do not added twice, consume it
                set.remove(num)

                //
                list.add(num)
            }
        }

        return list.toIntArray()
    }
}

// https://leetcode.com/problems/group-anagrams/
class Solution_49 {
    companion object {
        fun test() = Solution_49().apply {
            listOf(this::groupAnagrams, this::groupAnagrams_0).runTimedTests("Solution_49") {
                listOf(
                    invoke(arrayOf("eat","tea","tan","ate","nat","bat")) to listOf(
                        listOf("bat"), listOf("nat","tan"), listOf("ate","eat","tea")
                    ),
                    invoke(arrayOf("hhhhu","tttti","tttit","hhhuh","hhuhh","tittt")) to listOf(
                        listOf("tittt","tttit","tttti"), listOf("hhhhu","hhhuh","hhuhh")
                    ),
                    invoke(arrayOf("")) to listOf(listOf("")),
                    invoke(arrayOf("a")) to listOf(listOf("a")),
                ).forEach {
                    val result = it.first
                    val expected = it.second
                    assert(result.size == expected.size){
                        "assertion failed, expected size:${expected.size}, actual size:${result.size}"
                    }
                    for (r in result) {
                        assert(expected.any { it.contentEquals(r, true) }) {
                            "assertion failed on actual:${r}, not in expected: ${expected.joinToString()}"
                        }
                    }
                }
            }
        }
    }

    //https://leetcode.com/submissions/detail/708318321/
    // 117 / 117 test cases passed.
    // Status: Accepted
    // Runtime: 1633 ms
    // Memory Usage: 44.1 MB
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val isAnagrams = Solution_242()::isAnagram
        val map = mutableMapOf<String, MutableList<String>>()

        for (i in strs.indices) {
            val str = strs[i]
            if (map.values.any { it.contains(str) }) continue

            map[str] = mutableListOf(str)
            for (j in i + 1 until strs.size) {
                val check = strs.getOrNull(j) ?: continue
                if (isAnagrams.invoke(str, check)) {
                    map[str]!!.add(check)
                }
            }
        }

        return map.values.toList()
    }


    // sample 264 ms submission
    fun groupAnagrams_0(strs: Array<String>): List<List<String>> {
        fun getHashValue(s: String): String {
            var value = ""
            val count = Array(26) { 0 }
            for (c in s) {
                count[c - 'a']++
            }
            for (i in 0 until 26) {
                if (count[i] != 0) {
                    value += "${count[i]}" + ('a' + i)
                }
            }
            return value
        }

        val result = HashMap<String, ArrayList<String>>()
        for (str in strs) {
            val value = getHashValue(str)
            val list = if (!result.contains(value)) {
                val temp = ArrayList<String>()
                result[value] = temp
                temp
            } else {
                result[value]
            }
            list?.add(str)
        }
        return result.values.toList()
    }
}


fun main() {
    Solution_1.test()
    Solution_242.test()
    Solution_349.test()
    Solution_49.test()
}



