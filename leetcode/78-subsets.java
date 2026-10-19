import java.util.*;

// pick, not-pick
// another solution could be using binary numbers
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

        // not considered
        recursion(nums, p+1, new ArrayList<>(curr), list);

        // considered
        List<Integer> l = new ArrayList<>(curr);
        l.add(nums[p]);
        recursion(nums, p+1, l, list);
    }
}

// cascading
class Solution2 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        output.add(new ArrayList<>());

        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList<>();
            for (List<Integer> curr : output) {
                List<Integer> temp = new ArrayList<>(curr);
                temp.add(num);
                newSubsets.add(temp);
            }
            for (List<Integer> curr : newSubsets) {
                output.add(curr);
            }
        }
        return output;
    }
}