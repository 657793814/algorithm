package com.liuzd.soft.algorithm.utils;

import com.liuzd.soft.algorithm.acm.NodeReverse;

import java.util.List;

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

    public static void printNode(NodeReverse.ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
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

    public static <T> void printList(List<T> list, int enter) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            if (t instanceof List) {
                printList((List<T>) t, 0);
                if (i == list.size() - 1) {
                    System.out.print("]");
                } else {
                    System.out.print("],");
                }
            } else {
                if (i == list.size() - 1) {
                    System.out.print(t);
                } else {
                    System.out.print(t + ",");
                }
            }
        }
        if (enter == 1) {
            System.out.print("]");
            System.out.println();
        }
    }

}
