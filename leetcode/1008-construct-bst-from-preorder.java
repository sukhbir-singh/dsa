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
// Little tough implementation, but interesting at the same time
// Idea: using global index, we keep trying to add node as left or right child
// This is a good implementation using a single preorder input. 
// Otherwise not so optimized algorithm for BST is using preorder + inorder
class Solution {
    private int index;
    
    public TreeNode bstFromPreorder(int[] preorder) {
        index = 0;
        return recursion(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode recursion(int[] preorder, int lower, int upper) {
        if (index == preorder.length) {
            return null;
        }
        int val = preorder[index];
        if (val < lower || val > upper) {
            return null;
        }

        // valid range so create node
        TreeNode node = new TreeNode(val);
        index++;
        node.left = recursion(preorder, lower, val);
        node.right = recursion(preorder, val, upper);

        return node;
    }
}