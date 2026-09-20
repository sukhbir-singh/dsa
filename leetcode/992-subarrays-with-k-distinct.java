import java.util.*;

// since it is difficult to find total subarrays with k different integers, lets break down the problem
// we will caculate total subarrays with <= k different integers and <= k-1 different integers and subtract them for answer
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return subarraysWithLessThanEqualToKDistinct(nums, k) - subarraysWithLessThanEqualToKDistinct(nums, k-1);
    }

    private int subarraysWithLessThanEqualToKDistinct(int[] nums, int k) {
        int total = 0, left = 0;
        Map<Integer, Integer> mp = new HashMap<>(); // int -> freq

        for (int right=0; right<nums.length; right++) {
            mp.put(nums[right], mp.getOrDefault(nums[right], 0) + 1);
            
            while (mp.size() > k) {
                mp.put(nums[left], mp.getOrDefault(nums[left], 0) - 1);
                if (mp.get(nums[left]) == 0) {
                    mp.remove(nums[left]);
                }
                left++;
            }

            total += (right-left)+1; // important: add all subarrays
        }

        return total;
    }
}