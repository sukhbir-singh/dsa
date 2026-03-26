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
// In this solution, I am moving entire subtree to left or right side.
// Other solution could be to replace deleted node with predessor or successor.
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            return deleteRootOfSubTree(root);
        }

        TreeNode temp = root;
        // Note: here we want to operate from one level above the node having key value. 
        // So that's why we are not equating the value of root.
        while (temp != null) {
            if (temp.val > key) {
                if (temp.left != null && temp.left.val == key) {
                    temp.left = deleteRootOfSubTree(temp.left);
                } else {
                    temp = temp.left;
                }
            } else {
                if (temp.right != null && temp.right.val == key) {
                    temp.right = deleteRootOfSubTree(temp.right);
                } else {
                    temp = temp.right;
                }
            }
        }

        return root;
    }

    private TreeNode deleteRootOfSubTree(TreeNode root) {
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }

        // otherwise both subtrees are present
        TreeNode rightChild = root.right;
        TreeNode lastRight = findLastRight(root.left);

        lastRight.right = rightChild;
        return root.left;
    }

    private TreeNode findLastRight(TreeNode node) {
        if (node == null) {
            return node;
        }
        while (node.right != null) node = node.right;
        return node;
    }
}