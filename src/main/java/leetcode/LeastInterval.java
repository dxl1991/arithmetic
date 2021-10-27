package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dengxinlong
 * @date 2021/9/23 17:35
 */
public class LeastInterval {
    public static void main(String[] args) {
        char[] tasks = new char[]{'A','A','A','B','B','B', 'C','C','C', 'D', 'D', 'E'};
        System.out.println(leastInterval(tasks,2));
    }

    public static int leastInterval(char[] tasks, int n) {
        if(n == 0){
            return tasks.length;
        }
        Map<Character, Integer> chars = new HashMap<>();
        for(char c : tasks){
            chars.put(c,1 + chars.getOrDefault(c,0));
        }
        List<CharWrap> charWraps = new ArrayList<>();
        for(char c : chars.keySet()){
            CharWrap charWrap = new CharWrap();
            charWrap.c = c;
            charWrap.count = chars.get(c);
            charWraps.add(charWrap);
        }
        charWraps.sort(Comparator.comparingInt(o -> -o.count));
        int step = 0;
        int all = tasks.length;
        while (all > 0){
            int i = step++ % n;
            CharWrap charWrap = null;
            if(i < charWraps.size() && charWraps.get(i).count > 0 && (charWraps.get(i).step == 0 || step - charWraps.get(i).step > n)){
                charWrap = charWraps.get(i);
            }else{
                for(CharWrap temp : charWraps){
                    if(temp.count <= 0 || (temp.step > 0 && step - temp.step <= n)){
                        continue;
                    }
                    charWrap = temp;
                    break;
                }
            }
            if(charWrap == null){
                System.out.print('-');
            }else{
                charWrap.count--;
                charWrap.step = step;
                all--;
                System.out.print(charWrap.c);
            }
        }
        System.out.println();
        return step;
    }

    private static class CharWrap{
        private char c;
        private int count;
        private int step;
    }
}
