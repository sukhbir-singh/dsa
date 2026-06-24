// Important: standard question
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        int n = nums.length;
        int[] len = new int[n];
        int[] count = new int[n];
        int mx = 1;

        for (int i=0; i<n; i++) {
            len[i] = 1;
            count[i] = 1;

            for (int j=0; j<i; j++) {
                if (nums[i] > nums[j] && len[j] + 1 > len[i])  {                   
                    len[i] = len[j] + 1;
                    count[i] = count[j];  // first time increase, count will become same as previous
                    mx = Math.max(mx, len[i]);

                } else if (nums[i] > nums[j] && len[j] + 1 == len[i]) {
                    count[i] += count[j];  // each time we meet same count, count will be added up
                }
            }
        }

        // System.out.println(Arrays.toString(len));
        // System.out.println(Arrays.toString(count));

        int ans = 0;
        for (int i=0; i<n; i++) {
            if (len[i] == mx) {
                ans += count[i];
            }
        }

        return ans;
    }
}