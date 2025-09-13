package com.liuzd.soft.algorithm.acm;

/**
 * @author: liuzd
 * @date: 2025/9/13
 * @email: liuzd2025@qq.com
 * @desc
 */
public class MyPow {

    public static double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public static double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        if (N == 1) {
            return x;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    public static void main(String[] args) {
        System.out.println(myPow(8.0, -3));
    }
}
