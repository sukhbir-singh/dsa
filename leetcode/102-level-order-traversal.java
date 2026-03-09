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
 // Instead of taking previous level, then matching with current Level and if level differs then adding to ans.
 // We could have taken a map having level number as keys. That could have simplified the solution.
class Solution1 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> l = new LinkedList<>();
        q.add(root);
        l.add(1);

        List<List<Integer>> ans = new ArrayList<>();
        int prevLevel = 1;
        List<Integer> list = new ArrayList<>();

        while (!q.isEmpty()) {
            TreeNode e = q.remove();
            int lv = l.remove();

            if (lv != prevLevel) {
                ans.add(list);
                list = new ArrayList<>();
            }

            list.add(e.val);

            if (e.left != null) {
                q.add(e.left);
                l.add(lv+1);
            }
            if (e.right != null) {
                q.add(e.right);
                l.add(lv+1);
            }

            prevLevel = lv;
        }
        ans.add(list);

        return ans;
    }
}

// Better Solution using Map
class Solution2 {
    private class Pair{
        public TreeNode node;
        public int level;
        public Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        Map<Integer, List<Integer>> map = new HashMap<>();

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while(!queue.isEmpty()) {
            Pair p = queue.remove();
            int level = p.level;
            TreeNode node = p.node;
            
            if (!map.containsKey(level)) {
                map.put(level, new ArrayList<>());
            }
            map.get(level).add(node.val);
            
            // add next level nodes
            if (node.left != null) {
                queue.add(new Pair(node.left, level + 1));
            }
            if (node.right != null) {
                queue.add(new Pair(node.right, level + 1));
            }
        }
        
        return new ArrayList<>(map.values());
    }
}
