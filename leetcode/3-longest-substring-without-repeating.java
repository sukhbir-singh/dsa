import java.util.*;
// one good optimization is to use hashmap and then jump to next index where the character is not present
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, mx = 0; // sliding window
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

// better
class Solution2 {

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> chars = new HashMap<>();

        int left = 0;
        int right = 0;

        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            chars.put(r, chars.getOrDefault(r, 0) + 1);

            while (chars.get(r) > 1) {
                char l = s.charAt(left);
                chars.put(l, chars.get(l) - 1);
                left++;
            }

            res = Math.max(res, right - left + 1); // only counting valid windows
            right++;
        }
        return res;
    }
}

// jump to previous position
class Solution3 {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> charToNextIndex = new HashMap<>(); // index after current character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (charToNextIndex.containsKey(s.charAt(j))) {
                i = Math.max(charToNextIndex.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            charToNextIndex.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}