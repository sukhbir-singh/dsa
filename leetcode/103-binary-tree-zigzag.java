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
// Feels like variation of level order traversal
// Instead of reversing at the end - each odd list. We can also use addLast and addFirst instead
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Queue<TreeNode> nodeQ = new LinkedList<>();
        Queue<Integer> level = new LinkedList<>();
        nodeQ.add(root);
        level.add(1);

        // Important to use map here - it makes overall algo easy
        Map<Integer, List<Integer>> mp = new HashMap<>();

        while(!nodeQ.isEmpty()) {
            TreeNode n = nodeQ.remove();
            int l = level.remove();

            if (!mp.containsKey(l)) {
                mp.put(l, new ArrayList<>());
            }

            mp.get(l).add(n.val);

            if (n.left != null) {
                nodeQ.add(n.left);
                level.add(l+1);
            }
            if (n.right != null) {
                nodeQ.add(n.right);
                level.add(l+1);
            }
        }

        var list = mp.values();
        List<List<Integer>> res = new ArrayList<>();
        for (var lt: list) {
            if (res.size()%2 == 1) {
                Collections.reverse(lt);
                res.add(lt);
            } else {
                res.add(lt);
            }
            
        }
        return res;
    }
}