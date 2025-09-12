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

    /**
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * 例如，121 是回文，而 123 不是。
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        return x == resolveInt(x);
    }

    /**
     * 输入一个整数 n ，反转这个整数
     *
     * @param x|int
     * @return
     */
    public static int resolveInt(int x) {
        int s = 0;
        while (x > 0) {
            s = s * 10 + x % 10;
            x /= 10;
        }
        return s;

    }

    /**
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 [−pow(2,31),  pow(2,31) − 1] ，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     * Math.pow(2, 31) = 2.147483648E9
     *
     *  得出几个推论：
     *  1. 两个等长度的数 a,b ； 如果 a>b ,则 a/10 >= b/10
     *  2. 数a绝对值长度为n,数b绝对值长度为 n+1,b的末位数为 m; 如果 a==b/10, 则 10*a+m >= b，当且仅当m==0时取等
     * @param x
     * @return
     */
    public static int reverseInt(int x) {
        int res = 0;
        int flag = Integer.MAX_VALUE / 10;  // 214748364
        while (x != 0) {
            //每次取末尾数字
            int tmp = x % 10;
            //判断是否 大于 最大32位整数
            if (res > flag || (res == flag && tmp > 7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res < -flag || (res == -flag && tmp < -8)) {
                return 0;
            }

            res = res * 10 + tmp;
            x /= 10;
        }
        return res;

    }

    /**
     * 8. 字符串转换整数 (atoi)
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     * 函数 myAtoi(string s) 的算法如下：
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * <p>
     * 0 <= s.length <= 200
     * s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
     * 2.147483648E9
     * 21474836460
     */
    public static int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.trim().toCharArray();
        int len = chars.length;
        int signal = 0;  // 0表示无符号，1表示正数，-1表示负数
        int ret = 0;
        for (int i = 0; i < len; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                //System.out.println("ret:" + ret);
                int tmp = chars[i] - '0';
                if (signal >= 0) {
                    if (ret > Integer.MAX_VALUE / 10 || (ret == Integer.MAX_VALUE / 10 && tmp > 7)) {
                        return Integer.MAX_VALUE;
                    }
                } else {
                    //这里要取等，因为正数运算如果是8，又越界了
                    if (ret > Integer.MAX_VALUE / 10 || (ret == Integer.MAX_VALUE / 10 && tmp >= 8)) {
                        return Integer.MIN_VALUE;
                    }
                }
                ret = ret * 10 + tmp;
            } else if (chars[i] == '-' || chars[i] == '+') {
                if (i == 0) {
                    signal = chars[i] == '-' ? -1 : 1;
                } else {  // eg: 123+1
                    break;
                }
            } else {
                break;
            }
        }
        return signal == 0 ? ret : signal * ret;
    }

    public static void main(String[] args) {
        // 测试更多用例
//        System.out.println("Manacher算法结果：" + maxPalindromeStringManacher("babad"));
//        System.out.println("Manacher算法结果：" + maxPalindromeStringManacher("cbbd"));
//        System.out.println("Manacher算法结果：" + maxPalindromeStringManacher("a"));
//        System.out.println("Manacher算法结果：" + maxPalindromeStringManacher("ac"));
//        System.out.println("Manacher算法结果：" + maxPalindromeStringManacher("1232123242321232"));

//        System.out.println(isPalindrome(121));
//        System.out.println(resolveInt(123));
//        System.out.println(reverseInt(1534236469));
//        System.out.println(myAtoi("   -042"));
//        System.out.println(myAtoi("21474836460"));
        System.out.println(myAtoi("-21474836482"));
//        System.out.println(myAtoi("   -91283472332"));
    }
}

