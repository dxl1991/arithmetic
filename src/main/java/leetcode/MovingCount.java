package leetcode;
//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
//
//
//
// 示例 1：
//
// 输入：m = 2, n = 3, k = 1
//输出：3
//
//
// 示例 2：
//
// 输入：m = 3, n = 1, k = 0
//输出：1
//
//
// 提示：
//
//
// 1 <= n,m <= 100
// 0 <= k <= 20
//
// 👍 298 👎 0

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jnr.ffi.annotations.In;

/**
 * @author dcsz
 * @date 2021/6/15 10:46
 * @version 1.0
 */
public class MovingCount {
    public static void main(String[] args) {
        System.out.println(movingCount(3,2,17));
    }
    static int[] x = {0, 0, -1, 1};
    static int[] y = {-1, 1, 0, 0};
    static int maxCount = 0;
    public static int movingCount(int m, int n, int k) {
        int[][] temp = new int[m][n];
        dfs(m,n,k,0,0,temp);
        return maxCount;
    }

    private static void dfs(int m, int n, int k,int i,int j,int[][] temp){
        if(i < 0 || i >= m || j < 0 || j >= n || sumBit(i,j) > k || temp[i][j] == 1){
            return;
        }
        maxCount++;
        temp[i][j] = 1;
        for(int index = 0;index < 4;index++){
            dfs(m,n,k,i+x[index],j+y[index],temp);
        }
    }

    private static int sumBit(int i,int j){
        int sum = 0;
        sum += (i % 10);
        if(i >= 100){
            sum += (i / 100);
            sum += (i % 100) / 10;
        }else{
            sum += (i / 10);
        }
        sum += (j % 10);
        if(j >= 100){
            sum += (j / 100);
            sum += (j % 100) / 10;
        }else{
            sum += (j / 10);
        }
        return sum;
    }
}
