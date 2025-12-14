import java.util.*;
class Solution {
    public int beautySum(String s) {
        int ans = 0;
        int len = s.length();
        for (int i=0; i<len; i++) {
            // this same map is getting used again and again. 
            // reuse in next jth iteration.
            Map<Character, Integer> mp = new HashMap<>();

            for (int j=i; j<len; j++) {
                // substring from i to j
                char ch = s.charAt(j);
                mp.put(ch, mp.getOrDefault(ch, 0) + 1);

                // It is very important to set correct initial values
                int mnf = Integer.MAX_VALUE, mxf = Integer.MIN_VALUE;
                for (var e: mp.entrySet()) {
                    mnf = Math.min(e.getValue(), mnf);
                    mxf = Math.max(e.getValue(), mxf);
                }

                ans += mxf - mnf;
            }
        }
        return ans;
    }
}