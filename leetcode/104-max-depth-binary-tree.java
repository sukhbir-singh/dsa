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
    public int maxDepth(TreeNode root) {
        return calc(root, 0);
    }

    private int calc(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        int dep1 = calc(root.left, depth+1);
        int dep2 = calc(root.right, depth+1);

        int mx = Math.max(dep1, dep2);
        return mx;
    }
}

// This is better solution and better for explanation. Here null checks are redundants.
class Solution2 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxDepth = 1;
        if (root.left != null) {
            maxDepth = Math.max(maxDepth, maxDepth(root.left) + 1);
        }
        if (root.right != null) {
            maxDepth = Math.max(maxDepth, maxDepth(root.right) + 1);
        }
        return maxDepth;
    }
}

// You can simply write in 2 lines.
class Solution3 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
