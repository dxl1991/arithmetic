package leetcode;
//给你一个链表数组，每个链表都已经按升序排列。
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。
//
//
//
// 示例 1：
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
//
//
// 示例 2：
//
// 输入：lists = []
//输出：[]
//
//
// 示例 3：
//
// 输入：lists = [[]]
//输出：[]
//
//
//
//
// 提示：
//
//
// k == lists.length
// 0 <= k <= 10^4
// 0 <= lists[i].length <= 500
// -10^4 <= lists[i][j] <= 10^4
// lists[i] 按 升序 排列
// lists[i].length 的总和不超过 10^4
//
// Related Topics 链表 分治 堆（优先队列） 归并排序
// 👍 1533 👎 0
/**
 * @author dengxinlong
 * @date 2021/10/7 10:27
 */
public class MergeKLists {
    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];
        ListNode node = new ListNode(1);
        lists[0] = node;
        node.next = new ListNode(4);
        node.next.next = new ListNode(5);
        node = new ListNode(1);
        lists[1] = node;
        node.next = new ListNode(3);
        node.next.next = new ListNode(4);
        node = new ListNode(2);
        lists[2] = node;
        node.next = new ListNode(6);
        ListNode listNode = mergeKLists(lists);
        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode root = new ListNode();
        ListNode current = root;
        boolean loop = true;
        while(loop){
            ListNode minNode = null;
            int index = 0;
            boolean flag = false;
            for(int i=0;i<lists.length;i++){
                if(lists[i] != null){
                    if(minNode == null || minNode.val > lists[i].val){
                        index = i;
                        minNode = lists[i];
                    }
                    flag = true;
                }
            }
            if(flag){
                current.next = minNode;
                current = current.next;
                lists[index] = lists[index].next;
            }else{
                loop = false;
            }
        }
        return root.next;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
