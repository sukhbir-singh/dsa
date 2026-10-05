package algorithms.tree;

public class FindPredessor {

    // Inorder predecessor: the node with the largest value strictly less than key.
    // key does not need to exist in the tree. Returns null if there is none.
    // Time: O(h), Space: O(1)
    static TreeNode predecessor(TreeNode root, int key) {
        TreeNode predecessor = null;
        TreeNode current = root;

        while (current != null) {
            if (current.val < key) {
                // Candidate; a closer one may exist in the right subtree
                predecessor = current;
                current = current.right;
            } else {
                current = current.left;
            }
        }

        return predecessor;
    }

    public static void main(String[] args) {
        TreeNode root = null;
        for (int val : new int[] {20, 8, 22, 4, 12, 10, 14}) {
            root = TreeNode.insert(root, val);
        }

        for (int key : new int[] {4, 8, 10, 14, 20, 22, 11}) {
            TreeNode result = predecessor(root, key);
            System.out.println("Predecessor of " + key + ": " + (result == null ? "none" : result.val));
        }
    }
}
