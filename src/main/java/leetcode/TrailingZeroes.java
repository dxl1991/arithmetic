package leetcode;

/**
 * @author dcsz
 * @date 2021/7/12 11:18
 * @version 1.0
 */
public class TrailingZeroes {
    public static void main(String[] args) {
        System.out.println(trailingZeroes(15));
    }

    public static int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }
}
