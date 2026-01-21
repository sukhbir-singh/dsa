import java.util.*;
// one good optimization is to use hashmap and then jump to next index where the character is not present
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, mx = 0;
        while (right < s.length() && left <= right) {
            char next = s.charAt(right);
            if (!set.contains(next)) {
                set.add(next);
                mx = Math.max(mx, right-left+1);
                right++;
                continue;
            }

            set.remove(s.charAt(left));
            left++;
            mx = Math.max(mx, right-left+1);
        }

        return mx;
    }
}
