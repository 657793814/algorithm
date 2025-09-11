package com.liuzd.soft.algorithm.acm;

import com.liuzd.soft.algorithm.utils.PrintUtils;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * @author: liuzd
 * @date: 2025/9/11
 * @email: liuzd2025@qq.com
 * @desc
 */
public class MiddleNum {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merge = new int[nums1.length + nums2.length];
        int l1 = 0;
        int l2 = 0;
        int i = 0;
        do {
            if (l1 == nums1.length) {
                merge[i++] = nums2[l2++];
            } else if (l2 == nums2.length) {
                merge[i++] = nums1[l1++];
            } else if (nums1[l1] < nums2[l2]) {
                merge[i++] = nums1[l1++];
            } else {
                merge[i++] = nums2[l2++];
            }
        } while (l1 < nums1.length || l2 < nums2.length);

        PrintUtils.printArray(merge);

        int len = nums1.length + nums2.length;
        if (len % 2 == 0) {
            return (merge[len / 2 - 1] + merge[len / 2]) / 2.0;
        } else {
            return merge[(len - 1) / 2];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
