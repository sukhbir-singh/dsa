import java.util.*;
// good question for practise
// O(n^2) solution wont work because n is gives as 10^5
// We need to find better way - Trie
// Even map cannot be used in this case, because boxing unboxing of int to Integer and HashMap fetching keys is taking time

// current solution: bitwise trie
// another solution - Bitwise Prefixes in Set
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

    // main logic
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

// Approach: Bitwise Prefixes in Set
// little difficult to understand - now it is making some sense
// in each iteration, we are saving all prefixes till ith bit in set
// trying to set 1 at each bit for answer (curXor), if possible then set maxXor to that number
class Solution2 {
  public int findMaximumXOR(int[] nums) {
    int maxNum = nums[0];
    for(int num : nums) maxNum = Math.max(maxNum, num);

    // length of max number in a binary representation
    int L = (Integer.toBinaryString(maxNum)).length();

    int maxXor = 0, currXor;
    Set<Integer> prefixes = new HashSet<>();
    for(int i = L - 1; i > -1; --i) {
      //Go to the next bit by the left shift
      maxXor <<= 1;

      // Set 1 in the smallest bit
      currXor = maxXor | 1;
      prefixes.clear();

      // compute all possible prefixes 
      // of length (L - i) in binary representation
      for(int num: nums) prefixes.add(num >> i);

      // Update maxXor, if two of these prefixes could result in currXor.
      // Check if p1^p2 == currXor, i.e. p1 == currXor^p2.
      // basically checking if p1^p2 == currXor is possible.
      for(int p: prefixes) {
        if (prefixes.contains(currXor^p)) {
          maxXor = currXor;
          break;
        }
      }
    }

    return maxXor;
  }
}