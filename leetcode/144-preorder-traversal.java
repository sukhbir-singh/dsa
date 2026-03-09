import java.util.*;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        return recursion(root, new ArrayList<>());
    }

    private List<Integer> recursion(TreeNode node, List<Integer> list) {
        if (node == null) {
            return new ArrayList<>();
        }
        list.add(node.val);
        recursion(node.left, list);
        recursion(node.right, list);
        return list;
    }
}