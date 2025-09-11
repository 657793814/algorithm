package com.liuzd.soft.algorithm.utils;

/**
 * @author: liuzd
 * @date: 2025/9/11
 * @email: liuzd2025@qq.com
 * @desc
 */
public class PrintUtils {

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void printCharArray(char[][] array, int cols, int rows) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void initCharArr(char[][] array, int cols, int rows) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = ' '; // 赋值为空格字符
            }
        }
    }

    public static String convertCharArray(char[][] array, int cols, int rows) {
        String str = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (array[i][j] != ' ') {
                    str += array[i][j];
                }
            }
        }
        return str;
    }
}
