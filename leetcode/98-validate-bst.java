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
// This can be solved in both iterative way or recursive way
// One innovative and interesting way to solve this question is using inorder traversal.
// left -> node -> right. Each value should be smaller than next one.
class Solution {
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long lowerLimit, long higherLimit) {
        if (node == null) {
            return true;
        }

        // check current node's value
        if (node.val * 1L <= lowerLimit || node.val*1L >= higherLimit) {
            return false;
        }
        return validate(node.left, lowerLimit, node.val) && validate(node.right, node.val, higherLimit);
    }
}