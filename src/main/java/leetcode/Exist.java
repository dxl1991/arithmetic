package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
//
//
//
// 示例 1：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CCED"
//输出：true
//
//
// 示例 2：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SE
//E"
//输出：true
//
//
// 示例 3：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CB"
//输出：false
//
//
//
//
// 提示：
//
//
// m == board.length
// n = board[i].length
// 1 <= m, n <= 6
// 1 <= word.length <= 15
// board 和 word 仅由大小写英文字母组成
//
//
//
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
// Related Topics 数组 回溯 矩阵
// 👍 1123 👎 0
/**
 * @author dengxinlong
 * @date 2021/12/22 16:07
 */
public class Exist {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(new Exist().exist(board,"ABCCED"));
    }
    public boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(find(board,word,i,j,0,new ArrayList<>())){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean find(char[][] board, String word,int i,int j,int index, List<Integer> list){
        if(board[i][j] != word.charAt(index)){
            return false;
        }
        if(index == word.length() - 1){
            return true;
        }
        list.add(getKey(i,j));
        if(i > 0 && !list.contains(getKey(i - 1,j))){
            if(find(board,word,i-1,j,index+1,list)){
                return true;
            }
        }
        if(i < board.length - 1 && !list.contains(getKey(i + 1,j))){
            if(find(board,word,i+1,j,index+1,list)){
                return true;
            }
        }
        if(j > 0 && !list.contains(getKey(i,j - 1))){
            if(find(board,word,i,j - 1,index+1,list)){
                return true;
            }
        }
        if(j < board[0].length - 1 && !list.contains(getKey(i,j + 1))){
            if(find(board,word,i,j + 1,index+1,list)){
                return true;
            }
        }
        list.remove(list.size() - 1);
        return false;
    }

    public int getKey(int i,int j){
        return i*100000+j;
    }
}
