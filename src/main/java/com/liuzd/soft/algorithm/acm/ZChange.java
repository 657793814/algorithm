package com.liuzd.soft.algorithm.acm;

import static com.liuzd.soft.algorithm.utils.PrintUtils.*;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 *
 * @author: liuzd
 * @date: 2025/9/11
 * @email: liuzd2025@qq.com
 * @desc
 */
public class ZChange {

    /**
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows <= 1) {
            return s;
        }

        char[] chars = s.toCharArray();
        int len = chars.length;
        int replace = (int) Math.ceil(len * 1.0 / (2 * numRows - 2)); //重复个数
        int more = (len % (2 * numRows - 2));  //余数
        int cols;  //计算列数
        if (more == 0) {
            cols = replace * (numRows - 1);
        } else if (more <= numRows) {
            cols = (replace - 1) * (numRows - 1) + 1;
        } else {
            cols = (replace - 1) * (numRows - 1) + (more % numRows) + 1;
        }

        System.out.println("s:" + s + " , rows:" + numRows + " , cols:" + cols + " ,replace:" + replace + " ,more:" + more);
        char[][] charArr = new char[numRows][cols];
        initCharArr(charArr, cols, numRows);

        int idx = 0;
        int rowIdx = 0;
        int colIdx = 0;
        int flag = 1;  //1 向下，2向上
        do {
            charArr[rowIdx][colIdx] = chars[idx++];
            if (rowIdx == (numRows - 1)) {
                rowIdx--;
                colIdx++;
                flag = 2;
            } else if (rowIdx == 0) {
                rowIdx++;
                flag = 1;
            } else {
                if (flag == 1) {
                    rowIdx++;
                } else {
                    rowIdx--;
                    colIdx++;
                }
            }
        } while (idx < len);

        printCharArray(charArr, cols, numRows);
        return convertCharArray(charArr, cols, numRows);
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
//        convert("123", 3);
//        convert("1234", 3);
//        convert("12345", 3);
    }
}
