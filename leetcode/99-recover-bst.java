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
// Good solution: Doing inorder traversal one time and finding the 2 nodes
// Learning: how to find swapped number in sorted array
class Solution {
    private TreeNode x;
    private TreeNode y;
    private TreeNode prev;

    public void recoverTree(TreeNode root) {
        inorder(root);
        swap(x, y);
    }

    private void swap(TreeNode x, TreeNode y) {
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        
        if (prev != null && prev.val > root.val) {
            if (x == null) {
                x = prev;
            }
            y = root;
        }
        
        prev = root;
        inorder(root.right);
    }
}