package com.example.android_lesson.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Loop {
    public static void main(String[] args) {
        int[] sums = new int[]{2, 5, 5, 11};
        String index = Arrays.toString(twoSum(sums, 10));
//        String index = Arrays.toString(twoSum2(sums, 10));
        System.out.println(index);
    }

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 来源：力扣（LeetCode）
     * 链接：<a href="https://leetcode.cn/problems/two-sum">...</a>
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            int data2 = target - nums[i];
            if (hashMap.containsKey(data2)) {
                return new int[]{hashMap.get(data2), i};
            }
            hashMap.put(nums[i], i);
        }
        return new int[0];
    }
}
