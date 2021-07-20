package leetcode;
//一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//
// 示例 1：
//
// 输入：n = 2
//输出：2
//
//
// 示例 2：
//
// 输入：n = 7
//输出：21
//
//
// 示例 3：
//
// 输入：n = 0
//输出：1
//
// 提示：
//
//
// 0 <= n <= 100
//
//
// 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/
//
//
// Related Topics 递归
// 👍 169 👎 0
/**
 * @author dcsz
 * @date 2021/6/11 12:27
 * @version 1.0
 */
public class NumWays {
    public static void main(String[] args) {
        System.out.println(numWays1(7));
    }

    //动态规划解法
    public static int numWays1(int n) {
        if(n < 2) return 1;
        long[] result = new long[n + 1];
        result[0] = 1;
        result[1] = 1;
        for(int i = 2; i <= n; i++){
            result[i] = result[i - 1] + result[i - 2];
            result[i] %= (Math.pow(10,9) +7);
        }
        return (int)result[n];
    }

    static int count = 0;
    public static int numWays(int n) {
        skip(41);
        return count;
    }

    private static void skip(int n){
        if(n == 0){
            count++;
            return;
        }
        if(n == 1){
            skip(n - 1);
        }else{
            skip(n - 1);
            skip(n - 2);
        }
    }
}
