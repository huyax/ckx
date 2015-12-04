/**
 * www.5bo.com Inc. Copyright (c) 2013 All Rights Reserved.
 */
package com.ckx.lang.util;

import java.util.Random;

/**
 * @Filename RandomUtil.java
 * @Description
 * @Version 1.0
 * @Author huqing
 * @Email qing.hu2009@gmail.com
 * @History <li>Author: huqing</li> <li>Date: 2013年12月30日</li> <li>Version: 1.0</li> <li>Content: create</li>
 */
public class RandomUtil {

    /**
     * 随机指定范围内N个不重复的数 最简单最基本的方法
     *
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n   随机数个数
     */
    public static int[] randomCommon(int min, int max, int n) {
        if (max == min + n) {
            return null;
        }

        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (num == result[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    public static String randomCommonStr(int min, int max, int n) {
        int[] arr = randomCommon(min, max, n);
        String tmp = "";
        for (int i = 0; i < arr.length; i++) {
            tmp += arr[i] + "";
        }
        return tmp;
    }

    public static int randomOne(int max) {
        return randomCommon(0, max, 1)[0];
    }

    /**
     * 生成随即密码
     *
     * @param pwd_len 生成的密码的总长度
     * @return 密码的字符串
     */
    public static String genRandomNum(int pwd_len) {
        //35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i; //生成的随机数
        int count = 0; //生成的密码的长度
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            //生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); //生成的数最大为36-1
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    public static void main(String[] args) {
        System.out.println(randomCommon(0, 9, 6));
    }
}
