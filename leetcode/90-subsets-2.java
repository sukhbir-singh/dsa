import java.util.*;
// current solution is backtracking recursive solution
// other solution could be using bitmasking
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

// third and better approach is using iteration over array approach and skip elements same as previous index.
// the approach is that first try to make combination of total 0 elements, then try to pick first element, then next element and so on.
class Solution2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> currentSubset = new ArrayList<>();

        subsetsWithDupHelper(subsets, currentSubset, nums, 0);
        return subsets;
    }

    private void subsetsWithDupHelper(List<List<Integer>> subsets, List<Integer> currentSubset, int[] nums, int index) {
        // Add the subset formed so far to the subsets list.
        subsets.add(new ArrayList<>(currentSubset));

        for (int i = index; i < nums.length; i++) {
            // If the current element is a duplicate, ignore.
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            currentSubset.add(nums[i]);
            subsetsWithDupHelper(subsets, currentSubset, nums, i + 1);
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}