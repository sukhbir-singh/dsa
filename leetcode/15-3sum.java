import java.util.*;
// best method is sorting + 2 pointer -> for both 3sum and 4sum
// but its important to remember other methods as well for interviews. Because constraint can be no-sort.
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> st = new HashSet<>();

        for (int i=0; i<nums.length; i++) {
            int j = i+1;
            int k = nums.length-1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    Integer[] arr = {nums[i], nums[j], nums[k]};
                    List<Integer> list = new ArrayList<>();
                    Collections.addAll(list, arr);
                    st.add(list);
                    
                    j++;
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for (var list: st) {
            res.add(list);
        }

        return res;
    }
}