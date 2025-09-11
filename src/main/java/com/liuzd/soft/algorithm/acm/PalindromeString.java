package com.liuzd.soft.algorithm.acm;

/**
 * 回文字符串
 * @author: liuzd
 * @date: 2025/9/10
 * @email: liuzd2025@qq.com
 * @desc
 */
public class PalindromeString {

    /**
     * 使用Manacher算法查找最长回文子串
     * 时间复杂度：O(n)
     * 1. 预处理字符串，在每个字符间插入 '#'，处理字符串长度即偶性问题
     * 2. 穷举位置i的回文长度P[i] ,本质通过中心扩展算法，计算以每个字符为中心的回文半径，并记录最长回文子串
     * 3. 利用回文对称性，标记一个当前最右边界的回文中心和回文半径，判断当前位置i是否在回文范围内，得出p[i]的最小值，再中心扩展
     * 4. 优化穷举，如果最右边界已经到了最右边字符，后续位置可以不用计算了，一定不回超过当前最右边界的中心回文长度
     * @param str 输入字符串
     * @return 最长回文子串
     */
    public static String maxPalindromeStringManacher(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        // 预处理字符串，在每个字符间插入 '#'
        String processedStr = preprocess(str);
        char[] chars = processedStr.toCharArray();
        int n = processedStr.length();

        // P[i] 表示以 i 为中心的回文半径
        int[] P = new int[n];

        // center 表示当前拥有最右边界回文的中心，right 表示当前回文能到达的最右边界
        int center = 0, right = 0;

        // 记录最长回文的中心位置和半径
        int maxLen = 0;
        int centerIndex = 0;

        for (int i = 0; i < n; i++) {
            // 利用回文的对称性优化
            int mirror = 2 * center - i;

            //根据对称性快速初步计算 P[i] 的最小值，然后在此基础上再中心扩展
            if (i < right) {
                P[i] = Math.min(right - i, P[mirror]);
            }

            // 尝试扩展以 i 为中心的回文
            int leftIndex = i - (P[i] + 1);
            int rightIndex = i + (P[i] + 1);
            while (leftIndex >= 0 && rightIndex < n && chars[leftIndex] == chars[rightIndex]) {
                P[i]++;
                leftIndex--;
                rightIndex++;
            }

            // 如果回文扩展超过了 right，需要更新 center 和 right
            if (i + P[i] > right) {
                center = i;
                right = i + P[i];
            }

            // 更新最长回文信息
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }

            //如果回文边界已经到了最右边，后续的可以不计算了
            if(right==(n-1)){
                break;
            }
        }

        // 从原字符串中提取最长回文子串
        int start = (centerIndex - maxLen) / 2;
        return str.substring(start, start + maxLen);
    }

    /**
     * 预处理字符串，在每个字符间插入 '#'
     * 例如："abc" -> "^#a#b#c#$"
     *
     * @param str 原始字符串
     * @return 处理后的字符串
     */
    private static String preprocess(String str) {
        int n = str.length();
        StringBuilder sb = new StringBuilder(2 * n + 3);
        sb.append("^");
        for (int i = 0; i < n; i++) {
            sb.append("#").append(str.charAt(i));
        }
        sb.append("#$");
        return sb.toString();
    }

    public static void main(String[] args) {
        // 测试更多用例
        System.out.println("Manacher算法结果：" + maxPalindromeStringManacher("babad"));
        System.out.println("Manacher算法结果：" + maxPalindromeStringManacher("cbbd"));
        System.out.println("Manacher算法结果：" + maxPalindromeStringManacher("a"));
        System.out.println("Manacher算法结果：" + maxPalindromeStringManacher("ac"));
        System.out.println("Manacher算法结果：" + maxPalindromeStringManacher("1232123242321232"));
    }
}

