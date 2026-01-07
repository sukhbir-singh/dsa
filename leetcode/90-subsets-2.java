import java.util.*;
// better approach is using iteration over array approach. and skip elements same as previous index.
// the approach is that first try to make combination of total 0 elements, then try to pick first element, then next element and so on.
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);  // Important: This is preventing duplicate answers in result
        Set<List<Integer>> st = new HashSet<>();
        helper(nums, 0, new ArrayList<>(), st);
        return new ArrayList<>(st);
    }

    private void helper(int[] nums, int index, List<Integer> list, Set<List<Integer>> st) {
        if (index == nums.length) {
            st.add(new ArrayList<>(list));
            return;
        }

        // not pick
        helper(nums, index + 1, list, st);

        // pick
        list.add(nums[index]);
        helper(nums, index + 1, list, st);
        list.remove(list.size()-1);
    }
}