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
// If you know how to visit tree iteratively then you can easily solve this problem
class BSTIterator {
    private Stack<TreeNode> st;
    private TreeNode cur;

    public BSTIterator(TreeNode root) {
        st = new Stack<>();
        cur = root;
    }
    
    public int next() {
        // loop left left
        while (cur != null) {
            st.push(cur);
            cur = cur.left;
        }

        // pop and move right
        TreeNode c = st.pop();
        cur = c.right;
        return c.val;
    }
    
    public boolean hasNext() {
        return cur != null || st.size() > 0;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */