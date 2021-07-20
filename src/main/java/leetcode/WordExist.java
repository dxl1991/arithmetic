package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
//
//
//
// 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
//
//
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
//输入：board = [["a","b"],["c","d"]], word = "abcd"
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= board.length <= 200
// 1 <= board[i].length <= 200
// board 和 word 仅由大小写英文字母组成
//
//
//
//
// 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/
// Related Topics 深度优先搜索
// 👍 334 👎 0
/**
 * @author dcsz
 * @date 2021/6/11 15:06
 * @version 1.0
 */
public class WordExist {
    public static void main(String[] args) {
        char[][] temp = new char[][]{
                {'a','a','a','a'},
                {'a','a','a','a'},
                {'a','a','a','a'}};
        System.out.println(exist(temp,"aaaaaaaaaa"));
    }

    static Map<Integer, Data> elementMap = new HashMap<>();

    public static boolean exist(char[][] board, String word) {
        if(word.length() > board.length * board[0].length){
            return false;
        }
        Stack<Data> stack = new Stack<>();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j] != word.charAt(0)){
                    continue;
                }
                int index = 1;
                int x = i;
                int y = j;
                Data data;
                while (index < word.length()){
                    List<Data> temp = existAround(board,x,y,word,index);
                    if(temp.size() > 0){
                        data = temp.get(0);
                        for(int k = 1;k<temp.size();k++){
                            stack.push(temp.get(k));
                        }
                    } else if(!stack.isEmpty()){
                        data = stack.pop();
                    } else {
                        break;
                    }
                    elementMap.put(index,new Data(x,y,index));
                    x = data.x;
                    y = data.y;
                    index = data.index + 1;
                }
                if(index == word.length()){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean contains(int x,int y,int index){
        for(int i = 0;i<index;i++){
            Data data = elementMap.get(i);
            if(data != null && data.x == x && data.y == y){
                return true;
            }
        }
        return false;
    }

    private static List<Data> existAround(char[][] board,int i,int j,String word,int index){
        List<Data> temp = new ArrayList<>();
        char c = word.charAt(index);
        if(!contains(i+1,j,index) && i + 1 < board.length && board[i + 1][j] == c){
            temp.add(new Data(i+1,j,index));
        }
        if(!contains(i-1,j,index) && i - 1 >= 0 && board[i - 1][j] == c){
            temp.add(new Data(i-1,j,index));
        }
        if(!contains(i,j+1,index) && j + 1 < board[0].length && board[i][j + 1] == c){
            temp.add(new Data(i,j+1,index));
        }
        if(!contains(i,j-1,index) && j - 1 >= 0 && board[i][j - 1] == c){
            temp.add(new Data(i,j-1,index));
        }
        return temp;
    }

    static class Data{
        int x;
        int y;
        int index;
        Data(int x,int y,int index){
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    public boolean exist2(char[][] board, String word) {
        char[] words = word.toCharArray();
        //建立上下左右数组,x代表横向，y代表纵向
        int[] x = {0, 0, -1, 1};
        int[] y = {-1, 1, 0, 0};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, x, y, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Depth-First-Search深度优先搜索
     */
    private static boolean dfs(char[][] board, char[] words, int i, int j, int[] x, int[] y, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || words[k] != board[i][j]) {
            return false;
        }
        //递归结束标志位
        if (k == words.length - 1) {
            return true;
        }
        //匹配成功 将当前位置字符置为''，防止二次使用
        char temp = board[i][j];
        board[i][j] = '\0';
        boolean res = false;
        for (int n = 0; n < 4; n++) {
            //如果递归成功，说明查询完毕，找到了目标单词word的路径
            if (dfs(board, words, i + y[n], j + x[n], x, y, k + 1)) {
                res = true;
                break;
            }
        }
        //复原当前位置的字符
        board[i][j] = temp;
        return res;
    }
}
