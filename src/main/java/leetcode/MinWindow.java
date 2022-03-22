package leetcode;

import java.util.HashMap;
import java.util.Map;
//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//
//
//
// 注意：
//
//
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。
//
//
//
//
// 示例 1：
//
//
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
//
//
// 示例 2：
//
//
//输入：s = "a", t = "a"
//输出："a"
//
//
// 示例 3:
//
//
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。
//
//
//
// 提示：
//
//
// 1 <= s.length, t.length <= 105
// s 和 t 由英文字母组成
//
//
//
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口
// 👍 1494 👎 0

/**
 * @author dengxinlong
 * @date 2021/12/17 15:34
 * https://assets.leetcode-cn.com/solution-static/76/76_fig1.gif
 */
public class MinWindow {
    public static void main(String[] args) {
        System.out.println(new MinWindow().minWindow("ADOBECODEBANC","ABC"));
    }
    Map<Character, Integer> ori = new HashMap<>();
    Map<Character, Integer> cnt = new HashMap<>();

    public String minWindow(String s, String t) {
        int tLen = t.length();
        for(int i=0;i<tLen;i++){
            ori.put(t.charAt(i),ori.getOrDefault(t.charAt(i),0) + 1);
        }
        int minLen = Integer.MAX_VALUE;
        int l = 0,r = -1,ansL = -1,ansR = -1;
        while (r < s.length()){
            r++;
            if(r < s.length() && ori.containsKey(s.charAt(r))){
                cnt.put(s.charAt(r),cnt.getOrDefault(s.charAt(r),0) + 1);
            }
            while (check() && l <= r){
                if(r - l + 1 < minLen){
                    minLen = r - l + 1;
                    ansL = l;
                    ansR = l + minLen;
                }
                if(ori.containsKey(s.charAt(l))){
                    cnt.put(s.charAt(l),cnt.getOrDefault(s.charAt(l),0) - 1);
                }
                l++;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL,ansR);
    }

    public boolean check(){
        for(char key : ori.keySet()){
            int value = ori.get(key);
            if(cnt.getOrDefault(key,0) < value){
                return false;
            }
        }
        return true;
    }
}
