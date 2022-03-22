package leetcode;

import java.util.ArrayList;
import java.util.List;
//给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
//
// 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
//
// 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
//
// 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
//
// 说明:
//
//
// 单词是指由非空格字符组成的字符序列。
// 每个单词的长度大于 0，小于等于 maxWidth。
// 输入单词数组 words 至少包含一个单词。
//
//
// 示例:
//
// 输入:
//words = ["This", "is", "an", "example", "of", "text", "justification."]
//maxWidth = 16
//输出:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
//
//
// 示例 2:
//
// 输入:
//words = ["What","must","be","acknowledgment","shall","be"]
//maxWidth = 16
//输出:
//[
//  "What   must   be",
//  "acknowledgment  ",
//  "shall be        "
//]
//解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
//     因为最后一行应为左对齐，而不是左右两端对齐。
//     第二行同样为左对齐，这是因为这行只包含一个单词。
//
//
// 示例 3:
//
// 输入:
//words = ["Science","is","what","we","understand","well","enough","to","explain
//",
//         "to","a","computer.","Art","is","everything","else","we","do"]
//maxWidth = 20
//输出:
//[
//  "Science  is  what we",
//  "understand      well",
//  "enough to explain to",
//  "a  computer.  Art is",
//  "everything  else  we",
//  "do                  "
//]
//
// Related Topics 字符串 模拟
// 👍 243 👎 0
/**
 * @author dengxinlong
 * @date 2021/12/16 12:08
 */
public class FullJustify {
    public static void main(String[] args) {
        String[] words = new String[]{"What","must","be","acknowledgment","shall","be"};
        System.out.println(new FullJustify().fullJustify(words,16));
    }
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        int len = 0;
        for(int i=0;i<words.length;i++){
            String s = words[i];
            if(len + s.length() == maxWidth){
                tempList.add(s);
                result.add(contact(tempList,maxWidth,false));
                tempList.clear();
                len = 0;
            }else if(len + s.length() > maxWidth){
                result.add(contact(tempList,maxWidth,false));
                tempList.clear();
                len = s.length() + 1;
                tempList.add(s);
            }else{
                len += s.length() + 1;
                tempList.add(s);
            }
        }
        if(tempList.size() > 0){
            result.add(contact(tempList,maxWidth,true));
        }
        return result;
    }

    private String contact(List<String> list,int maxWidth,boolean last){
        if(list.size() == 1){
            StringBuilder sb = new StringBuilder(list.get(0));
            int length = maxWidth - sb.length();
            for(int i=0;i<length;i++){
                sb.append(" ");
            }
            return sb.toString();
        }
        int size = 0;
        for(String s : list){
            size += s.length();
        }
        int space = maxWidth - size;
        int m = space / (list.size() - 1);
        int n = space % (list.size() - 1);
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for(String s : list){
            sb.append(s);
            if(index++ < list.size() - 1){
                if(last){
                    sb.append(" ");
                }else{
                    for(int i=0;i<m;i++){
                        sb.append(" ");
                    }
                    if(n > 0){
                        sb.append(" ");
                        n--;
                    }
                }
            }
        }
        if(last){
            int length = maxWidth - sb.length();
            for(int i=0;i<length;i++){
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
