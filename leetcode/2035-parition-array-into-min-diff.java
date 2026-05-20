import java.util.*;
// Meet in the middle algorithm
// This reduced complexity from O(2^2n) to O(n*2^n)
class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length / 2;
        int total = 0;
        for (int num : nums) total += num;

        // Generate subsets for left half (0..n-1) and right half (n..2n-1)
        // Map: size -> list of sums
        Map<Integer, List<Integer>> leftMap = new HashMap<>();
        Map<Integer, List<Integer>> rightMap = new HashMap<>();

        findSubsets(nums, 0, n, 0, 0, leftMap);
        findSubsets(nums, n, 2 * n, 0, 0, rightMap);

        // Ensure size 0 is present (empty subset)
        leftMap.putIfAbsent(0, new ArrayList<>(List.of(0)));
        rightMap.putIfAbsent(0, new ArrayList<>(List.of(0)));

        int minDiff = Integer.MAX_VALUE;

        // For each split: pick i elements from left, n-i from right
        for (int i = 0; i <= n; i++) {
            List<Integer> left = leftMap.getOrDefault(i, List.of());
            List<Integer> right = rightMap.getOrDefault(n - i, List.of());
            if (left.isEmpty() || right.isEmpty()) continue;

            Collections.sort(left);
            Collections.sort(right);

            // Two pointers to find sum closest to total/2
            int p1 = 0, p2 = right.size() - 1;
            while (p1 < left.size() && p2 >= 0) {
                int sum = left.get(p1) + right.get(p2);
                int remaining = total - sum;
                int diff = Math.abs(remaining - sum);
                minDiff = Math.min(minDiff, diff);
                if (sum > total / 2) p2--;
                else p1++;
            }
        }

        return minDiff;
    }

    // Recursively generate all subsets of nums[start..end-1], grouped by size
    private void findSubsets(int[] nums, int index, int end, int count, int sum, Map<Integer, List<Integer>> map) {
        if (index == end) {
            map.computeIfAbsent(count, k -> new ArrayList<>()).add(sum);
            return;
        }

        // Include nums[index]
        findSubsets(nums, index + 1, end, count + 1, sum + nums[index], map);
        // Exclude nums[index]
        findSubsets(nums, index + 1, end, count, sum, map);
    }
}

// This solution is also correct but resulting in time limit exceed on leetcode. 
// It should be acceptable in interview i think. But its good to know the meet in the middle algorithm for optimization.
class Solution2 {
    private record Pair(int index, int sum){}

    public int minimumDifference(int[] nums) {
        int total = 0;
        for (int num: nums) {
            total += num;
        }

        Set<Pair> st = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        findSubsets(nums, nums.length-1, 0, st, list);

        int minDiff = Integer.MAX_VALUE;
        for (Pair e: st){
            int subsetSum1 = e.sum();
            int subsetSum2 = total - subsetSum1;

            int diff = Math.abs(subsetSum1 - subsetSum2);
            minDiff = Math.min(minDiff, diff);
        }

        return minDiff;
    }

    private void findSubsets(int[] nums, int index, int sum, Set<Pair> st, List<Integer> list) {
        Pair p = new Pair(index, sum);
        if (st.contains(p)) {
            return;
        }

        // Note: this condition is very useful in multiple questions
        if (list.size() == nums.length/2) {
            st.add(p);
        }

        if (index < 0) {
            return;
        }
        
        list.add(nums[index]);
        findSubsets(nums, index-1, sum+nums[index], st, list);
        list.remove(list.size()-1);
        findSubsets(nums, index-1, sum, st, list);
    }
}