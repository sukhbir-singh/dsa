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
 // Interestingly, here if we add heights of left and right child nodes. it comes total length, that is diameter
class Solution {
    private int maxD;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxD = 0;
        findHeight(root);
        return maxD;
    }

    private int findHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int lh = findHeight(root.left);
        int rh = findHeight(root.right);
        maxD = Math.max(maxD, lh + rh);
        return Math.max(lh, rh) + 1;
    }
}