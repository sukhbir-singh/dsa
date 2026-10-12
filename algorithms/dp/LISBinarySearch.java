package algorithms.dp;

import java.util.ArrayList;
import java.util.Collections;

public class LISBinarySearch {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        // Stores the smallest tail of all increasing subsequences found so far
        ArrayList<Integer> tails = new ArrayList<>();
        
        for (int num : nums) {
            // Binary search to find the insertion point of 'num' in 'tails'
            int idx = Collections.binarySearch(tails, num);
            
            // If the element is not found, binarySearch returns: -(insertion_point) - 1
            if (idx < 0) {
                idx = -(idx + 1);
            }
            
            // If idx matches the size, 'num' extends the longest subsequence found so far
            if (idx == tails.size()) {
                tails.add(num);
            } else {
                // Otherwise, replace the existing element to create a smaller, better tail
                tails.set(idx, num);
            }
        }
        
        // The length of the tails list is the length of the LIS
        return tails.size();
    }
}

