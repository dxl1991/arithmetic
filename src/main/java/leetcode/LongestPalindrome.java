package leetcode;

/**
 * @author dcsz
 * @date 2021/6/4 17:14
 * @version 1.0
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
    }

    public static String longestPalindrome(String s) {
        int begin = 0,end = 0,maxLength = 0;
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<s.length();j++){
                int index = i;
                boolean flag = true;
                while (index <= (i+j)/2){
                    if(s.charAt(index) != s.charAt(j + i - index)){
                        flag = false;
                        break;
                    }
                    index++;
                }
                if(flag && (j - i) > maxLength){
                    begin = i;
                    end = j;
                    maxLength = j - i;
                }
            }
        }
        return s.substring(begin,end+1);
    }
}
