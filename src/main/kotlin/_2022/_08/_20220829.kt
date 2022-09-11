@file:Suppress("DuplicatedCode", "ClassName", "ObjectPropertyName")

package _2022._08

import Testable
import utils.assertEqualTo
import utils.runTimedTests


// 4. Median of Two Sorted Arrays
// https://leetcode.com/problems/median-of-two-sorted-arrays/
private interface LC_4 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double

    // Runtime: 522 ms, faster than 59.01% of Kotlin online submissions for Median of Two Sorted Arrays.
    // Memory Usage: 58.1 MB, less than 37.69% of Kotlin online submissions for Median of Two Sorted Arrays.
    // https://leetcode.com/submissions/detail/786267138/
    // Note: this doesn't satisfy the requirement of ```The overall run time complexity should be O(log (m+n)).```
    private class M1 : LC_4 {
        override fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
            if (nums1.isEmpty()) return findMedianSortedArray(nums2)
            if (nums2.isEmpty()) return findMedianSortedArray(nums1)
            var l1 = 0
            var r1 = nums1.lastIndex
            var l2 = 0
            var r2 = nums2.lastIndex

            val totalSize = (nums1.size+nums2.size)
            var operationCounter = if (totalSize%2==0) totalSize/2-1 else totalSize/2

            fun moveLeft() {
                if (l1 == nums1.size || l2 == nums2.size) {
                    if (l1 == nums1.size) l2++ else l1++
                } else {
                    if (nums1[l1]<nums2[l2]) l1++ else l2++
                }
            }

            fun moveRight() {
                if (r1==-1||r2==-1) {
                    if (r1==-1) r2-- else r1--
                } else {
                    if (nums1[r1]>nums2[r2]) r1-- else r2--
                }
            }

            while(operationCounter != 0) {
                moveLeft()
                moveRight()
                operationCounter--
            }

            // constant space and time complicity
            val array = ArrayList<Int>()
            fun addToArray(nums: IntArray, l: Int, r: Int) {
                if (l==-1||r==nums.size) return
                for (i in l .. r) {
                    array.add(nums[i])
                }
            }

            addToArray(nums1, l1, r1)
            addToArray(nums2, l2, r2)
            array.sort()
            return findMedianSortedArray(array)
        }

        fun findMedianSortedArray(nums: IntArray) : Double {
            val mid = nums.size / 2
            return if (nums.size % 2 == 0) {
                (nums[mid]+nums[mid-1])/2.0
            } else nums[mid].toDouble()
        }
        fun findMedianSortedArray(nums: List<Int>) : Double {
            val mid = nums.size / 2
            return if (nums.size % 2 == 0) {
                (nums[mid]+nums[mid-1])/2.0
            } else nums[mid].toDouble()
        }
    }
}

class Solution {

}

val _202208 = listOf<Testable>(

)