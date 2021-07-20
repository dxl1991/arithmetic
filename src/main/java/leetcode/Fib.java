package leetcode;

import java.util.HashMap;

/**
 * @author dcsz
 * @date 2021/6/10 10:47
 * @version 1.0
 */
public class Fib {
    public static void main(String[] args) {
        System.out.println(fib(95));
    }

    public static int fib(int n) {
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        int m1 = 0;
        int m2 = 1;
        for(int i=2;i<n;i++){
            int temp = (m1 + m2)  % 1000000007;
            m1 = m2;
            m2 = temp;
        }
        return (m1 + m2) % 1000000007;
    }
}
