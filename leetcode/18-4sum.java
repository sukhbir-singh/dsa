import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> st = new HashSet<>();

        for (int i=0; i<nums.length; i++) {
            for (int j=i+1; j<nums.length; j++) {
                int k = j+1;
                int l = nums.length-1;

                while (k < l) {
                    long sum = (long)nums[i] + (long)nums[j] + (long)nums[k] + (long)nums[l];
                    //System.out.println("sum of (" + nums[i] +", "+nums[j] +","+nums[k] +","+nums[l]+") is " + sum);
                    if (sum == target) {
                        Integer[] arr = {nums[i], nums[j], nums[k], nums[l]};
                        List<Integer> list = new ArrayList<>();
                        Collections.addAll(list, arr);
                        Collections.sort(list);
                        st.add(list);
                        
                        k++;
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
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