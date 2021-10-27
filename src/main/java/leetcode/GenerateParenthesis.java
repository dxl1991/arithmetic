package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 有效括号组合需满足：左括号必须以正确的顺序闭合。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：["()"]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 8
//
// Related Topics 字符串 动态规划 回溯
// 👍 2065 👎 0
/**
 * @author dengxinlong
 * @date 2021/9/30 17:11
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    private static class TempClass{
        String s;
        int n;
        int count;
        boolean flag;
    }

    public static List<String> generateParenthesis(int nn) {
        List<String> result = new ArrayList<>();
        Random random = new Random();
        Stack<TempClass> tempStack = new Stack<>();
        while (!tempStack.isEmpty() || result.isEmpty()){
            int count = 0;
            String s = "";
            int n = nn;
            if(!tempStack.isEmpty()){
                TempClass tempClass = tempStack.pop();
                count = tempClass.count;
                s = tempClass.s;
                n = tempClass.n;
                if(tempClass.flag){
                    s += ")";
                    count--;
                }else{
                    s += "(";
                    count++;
                    n--;
                }
            }
            while(n > 0 || count > 0){
                if(count == 0){
                    s += "(";
                    count++;
                    n--;
                }
                if(n == 0){
                    s += ")";
                    count--;
                }else{
                    boolean flag = random.nextBoolean();
                    TempClass tempClass = new TempClass();
                    tempClass.s = s;
                    tempClass.n = n;
                    tempClass.count = count;
                    tempClass.flag = flag;
                    tempStack.push(tempClass);
                    if(flag){
                        s += "(";
                        count++;
                        n--;
                    }else{
                        s += ")";
                        count--;
                    }
                }
            }
            result.add(s);
        }
        return result;
    }
}
