package leetcode;

import java.util.ArrayList;
import java.util.List;
//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
//
//
//
// 示例 1：
//
//
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
//
//
// 示例 2：
//
//
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 10
// -100 <= matrix[i][j] <= 100
//
// Related Topics 数组 矩阵 模拟
// 👍 916 👎 0
/**
 * @author dengxinlong
 * @date 2021/11/26 18:04
 */
public class SpiralOrder {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(spiralOrder(matrix));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        int i = 0,j = 0;
        int[] tags = new int[]{1,0,0,0};//右，下，左，上
        int tag = 0;
        for(int k=0;k<m*n;k++){
            result.add(matrix[i][j]);
            if(tag == 0){ //右
                if(j < n - 1 - tags[1]){
                    j++;
                }else{
                    tag = 1;
                    i++;
                    tags[1]++;
                }
            }else if(tag == 1){ //下
                if(i < m - 1 - tags[2]){
                    i++;
                }else{
                    tag = 2;
                    j--;
                    tags[2]++;
                }
            }else if(tag == 2){ //左
                if(j > tags[3]){
                    j--;
                }else{
                    tag = 3;
                    i--;
                    tags[3]++;
                }
            }else if(tag == 3){ //上
                if(i > tags[0]){
                    i--;
                }else{
                    tag = 0;
                    j++;
                    tags[0]++;
                }
            }
        }
        return result;
    }
}
