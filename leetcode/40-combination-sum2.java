import java.util.*;
// Good recursive implementation with loop approach: For picking each element, iterate over the loop.
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        helper(0, candidates, target, new ArrayList<>(), res);
        return res;
    }

    private void helper(int index, int[] candidates, int left, List<Integer> list, List<List<Integer>> res) {
        if (left < 0) {
            return;
        } else if (left == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i=index; i<candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i-1]) continue;
            if (candidates[i] > left) break;

            list.add(candidates[i]);
            helper(i + 1, candidates, left - candidates[i], list, res);
            // list.removeLast(); 
            list.remove(list.size() - 1);
        }
    }
}