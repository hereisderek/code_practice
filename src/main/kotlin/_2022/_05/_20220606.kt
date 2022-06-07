@file:Suppress("DuplicatedCode", "ClassName")

package _2022._05

import utils.*


fun main() {
    // Leetcode_17.test()
    Leetcode_19.test()
}

// 17. Letter Combinations of a Phone Number
private interface Leetcode_17 {
    fun letterCombinations(digits: String): List<String>

    companion object {
        fun test() = listOf(
            My1()::letterCombinations,
            // Sample1()::letterCombinations,
            Sample2()::letterCombinations,
        ).runTimedTests {

            invoke("2").assertThat {
                contentEquals(listOf("a", "b", "c"), true)
            }
            invoke("23").assertThat {
                contentEquals(listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"), true)
            }
            invoke("").assertEqualTo(emptyList())

        }
    }


    // Runtime: 216 ms, faster than 71.68% of Kotlin online submissions for Letter Combinations of a Phone Number.
    // Memory Usage: 35.2 MB, less than 99.03% of Kotlin online submissions for Letter Combinations of a Phone Number.
    // https://leetcode.com/submissions/detail/715543782/
    class My1 : Leetcode_17 {
        override fun letterCombinations(digits: String): List<String> {

            val letterMap = arrayOf(
                "", // 0
                "", // 1
                "abc", // 2
                "def", // 3
                "ghi", // 4
                "jkl", // 5
                "mno", // 6
                "pqrs", // 7
                "tuv", // 8
                "wxyz", // 9
            )

            var result = mutableListOf<String>()

            digits.forEachIndexed { index, digit ->
                // val digitIndex = (digit - '0').toInt()       // use this next line on leetcode since it uses an earlier version of kotlin
                val digitIndex = digit.digitToInt()             // this is for the latest version of kotlin

                val chars = letterMap[digitIndex]

                if (index == 0) {
                    result.addAll(chars.map { it.toString() })
                } else {
                    val newList = mutableListOf<String>()
                    result.forEach { previous ->
                        chars.forEach { char ->
                            newList += "${previous}${char}"
                        }
                    }
                    result = newList
                }
            }
            return result
        }
    }

    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0017.%E7%94%B5%E8%AF%9D%E5%8F%B7%E7%A0%81%E7%9A%84%E5%AD%97%E6%AF%8D%E7%BB%84%E5%90%88.md
    // automatic converted from java to kotlin, however it breaks the tests
    class Sample1 : Leetcode_17 {
        //设置全局列表存储最后的结果
        var list: MutableList<String> = ArrayList()

        override fun letterCombinations(digits: String): List<String> {
            if (digits.isEmpty()) {
                return list
            }
            //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
            val numString = arrayOf("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")
            //迭代处理
            backTracking(digits, numString, 0)
            return list
        }

        //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
        var temp = StringBuilder()

        //比如digits如果为"23",num 为0，则str表示2对应的 abc
        private fun backTracking(digits: String, numString: Array<String>, num: Int) {
            //遍历全部一次记录一次得到的字符串
            if (num == digits.length) {
                list.add(temp.toString())
                return
            }
            //str 表示当前num对应的字符串
            val str = numString[digits[num].code - '0'.code]
            for (element in str) {
                temp.append(element)
                //c
                backTracking(digits, numString, num + 1)
                //剔除末尾的继续尝试
                temp.deleteCharAt(temp.length - 1)
            }
        }
    }

    // sample 140 ms submission
    // it actually took much longer in my local test:
    // execution for _2022._05.Leetcode_17.My1.letterCombinations finished, took 63463 Nanoseconds
    // execution for _2022._05.Leetcode_17.Sample2.letterCombinations finished, took 2083344 Nanoseconds
    class Sample2 : Leetcode_17 {
        override fun letterCombinations(digits: String): List<String> {
            val ret : MutableList<String> = mutableListOf()
            if(digits.isEmpty()) return ret

            fun dfs(i : Int, prfx : String){
                val r = when(digits[i] - '0'){
                    2 -> 'a'..'c'
                    3 -> 'd'..'f'
                    4 -> 'g'..'i'
                    5 -> 'j'..'l'
                    6 -> 'm'..'o'
                    7 -> 'p'..'s'
                    8 -> 't'..'v'
                    9 -> 'w'..'z'
                    else -> CharRange.EMPTY
                }
                for(c in r){
                    val s = prfx + c
                    if(i == digits.length - 1) ret += s
                    else dfs(i + 1, s)
                }
            }
            dfs(0, "")
            return ret
        }
    }
}

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

// private typealias ListNode = Node<Int>
private typealias ListNode = IntNode
private interface Leetcode_19 {

    // class ListNode(var `val`: Int) {
    //     var next: ListNode? = null
    // }

    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode?

    companion object {
        fun test() = listOf(
            My2()::removeNthFromEnd
        ).runTimedTests {
            // invoke(
            //     linkedNodesOf(1, 2, 3, 4, 5), 2
            // )?.toList().assertEqualTo(listOf(1, 2, 3, 5))
            //
            // invoke(
            //     linkedNodesOf(1, 2, 3, 4, 5), 4
            // )?.toList().assertEqualTo(listOf(1, 3, 4, 5))
            //
            // assert(invoke(linkedNodesOf(1), 1) == null)
            //
            // invoke(linkedNodesOf(1, 2), 1)?.toList().assertEqualTo(listOf(1))

            invoke(linkedNodesOf(1, 2), 2)?.toList().assertEqualTo(listOf(2))
        }

    }

    // TODO: WIP
    private class My1 : Leetcode_19 {
        override fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
            if (head == null) return null

            var currentHead = head
            var counter = 0
            val buff = Array<ListNode?>(n+1){
                if (it == 0) head else null
            }

            while (currentHead?.next != null) {
                counter++
                val index = counter % (n+1)
                buff[index] = currentHead
                currentHead = currentHead.next
            }
            if (n > counter) return null
            val index = (counter - n) % (n+1)
            val left = buff[0]
            left!!.next = buff[2]

            return head
        }
    }
    // 1 2 3 4
    private class My2 : Leetcode_19 {
        override fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
            if (head == null) return null
            val dummy = ListNode(-1, head)
            var fast : ListNode? = dummy
            var slow : ListNode? = dummy
            for (i in 0 until n) {
                fast = fast?.next
            }
            while (fast?.next != null) {
                fast = fast?.next
                slow = slow?.next
            }
            val clean = slow?.next
            slow?.next = slow?.next?.next
            clean?.next = null
            return dummy.next
        }
    }
}