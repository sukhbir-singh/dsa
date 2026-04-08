// good question for practise
// O(n^2) solution wont work because n is gives as 10^5
// We need to find better way - trie
// Even map cannot be used in this case, because boxing unboxing of int to Integer and HashMap fetching keys is taking time
class Node {
    public Node[] children;
    public Node() {
        children = new Node[2];
    }
    public boolean containsKey(int n) {
        return children[n] != null;
    }
    public Node get(int n) {
        return children[n];
    }
    public void put(int n, Node node) {
        children[n] = node;
    }
}

class Trie {
    private Node root;
    public Trie() {
        root = new Node();
    }

    public void insert(int num) {
        Node cur = root;
        for (int i=31; i>=0; i--) {
            int bit = num>>i & 1;
            if (!cur.containsKey(bit)) {
                cur.put(bit, new Node());
            }
            cur = cur.get(bit);
        }
    }

    public int getMaxXor(int num) {
        int res = 0;
        Node cur = root;
        for (int i=31; i>=0; i--) {
            int bit = num>>i & 1;
            if (cur.containsKey(1-bit)) {
                cur = cur.get(1-bit);
                // set bit in result because 1-bit is present
                res = res | 1<<i; 
            } else {
                cur = cur.get(bit);
                // no need to update res because ith bit is already unset i.e. 0
            }
        }
        return res;
    }
}

class Solution {
    public int findMaximumXOR(int[] nums) {
        Trie t = new Trie();
        for (int n: nums) {
            t.insert(n);
        }

        int res = 0;
        for (int n: nums) {
            res = Math.max(res, t.getMaxXor(n));
        }
        return res;
    }
}