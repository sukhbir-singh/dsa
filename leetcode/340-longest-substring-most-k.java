import java.util.*;

// set is not useful in this problem, because while moving left if we remove element from set - we might be wrong because of element frequency
// Later realization: count variable was not needed. actually the total number of keys present in map is equal to total unique keys present.

// More optimized algorithm: we dont even need inner while loop, because no need to trim window till it is valid. keep the window size and move 
// forward both left and right so see if any valid windows comes. otherwise existing found window is maximum answer.
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) {
            return 0;
        }

        Map<Character, Integer> mp = new HashMap<>(); // char -> count
        int maxLen = 1, left = 0, right = 0;
        int count = 0; // unique -> NOT NEEDED

        while (right < s.length()) {
            char ch = s.charAt(right);
            if (!mp.containsKey(ch)) {
                count++;
            }
            mp.put(ch, mp.getOrDefault(ch, 0) + 1);

            while (count > k) {
                char leftChar = s.charAt(left);
                int currentCount = mp.get(leftChar);
                if (currentCount == 1) {
                    count--;
                    mp.remove(leftChar);
                } else {
                    mp.put(leftChar, mp.getOrDefault(leftChar, 0) - 1);
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        
        return maxLen;
    }
}