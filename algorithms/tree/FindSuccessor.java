package algorithms.tree;

public class FindSuccessor {

    // Inorder successor: the node with the smallest value strictly greater than key.
    // key does not need to exist in the tree. Returns null if there is none.
    // Time: O(h), Space: O(1)
    static TreeNode successor(TreeNode root, int key) {
        TreeNode successor = null;
        TreeNode current = root;

        while (current != null) {
            if (current.val > key) {
                // Candidate; a closer one may exist in the left subtree
                successor = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return successor;
    }

    public static void main(String[] args) {
        TreeNode root = null;
        for (int val : new int[] {20, 8, 22, 4, 12, 10, 14}) {
            root = TreeNode.insert(root, val);
        }

        for (int key : new int[] {4, 8, 10, 14, 20, 22, 11}) {
            TreeNode result = successor(root, key);
            System.out.println("Successor of " + key + ": " + (result == null ? "none" : result.val));
        }
    }
}
