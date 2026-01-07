import java.util.*;
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(1, k, n, 0, new ArrayList<>(), res);
        return res;
    }

    private void helper(int index, int k, int n, int sum, List<Integer> list, List<List<Integer>> res) {
        if (list.size() == k) {
            if (sum == n) {
                res.add(new ArrayList<>(list));
            }
            return;
        }

        for (int i=index; i<=9; i++) {
            list.add(i);
            helper(i + 1, k, n, sum + i, list, res);
            list.remove(list.size() - 1);
        }
    }
}