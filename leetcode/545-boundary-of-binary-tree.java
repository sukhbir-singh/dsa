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
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        // important condition for skipping duplicate entries
        if (!isLeaf(root)) {
            res.add(root.val);
        }

        addLeftBoundary(root.left, res);
        addLeafBoundary(root, res);
        addRightBoundary(root.right, res);
        return res;
    }

    private boolean isLeaf(TreeNode root) {
        if (root == null) return false;
        return root.left == null && root.right == null;
    }

    private void addLeftBoundary(TreeNode root, List<Integer> list) {
        TreeNode cur = root;
        while (cur != null) {
            if (!isLeaf(cur)) {
                list.add(cur.val);
            } else {
                break;
            }
            
            if (cur.left != null) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
    }

    private void addLeafBoundary(TreeNode root, List<Integer> list) {
        if (root == null) return;
        if (isLeaf(root)) {
            list.add(root.val);
            return;
        }
        addLeafBoundary(root.left, list);
        addLeafBoundary(root.right, list);
    }

    private void addRightBoundary(TreeNode root, List<Integer> list) {
        TreeNode cur = root;
        List<Integer> temp = new ArrayList<>();
        while (cur != null) {
            if (!isLeaf(cur)) {
                temp.add(cur.val);
            } else {
                break;
            }

            if (cur.right != null) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        Collections.reverse(temp);
        list.addAll(temp);
    }
}