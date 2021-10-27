package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
//
//
//
// 示例 1：
//
//
//输入：s = "barfoothefoobarman", words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
//
//
// 示例 2：
//
//
//输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//输出：[]
//
//
// 示例 3：
//
//
//输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//输出：[6,9,12]
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 104
// s 由小写英文字母组成
// 1 <= words.length <= 5000
// 1 <= words[i].length <= 30
// words[i] 由小写英文字母组成
//
// Related Topics 哈希表 字符串 滑动窗口
// 👍 549 👎 0
/**
 * @author dengxinlong
 * @date 2021/10/13 12:18
 */
public class FindSubstring {
    public static void main(String[] args) {
        String[] words = new String[]{"word","good","best","good"};
        System.out.println(findSubstring1("wordgoodgoodgoodbestword",words));
    }

    public static List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        int one_word = words[0].length();
        int word_num = words.length;
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < one_word; i++) {
            int left = i, right = i, count = 0;
            HashMap<String, Integer> tmp_map = new HashMap<>();
            while (right + one_word <= s.length()) {
                String w = s.substring(right, right + one_word);
                right += one_word;
                if (!map.containsKey(w)) {
                    count = 0;
                    left = right;
                    tmp_map.clear();
                } else {
                    tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                    count++;
                    while (tmp_map.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
                        String t_w = s.substring(left, left + one_word);
                        count--;
                        tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                        left += one_word;
                    }
                    if (count == word_num) res.add(left);
                }
            }
        }
        return res;
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        int num = 0;
        for(int i=0;i<words.length;i++){
            num += words[i].length();
        }
        List<Integer> results = new ArrayList<>();
        if(num > s.length()){
            return results;
        }
        char[] chars = s.toCharArray();
        Set<Integer> collect = new HashSet<>();
        for(int i=0;i<=chars.length - num;i++){
            collect.clear();
            boolean loop;
            int index = 0;
            do{
                loop = false;
                for(int n=0;n<words.length;n++){
                    if(collect.contains(n)){
                        continue;
                    }
                    int index2 = index;
                    boolean same = true;
                    for(int k = 0;k<words[n].length();k++){
                        if(chars[i + index2++] != words[n].charAt(k)){
                            same = false;
                            break;
                        }
                    }
                    if(same){
                        loop = true;
                        collect.add(n);
                        index = index2;
                        if(collect.size() == words.length){
                            results.add(i);
                            loop = false;
                            break;
                        }
                    }
                }
            }while(loop);
        }
        return results;
    }
}
