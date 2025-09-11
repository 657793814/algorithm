package com.liuzd.soft.algorithm.acm;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复最长子字符串
 *
 * @author: liuzd
 * @date: 2025/9/11
 * @email: liuzd2025@qq.com
 * @desc
 */

public class NoReplaceStr {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] p = new int[s.length()];
        int maxLen = 1;
        int maxIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            p[i] = 1;
            Map<String, Integer> map = new HashMap<>();
            map.put(String.valueOf(chars[i]), i);
            for (int j = i + 1; j < s.length(); j++) {
                if (map.containsKey(String.valueOf(chars[j]))) {
                    p[i] = j - i;
                    break;
                } else {
                    map.put(String.valueOf(chars[j]), j);
                    p[i]++;
                }
            }

            if (p[i] > maxLen) {
                maxLen = p[i];
                maxIndex = i;
            }
        }
        System.out.println("str:" + s);
        System.out.println("maxLen:" + maxLen);
        System.out.println("maxIndex:" + maxIndex);
        System.out.println("subStr:" + s.substring(maxIndex, maxIndex + maxLen));
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "abcb";
        lengthOfLongestSubstring(s);
        lengthOfLongestSubstring(s1);
        lengthOfLongestSubstring(s2);
        lengthOfLongestSubstring(s3);
    }
}
