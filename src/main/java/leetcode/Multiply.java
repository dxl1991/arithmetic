package leetcode;
//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
//
// 示例 1:
//
// 输入: num1 = "2", num2 = "3"
//输出: "6"
//
// 示例 2:
//
// 输入: num1 = "123", num2 = "456"
//输出: "56088"
//
// 说明：
//
//
// num1 和 num2 的长度小于110。
// num1 和 num2 只包含数字 0-9。
// num1 和 num2 均不以零开头，除非是数字 0 本身。
// 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
//
// Related Topics 数学 字符串
// 👍 640 👎 0
/**
 * @author dengxinlong
 * @date 2021/10/19 17:16
 */
public class Multiply {
    public static void main(String[] args) {
        System.out.println(multiply("123","456"));
    }
    public static String multiply(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)){
            return "0";
        }
        String[] temp = new String[num2.length()];
        for(int i=0;i<num2.length();i++){
            int a = num2.charAt(i) - '0';
            int add = 0;
            StringBuilder sb = new StringBuilder();
            for(int j=num1.length() - 1;j>=0;j--){
                int b = num1.charAt(j) - '0';
                int m = a * b + add;
                sb.append(m % 10);
                add = m / 10;
            }
            temp[i] = add > 0 ? sb.toString() + add : sb.toString();
        }
        StringBuilder res = new StringBuilder();
        int add = 0;
        for(int i=0;i<num1.length() + num2.length();i++){
            int a = 0;
            for(int j=temp.length - 1;j>=0;j--){
                int index = i - (temp.length - 1 - j);
                if(index < 0){
                    break;
                }
                if(index >= temp[j].length()){
                    continue;
                }
                int b = temp[j].charAt(index) - '0';
                a += b;
            }
            a += add;
            add = a / 10;
            res.append(a % 10);
        }
        String str = res.reverse().toString();
        if(str.charAt(0) == '0'){
            str = str.substring(1);
        }
        return add > 0 ? add + str : str;
    }
}
