package com.liuzd.soft.algorithm.acm;

/**
 * 贪心算法
 *
 * @author: liuzd
 * @date: 2025/9/12
 * @email: liuzd2025@qq.com
 * @desc
 */
public class Greed {

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * @param nums
     * @param start 默认0
     * @param len   默认nums.length
     * @return
     */
    public static int rob(int[] nums, int start, int len) {
        //动态规划 p[i]的最大收益 = max(p[i-1],p[i-2]+nums[i])
        if (nums.length == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[start];
        }
        if (len == 2) {
            return Math.max(nums[start], nums[start + 1]);
        }

        int max = nums[start];
        int p = nums[1 + start]; //存储前一个位置的max值
        for (int i = 2 + start; i < (len + start); i++) {
            max = Math.max((nums[i - 2] + nums[i]), p);
            p = max;
        }
        return max;
    }

    /**
     * 升级版：环状
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
     * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
     * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        //1. 从0开始，滑道宽度为nums.length-2
        //2. 从1开始，滑道宽度为nums.length-1
        //3. 取两者最大值
        return Math.max(rob(nums, 0, nums.length - 1), rob(nums, 1, nums.length - 1));
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(rob(nums, 0, nums.length));
        System.out.println(rob(nums));
    }
}
