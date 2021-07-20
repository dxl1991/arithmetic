package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dcsz
 * @date 2021/6/4 11:22
 * @version 1.0
 */
public class LetterCombinations {
    public static void main(String[] args) {
        LetterCombinations letters = new LetterCombinations();
        System.out.println(letters.letterCombinations("23476435"));
    }

    private String letterMap[] = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    private ArrayList<String> res;

    public List<String> letterCombinations(String digits) {

        res = new ArrayList<>();
        if(digits.equals(""))
            return res;

        findCombination(digits, 0, "");
        return res;
    }

    private void findCombination(String digits, int index, String s){

        if(index == digits.length()){
            res.add(s);
            return;
        }

        Character c = digits.charAt(index);
        String letters = letterMap[c - '0'];
        for(int i = 0 ; i < letters.length() ; i ++){
            findCombination(digits, index+1, s + letters.charAt(i));
        }
    }
}
