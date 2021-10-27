package leetcode;

//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
//
//
// 示例 2：
//
//
//输入：head = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：head = [1]
//输出：[1]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目在范围 [0, 100] 内
// 0 <= Node.val <= 100
//
//
//
//
// 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
// Related Topics 递归 链表
// 👍 1068 👎 0
/**
 * @author dengxinlong
 * @date 2021/10/7 16:36
 */
public class SwapPairs {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        ListNode head = reverseKGroup(node,2);
        System.out.println(head);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k <= 1){
            return head;
        }
        ListNode root = new ListNode(0,head);
        ListNode beginNode = root;
        ListNode[] tempList = new ListNode[k];
        while (true){
            ListNode tempNode = beginNode.next;
            int index = 0;
            while (tempNode != null && index < k){
                tempList[index++] = tempNode;
                tempNode = tempNode.next;
            }
            if(index == k){
                tempNode = beginNode;
                ListNode endNextNode = tempList[k - 1].next;
                for(int i=k-1;i>=0;i--){
                    tempNode.next = tempList[i];
                    tempNode = tempNode.next;
                }
                tempList[0].next = endNextNode;
                beginNode = tempNode;
            }else{
                return root.next;
            }
        }
    }

    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode current = head;
        ListNode temp1 = null,temp2 = null;
        boolean change = false;
        while (current != null){
            ListNode tempNext = current.next;
            if(change){
                temp2.next = current.next;
                current.next = temp2;
                if(temp1 != null){
                    temp1.next = current;
                }else{
                    head = current;
                }
                change = false;
            }else{
                change = true;
                temp1 = temp2;
                temp2 = current;
            }
            current = tempNext;
        }
        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return "ListNode{" + "val=" + val + ", next=" + next + '}';
        }
    }
}
