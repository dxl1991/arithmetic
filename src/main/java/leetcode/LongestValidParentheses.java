package leetcode;

import java.util.ArrayList;
import java.util.List;
//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
//
//
//
//
//
// 示例 1：
//
//
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
//
//
// 示例 2：
//
//
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
//
//
// 示例 3：
//
//
//输入：s = ""
//输出：0
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 3 * 104
// s[i] 为 '(' 或 ')'
//
//
//
// Related Topics 栈 字符串 动态规划
// 👍 1498 👎 0
/**
 * @author dengxinlong
 * @date 2021/10/14 9:57
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        List<Unit> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            for(Unit unit : list){
                if(unit.count < 0){
                    continue;
                }
                unit.count += (chars[i] == '(' ? 1 : -1);
                if(unit.count == 0){
                    unit.length = i - unit.index + 1;
                }
            }
            if(chars[i] == '('){
                list.add(new Unit(i,1,0));
            }
        }
        int max = 0;
        for(Unit unit : list){
            max = Math.max(max,unit.length);
        }
        return max;
    }

    private class Unit{
        int index;
        int count;
        int length;
        Unit(int index,int count,int length){
            this.index = index;
            this.count = count;
            this.length = length;
        }
    }
}
