/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Loved the simplicity of the implementation :)
// This is based on only 2 conditions:
// When left and right both return non-null -> it means that current node is LCA
// If either one of them is non-null -> it means that the returned node is LCA
// Oops, I think i have implemented it in generic way. I have not implemented it using BST property.
// Iterative is also having good implementation in this question
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode leftAns = lowestCommonAncestor(root.left, p, q);
        TreeNode rightAns = lowestCommonAncestor(root.right, p, q);

        if (leftAns != null && rightAns != null) {
            return root;
        } else if (leftAns != null) {
            return leftAns;
        }
        return rightAns;
    }
}