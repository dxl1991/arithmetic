package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dcsz
 * @date 2021/6/9 17:25
 * @version 1.0
 * 二叉树的中序遍历，左中右
 */
public class InorderTraversal {
    public static void main(String[] args) {

    }
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(result,root);
        return result;
    }

    private static void inorderHelper(List<Integer> result,TreeNode node){
        if(node == null){
            return;
        }
        inorderHelper(result,node.left);
        result.add(node.val);
        inorderHelper(result,node.right);
    }

   static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
