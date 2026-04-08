import java.util.*;
// Intuition is that if Trie contains only elements <= ai, then this will boil down to previous question 
// where we just have to fine maxXor on Trie
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
                // opposite bit is present in trie, it means 1 bit is possible for this XOR
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

record Element(int x, int m, int i){};

class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        
        int qn = queries.length;
        List<Element> list = new ArrayList<>();
        for (int i=0; i<qn; i++) {
            list.add(new Element(queries[i][0], queries[i][1], i));
        }

        Collections.sort(list, (e1, e2) -> e1.m() - e2.m());
        // System.out.println(list);

        int pointer = 0;
        Trie trie = new Trie();
        int[] result = new int[qn];

        // now solve by iterating over list
        for (Element e: list) {
            int m = e.m();
            int ans = -1;

            // creating trie only till m
            while (pointer < nums.length) {
                if (nums[pointer] <= m) {
                    trie.insert(nums[pointer]);
                    pointer++;
                } else {
                    break;
                }
            }
            
            result[e.i()] = pointer == 0 ? -1 : trie.getMaxXor(e.x());
        }

        return result;
    }
}