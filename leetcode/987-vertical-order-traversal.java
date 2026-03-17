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
// Another way to solve this problem is to traverse and add all NodeInfo objects to global list. And then sort the list and prepare the final result array to return
class Solution {
    class NodeInfo implements Comparable<NodeInfo> {
        int value;
        int row;
        int col;

        public NodeInfo(int v, int r, int c) {
            this.value = v;
            this.row = r;
            this.col = c;
        }

        public String toString() {
            return "("+value + ", " + row + ", " + col+")";
        }

        public int compareTo(NodeInfo n1) {
            if (this.col != n1.col) {
                return this.col-n1.col;
            }
            if (this.row != n1.row) {
                return this.row-n1.row;
            }
            return this.value-n1.value;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // col->(row,val)
        Map<Integer, List<NodeInfo>> mp = new HashMap<>();
        traverse(root, 0, 0, mp);

        var vl = mp.values();
        // find min and max column value
        int mn = 0, mx = 0;
        for (var list : vl) {
            for (var n: list) {
                mn = Math.min(mn, n.col);
                mx = Math.max(mx, n.col);
            }
        }

        // iterate from min to max colum number and prepare final list
        List<List<Integer>> res = new ArrayList<>();
        for (int i=mn; i<=mx; i++) {
            List<NodeInfo> list = mp.get(i);
            Collections.sort(list); // for applying comparator to each values
            List<Integer> rs = new ArrayList<>();
            for (var n: list) {
                rs.add(n.value);
            }
            res.add(rs);
        }
        
        return res;
    }

    private void traverse(TreeNode node, int row, int col, Map<Integer, List<NodeInfo>> mp) {
        if (node == null) {
            return;
        }

        if (!mp.containsKey(col)) {
            mp.put(col, new ArrayList<>());
        }

        mp.get(col).add(new NodeInfo(node.val, row, col));

        traverse(node.left, row+1, col-1, mp);
        traverse(node.right, row+1, col+1, mp);
    }
}