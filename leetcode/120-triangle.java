import java.util.*;
class Solution {
    private record Pair(int r, int c){}

    public int minimumTotal(List<List<Integer>> triangle) {
        Map<Pair, Integer> dp = new HashMap<>();
        return recursion(triangle, 0, 0, dp);
    }

    private int recursion(List<List<Integer>> triangle, int r, int c, Map<Pair, Integer> dp) {
        Pair p = new Pair(r, c);

        // r cannot exceed the maximum value, c can exceed
        if (c >= triangle.get(r).size()) {
            return Integer.MAX_VALUE;
        }

        if (r == triangle.size() - 1) {
            int ans = triangle.get(r).get(c);
            dp.put(p, ans);
            return ans;
        }
        
        if (dp.containsKey(p)) {
            return dp.get(p);
        }

        int p1 = recursion(triangle, r+1, c, dp);
        int p2 = recursion(triangle, r+1, c+1, dp);
        int ans = Math.min(p1, p2) + triangle.get(r).get(c);

        dp.put(p, ans);
        return ans;
    }
}