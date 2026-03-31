/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// standard question
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode s = null;
        TreeNode node = root;
        while (node != null) {
            if (node.val > p.val) {
                s = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return s;
    }
}