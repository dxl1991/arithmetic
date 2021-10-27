package leetcode;
//给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
//
// '?' 可以匹配任何单个字符。
//'*' 可以匹配任意字符串（包括空字符串）。
//
//
// 两个字符串完全匹配才算匹配成功。
//
// 说明:
//
//
// s 可能为空，且只包含从 a-z 的小写字母。
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
//
//
// 示例 1:
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
//
// 示例 2:
//
// 输入:
//s = "aa"
//p = "*"
//输出: true
//解释: '*' 可以匹配任意字符串。
//
//
// 示例 3:
//
// 输入:
//s = "cb"
//p = "?a"
//输出: false
//解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
//
//
// 示例 4:
//
// 输入:
//s = "adceb"
//p = "*a*b"
//输出: true
//解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
//
//
// 示例 5:
//
// 输入:
//s = "acdcb"
//p = "a*c?b"
//输出: false
// Related Topics 贪心 递归 字符串 动态规划
// 👍 764 👎 0
/**
 * @author dengxinlong
 * @date 2021/10/20 10:50
 */
public class IsMatch {
    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(isMatch2("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb","**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
        System.out.println(System.nanoTime() - start);
    }
    public static boolean isMatch(String s, String p) {
        boolean flag = false;
        int index = 0;
        for(int i=0;i<p.length();i++){
            if(p.charAt(i) == '?'){
                index++;
                flag = true;
            }else if(p.charAt(i) == '*'){
                if(i == p.length() - 1){
                    if(flag){
                        return index <= s.length();
                    }
                    return true;
                }
                int j = i + 1;
                while (j < p.length()){
                    if(p.charAt(j) != '*'){
                        break;
                    }
                    j++;
                }
                if(j == p.length()){
                    return true;
                }
                int temp = index;
                while (temp < s.length()){
                    char c = p.charAt(j);
                    if(c == '?' || s.charAt(temp) == c){
                        if(isMatch(s.substring(temp),p.substring(j))){
                            return true;
                        }
                    }
                    temp++;
                }
                return false;
            }else{
                if(index >= s.length()){
                    return false;
                }
                if(s.charAt(index) != p.charAt(i)){
                    return false;
                }
                flag = false;
                index++;
            }
        }
        return index == s.length();
    }

    public static boolean isMatch2(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
