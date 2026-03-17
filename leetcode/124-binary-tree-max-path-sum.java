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
    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        findMaxSum(root);
        return maxSum;
    }

    private int findMaxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftPathMax = findMaxSum(root.left);
        int rightPathMax = findMaxSum(root.right);

        // setting as zero if any of the path is negative
        leftPathMax = Math.max(0, leftPathMax);
        rightPathMax = Math.max(0, rightPathMax);

        // check maximum path sum passing through this node
        maxSum = Math.max(leftPathMax + rightPathMax + root.val, maxSum);

        return Math.max(leftPathMax, rightPathMax) + root.val;
    }
}

// This is little overdone. TreeInfo is not needed, no need to keep maxSum at each node. Just keep a global thing and update it.
class Solution2 {
    private class TreeInfo {
        private int maxsum;
        private int pathsum;
        public TreeInfo(int maxsum, int pathsum) {
            this.maxsum = maxsum;
            this.pathsum = pathsum;
        }
        
        public String toString() {
            return "( maxsum = " + maxsum + ", pathsum = " + pathsum + ")";
        }
    }

    public int maxPathSum(TreeNode root) {
        return recursion(root).maxsum;
    }
    
    public TreeInfo recursion(TreeNode root) {
        if (root == null) {
            return new TreeInfo(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
        
        TreeInfo left = recursion(root.left);
        TreeInfo right = recursion(root.right);
        
        int pathsum = Math.max(0, Math.max(left.pathsum, right.pathsum)) + root.val;  
        int total = Math.max(0, left.pathsum) +  Math.max(0, right.pathsum) + root.val;
        int maxsum = Math.max(Math.max(Math.max(left.maxsum, right.maxsum), pathsum), total);
        
        TreeInfo curr = new TreeInfo(maxsum, pathsum);
        // System.out.println("root.val = " + root.val + " => " + curr);
        return curr;
    }
}
