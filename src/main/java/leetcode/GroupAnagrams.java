package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
//
// 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。
//
//
//
// 示例 1:
//
//
//输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
//
// 示例 2:
//
//
//输入: strs = [""]
//输出: [[""]]
//
//
// 示例 3:
//
//
//输入: strs = ["a"]
//输出: [["a"]]
//
//
//
// 提示：
//
//
// 1 <= strs.length <= 104
// 0 <= strs[i].length <= 100
// strs[i] 仅包含小写字母
//
// Related Topics 哈希表 字符串 排序
// 👍 897 👎 0
/**
 * @author dengxinlong
 * @date 2021/11/12 12:25
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        System.out.println(new GroupAnagrams().groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        for(String s : strs){
            boolean added = false;
            for(List<String> list : result){
                if(match(list.get(0),s)){
                    list.add(s);
                    added = true;
                    break;
                }
            }
            if(!added){
                List<String> temp = new ArrayList<>();
                temp.add(s);
                result.add(temp);
            }
        }
        return result;
    }

    public boolean match(String s1,String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        int num1 = 0,num2 = 0;
        for(char c : s1.toCharArray()){
            num1 += c - 'a';
        }
        for(char c : s2.toCharArray()){
            num2 += c - 'a';
        }
        if(num1 != num2){
            return false;
        }
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0;i<s1.length();i++){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            map.compute(c1,(k,v) -> v == null ? 1 : v+1);
            map.compute(c2,(k,v) -> v == null ? -1 : v-1);
        }
        for(int count : map.values()){
            if(count != 0){
                return false;
            }
        }
        return true;
    }
}
