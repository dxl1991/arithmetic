import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Author dengxinlong
 * @Date 2019/10/17
 */
public class Exercise {

    public static void main(String[] args){
//        String s = "atcrta";
//        String t = "aatact";
//        System.out.println(test2(s,t));
        String s = "RLRRLLRLRL";
        System.out.println(queensAttacktheKing(new int[][]{{0,1},{1,0},{4,0},{0,4},{3,3},{2,4}},
new int[]{0,0}));
    }

    //判断字符串t是否为s的重排后组成的单词
    public static boolean test1(String s,String t){
        if(s.length() != t.length()){
            return false;
        }
        if(s.equals(t)){
            return true;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0;i<s.length();i++){
            int v = s.charAt(i);
            if(map.containsKey(v)){
                map.put(v,map.get(v)+1);
            }else{
                map.put(v,1);
            }
        }
        for(int i =0;i<t.length();i++){
            int v = t.charAt(i);
            if(!map.containsKey(v)){
                return false;
            }
            int count = map.get(v) - 1;
            if(count == 0){
                map.remove(v);
            }else{
                map.put(v,count);
            }
        }
        return map.size() == 0;
    }

    public static boolean test2(String s,String t){
        if(s.length() != t.length()){
            return false;
        }
        if(s.equals(t)){
            return true;
        }
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        char[] chars2 = t.toCharArray();
        Arrays.sort(chars2);
        for(int i = 0;i<chars.length;i++){
            if(chars[i] != chars2[i]){
                return false;
            }
        }
        return true;
    }

public static class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }

    @Override
    public String toString() {
        return val+"";
    }
}
    public static ListNode insertionSortList(ListNode head) {
        ListNode newHead = head;
        while(newHead.next != null){
            ListNode head1 = newHead;
            newHead = newHead.next;
            head1.next = newHead.next;
            newHead.next = null;
            ListNode newHead2 = head;
            while(newHead2 != null && newHead2.val != newHead.val){
                if(newHead2.val > newHead.val){

                    break;
                }
                newHead2 = newHead2.next;
            }
            ListNode temp = newHead2.next;
            newHead2.next = newHead;
            newHead.next = temp;
        }
        return head;
    }

    public static int balancedStringSplit(String s){
        int count = 0;
        int r = 0;
        int l = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == 'R'){
                r++;
            }else{
                l++;
            }
            if(r == l){
                count++;
                r = 0;
                l = 0;
            }
        }
        return count;
    }
    public static List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> temp = new ArrayList<>();
        if(queens == null || king == null){
            return temp;
        }

        int x = king[0] - 1;
        while (x >= 0){
            for(int i = 0;i<queens.length;i++){
                if(queens[i][1] == x && queens[i][0] == king[0]){
                    List<Integer> index = new ArrayList<>();
                    index.add(queens[i][0]);
                    index.add(queens[i][1]);
                    temp.add(index);
                    break;
                }
            }
            x--;
        }
        x = king[0] + 1;
        while (x < 8){
            for(int i = 0;i<queens.length;i++){
                if(queens[i][1] == x && queens[i][0] == king[0]){
                    List<Integer> index = new ArrayList<>();
                    index.add(queens[i][0]);
                    index.add(queens[i][1]);
                    temp.add(index);
                    break;
                }
            }
            x++;
        }
        int y = king[1] - 1;
        while (y >= 0){
            for(int i = 0;i<queens.length;i++){
                if(queens[i][0] == y && queens[i][1] == king[1]){
                    List<Integer> index = new ArrayList<>();
                    index.add(queens[i][0]);
                    index.add(queens[i][1]);
                    temp.add(index);
                    break;
                }
            }
            y--;
        }
        y = king[1] + 1;
        while (y < 8){
            for(int i = 0;i<queens.length;i++){
                if(queens[i][0] == y && queens[i][1] == king[1]){
                    List<Integer> index = new ArrayList<>();
                    index.add(queens[i][0]);
                    index.add(queens[i][1]);
                    temp.add(index);
                    break;
                }
            }
            y++;
        }
        return temp;
    }
}
