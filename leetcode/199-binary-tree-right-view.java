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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traverse(root, res, 0);
        return res;
    }

    // reverse preorder so that right side will be visited first
    private void traverse(TreeNode root, List<Integer> res, int level) {
        if (root == null) {
            return;
        }
        // smart thinking - for avoiding multiple updates for obtaining this value. otherwise my initial
        // thinking was to keep a map and update key-value for each node. It will be lots of duplicate updates.
        if (res.size() == level) {
            res.add(root.val);
        }
        traverse(root.right, res, level + 1);
        traverse(root.left, res, level + 1);
    }
}

class Solution2 {
    private class Pair {
        private TreeNode node;
        private int level;
        public Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
    
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        
        Map<Integer, Integer> map = new HashMap<>();

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        
        int maxlevel = 0;

        while(!queue.isEmpty()) {
            Pair p = queue.remove();
            TreeNode node = p.node;
            
            map.put(p.level, node.val);
            maxlevel = Math.max(maxlevel, p.level);
                
            if (node.left != null) {
                queue.add(new Pair(node.left, p.level+1));
            }
            if (node.right != null) {
                queue.add(new Pair(node.right, p.level+1));
            }
        }
        
        for (int i=0; i<=maxlevel; i++) {
            list.add(map.get(i));
        }

        return list;
    }
}