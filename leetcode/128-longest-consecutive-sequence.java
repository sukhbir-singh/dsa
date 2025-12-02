import java.util.*;

class Solution {
    // we will not start looking from this current element if it is not start of the sequence.
    // that is, if i-1 is present.
    public int longestConsecutive(int[] nums) {
        Set<Integer> st = new HashSet<>();
        for (int n: nums) {
            st.add(n);
        }

        if (st.size() == 0){
            return 0;
        }

        int mx = 1;
        for (int n: st) {
            if (!st.contains(n-1)) {
                // it means that n is starting point of the sequence
                int len = 1;
                while (st.contains(n+1)) {
                    len++;
                    n++;
                }

                mx = Math.max(len, mx);
            }
        }

        return mx;
    }
}