package exercise;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author dengxinlong
 * @Date 2020/5/16 11:41
 * @slogan CODE IS TRUTH
 *  输入n个整数，整数长度在100以内，求相加后的结果
 */
public class SumLongNum {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("0.1");
        System.out.println(a);
        BigDecimal b = new BigDecimal("2.34");
        System.out.println(b);
        System.out.println(a.add(b));
        System.out.println(ArithmeticUtils
                .mul("9999999999999999999999999", "9999999999999999999999999999999999999999999"));
    }

    private static void test1() {
        int singleNumLength = String.valueOf(Long.MAX_VALUE).length() - 1;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 2 || n > 5) {
            System.out.println("数字个数范围在2，5之间");
            return;
        }
        List<long[]> list = new ArrayList<>(n);
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            String num = sc.next();
            if (num.length() > 100) {
                System.out.println("输入整数长度不能大于100");
                return;
            }
            int len = num.length() / singleNumLength + 1;
            int minLen = num.length() % singleNumLength;
            maxLen = Math.max(maxLen, len);
            long[] nums = new long[len];
            int index = 0;
            for (int j = 0; j < len; j++) {
                if (j == 0) {
                    nums[j] = Long.valueOf(num.substring(0, index += minLen));
                } else {
                    nums[j] = Long.valueOf(num.substring(index, index += singleNumLength));
                }
            }
            list.add(nums);
        }
        long[] resultNums = new long[maxLen];
        long add = 0;
        for (int i = 0; i < resultNums.length; i++) {
            for (long[] temp : list) {
                int index = temp.length - 1 - i;
                if (index >= 0) {
                    resultNums[i] += temp[index];
                }
            }
            resultNums[i] += add;
            String temp = String.valueOf(resultNums[i]);
            if (temp.length() == singleNumLength + 1) {
                add = Long.valueOf(temp.substring(0, 1));
                resultNums[i] = Long.valueOf(temp.substring(1));
            } else {
                add = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = resultNums.length - 1; i >= 0; i--) {
            sb.append(resultNums[i]);
        }
        System.out.println(sb);
    }

    private static void test2() {
        System.out.println("请输入要计算的长整型的个数");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        String[] l = new String[i];
        if (i < 2 || i > 5) {
            System.out.println("数字个数范围在2，5之间");
            return;
        }
        for (int j = 0; j < i; j++) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("请输入第" + (j + 1) + "个长整数:");
            l[j] = scanner1.nextLine();
        }
        BigDecimal bigDecimal = new BigDecimal("0");
        BigDecimal add;
        if (l.length == 3) {
            BigDecimal bigDecimal1 = new BigDecimal(l[0]);
            BigDecimal bigDecimal2 = new BigDecimal(l[1]);
            BigDecimal bigDecimal3 = new BigDecimal(l[2]);
            add = bigDecimal.add(bigDecimal1).add(bigDecimal2).add(bigDecimal3);
        } else {
            BigDecimal bigDecimal1 = new BigDecimal(l[0]);
            BigDecimal bigDecimal2 = new BigDecimal(l[1]);
            BigDecimal bigDecimal3 = new BigDecimal(l[2]);
            BigDecimal bigDecimal4 = new BigDecimal(l[3]);
            add = bigDecimal.add(bigDecimal1).add(bigDecimal2).add(bigDecimal3).add(bigDecimal4);
        }
        System.out.println(add);
    }
}
