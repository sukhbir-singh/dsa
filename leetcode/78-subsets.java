import java.util.*;
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        recursion(nums, 0, curr, list);
        return list;
    }

    private void recursion(int[] nums, int p, List<Integer> curr, List<List<Integer>> list) {
        if (p == nums.length) {
            list.add(curr);
            return;
        }

        // consider
        recursion(nums, p+1, new ArrayList<>(curr), list);

        // not consider
        List<Integer> l = new ArrayList<>(curr);
        l.add(nums[p]);
        recursion(nums, p+1, l, list);
    }
}