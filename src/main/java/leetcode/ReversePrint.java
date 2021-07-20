package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
//
//
//
// 示例 1：
//
// 输入：head = [1,3,2]
//输出：[2,3,1]
//
//
//
// 限制：
//
// 0 <= 链表长度 <= 10000
// Related Topics 链表
// 👍 157 👎 0
/**
 * @author dcsz
 * @date 2021/6/15 14:15
 * @version 1.0
 */
public class ReversePrint {
    public static void main(String[] args) {
        ListNode head = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,null))));
        int[] result = reversePrint2(head);
        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }

    //解法一：使用递归
    static List<Integer> temp = new ArrayList<>();
    public static int[] reversePrint(ListNode head) {
        recursion(head);
        int[] result = new int[temp.size()];
        for(int i=0;i<result.length;i++){
            result[i] = temp.get(i);
        }
        return result;
    }
    public static void recursion(ListNode head) {
        if(head == null){
            return;
        }
        recursion(head.next);
        temp.add(head.val);
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x,ListNode next) { val = x; this.next = next;}
    }
    //解法二：使用栈
    public static int[] reversePrint2(ListNode head) {
        Stack<Integer> stack = new Stack();
        ListNode node = head;
        while (node !=null){
            stack.push(node.val);
            node = node.next;
        }
        int[] result = new int[stack.size()];
        for(int i=0;i<result.length;i++){
            result[i] = stack.pop();
        }
        return result;
    }
}
