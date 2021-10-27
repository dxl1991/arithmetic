package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author dengxinlong
 * @date 2021/8/26 16:37
 * //编写一个程序，通过填充空格来解决数独问题。 
 * //
 * // 数独的解法需 遵循如下规则： 
 * //
 * // 
 * // 数字 1-9 在每一行只能出现一次。 
 * // 数字 1-9 在每一列只能出现一次。 
 * // 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
 * // 
 * //
 * // 数独部分空格内已填入了数字，空白格用 '.' 表示。 
 * //
 * // 
 * //
 * // 
 * // 
 * // 
 * // 示例： 
 * //
 * // 
 * //输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5","."
 * //,".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".","."
 * //,"3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"
 * //],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],["
 * //.",".",".",".","8",".",".","7","9"]]
 * //输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"
 * //],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["
 * //4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","
 * //6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","
 * //5","2","8","6","1","7","9"]]
 * //解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
 * //
 * //
 * // 
 * //
 * // 
 * //
 * // 提示： 
 * //
 * // 
 * // board.length == 9 
 * // board[i].length == 9 
 * // board[i][j] 是一位数字或者 '.' 
 * // 题目数据 保证 输入数独仅有一个解 
 * // 
 * // 
 * // 
 * // 
 * // Related Topics 数组 回溯 矩阵 
 * // 👍 917 👎 0
 */
public class SolveSudoku {
    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(board);
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                System.out.print(board[i][j]+",");
            }
            System.out.println();
        }
    }
    public static void solveSudoku(char[][] board) {
        // 三个布尔数组 表明 行, 列, 还有 3*3 的方格的数字是否被使用过
        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        boolean[][][] boxUsed = new boolean[3][3][10];
        // 初始化
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++) {
                int num = board[row][col] - '0';
                if(1 <= num && num <= 9){
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row/3][col/3][num] = true;
                }
            }
        }
        // 递归尝试填充数组
        recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, 0, 0);
    }

    /**
     * 回溯算法的基本思想是：从一条路往前走，能进则进，不能进则退回来，换一条路再试
     * 回溯算法实际上一个类似枚举的搜索尝试过程，主要是在搜索尝试过程中寻找问题的解，当发现已不满足求解条件时，就“回溯”返回，尝试别的路径。
     * 回溯法是一种选优搜索法，按选优条件向前搜索，以达到目标。但当探索到某一步时，发现原先选择并不优或达不到目标，就退回一步重新选择，
     * 这种走不通就退回再走的技术为回溯法，而满足回溯条件的某个状态的点称为“回溯点”。
     * 许多复杂的，规模较大的问题都可以使用回溯法，有“通用解题方法”的美称。
     * @param board
     * @param rowUsed
     * @param colUsed
     * @param boxUsed
     * @param row
     * @param col
     * @return
     */
    private static boolean recusiveSolveSudoku(char[][]board, boolean[][]rowUsed, boolean[][]colUsed, boolean[][][]boxUsed, int row, int col){
        // 边界校验, 如果已经填充完成, 返回true, 表示一切结束
        if(col == board[0].length){
            col = 0;
            row++;
            if(row == board.length){
                return true;
            }
        }
        // 是空则尝试填充, 否则跳过继续尝试填充下一个位置
        if(board[row][col] == '.') {
            // 尝试填充1~9
            for(int num = 1; num <= 9; num++){
                boolean canUsed = !(rowUsed[row][num] || colUsed[col][num] || boxUsed[row/3][col/3][num]);
                if(canUsed){
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row/3][col/3][num] = true;

                    board[row][col] = (char)('0' + num);
                    if(recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, row, col + 1)){
                        return true;
                    }
                    board[row][col] = '.';

                    rowUsed[row][num] = false;
                    colUsed[col][num] = false;
                    boxUsed[row/3][col/3][num] = false;
                }
            }
        } else {
            return recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, row, col + 1);
        }
        return false;
    }
    
}
