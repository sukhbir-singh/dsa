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
    private int maxSum;

    private class TreeInfo {
        public boolean isBst; // this can be skipped
        public int sum;
        public int min;
        public int max;

        public TreeInfo(boolean isBst, int sum) {
            this.isBst = isBst;
            this.sum = sum;
            this.min = Integer.MAX_VALUE;
            this.max = Integer.MIN_VALUE;
        }

        public String toString() {
            return "(" + isBst + ", " + sum + ", " + min + ", " + max + ")";
        }
    }

    public int maxSumBST(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        inorder(root);
        return Math.max(0, maxSum); // give non-negative answer
    }

    private TreeInfo inorder(TreeNode root) {
        if (root == null) {
            return new TreeInfo(true, 0);
        }

        TreeInfo left = inorder(root.left);
        TreeInfo right = inorder(root.right);

        boolean isBst = left.isBst && right.isBst;
        if (root.left != null && root.val <= left.max) {
            isBst = false;
        }
        if (root.right != null && root.val >= right.min) {
            isBst = false;
        }

        if (isBst) {
            int curSum = left.sum + right.sum + root.val;
            maxSum = Math.max(maxSum, curSum);
            TreeInfo res = new TreeInfo(true, curSum);
            res.min = Math.min(left.min, root.val);
            res.max = Math.max(right.max, root.val);

            // System.out.println("for " + root.val + " => " + res);
            return res;
        }

        // System.out.println("for " + root.val + " => " + new TreeInfo(false, 0));
        return new TreeInfo(false, 0);
    }
}