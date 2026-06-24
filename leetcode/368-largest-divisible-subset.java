import java.util.*;
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>() {};
        } else if (nums.length == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[0]);
            return list;
        }

        int n = nums.length;
        int[] lens = new int[n];
        int[] parent = new int[n];
        for (int i=0; i<n; i++) {
            lens[i] = 1;
            parent[i] = i;
        }

        Arrays.sort(nums);

        int mx = 1, mxIndex = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<i; j++) {

                // Intuition: For any value that can be divided by the largest element in the divisible subset, 
                // by adding the new value into the subset, one can form another divisible subset, 
                // i.e. for all h, if h % G == 0, then [E, F, G, h] forms a new divisible subset.
                // That's why LIS algorithm is adaptible here

                if (nums[i] % nums[j] == 0) {
                    if (lens[j] + 1 > lens[i]) {
                        lens[i] = lens[j] + 1;
                        parent[i] = j;

                        if (lens[i] > mx) {
                            mx = lens[i];
                            mxIndex = i;
                        }
                    }
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        int ind = mxIndex, cnt = 0;
        while (cnt < mx) {
            list.add(nums[ind]);
            ind = parent[ind];
            cnt++;
        }

        Collections.reverse(list);
        return list;
    }
}