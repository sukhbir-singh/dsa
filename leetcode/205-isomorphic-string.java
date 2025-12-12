import java.util.*;

// good question - it is not as straight forward as it looks
// remember that it should be both way mapping. one way mapping is wrong solution.
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Character> mp1 = new HashMap<>();
        Map<Character, Character> mp2 = new HashMap<>();

        for (int i=0; i<s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            if (!mp1.containsKey(ch1)) {
                mp1.put(ch1, ch2);
            }
            if (!mp2.containsKey(ch2)) {
                mp2.put(ch2, ch1);
            }

            if ((ch1 != mp2.get(ch2)) || (ch2 != mp1.get(ch1))) {
                return false;
            }
        }
        return true;
    }
}