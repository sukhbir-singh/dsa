package algorithms.dp;

// To find the number of longest increasing subsequences (LIS), you cannot just track the length of the LIS; 
// you must also track how many ways you can form a subsequence of that maximum length.
public class NumberOfLIS {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int[] len = new int[n];
        int[] count = new int[n];
        
        // Base case initialization
        java.util.Arrays.fill(len, 1);
        java.util.Arrays.fill(count, 1);
        
        int maxLen = 1;
        
        // Build the DP tables
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (len[j] + 1 > len[i]) {
                        // Found a strictly longer subsequence
                        len[i] = len[j] + 1;
                        count[i] = count[j]; // Inherit count
                    } else if (len[j] + 1 == len[i]) {
                        // Found an alternate path of the exact same length
                        count[i] += count[j]; // Sum counts
                    }
                }
            }
            maxLen = Math.max(maxLen, len[i]);
        }
        
        // Sum up the counts of all subsequences that achieve the maximum length
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (len[i] == maxLen) {
                result += count[i];
            }
        }
        
        return result;
    }
}
