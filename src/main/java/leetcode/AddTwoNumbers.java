package leetcode;
//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//
//
// 示例 1：
//
//
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
//
//
// 示例 2：
//
//
//输入：l1 = [0], l2 = [0]
//输出：[0]
//
//
// 示例 3：
//
//
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
//
//
//
//
// 提示：
//
//
// 每个链表中的节点数在范围 [1, 100] 内
// 0 <= Node.val <= 9
// 题目数据保证列表表示的数字不含前导零
//
// Related Topics 递归 链表 数学
// 👍 6277 👎 0
/**
 * @author dcsz
 * @date 2021/6/3 15:34
 * @version 1.0
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2,new ListNode(4,new ListNode(3)));
        ListNode l2 = new ListNode(5,new ListNode(6,new ListNode(4)));
        ListNode listNode = addTwoNumbers(l1,l2);
        System.out.println(listNode);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode();
        int extra = 0;
        ListNode listNode1 = l1;
        ListNode listNode2 = l2;
        ListNode result = listNode;
        while (listNode1 != null || listNode2 != null || extra > 0){
            int sum = extra;
            if(listNode1 != null){
                sum += listNode1.val;
            }
            if(listNode2 != null){
                sum += listNode2.val;
            }
            result.next = new ListNode(sum % 10);
            extra = sum / 10;
            listNode1 = listNode1 != null ? listNode1.next : null;
            listNode2 = listNode2 != null ? listNode2.next : null;
            result = result.next;
        }
        return listNode.next;
    }

   public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
