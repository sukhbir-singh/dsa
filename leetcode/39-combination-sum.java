import java.util.*;
// for this question: refer striver's solution. that's is better and simpler.
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        helper(candidates, target, 0, 0, new ArrayList<>(), res);
        return res;
    }

    private void helper(int[] input, int target, int index, int sum, List<Integer> list, List<List<Integer>> res) {
        //System.out.println(">> index: "+index+", sum:"+sum+", list:"+list);
        if (sum == target) {
            if (!res.contains(list)) {
                res.add(list);
            }
            //System.out.println("added :" + list);
            return;
        } else if (sum > target || index == input.length) {
            return;
        }

        // not take
        helper(input, target, index+1, sum, new ArrayList<>(list), res);

        // take and stay
        List<Integer> temp = new ArrayList<>(list);   // Note: instead of this cloning, better to add and remove integer to this list.
        temp.add(input[index]);
        helper(input, target, index, sum + input[index], temp, res);

        // take and move forward
        temp = new ArrayList<>(list);
        temp.add(input[index]);
        helper(input, target, index+1, sum + input[index], temp, res);
    }
}